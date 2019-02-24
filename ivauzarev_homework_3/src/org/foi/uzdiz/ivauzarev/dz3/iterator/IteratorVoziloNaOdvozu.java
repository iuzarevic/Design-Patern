/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.iterator;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;

/**
 *
 * @author Ivan
 */
public class IteratorVoziloNaOdvozu {

    private VozniParkRepository repository;

    private int index;

    public IteratorVoziloNaOdvozu(VozniParkRepository repository) {
        index = -1;
        this.repository = repository;
    }

    public int size() {
        return repository.listaNaOdvozu.size();
    }

    public boolean hasNext() {
        index++;
        if (index >= repository.listaNaOdvozu.size()) {
            index = -1;
            return false;
        } else if (repository.listaNaOdvozu.size() == 0) {
            index = -1;
            return true;
        } else {
            return true;
        }
    }

    public Vozilo next() {
        if (index >= 0) {
            return repository.listaNaOdvozu.get(index);
        } else {
            return null;
        }
    }

    public void remove(Vozilo v) {
        repository.listaNaOdvozu.remove(v);
    }
}
