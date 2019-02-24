/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.loggers;

import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.KonkretniSpremnik;
import org.foi.uzdiz.ivauzarev.dz3.composite.PodrucjeComponent;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GenerateNumber;

/**
 *
 * @author Ivan
 */
public class KorisnikLogger {

    public static void printSpremnika(List<KonkretniSpremnik> spremnici) {
        GenerateNumber generator = GenerateNumber.getInstance();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %30s %20s %20s", "Id spremnika", "Tip spremnika", "Nosivost spremnika", "Napunjenost spremnika");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (KonkretniSpremnik comp : spremnici) {
            System.out.format("%10s %30s %20s %20s",
                    comp.getId(), comp.getNaziv(), comp.getNosivost(), generator.getFloatNumber(comp.getNapunjenost()));
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }
}
