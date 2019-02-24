/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.helpers;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz1.KonkretniSpremnik;
import org.foi.uzdiz.ivauzarev.dz1.Korisnik;
import org.foi.uzdiz.ivauzarev.dz1.Spremnik;

/**
 *
 * @author Ivan
 */
public abstract class KorisnikCreator {

    protected List<Korisnik> users = new ArrayList<>();
    protected List<KonkretniSpremnik> spremniciUlice = new ArrayList<>();

    public KorisnikCreator(int udio) {
        this.makeUser(udio);
    }

    public List<Korisnik> getUsers() {
        return users;
    }

    public List<KonkretniSpremnik> getSpremniciUlice() {
        return spremniciUlice;
    }

    abstract protected void makeUser(int udio);

}
