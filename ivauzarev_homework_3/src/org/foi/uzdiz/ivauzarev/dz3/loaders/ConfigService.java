/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loaders;

import java.util.Map;

/**
 * Ovo je context strategy patterna
 *
 * @author Ivan
 */
public class ConfigService {

    private static final LoaderStrategy[] loaders = new FileLoader[]{
        new SpremniciFileLoader(), 
        new UliceFileLoader(), 
        new PodrucjaFileLoader(), 
        new VozilaFileLoader(),
        new DispatcherFileLoader()};

    public static void getConfig() {
        for (LoaderStrategy loader : loaders) {
            loader.solve();
        }
    }

    public static void getMainConfig(String file, Map<String, String> map) {
        LoaderStrategy main = new ConfigFileLoader(file, map);
        main.solve();
    }

}
