/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.state;

/**
 *
 * @author Ivan
 */
public class Vozac {

    private VozacState currentState;
    private String ime;
    
    public Vozac(String ime) {
        this.ime = ime;
        currentState = new Nedodijeljen();
    }

    public VozacState getCurrentState() {
        return currentState;
    }

    public String getIme() {
        return ime;
    }

    public void set_state(VozacState s) {
        currentState = s;
    }

    public void postaviGodisnji() {
        currentState.postaviGodisnji(this);
    };

    public void postaviBolovanje() {
        currentState.postaviBolovanje(this);
    };

    public void postaviOtkaz() {
        currentState.postaviOtkaz(this);
    };

    public void postaviPreuzimanje() {
        currentState.postaviPreuzimanje(this);
    };

    public void postaviNedodijeljen() {
        currentState.postaviNedodijeljen(this);
    };
}
