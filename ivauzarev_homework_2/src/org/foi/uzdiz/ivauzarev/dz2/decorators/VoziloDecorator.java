/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.decorators;

import org.foi.uzdiz.ivauzarev.dz2.KonkretniSpremnik;

/**
 *
 * @author Ivan
 */
public abstract class VoziloDecorator implements VoziloInterface {

    protected VoziloInterface customVozilo;

    public VoziloDecorator(VoziloInterface customVozilo) {
        this.customVozilo = customVozilo;
    }

    public void dohvatiOtpad(KonkretniSpremnik ks) {
        customVozilo.dohvatiOtpad(ks);
    }
}
