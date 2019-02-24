/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.command;

/**
 *
 * @author Ivan
 */
public class VoziloObradiCommand implements Command {

    private DispatcherReceiver dr;
    String podrucja;
    String vozila;

    public VoziloObradiCommand(DispatcherReceiver dr, String podrucja, String vozila) {
        this.dr = dr;
        this.podrucja = podrucja;
        this.vozila = vozila;
    }

    @Override
    public void execute() {
        dr.obradi(podrucja, vozila);
    }
}
