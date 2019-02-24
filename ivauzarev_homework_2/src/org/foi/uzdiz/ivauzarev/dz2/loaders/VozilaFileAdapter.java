/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2.loaders;

import org.foi.uzdiz.ivauzarev.dz2.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class VozilaFileAdapter extends FileLoader{
        private VozilaFileLoader adaptee;

    public VozilaFileAdapter(VozilaFileLoader fl) {
        adaptee = fl;
    }

    @Override
    public void dataModifying() {
        String file = EzoConfig.getInstance().getVozila();
        //adaptee.dataModifying(retrieveUniversalData(file));
    }
}
