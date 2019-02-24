/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.loaders;

import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class SpremniciFileAdapter extends FileLoader{
    private SpremniciFileLoader adaptee;

    public SpremniciFileAdapter(SpremniciFileLoader fl) {
        adaptee = fl;
    }

    @Override
    public void dataModifying() {
        String file = EzoConfig.getInstance().getSpremnici();
        adaptee.dataModifying(retrieveUniversalData(file));
    }
}
