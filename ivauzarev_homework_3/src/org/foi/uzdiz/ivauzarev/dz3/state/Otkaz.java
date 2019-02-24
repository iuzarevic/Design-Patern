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
public class Otkaz implements VozacState {

    @Override
    public void postaviGodisnji(Vozac v) {
        v.set_state(new Godisnji());
        System.out.println("#Vozac " + v.getIme() + " mijenja stanje u GODIŠNJI ODMOR");
    }

    @Override
    public void postaviBolovanje(Vozac v) {
        v.set_state(new Bolovanje());
        System.out.println("#Vozac " + v.getIme() + " mijenja stanje u BOLOVANJE");
    }

    @Override
    public void postaviOtkaz(Vozac v) {
        v.set_state(new Otkaz());
        System.out.println("#Vozac " + v.getIme() + " mijenja stanje u OTKAZ");
    }

    @Override
    public void postaviPreuzimanje(Vozac v) {
        v.set_state(new Preuzimanje());
        System.out.println("#Vozac " + v.getIme() + " mijenja stanje u PREUZIMANJE");
    }

    @Override
    public void postaviNedodijeljen(Vozac v) {
        System.out.println("#Vozac " + v.getIme() + " mijenja stanje u NEDODIJELJENO");
    }

    @Override
    public String toString() {
        return "OTKAZ";
    }

}
