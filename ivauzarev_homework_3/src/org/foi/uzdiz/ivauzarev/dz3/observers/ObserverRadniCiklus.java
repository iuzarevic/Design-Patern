/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.observers;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz3.state.VoziloIsprazni;
import org.foi.uzdiz.ivauzarev.dz3.state.VoziloPunjenje;

/**
 *
 * @author Ivan
 */
public class ObserverRadniCiklus extends ObserverZaVozila {

    public ObserverRadniCiklus(Vozilo v) {
        this.subject = v;
        this.subject.attach(this);
    }

    @Override
    public void premjesti() {
        if ((this.subject.getCurrentState()) instanceof VoziloIsprazni && provjeriStanjeCiklusa()) {
            this.subject.postaviPreuzimanje();
            this.subject.setPopunjenost(0);
            this.subject.interniCiklus = 0;
            System.out.println("#STATUS#" + subject.getNaziv() + " se vraća na preuzimanje otpada");
        } else if((this.subject.getCurrentState()) instanceof VoziloPunjenje && provjeriStanjeCiklusaPunjenja()) {
            this.subject.postaviPreuzimanje();
            this.subject.trenutniCiklusiRada = 0;
            this.subject.stanjeGoriva = 0;
            System.out.println("#STATUS#" + subject.getNaziv() + " se vraća na preuzimanje otpada");
        }
    }

    boolean provjeriStanjeCiklusa() {
        int brojRadnihCiklusaZaOdvoz = EzoConfig.getInstance().getBrojRadnihCiklusaZaOdvoz();
        if (this.subject.interniCiklus == brojRadnihCiklusaZaOdvoz + 1) {
            return true;
        }
        return false;
    }

    boolean provjeriStanjeCiklusaPunjenja () {
        this.subject.trenutniCiklusiRada++;
        if (this.subject.trenutniCiklusiRada == (subject.ciklusiPunjenjaGoriva + 1)) {
            return true;
        }
        return false;
    }
}
