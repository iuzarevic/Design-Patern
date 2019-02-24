/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.ivauzarev.dz3.helpers;

/**
 *
 * @author Ivan
 */
public class UserFactory {

    public static KorisnikCreator getKorisnikCreator(UserTypes userType, int udio) {
            switch (userType) {
                case MALI: {
                    KorisnikCreator c = new MaliKorisnikCreator(udio);
                    return c;
                }

                case SREDNJI: {
                    KorisnikCreator c = new SrednjiKorisnikCreator(udio);
                    return c;
                }

                case VELIKI: {
                    KorisnikCreator c = new VelikiKorisnikCreator(udio);
                    return c;
                }

                default: {
                    return null;
                }
            }
    }
}
