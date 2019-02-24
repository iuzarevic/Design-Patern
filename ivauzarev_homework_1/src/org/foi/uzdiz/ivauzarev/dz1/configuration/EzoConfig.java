/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.ivauzarev.dz1.KonkretniSpremnik;
import org.foi.uzdiz.ivauzarev.dz1.Korisnik;
import org.foi.uzdiz.ivauzarev.dz1.Spremnik;
import org.foi.uzdiz.ivauzarev.dz1.Ulica;
import org.foi.uzdiz.ivauzarev.dz1.loaders.FileLoader;
import org.foi.uzdiz.ivauzarev.dz1.loaders.SpremniciFileAdapter;
import org.foi.uzdiz.ivauzarev.dz1.loaders.SpremniciFileLoader;
import org.foi.uzdiz.ivauzarev.dz1.loaders.UliceFileAdapter;
import org.foi.uzdiz.ivauzarev.dz1.loaders.UliceFileLoader;

/**
 *
 * @author Ivan
 */
public class EzoConfig {

    String ulice;
    String spremnici;
    String vozila;
    String izlaz;
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
    int maliMješano;
    int srednjiStaklo;
    int srednjiPapir;
    int srednjiMetal;
    int srednjiBio;
    int srednjiMješano;
    int velikiStaklo;
    int velikiPapir;
    int velikiMetal;
    int velikiBio;
    int velikiMješano;

    Map<String, String> paramMap = new HashMap<String, String>();
    List<Ulica> listaUlica = new ArrayList<>();
    List<Spremnik> listaSpremnika = new ArrayList<>();
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
        try {
            Scanner scanner = new Scanner(new FileReader(parFile));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String splitted[] = line.split(":");
                paramMap.put(splitted[0], splitted[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EzoConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Arrays.asList(paramMap)); //pomocni ispis sadrzaja ulica
        assignParam();
        FileLoader fl = new SpremniciFileAdapter(new SpremniciFileLoader());
        fl.dataModifying();
        fl = new UliceFileAdapter(new UliceFileLoader());
        fl.dataModifying();
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
            u.ukupnoOtpada();
        }
        for (Ulica u : listaUlica) {
            System.out.println("-----------------------------------");
            System.out.println("Ulica: " + u.getNaziv());
            u.ukupnoOtpada();
        }
    }

    private void assignParam() {
        ulice = paramMap.get("ulice");
        /*
        if (paramMap.containsKey("ulice")) {
            System.out.println(ulice);
        }*/
        spremnici = paramMap.get("spremnici");
        //System.out.println(spremnici);
        vozila = paramMap.get("vozila");
        //System.out.println(vozila);
        izlaz = paramMap.get("izlaz");
        //System.out.println(izlaz);
        ispis = Integer.parseInt(paramMap.get("ispis"));
        sjemeGeneratora = Integer.parseInt(paramMap.get("sjemeGeneratora"));
        brojDecimala = Integer.parseInt(paramMap.get("brojDecimala"));
        brojRadnihCiklusaZaOdvoz = Integer.parseInt(paramMap.get("brojRadnihCiklusaZaOdvoz"));
        preuzimanje = Integer.parseInt(paramMap.get("preuzimanje"));
        maliMin = Integer.parseInt(paramMap.get("maliMin"));
        srednjiMin = Integer.parseInt(paramMap.get("srednjiMin"));
        velikiMin = Integer.parseInt(paramMap.get("velikiMin"));
        maliBio = Integer.parseInt(paramMap.get("maliBio"));
        maliMetal = Integer.parseInt(paramMap.get("maliMetal"));
        maliMješano = Integer.parseInt(paramMap.get("maliMješano"));
        maliPapir = Integer.parseInt(paramMap.get("maliPapir"));
        maliStaklo = Integer.parseInt(paramMap.get("maliStaklo"));
        srednjiBio = Integer.parseInt(paramMap.get("srednjiBio"));
        srednjiMetal = Integer.parseInt(paramMap.get("srednjiMetal"));
        srednjiMješano = Integer.parseInt(paramMap.get("srednjiMješano"));
        srednjiPapir = Integer.parseInt(paramMap.get("srednjiPapir"));
        srednjiStaklo = Integer.parseInt(paramMap.get("srednjiStaklo"));
        velikiBio = Integer.parseInt(paramMap.get("velikiBio"));
        velikiMetal = Integer.parseInt(paramMap.get("velikiMetal"));
        velikiMješano = Integer.parseInt(paramMap.get("velikiMješano"));
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

    public int getMaliMješano() {
        return maliMješano;
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

    public int getSrednjiMješano() {
        return srednjiMješano;
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

    public int getVelikiMješano() {
        return velikiMješano;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public List<Ulica> getListaUlica() {
        return listaUlica;
    }

    public List<Spremnik> getListaSpremnika() {
        return listaSpremnika;
    }

}
