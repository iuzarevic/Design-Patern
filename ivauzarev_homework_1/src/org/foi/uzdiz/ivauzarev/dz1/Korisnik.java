/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz1.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz1.helpers.UserTypes;

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
        //System.out.println("id: " + id); //sluzi za provjeru ispravnosti id-a
        EzoConfig conf = EzoConfig.getInstance();
        if (grupaKorisnika.equals(UserTypes.MALI)) {
            min = conf.getMaliMin();
            staklo = conf.getMaliStaklo();
            bio = conf.getMaliBio();
            mjesano = conf.getMaliMješano();
            papir = conf.getMaliPapir();
            metal = conf.getMaliMetal();
        } else if (grupaKorisnika.equals(UserTypes.SREDNJI)) {
            min = conf.getSrednjiMin();
            staklo = conf.getSrednjiStaklo();
            bio = conf.getSrednjiBio();
            mjesano = conf.getSrednjiMješano();
            papir = conf.getSrednjiPapir();
            metal = conf.getSrednjiMetal();
        } else {
            min = conf.getVelikiMin();
            staklo = conf.getVelikiStaklo();
            bio = conf.getVelikiBio();
            mjesano = conf.getVelikiMješano();
            papir = conf.getVelikiPapir();
            metal = conf.getVelikiMetal();
        }
    }

    public void generirajOtpad() {
        GenerateNumber generator = GenerateNumber.getInstance();
        float donji;
        for (KonkretniSpremnik k : korisnickiSpremnici) {
            if (k.getNaziv().equals("staklo")) {
                donji = (float)min / 100 * (float)staklo;
                System.out.println("staklo: " + donji);
                gornji = staklo;
            } else if (k.getNaziv().equals("bio")) {
                donji = (float)min / 100 * (float)bio;
                gornji = bio;
            } else if (k.getNaziv().equals("mješano")) {
                donji = (float)min / 100 * (float)mjesano;
                gornji = mjesano;
            } else if (k.getNaziv().equals("papir")) {
                donji = (float)min / 100 * (float)papir;
                gornji = papir;
            } else{
                donji = (float)min / 100 * (float)metal;
                gornji = metal;
            }
            float generiraniOtpad = generator.getFloatNumber(donji, (float)gornji);
            System.out.println("->Id spremnika: " + k.getId());
            System.out.println("->Tip spremnika: " + k.getNaziv());
            System.out.println("->Generirani otpad: " + generiraniOtpad );
            System.out.println("->Nosivost spremnika: " + k.getNosivost() );
            System.out.println("->Napunjenost spremnika: " + k.getNapunjenost());
            if(generiraniOtpad <= (k.getNosivost()- k.getNapunjenost())) {
                System.out.println("-->Korisnik je odložio otpad");
                k.setNapunjenost(generiraniOtpad);
            } else {
                System.out.println("-->Korisnik nije odložio otpad");
            }
        }
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
