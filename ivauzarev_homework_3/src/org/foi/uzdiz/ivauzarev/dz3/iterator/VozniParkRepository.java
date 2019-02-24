/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.iterator;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.ivauzarev.dz3.Ulica;
import org.foi.uzdiz.ivauzarev.dz3.Vozilo;
import org.foi.uzdiz.ivauzarev.dz3.composite.PodrucjeComponent;
import org.foi.uzdiz.ivauzarev.dz3.decorators.VoziloInterface;
import org.foi.uzdiz.ivauzarev.dz3.helpers.GenerateNumber;
import org.foi.uzdiz.ivauzarev.dz3.state.Vozac;

/**
 *
 * @author Ivan
 */
public class VozniParkRepository {

    public List<Ulica> listaUlica = new ArrayList<>();
    public List<Vozilo> listaVozila = new ArrayList<>();
//    public List<VoziloInterface> listaVozilaPrikupljaju = new ArrayList<>();
//    public List<Vozilo> listaVozilaKvar = new ArrayList<>();
//    public List<Vozilo> listaVozilaKontrola = new ArrayList<>();
    public List<Vozilo> listaNaOdvozu = new ArrayList<>();
    
    public List<Vozac> listaVozaca = new ArrayList<>();
    public List<PodrucjeComponent> listaPodrucja = new ArrayList<>();
    

    public VozniParkRepository(List<Vozilo> lista, List<Ulica> lu, List<Vozac> lv, List<PodrucjeComponent> lp) {
        listaVozila = lista;
        listaUlica = lu;
        listaVozaca = lv;
        listaPodrucja = lp;
    }

    public void addVozilo(Vozilo v) {
        listaVozila.add(v);
    }

    public IteratorVozilo getIterator() {
        return new IteratorVozilo(this);
    }

    public IteratorVoziloAktivni getIteratorAktivni() {
        return new IteratorVoziloAktivni(this);
    }

    public IteratorVoziloNaOdvozu getIteratorNaOdvozu() {
        return new IteratorVoziloNaOdvozu(this);
    }

    public List<Ulica> getListaUlica() {
        GenerateNumber gn = GenerateNumber.getInstance();
        gn.shuffleArray(listaUlica);
        return listaUlica;
    }

    public List<Vozilo> getListaVozila() {
        return listaVozila;
    }

//    public List<VoziloInterface> getListaVozilaPrikupljaju() {
//        return listaVozilaPrikupljaju;
//    }
//
//    public List<Vozilo> getListaVozilaKvar() {
//        return listaVozilaKvar;
//    }
//
//    public List<Vozilo> getListaVozilaKontrola() {
//        return listaVozilaKontrola;
//    }

    public List<Vozac> getListaVozaca() {
        return listaVozaca;
    }
    
    public PodrucjeComponent provjeriIshodista(String podrucje) {
        for(PodrucjeComponent pc: listaPodrucja) {
            if((pc.getId()).equals(podrucje)) {
                return pc;
            }
        }
        return null;
    }
    
    

}
