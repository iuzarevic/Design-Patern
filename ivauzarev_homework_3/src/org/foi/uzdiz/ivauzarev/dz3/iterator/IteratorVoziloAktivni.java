/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.iterator;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.decorators.VoziloInterface;
import org.foi.uzdiz.ivauzarev.dz3.observers.ObserverZaVozila;

/**
 *
 * @author Ivan
 */
public class IteratorVoziloAktivni {

    private VozniParkRepository repository;

    private int index;

    public IteratorVoziloAktivni(VozniParkRepository repository) {
        index = -1;
        this.repository = repository;
    }

    public int size() {
        return repository.listaVozila.size();
    }

    public boolean hasNextAktivni() {
        index++;
        if (index >= repository.listaVozila.size()) {
            index = -1;
            return false;
        } else if (repository.listaVozila.size() == 0) {
            index = -1;
            return true;
        } else {
            return true;
        }
    }

    public Vozilo nextAktivni() {
        if (index >= 0) {
            return (Vozilo)repository.listaVozila.get(index);
        } else {
            return null;
        }
    }

    public VoziloInterface nextAktivniAndUniversal() {
        if (index >= 0) {
            return repository.listaVozila.get(index);
        } else {
            return null;
        }
    }

    public void remove(Vozilo v) {
        repository.listaVozila.remove(v);
    }
}
