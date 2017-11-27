/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.comtor.aaa.helper.EncryptionException;
import net.comtor.aaa.helper.PasswordHelper;

/**
 *
 * @author juanmanuel
 */
public class Main {
        
    public static void main(String[] args) throws EncryptionException, ParseException {
        // Clave de Miguel, para Paola. (Aun no me lo ha pedido)
        // String decryption = PasswordHelper.getHelper().decryption("wLIrIgzRrRyngm0KZIGZUg==");
        // System.out.println(decryption);
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lol = format.parse("2017-10-25 00:00:00");
        System.out.println(lol.getTime()/1000);
    }
    
}
