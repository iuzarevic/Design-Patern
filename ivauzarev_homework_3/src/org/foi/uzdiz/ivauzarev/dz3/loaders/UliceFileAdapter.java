/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.util.Iterator;
import org.foi.uzdiz.ivauzarev.dz3.Ulica;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class UliceFileAdapter extends FileLoader {

    private UliceFileLoader adaptee;

    public UliceFileAdapter(UliceFileLoader fl) {
        adaptee = fl;
    }

    @Override
    public void dataModifying() {
        String file = EzoConfig.getInstance().getUlice();
        //adaptee.dataModifying(retrieveUniversalData(file));
    }

}
