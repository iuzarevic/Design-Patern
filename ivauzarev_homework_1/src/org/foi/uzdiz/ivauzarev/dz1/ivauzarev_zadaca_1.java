/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1;

import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz1.helpers.GenerateNumber;

/**
 *
 * @author Ivan
 */
public class ivauzarev_zadaca_1 {
    public static void main(String[] args) {
        EzoConfig config = EzoConfig.getInstance();
        config.loadConfigData(args[0]);
        EzoConfig.getInstance().initSystem();
    }
}
