import java.util.*;
import java.io.*;

/**
 * Laskutus.java.
 * <p>Sisältää laskutuslistan luomiseen ja muokkaamiseen tarvittavia metodeja.
 * <p>Luokan avulla muodostetaan laskuista vektori, jonka avulla laskuja on helppo
 * lisätä ja poistaa jne.
 *
 * @author  Henri Tanskanen
 * @version 30.3.2007
 */

public class Laskutus implements Serializable {

    private Vector<Lasku> laskut;

    /**
     * Laskutus. 
     * <p>Luokan alustaja, luo uuden tyhjän laskutuslistan.
     */
    public Laskutus() 
    {
        laskut = new Vector<Lasku>();
        
    }

    /**
     * lisaaLasku.
     * <p>Lisää syötteenään saaman laskun laskutuslistaan. Mikäli lisääminen ei onnistu,
     * ilmoittaa virheellä.
     * 
     * @param lasku         listaan lisättävä lasku, tyyppiä Lasku
     * @throws Exception    jos laskun lisäämisessä on ongelmia 
     */
    public void lisaaLasku(Lasku lasku) 
    {
        try { 
            laskut.add(lasku);
            System.out.println("Lasku lisätty. Laskun numero on "+ lasku.palautaNumero());
        } catch (Exception e) {
            System.out.println("Ongelmia laskun lisäämisessä. Laskua ei lisätty.");
        }
    }
    
    /**
     * poistaLasku.
     * <p>Poistaa laskunumeron perusteella haetun laskun laskutusvektorista.
     * <p>Lasku haetaan numeron perusteella metodia haeLasku käyttäen.
     * 
     * @param nro           poistettavan laskun numero
     * @throws Exception    jos numerolla ei ole laskua
     */
    public void poistaLasku(int nro) {
        try {
        laskut.remove(haeLasku(nro));
        System.out.println("Poistettiin laskunro " + nro);
        } catch (Exception e) {
            System.out.println("Laskua antamallasi numerolla ei löydy.");
        }
    }
    
    /**
     * haeLasku.
     * <p>Palauttaa laskun numeron perusteella haetun laskun järjestysnumeron vektorissa.
     * <p>Jos laskua ei löydy, palauttaa -1.
     * 
     * @param nro                           etsittävän laskun numero
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
     * <p>Läettää laskun tai karhulaskun asiakkaalle.
     * <p>Kysyy käyttäjältä poistetaanko lähetetty lasku laskutusluettelosta.
     * 
     * @param nro     lähetettävän laskun numero
     */
    
    public void lahetaLasku(Lasku lasku)
    {
        //lähettää laskun asiakkaalle ???
    }
    
    /**
     * tarkistaEra.
     * <p>Palauttaa laskun eräpäivän ja nykyisen päivän eron päivinä. 
     * Jos lasku on myöhässä palautusarvo on negatiivinen.
     * Jos lasku on myöhässä yli 30 päivää palauttaa -31 (karhulaskun aikaraja).
     * 
     * @param nykyinen nykyinen aika
     * @return ero nykyisen päivän ja eräpäivän ero tai -31
     */
    
    // TÄMÄ ON VIELÄ KESKEN PALJON
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
        /* tässä tarkistetaan jos eräpv on joulukuu ja nyt on tammikuu */
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
     * Palauttaa tosi jos laskutusluettelo on tyhjä, muuten epätosi.
     * <p>Tätä käytetään hyväksi pääohjelman ajossa.
     * 
     * @return true     jos laskutustiedosto on tyhjä, muuten false
     * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/Vector.html">Vector</a>
     */
    public boolean onTyhja() {
        if (laskut.isEmpty())
            return true;
        else return false;
    }
    
    /**
     * palautaViimeinen.
     * <p>Palauttaa laskuista järjestykseltään viimeisen.
     * Tätä käytetään pääohjelman ajossa.
     * 
     * @return laskut.lastElement()     vektorin viimeinen elementti
     * @see <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/Vector.html">Vector</a>
     */
    public Lasku palautaViimeinen() {
        return laskut.lastElement();
    }
    
    /**
     * tulostaLasku.
     * <p>Tulostaa laskunumeron perusteella haetun laskun. Lasku etsitään käyttäen
     * haeLasku metodia.
     * <p>Jos laskua ei löydy ilmoittaa virheellisestä laskunumerosta.
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
     * Tulostaa kaikki laskut. Jos laskutusluettelo on tyhjä (tarkistetaan onTyhjä metodilla)
     * ilmoittaa ettei laskuja ole. Muuten käy silmukan avulla kaikki laskut läpi ja tulostaa
     * jokaisen käyttäen Laskut-luokan toString metodia.
     * Mikäli tulostamisessa on ongelmia, ilmoittaa virheellä.
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
            System.out.println("Laskuja ei ole tai niitä ei voida tulostaa.");
        }
    }
}
