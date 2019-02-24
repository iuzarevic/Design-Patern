/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz1.helpers;

import java.util.Random;
import org.foi.uzdiz.ivauzarev.dz1.configuration.EzoConfig;

/**
 *
 * @author Ivan
 */
public class GenerateNumber {

    private static GenerateNumber instance = null;
    Random rand;

    private GenerateNumber() {
        rand = new Random(EzoConfig.getInstance().getSjemeGeneratora());
    }

    public static GenerateNumber getInstance() {
        if (instance == null) {
            synchronized (GenerateNumber.class) {
                if (instance == null) {
                    instance = new GenerateNumber();
                }
            }
        }

        return instance;
    }

    public int getIntNumber(int from, int to) {
        int result = rand.nextInt(to - from) + from;
        return result;
    }

    public long getLongNumber(int from, int to) {
        long result = rand.nextInt(to - from) + from;
        return result;
    }

    public float getFloatNumber(float from, float to) {
        float result = from + rand.nextFloat() * (to - from);
        int precision = EzoConfig.getInstance().getBrojDecimala();
        return round(result,precision);
    }

    private static float round(float value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }
}
