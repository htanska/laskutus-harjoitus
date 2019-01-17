import java.util.*;
import java.io.*;

/**
 * Lasku.java<p>
 * Luo laskun jossa laskun tiedot. Sis‰lt‰‰ operaatioita laskun k‰sittelemiseen, kuten summan
 * <p>muuttamiseen, kulujen lis‰‰miseen jne.
 * 
 * @author Henri Tanskanen, Joensuun yliopisto
 * @version 30.3.2007
 */

public class Lasku implements Serializable {
    private int numero;
    private Asiakas asiakas;
    private double summa;
    private Aika erapv;
    private double kulut;
    
    /**
     * Luokan Lasku alustaja. Luo uuden laskun saamillaan parametreilla.
     * 
     * @param numero    laskun numero
     * @param asiakas   asiakkaan tiedot
     * @param summa     laskun loppusumma
     * @param erapv     laskun er‰p‰iv‰m‰‰r‰
     * @param kulut     laskun kulut
     */
    public Lasku(int numero, Asiakas asiakas, double summa, Aika erapv, double kulut)
    {
        this.numero = numero;
        this.asiakas = asiakas;
        this.summa = summa;
        this.erapv = erapv;
        this.kulut = kulut;
    }
    
    /**
     * palautaNumero. Palauttaa laskunumeron.
     * 
     * @return this.numero laskunumero
     */
    public int palautaNumero() {
        return this.numero;
    }
    
    /**
     * palautaAsiakas. Palauttaa laskun asiakastiedot, tyyppi‰ Asiakas.
     * 
     * @return this.asiakas asiakkaan tiedot
     */
    public Asiakas palautaAsiakas() {
        return this.asiakas;
    }
    
    /**
     * palautaSumma. Palauttaa laskun summan.
     * 
     * @return this.summa laskun loppusumma
     */
    public double palautaSumma() {
        return this.summa;
    }
    
    /**
     * palautaErapv. Palauttaa laskun er‰p‰iv‰tiedot, tyyppi‰ Aika.
     * 
     * @return this.erapv laskun er‰p‰iv‰m‰‰r‰
     */
    public Aika palautaErapv() {
        return this.erapv;
    }
    
    /**
     * palautaKulut. Palauttaa laskun kulut.
     * 
     * return this.kulut laskun kulut
     */
    public double palautaKulut() {
        return this.kulut;
    }

    /**
     * toString.
     * <p>Muuttaa laskun tiedot yhdeksi merkkijonoksi, jolla lasku saadaan tulostettua,
     * ja palauttaa luodun merkkijonon.
     */
    public String toString() {
        return "\n----------------------------------------------------------\n" +
        "Laskun numero: "+this.numero + "\t Maksaja: "+this.asiakas.toString() + 
        "\nSumma: "+this.summa + "Ä\t\t Kulut: " + this.kulut + 
        "Ä\t Erap‰iv‰: "+this.erapv.toString() +
        "\n----------------------------------------------------------\n";
    }

    /** 
     * muutaErapv. 
     * <p>Muuttaa laskun er‰p‰iv‰‰. Kysyy k‰ytt‰j‰lt‰ uuden er‰p‰iv‰n ja asettaa sen laskuun.
     * Luetaan uusi er‰p‰iv‰ Lue-luokkaa k‰ytt‰en.
     */
    public void muutaErapv() {
        System.out.print("Anna uusi er‰p‰iv‰: ");
        this.erapv=Lue.lueAika();
        System.out.println("\nEr‰p‰iv‰ muutettu.");
    }
    
    /**
     * asetaKulut. 
     * <p>Asettaa laskuun mahdolliset kulut. Kulut asetetaan sen mukaan kuinka paljon lasku on
     * myˆh‰ss‰, k‰ytt‰en metodia tarkistaEra.
     */
    public void asetaKulut() {
        //t‰ss‰ asetetaan kulut laskuun.
    }
    
    /**
     * asetaSumma.
     * <p>Asettaa laskulle uuden loppusumman. Kysyy uuden summan k‰ytt‰j‰lt‰ ja sijoittaa sen
     * summaksi. Summa luetaan k‰ytt‰en Lue-luokkan metodia.
     */
    public void asetaSumma() {
        double uusi_summa;
        System.out.print("Anna laskun summa: ");
        uusi_summa = Lue.dluku();
        this.summa = uusi_summa;
    }
    
    /**
     * karhulasku.
     * <p>Luo karhulaskun l‰hetett‰v‰ksi.
     */
    public void karhulasku() {
    }
    
                    
}

