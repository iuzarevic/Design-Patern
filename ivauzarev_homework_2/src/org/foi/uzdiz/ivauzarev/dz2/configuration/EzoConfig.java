/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.foi.uzdiz.ivauzarev.dz2.Dispatcher;
import org.foi.uzdiz.ivauzarev.dz2.Korisnik;
import org.foi.uzdiz.ivauzarev.dz2.Spremnik;
import org.foi.uzdiz.ivauzarev.dz2.Ulica;
import org.foi.uzdiz.ivauzarev.dz2.Vozilo;
import org.foi.uzdiz.ivauzarev.dz2.composite.PodrucjeComponent;
import org.foi.uzdiz.ivauzarev.dz2.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz2.iterator.VozniParkRepository;
import org.foi.uzdiz.ivauzarev.dz2.loaders.ConfigService;
import org.foi.uzdiz.ivauzarev.dz2.loggers.PodrucjaLogger;

/**
 *
 * @author Ivan
 */
public class EzoConfig {

    String ulice;
    String spremnici;
    String vozila;
    String izlaz;
    String podrucja;
    String dispatcher;
    int ispis;
    int sjemeGeneratora;
    int brojDecimala;
    int brojRadnihCiklusaZaOdvoz;
    int preuzimanje;
    int maliMin;
    int srednjiMin;
    int velikiMin;
    int maliStaklo;
    int maliPapir;
    int maliMetal;
    int maliBio;
    int maliMjesano;
    int srednjiStaklo;
    int srednjiPapir;
    int srednjiMetal;
    int srednjiBio;
    int srednjiMjesano;
    int velikiStaklo;
    int velikiPapir;
    int velikiMetal;
    int velikiBio;
    int velikiMjesano;

    Map<String, String> paramMap = new HashMap<String, String>();
    List<Ulica> listaUlica = new ArrayList<>();
    List<Spremnik> listaSpremnika = new ArrayList<>();
    List<Vozilo> listaVozila = new ArrayList<>();
    PodrucjeComponent glavnoPodrucje;
    Dispatcher dispatcherControl;
    private static volatile EzoConfig instance = null;

    private EzoConfig() {

    }

    /**
     * getInstance metoda singleton klase
     *
     * @return
     */
    public static EzoConfig getInstance() {
        if (instance == null) {
            synchronized (EzoConfig.class) {
                if (instance == null) {
                    instance = new EzoConfig();
                }
            }
        }

        return instance;
    }

    /**
     * Učitavanje podataka na temelju datoteke parametara
     *
     * @param parFile
     */
    public void loadConfigData(String parFile) {
        ConfigService.getMainConfig(parFile, paramMap);
        assignParam();
        //FileLoader fl = new SpremniciFileAdapter(new SpremniciFileLoader());
        //fl.dataModifying();
        //fl = new UliceFileAdapter(new UliceFileLoader());
        //fl.dataModifying();
        ConfigService.getConfig();
    }

    public void initSystem() {
        for (Ulica u : listaUlica) {
            System.out.println("-----------------------------------");
            System.out.println("Ulica: " + u.getNaziv());
            for (Korisnik k : u.getListaKorisnika()) {
                System.out.println("\nKorisnik: " + k.getId());
                System.out.println("Tip korisnika: " + k.getGrupaKorisnika());
                k.generirajOtpad();
            }
            //u.ukupnoOtpada();
        }
        PodrucjaLogger.print(glavnoPodrucje.getPodrucjeComponents());
    }

    public void simulation() {
        VozniParkRepository vpr = new VozniParkRepository(listaVozila,listaUlica);
        Dispatcher dp = Dispatcher.getInstance();
        dp.addVozniPark(vpr);
        dp.pokreniVozniPark();
        //GenerateNumber gn = GenerateNumber.getInstance();
        //gn.shuffleArray(listaUlica);
        System.out.println("tu sam");
    }

    private void assignParam() {
        podrucja = paramMap.get("područja");
        ulice = paramMap.get("ulice");
        spremnici = paramMap.get("spremnici");
        vozila = paramMap.get("vozila");
        izlaz = paramMap.get("izlaz");
        dispatcher = paramMap.get("dispečer");
        ispis = Integer.parseInt(paramMap.get("ispis"));
        sjemeGeneratora = Integer.parseInt(paramMap.get("sjemeGeneratora"));
        brojDecimala = Integer.parseInt(paramMap.get("brojDecimala"));
        brojRadnihCiklusaZaOdvoz = Integer.parseInt(paramMap.get("brojRadnihCiklusaZaOdvoz"));
        preuzimanje = Integer.parseInt(paramMap.get("preuzimanje"));
        maliMin = Integer.parseInt(paramMap.get("maliMin"));
        srednjiMin = Integer.parseInt(paramMap.get("srednjiMin"));
        velikiMin = Integer.parseInt(paramMap.get("velikiMin"));
        assignParam2();
    }

    private void assignParam2() {
        maliBio = Integer.parseInt(paramMap.get("maliBio"));
        maliMetal = Integer.parseInt(paramMap.get("maliMetal"));
        maliMjesano = Integer.parseInt(paramMap.get("maliMješano"));
        maliPapir = Integer.parseInt(paramMap.get("maliPapir"));
        maliStaklo = Integer.parseInt(paramMap.get("maliStaklo"));
        srednjiBio = Integer.parseInt(paramMap.get("srednjiBio"));
        srednjiMetal = Integer.parseInt(paramMap.get("srednjiMetal"));
        srednjiMjesano = Integer.parseInt(paramMap.get("srednjiMješano"));
        srednjiPapir = Integer.parseInt(paramMap.get("srednjiPapir"));
        srednjiStaklo = Integer.parseInt(paramMap.get("srednjiStaklo"));
        velikiBio = Integer.parseInt(paramMap.get("velikiBio"));
        velikiMetal = Integer.parseInt(paramMap.get("velikiMetal"));
        velikiMjesano = Integer.parseInt(paramMap.get("velikiMješano"));
        velikiPapir = Integer.parseInt(paramMap.get("velikiPapir"));
        velikiStaklo = Integer.parseInt(paramMap.get("velikiStaklo"));
    }

    public String getUlice() {
        return ulice;
    }

    public String getSpremnici() {
        return spremnici;
    }

    public String getVozila() {
        return vozila;
    }

    public String getIzlaz() {
        return izlaz;
    }

    public int getIspis() {
        return ispis;
    }

    public int getSjemeGeneratora() {
        return sjemeGeneratora;
    }

    public int getBrojDecimala() {
        return brojDecimala;
    }

    public int getBrojRadnihCiklusaZaOdvoz() {
        return brojRadnihCiklusaZaOdvoz;
    }

    public int getPreuzimanje() {
        return preuzimanje;
    }

    public int getMaliMin() {
        return maliMin;
    }

    public int getSrednjiMin() {
        return srednjiMin;
    }

    public int getVelikiMin() {
        return velikiMin;
    }

    public int getMaliStaklo() {
        return maliStaklo;
    }

    public int getMaliPapir() {
        return maliPapir;
    }

    public int getMaliMetal() {
        return maliMetal;
    }

    public int getMaliBio() {
        return maliBio;
    }

    public int getMaliMjesano() {
        return maliMjesano;
    }

    public int getSrednjiStaklo() {
        return srednjiStaklo;
    }

    public int getSrednjiPapir() {
        return srednjiPapir;
    }

    public int getSrednjiMetal() {
        return srednjiMetal;
    }

    public int getSrednjiBio() {
        return srednjiBio;
    }

    public int getSrednjiMjesano() {
        return srednjiMjesano;
    }

    public int getVelikiStaklo() {
        return velikiStaklo;
    }

    public int getVelikiPapir() {
        return velikiPapir;
    }

    public int getVelikiMetal() {
        return velikiMetal;
    }

    public int getVelikiBio() {
        return velikiBio;
    }

    public int getVelikiMjesano() {
        return velikiMjesano;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public List<Ulica> getListaUlica() {
        return listaUlica;
    }

    public PodrucjeComponent getParticularUlica(String id) {
        for (PodrucjeComponent u : listaUlica) {
            if (id.equals(u.getId())) {
                return u;
            }
        }
        return null;
    }

    public List<Spremnik> getListaSpremnika() {
        return listaSpremnika;
    }

    public List<Vozilo> getListaVozila() {
        return listaVozila;
    }

    public PodrucjeComponent getGlavnoPodrucje() {
        return glavnoPodrucje;
    }

    public void setGlavnoPodrucje(PodrucjeComponent glavnoPodrucje) {
        this.glavnoPodrucje = glavnoPodrucje;
    }

    public String getPodrucja() {
        return podrucja;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public Dispatcher getDispatcherControl() {
        return dispatcherControl;
    }

}
