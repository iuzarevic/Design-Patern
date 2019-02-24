/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.iterator;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.decorators.VoziloInterface;

/**
 *
 * @author Ivan
 */
public class IteratorVozilo {

    private VozniParkRepository repository;

    private int index = 0;

    public IteratorVozilo(VozniParkRepository repository) {
        this.repository = repository;
    }

    public int size() {
        return repository.listaUlica.size();
    }

    public boolean hasNext() {
        if (index >= repository.listaVozila.size()) {
            return false;
        } else {
            return true;
        }
    }

    public Vozilo next() {
        if (index >= repository.listaVozila.size()) {
            index = 0;
        } else {
            index++;
        }
        if (index > 0) {
            return repository.listaVozila.get(index - 1);
        } else {
            return null;
        }
    }

    public void remove(Vozilo v) {
        repository.listaVozila.remove(v);
    }

//    public void premjestiUAktivne(String[] vozila) {
//        for (String voz_check : vozila) {
//            for (Vozilo v : repository.listaVozila) {
//                if (voz_check.equals(v.getId())) {
//                    repository.listaVozilaPrikupljaju.add(v);
//                    remove(v);
//                    break;
//                }
//            }
//            for (Vozilo v : repository.listaVozilaKontrola) {
//                if (voz_check.equals(v.getId())) {
//                    repository.listaVozilaPrikupljaju.add(v);
//                    remove(v);
//                    break;
//                }
//            }
//        }
//    }
//
//    public void premjestiUKvar(String[] vozila) {
//        for (String voz_check : vozila) {
//            for (VoziloInterface v : repository.listaVozilaPrikupljaju) {
//                if (voz_check.equals(((Vozilo) v).getId())) {
//                    repository.listaVozilaKvar.add((Vozilo) v);
//                    repository.listaVozilaPrikupljaju.remove(v);
//                    break;
//                }
//            }
//        }
//    }
//
//    public void premjestiUKontrolu(String[] vozila) {
//        for (String voz_check : vozila) {
//            for (VoziloInterface v : repository.listaVozila) {
//                if (voz_check.equals(((Vozilo) v).getId())) {
//                    repository.listaVozilaKontrola.add((Vozilo) v);
//                    repository.listaVozila.remove((Vozilo)v);
//                    break;
//                }
//            }
//        }
//    }
}
