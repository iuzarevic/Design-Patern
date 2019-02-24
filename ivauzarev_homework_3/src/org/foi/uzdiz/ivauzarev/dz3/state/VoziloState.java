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
public interface VoziloState {
    
    void postaviInicijalno(Vozilo v);

    void postaviPreuzimanje(Vozilo v);

    void postaviKvar(Vozilo v);

    void postaviKontrola(Vozilo v);

    void postaviIsprazni(Vozilo v);

    void postaviBezVozaca(Vozilo v);
    
    void postaviPunjenje(Vozilo v);
    
    @Override
    public String toString();
}
