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
public class VoziloKvarCommand implements Command {

    private DispatcherReceiver dr;
    String vozila;

    public VoziloKvarCommand(DispatcherReceiver dr, String vozila) {
        this.dr = dr;
        this.vozila = vozila;
    }

    @Override
    public void execute() {
        dr.kvar(vozila);
    }
}
