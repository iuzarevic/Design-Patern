/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.observers;

import org.foi.uzdiz.ivauzarev.dz2.Vozilo;
import org.foi.uzdiz.ivauzarev.dz2.iterator.VozniParkRepository;

/**
 *
 * @author Ivan
 */
public class KonkretniObserverZaVozilaVrati extends ObserverZaVozila{
        public KonkretniObserverZaVozilaVrati(Vozilo v, VozniParkRepository vpr) {
        this.subject = v;
        this.vpr = vpr;
        this.subject.attach(this);
    }

    @Override
    public void premjesti() {
        System.out.println("####Vozilo " + subject.getNaziv() + " vraća se sa odvoza####");
        vpr.listaVozilaPrikupljaju.add(subject);
        vpr.listaNaOdvozu.remove(subject);
    }
}
