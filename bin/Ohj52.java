import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Ohj52.java. Ohjelmointity� nro 52, Yrityksen laskutus.
 * <p>Joensuun yliopisto, tietojenk�sittelytieteen laitos.
 * 
 * <p>T�m� luokka sis�lt�� p��ohjelman ja k�ytt�liittym�n. 
 * @author Henri Tanskanen
 * @version 19.4.2007
 */
public class Ohj52 {
    
    public static void Ohjelma() {
    }
    
    /**
     * main.
     * <p>P��ohjelma, sis�lt�� pelk�st��n ohjelman k�ytt�liittym�n.
     * T�ss� suoritetaan ohjelman ajoa.
     */
    public static void main(String[] args) {
        
        /* muuttujia k�ytt�liittym�n k�ytt�� varten. */
        int syote=1, tmp_laskunro=0;
        int laskunumero = 1000; 
        double summa, kulut;
        String tmp;
        Asiakas asiakas;
        Aika erapv, tamapv;
        Lasku lasku;
        
        /* Tyhj� laskutus-olio laskutustiedoston tyhjent�mist� ja vertailua varten. */
        Laskutus tyhja = new Laskutus();
        
        /* Varsinainen laskutus-olio jota k�ytet��n ohjelmassa. */
        Laskutus laskutus = new Laskutus();
        
        /* Ladataan laskutustiedosto. */
        laskutus = LueKirjoitaBin.lueBin();
        
        /* Kysyt��n k�ytt�j�lt� nykyinen aika. */
        System.out.print("********* LASKUTUS *********\nAnna nykyinen p�iv�m��r�: ");
        tamapv = Lue.lueAika();
        
        /* Tarkistetaan laskutustiedoston viimeisen laskun numero ja asetetaan laskunumeroksi
        seuraava luku, jos tiedosto on tyhj�, asetetaan 1000. Jos laskutustiedostoa ei voida
        lukea, suositellaan alustamaan laskutustiedosto */
        try {
        if (laskutus.onTyhja())
            laskunumero=1000;
        else
            laskunumero=laskutus.palautaViimeinen().palautaNumero() +1;
        } catch (Exception e) {
            System.out.print("Laskutustiedosto on virheellinen. \nJotta ohjelma toimii kunnolla, " +
                "laskutustiedosto on kannattaa alustaa. \nAlustetaanko nyt (k/e):");
            tmp = Lue.rivi();
            if (tmp.equals("k") || tmp.equals("K")) {
                LueKirjoitaBin.alustaBin(tyhja);
                laskutus = LueKirjoitaBin.lueBin();
                System.out.println("Laskutustiedosto on alustettu.");
                laskunumero=1000;
                }
             else
                System.out.println("VAROITUS! \nTiedostoa ei alustettu. " +
                    "\nLaskujen lis��misess� ja lukemisessa voi olla ongelmia.");
        }
        
        /* ALOITETAAN P��OHJELMA-SILMUKKA */
        while (syote!=0) {

            System.out.println("(1) LIS�� LASKU\t\t(2) POISTA LASKU\t(3) TULOSTA LASKU(T)\n"+
                "(4) TARKISTA LASKUT\t(5) TALLENNA LASKUT\t(6) ALUSTA LASKUTIEDOSTO\n"+
                "(0) LOPETA\t\nValintasi");
            syote = Lue.kluku();

            /* LASKUN LIS��MINEN */
            if (syote == 1) {
                System.out.println("Anna asiakastiedot: ");
                    asiakas = Lue.lueAsiakas();
                System.out.print("Anna laskun summa: ");
                    summa = Lue.dluku();
                System.out.print("Anna er�p�iv�: ");
                    erapv = Lue.lueAika();
                kulut = 0.0;
                
                laskutus.lisaaLasku(lasku = new Lasku(laskunumero, asiakas, summa, erapv, kulut));
                laskunumero++;
            }

            /* LASKUN POISTAMINEN */
            else if (syote==2) {
                System.out.print("Anna poistettavan laskun numero (nelinumeroinen): ");
                tmp_laskunro = Lue.kluku();
                laskutus.poistaLasku(tmp_laskunro);
            }
            
            /* LASKUN TULOSTUS */
            else if (syote==3) {
                System.out.print("Anna laskun numero (anna 0 jos haluat tulostaa kaikki): ");
                tmp_laskunro = Lue.kluku();
                if (tmp_laskunro==0)
                    laskutus.tulostaLaskut();
                else laskutus.tulostaLasku(tmp_laskunro);
            }
            
            /* LASKUJEN TARKISTUS */
            else if (syote==4) {
                int aikaa_jaljella=-99;
                System.out.print("Anna laskun numero (anna 0 jos haluat tarkistaa kaikki): ");
                tmp_laskunro = Lue.kluku();
                if (tmp_laskunro==0)
                    System.out.println("Kaikkien laskujen tarkistus ei viel� toimi.");
                else 
                aikaa_jaljella = laskutus.tarkistaEra(tmp_laskunro, tamapv);
                
                /* T�h�n toimintoja jos lasku on my�h�ss� */
                System.out.println("Laskun er�p�iv��n on aikaa " + aikaa_jaljella +" p�iv��.");
            }
            
            /* LASKUJEN TALLENNUS */
            else if (syote == 5)
                LueKirjoitaBin.kirjoitaBin(laskutus);
            
            /* LASKUTIEDOSTON TYHJENNYS */
            else if (syote == 6) {
                System.out.print("Alustetaanko laskutustiedosto (kaikki laskut menetet��n)(k/e): ");
                tmp = Lue.rivi();
                if (tmp.equals("K") || tmp.equals("k")) {
                    LueKirjoitaBin.alustaBin(tyhja);
                    laskutus = LueKirjoitaBin.lueBin();
                    System.out.println("Laskutustiedosto on alustettu.");
                    laskunumero=1000;
                }
                else
                    System.out.println("Laskutustiedostoa ei alustettu.");
            }
            
            /* LOPETUS JA TALLENNUS */
            else if (syote == 0) {
                System.out.print("Tallennetaanko laskut (k/e): ");
                tmp = Lue.rivi();
                if (tmp.equals("e") || tmp.equals("E"))
                    System.out.println("Ohjelma lopetettiin. Laskuja ei tallennettu.");
                else {
                    System.out.println("Lopetettiin ohjelma ja tallennettiin laskut.");
                    LueKirjoitaBin.kirjoitaBin(laskutus);
                }
            }
            else
                System.out.println("Sy�tteesi ei ole kelvollinen. Lopeta antamalla 0 (nolla)");
        }
        /* P��OHJELMA-SILMUKKA P��TTYY */
        
    }
    
}
