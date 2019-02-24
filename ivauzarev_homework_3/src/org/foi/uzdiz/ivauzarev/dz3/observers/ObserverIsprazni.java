/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.observers;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.state.VoziloPreuzimanje;

/**
 *
 * @author Ivan
 */
public class ObserverIsprazni extends ObserverZaVozila {

    public ObserverIsprazni(Vozilo v) {
        this.subject = v;
        this.subject.attach(this);
    }

    @Override
    public void premjesti() {
        if ((this.subject.getCurrentState()) instanceof VoziloPreuzimanje && this.subject.getPopunjenost() != 0) {
            this.subject.postaviIsprazni();
            System.out.println("#STATUS#" + subject.getNaziv() + " odlazi na odlagalište");
        }
    }
}
