import java.io.*;
import java.util.*;

/**
 * LueKirjoitaBin.java
 * <p>Luokka joka sis�lt�� laskutustiedoston tallentamiseen ja lukemiseen
 * tarvittavat operaatiot. Kirjoittaa ja lukee bin-tiedostoon.
 * 
 * @author (Henri Tanskanen) 
 * @version (18.4.2007)
 */
public class LueKirjoitaBin
{
    
    /* lukemiseen ja kirjoittamiseen tarvittavat sy�tt�- ja kirjoitusvirrat */
    static FileOutputStream tulosvirta;
    static ObjectOutputStream tulostdsto = null;
    static FileInputStream syottovirta;
    static ObjectInputStream syottotdsto = null;

    /**
     * kirjoitaBin.
     * <p>Kirjoittaa parametrina saamansa laskutustiedoston tiedostoon.
     * 
     * @param laskut        tiedostoon kirjoitettava tieto laskutuslistasta
     * @throws IOException  jos laskujen kirjoittamisessa on ongelmia
     */
    public static void kirjoitaBin(Laskutus laskut)
    {
        try {
            tulosvirta = new FileOutputStream("laskut.tmp");
            tulostdsto = new ObjectOutputStream(tulosvirta);
        
            tulostdsto.writeObject(laskut);
            System.out.println("Tallennettu.");
        }
        catch (IOException e) {
            System.out.println("Ongelmia laskutustiedostoon kirjoittaessa");
        }
        /* Suljetaan tulostietovirta */
        try {
            if (tulostdsto != null)
            tulostdsto.close();
        }
        catch (IOException e) {
            System.out.println("Ongelmia laskutustiedoston sulkemisessa");
        }

    }
    
    /**
     * lueBin.
     * <p>Lukee laskutustiedostot tiedostosta 
     * ja laskutuslistan tiedot paluttaa jos lukeminen onnistuu.
     * Jos ongelmia lukemisessa, ilmoittaa virheell�.
     * 
     * @return laskut       laskut jotka olivat laskutustiedostossa
     * @throws ClassNotFoundException   jos tiedostoa ei l�ydy
     * @throws IOException  jos tietojen lukemisessa on ongelmia
     */
    public static Laskutus lueBin() {
        Laskutus laskut = null;
        
        try {
            syottovirta = new FileInputStream("laskut.tmp");
            syottotdsto = new ObjectInputStream(syottovirta);            
            laskut = (Laskutus) syottotdsto.readObject();
            //System.out.println("Luku onnistui.");
        }
        catch (ClassNotFoundException e1) {
            System.out.println("Olion luokkaa ei l�ydy");            
        }
        catch (IOException e2) {
            System.out.println("Ongelmia laskutietojen lukemisessa");            
        }
        try {
            if (syottotdsto != null)
            syottotdsto.close();            
        }
        catch(IOException e) {
            System.out.println("Ongelmia sy�tt�tiedoston sulkemisessa");
            
        }        
        return laskut;
    }
    
    /**
     * alustaBin.
     * <p>Tyhjent�� laskutustiedoston.
     * Mahdollisten lukuongelmien vuoksi alustaa laskutustiedoston uudelle
     * laskutusj�rjestelm�lle, jonka j�lkeen laskuja voidaan taas lis�t�
     * ja poistaa ym. muita operaatioita tehd� normaalisti.
     * 
     * @param tyhja         tyhj� laskutuslista
     * @throws IOException  jos tiedostoon kirjoittamisessa on ongelmia
     */
    public static void alustaBin(Laskutus tyhja)
    { 
        try {
            
            tulosvirta = new FileOutputStream("laskut.tmp");
            tulostdsto = new ObjectOutputStream(tulosvirta);
        
            tulostdsto.writeObject(tyhja);
            System.out.println("Laskut tiedosto tyhjennetty.");
        }
        catch (IOException e) {
            System.out.println("Ongelmia laskut tiedoston tyhjent�misess�");
        }
        // Suljetaan tulostietovirta
        try {
            if (tulostdsto != null)
            tulostdsto.close();
        }
        catch (IOException e) {
            System.out.println("Ongelmia tulostiedoston sulkemisessa");
        }

    }
}
