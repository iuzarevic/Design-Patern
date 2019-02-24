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
public interface VozacState {
    void postaviGodisnji(Vozac v);
    void postaviBolovanje(Vozac v);
    void postaviOtkaz(Vozac v);
    void postaviPreuzimanje(Vozac v);
    void postaviNedodijeljen(Vozac v);
    String toString();
}
