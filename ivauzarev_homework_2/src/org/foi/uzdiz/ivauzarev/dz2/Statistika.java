/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz2;

/**
 *
 * @author Ivan
 */
public class Statistika {
    int ukupnoSpremnika = 0;
    int ukupnoMjesta = 0;
    float ukupnoKolicinaOtpada = 0;
    int ukupnoZbrinjavanje = 0;

    public int getUkupnoSpremnika() {
        return ukupnoSpremnika;
    }

    public void setUkupnoSpremnika(int ukupnoSpremnika) {
        this.ukupnoSpremnika += ukupnoSpremnika;
    }

    public int getUkupnoMjesta() {
        return ukupnoMjesta;
    }

    public void setUkupnoMjesta(int ukupnoMjesta) {
        this.ukupnoMjesta += ukupnoMjesta;
    }

    public float getUkupnoKolicinaOtpada() {
        return ukupnoKolicinaOtpada;
    }

    public void setUkupnoKolicinaOtpada(float ukupnoKolicinaOtpada) {
        this.ukupnoKolicinaOtpada += ukupnoKolicinaOtpada;
    }

    public int getUkupnoZbrinjavanje() {
        return ukupnoZbrinjavanje;
    }

    public void setUkupnoZbrinjavanje(int ukupnoZbrinjavanje) {
        this.ukupnoZbrinjavanje += ukupnoZbrinjavanje;
    }
    
    
}
