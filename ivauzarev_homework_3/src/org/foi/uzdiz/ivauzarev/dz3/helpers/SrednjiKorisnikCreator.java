/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.helpers;

import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.KonkretniSpremnik;
import org.foi.uzdiz.ivauzarev.dz3.Korisnik;
import org.foi.uzdiz.ivauzarev.dz3.Spremnik;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class SrednjiKorisnikCreator extends KorisnikCreator {

    public SrednjiKorisnikCreator(int udio) {
        super(udio);
    }

    @Override
    protected void makeUser(int udio) {
        List<Spremnik> dostupniSpremnici = EzoConfig.getInstance().getListaSpremnika();
        KonkretniSpremnik novi = null;
        for (int i = 0; i < udio; i++) {
            users.add(new Korisnik(UserTypes.SREDNJI));
        }

        for (Spremnik s : dostupniSpremnici) {
            //System.out.println("\nBroj spremnika " + s.getNaziv() + " na broj srednjih: " + s.getNaBrojSrednjih());
            //System.out.println("Broj korisnika " + users.size());
            int brojac = 0;
            if (s.getNaBrojSrednjih()!= 0) {

                for (Korisnik k : users) {
                    if (k.getGrupaKorisnika() == UserTypes.SREDNJI) {
                        if (brojac == 0 || (brojac % s.getNaBrojSrednjih()== 0)) {
                            novi = new KonkretniSpremnik(s.getNaziv(), s.getVrsta(), s.getNosivost(), 0);
                            //System.out.println("->Dodajem novi spremnik: " + novi.getId() + "-" + novi.getNaziv());
                            spremniciUlice.add(novi);
                            k.addSpremnik(novi);
                        } else {
                            //System.out.println("-->Dodajem postojeći spremnik: " + novi.getId() + "-" + novi.getNaziv());
                            k.addSpremnik(novi);
                        }
                    }
                    brojac++;
                }
            }
        }
    }

}
