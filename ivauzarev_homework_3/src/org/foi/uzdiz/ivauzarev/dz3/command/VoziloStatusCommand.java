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
public class VoziloStatusCommand implements Command {

    private DispatcherReceiver dr;

    public VoziloStatusCommand(DispatcherReceiver dr) {
        this.dr = dr;
    }

    @Override
    public void execute() {
        dr.status();
    }
}
