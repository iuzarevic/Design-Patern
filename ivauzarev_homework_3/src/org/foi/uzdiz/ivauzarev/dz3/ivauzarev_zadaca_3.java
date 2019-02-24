/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.foi.uzdiz.ivauzarev.dz3.configuration.EzoConfig;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GenerateNumber;

/**
 *
 * @author Ivan
 */
public class ivauzarev_zadaca_3 {

    public static void main(String[] args) {
        EzoConfig config = EzoConfig.getInstance();
        Path path = Paths.get(args[0]);
        if (Files.exists(path)) {
            config.loadConfigData(args[0]);
            EzoConfig.getInstance().initSystem();
            EzoConfig.getInstance().simulation();
        }
        if (Files.notExists(path)) {
            System.out.println("Datoteka parametara ne postoji");
        }
    }
}
