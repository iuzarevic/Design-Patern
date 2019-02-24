/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.observers;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.iterator.VozniParkRepository;

/**
 * Ova klasa više ne sudjeluje u aplikaciji
 * @author Ivan
 */
public class KonkretniObserverZaVozila extends ObserverZaVozila {

    public KonkretniObserverZaVozila(Vozilo v, VozniParkRepository vpr) {
        this.subject = v;
        this.vpr = vpr;
        this.subject.attach(this);
    }

    @Override
    public void premjesti() {
        System.out.println("####Vozilo " + subject.getNaziv() + " ide na odvoz####");
        vpr.listaNaOdvozu.add(subject);
        //vpr.listaVozilaPrikupljaju.remove(subject);
    }

}
