/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz3.helpers.UserTypes;
import org.foi.uzdiz.ivauzarev.dz3.loggers.KorisnikLogger;

/**
 *
 * @author Ivan
 */
public class Korisnik {

    private static int counter = 0;
    private int id = 0;
    private UserTypes grupaKorisnika;
    int min, staklo, bio, mjesano, papir, metal, gornji;

    List<KonkretniSpremnik> korisnickiSpremnici;

    public Korisnik(UserTypes grupaKorisnika) {
        id = ++counter;
        this.grupaKorisnika = grupaKorisnika;
        korisnickiSpremnici = new ArrayList<>();
        EzoConfig conf = EzoConfig.getInstance();
        if (grupaKorisnika.equals(UserTypes.MALI)) {
            min = conf.getMaliMin();
            staklo = conf.getMaliStaklo();
            bio = conf.getMaliBio();
            mjesano = conf.getMaliMjesano();
            papir = conf.getMaliPapir();
            metal = conf.getMaliMetal();
        } else if (grupaKorisnika.equals(UserTypes.SREDNJI)) {
            min = conf.getSrednjiMin();
            staklo = conf.getSrednjiStaklo();
            bio = conf.getSrednjiBio();
            mjesano = conf.getSrednjiMjesano();
            papir = conf.getSrednjiPapir();
            metal = conf.getSrednjiMetal();
        } else {
            min = conf.getVelikiMin();
            staklo = conf.getVelikiStaklo();
            bio = conf.getVelikiBio();
            mjesano = conf.getVelikiMjesano();
            papir = conf.getVelikiPapir();
            metal = conf.getVelikiMetal();
        }
    }

    public void generirajOtpad() {
        GenerateNumber generator = GenerateNumber.getInstance();
        float donji;
        for (KonkretniSpremnik k : korisnickiSpremnici) {
            if (k.getNaziv().equals("staklo")) {
                donji = (float) min / 100 * (float) staklo;
                gornji = staklo;
            } else if (k.getNaziv().equals("bio")) {
                donji = (float) min / 100 * (float) bio;
                gornji = bio;
            } else if (k.getNaziv().equals("mješano")) {
                donji = (float) min / 100 * (float) mjesano;
                gornji = mjesano;
            } else if (k.getNaziv().equals("papir")) {
                donji = (float) min / 100 * (float) papir;
                gornji = papir;
            } else {
                donji = (float) min / 100 * (float) metal;
                gornji = metal;
            }
            float generiraniOtpad = generator.getFloatNumber(donji, (float) gornji);
            System.out.println("-->Generirano " + k.getNaziv() + ": " + generiraniOtpad);
            if (generiraniOtpad <= (k.getNosivost() - k.getNapunjenost())) {
                k.setNapunjenost(generiraniOtpad);
            } else {
                System.out.println("--->Korisnik nije odložio otpad");
            }
        }
        KorisnikLogger.printSpremnika(korisnickiSpremnici);
    }

    public UserTypes getGrupaKorisnika() {
        return grupaKorisnika;
    }

    public void setGrupaKorisnika(UserTypes grupaKorisnika) {
        this.grupaKorisnika = grupaKorisnika;
    }

    public List<KonkretniSpremnik> getKorisnickiSpremnici() {
        return korisnickiSpremnici;
    }

    public void setKorisnickiSpremnici(List<KonkretniSpremnik> korisnickiSpremnici) {
        this.korisnickiSpremnici = korisnickiSpremnici;
    }

    public void addSpremnik(KonkretniSpremnik s) {
        this.korisnickiSpremnici.add(s);
    }

    public int getId() {
        return id;
    }
}
