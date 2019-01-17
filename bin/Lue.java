import java.io.*;

/**
 * Lue.java
 * <p>Luokka sy�tetietojen lukemiseen k�ytt�j�lt�. Luokan avulla voidaan lukea
 * merkkijono, kokonaisluku, liukuluku, Aika- ja Asiakas-tyyppiset tiedot.
 * 
 * @author Henri Tanskanen
 * @version 20.3.2007
 */
public class Lue {

  
    static DataInputStream stdin = new DataInputStream(System.in);

    /**
     * rivi.
     * <p>Lukee k�ytt�j�lt� yhden rivin. Vaatii kelvollisen sy�tteen.
     * Palauttaa sy�tteen.
     * 
     * @return arvo         k�ytt�j�lt� luettu merkkijono
     * @throws Exception    jos sy�tteen lukemisessa on ongelmia
     */
    public static String rivi() {
        String arvo=null;
        boolean ok;
        do {
            try {
                arvo = stdin.readLine();
                ok = true;
            } catch (Exception e) {
                System.out.println("Virhe rivin lukemisessa. Anna uusi!");
                ok = false;
            }
        }
        while (!ok);
        return arvo;
    }

    /**
     * kluku.
     * <p>Lukee k�ytt�j�lt� kokonaisluvun, vaatii kelvollisen sy�tteen.
     * Palauttaa saadun sy�tteen.
     * 
     * @return arvo         k�ytt�j�lt� luettu kokonaisluku
     * @throws Exception    jos sy�tteen lukemisessa on ongelmia
     */
    public static int kluku() {
        int arvo=-1;
        boolean ok;
        do {
            try {
                arvo = Integer.parseInt(Lue.rivi());
                ok = true;
            } catch (Exception e) {
                System.out.println("Kelvoton kokonaisluku. Anna uusi!");
                ok = false;
            }
            if (arvo < 0) {
                System.out.println("Luku ei voi olla negatiivinen. Anna uusi!");
                ok = false;
            }
        } while (!ok);
        return arvo;
    }

    /**
     * dluku.
     * <p>Lukee k�ytt�j�lt� liukuluvun, vaatii kelvollisen sy�tteen.
     * Palauttaa sy�tteen� saadun arvon.
     * 
     * @return arvo         sy�tteen� saatu liukuluku
     * @throws Exception    jos sy�tteen lukemisessa on ongelmia    
     */
    public static double dluku() {
        double arvo=-1;
        boolean ok;
        do {
            try {
                arvo = new Double(Lue.rivi()).doubleValue();
                ok = true;
            } catch (Exception e) {
                System.out.println("Kelvoton desimaaliluku. Anna uusi!");
                ok = false;
            }
            if (arvo < 0) {
                System.out.println("Luku ei voi olla negatiivinen. Anna uusi!");
                ok = false;
            }
        } while (!ok);
        return arvo;
    }
    
    /**
     * lueAsiakas.
     * <p>Lukee k�ytt�j�lt� Asiakas-tyyppisen muuttujan tiedot (nimen ja osoitteen),
     * vaatii kelvolliset sy�tteet. K�ytt�� lukemiseen t�m�n luokan metodia rivi().
     * 
     * @return Asiakas  uusi Asiakas-olio annetuilla tiedoilla
     */
    public static Asiakas lueAsiakas() {
        String nimi;
        String osoite;
        
        System.out.print("Anna asiakkaan nimi: ");
        nimi = Lue.rivi();
        System.out.print("Anna asiakkaan osoite: ");
        osoite = Lue.rivi();
        
        return new Asiakas(nimi, osoite);
    }
        
    /**
     * lueAika.
     * <p>Lukee k�ytt�j�lt� Aika-tyyppisen ajan. Aika luetaan String tiedostona ja muutetaan
     * erillisiksi osiksi, p�iv�, kuukausi ja vuosi. Vaatii kelvollisen ajan, tarkistaa
     * kelvollisuuden Aika-luokassa.
     * 
     * @return pwm          uusi Aika-olio annetuilla tiedoilla
     * @throws Exception    jos aika ei ole oikeaa muotoa
     */
    public static Aika lueAika() {
        String[] pkv;
        boolean ok;
        Aika pvm;
        String tmp;
        
        do {

            tmp = Lue.rivi();
            pkv = tmp.split("\\.");
            
            try {
                ok = Aika.tarkistaPvm(Integer.parseInt(pkv[0]),Integer.parseInt(pkv[1]),
                            Integer.parseInt(pkv[2]));
            } catch (Exception e) {
                System.out.println("Anna pvm muodossa 'pv.kk.vu'.");
                ok=false;
            }
            if (!ok)
                System.out.print("P�iv�m��r� ei ole kelvollinen. Anna uusi: ");
        } while (!ok);
        pvm = new Aika(Integer.parseInt(pkv[0]),Integer.parseInt(pkv[1]),
                            Integer.parseInt(pkv[2]));
        return pvm;
    }
}