/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.loaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz1.Ulica;
import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class UliceFileLoader {

    public void dataModifying(List<String> dataList) {
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = dataList.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            String[] data = next.split(cvsSplitBy);
            Ulica u = new Ulica.Builder()
                    .addDataFromFile(data)
                    .addMaliUsers()
                    .addSrednjiUsers().addVelikiUsers().build();
            EzoConfig.getInstance().getListaUlica().add(u);
        }
    }
}
