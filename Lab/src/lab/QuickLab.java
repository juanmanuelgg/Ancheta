package lab;

import java.text.ParseException;
import net.comtor.aaa.helper.EncryptionException;
import net.comtor.aaa.helper.PasswordHelper;
//import net.comtor.aaa.helper.PasswordHelper;

/**
 * @author juanmanuel
 */
public class QuickLab {

    public static void main(String[] args) throws ParseException, EncryptionException {
        String pass = PasswordHelper.getHelper().decryption("hI/JytEMe/Omb3XpTUCPZw==");
        System.out.println("PASS: " + pass);
        
        String zone = "2559400";
         String adition = (zone.length() < 8) ? "1" : "";
        int numZeros = 7 - (zone.length());
        while (numZeros-- != 0) {
            adition += "0";
        }
        zone = adition + zone;
        System.out.println(zone);
        
    }
}
