/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.state;

import org.foi.uzdiz.ivauzarev.dz3.Vozilo;

/**
 *
 * @author Ivan
 */
public class VoziloKontrola implements VoziloState {

    @Override
    public void postaviPreuzimanje(Vozilo v) {
        v.set_state(new VoziloPreuzimanje());
    }

    @Override
    public void postaviKvar(Vozilo v) {
        v.set_state(new VoziloKvar());
    }

    @Override
    public void postaviKontrola(Vozilo v) {
        v.set_state(new VoziloKontrola());
    }

    @Override
    public void postaviIsprazni(Vozilo v) {
        v.set_state(new VoziloIsprazni());
    }

    @Override
    public void postaviBezVozaca(Vozilo v) {
        v.set_state(new VoziloBezVozaca());
    }

    @Override
    public void postaviPunjenje(Vozilo v) {
        v.set_state(new VoziloPunjenje());
    }

    @Override
    public String toString() {
        return "KONTROLA";
    }

    @Override
    public void postaviInicijalno(Vozilo v) {
        v.set_state(new VoziloInicijalno());
    }
}
