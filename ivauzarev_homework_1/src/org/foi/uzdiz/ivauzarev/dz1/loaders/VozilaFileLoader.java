/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.loaders;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class VozilaFileLoader {
        public void dataModifying(List<String> dataList) {
        String cvsSplitBy = ";";
        for (Iterator<String> iterator = dataList.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            String[] data = next.split(cvsSplitBy);
        }
    }
}
