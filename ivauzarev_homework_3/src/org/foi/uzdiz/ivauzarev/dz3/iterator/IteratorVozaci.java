/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.iterator;

import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.state.Vozac;

/**
 *
 * @author Ivan
 */
public class IteratorVozaci {

    private List<Vozac> listaVozaca;
    private int index;

    public IteratorVozaci(List<Vozac> lista) {
        index = -1;
        this.listaVozaca = lista;
    }

    public int size() {
        return listaVozaca.size();
    }

    public boolean hasNextAktivni() {
        index++;
        if (index >= listaVozaca.size()) {
            index = -1;
            return false;
        } else if (listaVozaca.size() == 0) {
            index = -1;
            return true;
        } else {
            return true;
        }
    }

    public Vozac nextAktivni() {
        if (index >= 0) {
            return (Vozac)listaVozaca.get(index);
        } else {
            return null;
        }
    }

}
