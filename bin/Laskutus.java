import java.util.*;
import java.io.*;

/**
 * Laskutus.java.
 * <p>Sis�lt�� laskutuslistan luomiseen ja muokkaamiseen tarvittavia metodeja.
 * <p>Luokan avulla muodostetaan laskuista vektori, jonka avulla laskuja on helppo
 * lis�t� ja poistaa jne.
 *
 * @author  Henri Tanskanen
 * @version 30.3.2007
 */

public class Laskutus implements Serializable {

    private Vector<Lasku> laskut;

    /**
     * Laskutus. 
     * <p>Luokan alustaja, luo uuden tyhj�n laskutuslistan.
     */
    public Laskutus() 
    {
        laskut = new Vector<Lasku>();
        
    }

    /**
     * lisaaLasku.
     * <p>Lis�� sy�tteen��n saaman laskun laskutuslistaan. Mik�li lis��minen ei onnistu,
     * ilmoittaa virheell�.
     * 
     * @param lasku         listaan lis�tt�v� lasku, tyyppi� Lasku
     * @throws Exception    jos laskun lis��misess� on ongelmia 
     */
    public void lisaaLasku(Lasku lasku) 
    {
        try { 
            laskut.add(lasku);
            System.out.println("Lasku lis�tty. Laskun numero on "+ lasku.palautaNumero());
        } catch (Exception e) {
            System.out.println("Ongelmia laskun lis��misess�. Laskua ei lis�tty.");
        }
    }
    
    /**
     * poistaLasku.
     * <p>Poistaa laskunumeron perusteella haetun laskun laskutusvektorista.
     * <p>Lasku haetaan numeron perusteella metodia haeLasku k�ytt�en.
     * 
     * @param nro           poistettavan laskun numero
     * @throws Exception    jos numerolla ei ole laskua
     */
    public void poistaLasku(int nro) {
        try {
        laskut.remove(haeLasku(nro));
        System.out.println("Poistettiin laskunro " + nro);
        } catch (Exception e) {
            System.out.println("Laskua antamallasi numerolla ei l�ydy.");
        }
    }
    
    /**
     * haeLasku.
     * <p>Palauttaa laskun numeron perusteella haetun laskun j�rjestysnumeron vektorissa.
     * <p>Jos laskua ei l�ydy, palauttaa -1.
     * 
     * @param nro                           etsitt�v�n laskun numero
     * @return laskut.elementAt(laskunro)   lasku jota haettiin
     */
    public Lasku haeLasku(int nro) {
        int laskunro = -1;
        for (int i=0; i < laskut.size(); i++)
            if (laskut.elementAt(i).palautaNumero() == nro)
                laskunro = i;
         return laskut.elementAt(laskunro);
    }
    
    /**
     * lahetaLasku.
     * <p>L�ett�� laskun tai karhulaskun asiakkaalle.
     * <p>Kysyy k�ytt�j�lt� poistetaanko l�hetetty lasku laskutusluettelosta.
     * 
     * @param nro     l�hetett�v�n laskun numero
     */
    
    public void lahetaLasku(Lasku lasku)
    {
        //l�hett�� laskun asiakkaalle ???
    }
    
    /**
     * tarkistaEra.
     * <p>Palauttaa laskun er�p�iv�n ja nykyisen p�iv�n eron p�ivin�. 
     * Jos lasku on my�h�ss� palautusarvo on negatiivinen.
     * Jos lasku on my�h�ss� yli 30 p�iv�� palauttaa -31 (karhulaskun aikaraja).
     * 
     * @param nykyinen nykyinen aika
     * @return ero nykyisen p�iv�n ja er�p�iv�n ero tai -31
     */
    
    // T�M� ON VIEL� KESKEN PALJON
    public int tarkistaEra(int nro, Aika nyt) {
        int ero=-32;
        Aika erapv;
        try {
          erapv = haeLasku(nro).palautaErapv();
          if (erapv.palautaVu() <= nyt.palautaVu()) {
            if (nyt.palautaKk() <= erapv.palautaKk())
              ero = (erapv.palautaPv() - nyt.palautaPv());
            else if (nyt.palautaKk() < erapv.palautaKk())
              ero = erapv.palautaPv()+(Aika.paiviaKk(nyt.palautaKk(), nyt.palautaVu()) - nyt.palautaVu());
            }
        /* t�ss� tarkistetaan jos er�pv on joulukuu ja nyt on tammikuu */
        else if ((nyt.palautaVu() < erapv.palautaVu()) 
                && nyt.palautaKk() == 12 && erapv.palautaKk() == 1)
            ero = erapv.palautaPv() + (Aika.paiviaKk(nyt.palautaKk(), nyt.palautaVu()) - nyt.palautaVu());
        else ero = -31;
        } catch (Exception e) {
        System.out.println("Laskua ei ole antamallasi numerolla.");
        }
        return ero;
    }
    
    /**
     * onTyhja.
     * <p>Tarkistaa onko laskutusluettelossa laskuja.
     * Palauttaa tosi jos laskutusluettelo on tyhj�, muuten ep�tosi.
     * <p>T�t� k�ytet��n hyv�ksi p��ohjelman ajossa.
     * 
     * @return true     jos laskutustiedosto on tyhj�, muuten false
     * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/Vector.html">Vector</a>
     */
    public boolean onTyhja() {
        if (laskut.isEmpty())
            return true;
        else return false;
    }
    
    /**
     * palautaViimeinen.
     * <p>Palauttaa laskuista j�rjestykselt��n viimeisen.
     * T�t� k�ytet��n p��ohjelman ajossa.
     * 
     * @return laskut.lastElement()     vektorin viimeinen elementti
     * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/Vector.html">Vector</a>
     */
    public Lasku palautaViimeinen() {
        return laskut.lastElement();
    }
    
    /**
     * tulostaLasku.
     * <p>Tulostaa laskunumeron perusteella haetun laskun. Lasku etsit��n k�ytt�en
     * haeLasku metodia.
     * <p>Jos laskua ei l�ydy ilmoittaa virheellisest� laskunumerosta.
     * 
     * @param numero        haettavan laskun numero
     * @throws Exception    jos laskua ei ole annetulla numerolla
     */
    public void tulostaLasku(int numero) {
        try {
            System.out.println(haeLasku(numero).toString());
        } catch (Exception e) {
            System.out.println("Antamallasi numerolla ei ole laskua. Tarkista laskun numero.");
        }
    }
    
    /**
     * tulostaLaskut.
     * Tulostaa kaikki laskut. Jos laskutusluettelo on tyhj� (tarkistetaan onTyhj� metodilla)
     * ilmoittaa ettei laskuja ole. Muuten k�y silmukan avulla kaikki laskut l�pi ja tulostaa
     * jokaisen k�ytt�en Laskut-luokan toString metodia.
     * Mik�li tulostamisessa on ongelmia, ilmoittaa virheell�.
     * 
     * @throws Exception jos laskuja ei voida tulostaa
     */
    
    public void tulostaLaskut () {
        int i=0;
        try { 
            if (onTyhja()) System.out.println("Laskuja ei ole.");
            for (i=0; i < laskut.size(); i++)
            System.out.print(laskut.elementAt(i).toString());
            System.out.println("\nTulostettiin "+ i +" laskua.");
        } catch (Exception e) {
            System.out.println("Laskuja ei ole tai niit� ei voida tulostaa.");
        }
    }
}
