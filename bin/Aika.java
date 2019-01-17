import java.io.*;
import java.util.*;
/**
 * Aika.java
 * <p>Luokka p‰iv‰m‰‰r‰n k‰sittelemiseen ja muokkaamiseen.
 * Sis‰lt‰‰ mm metodit p‰iv‰m‰‰r‰n oikeellisuuden tarkistamiseen ja tietyn kuukauden
 * p‰ivien m‰‰r‰n ilmoittamiseen.
 * 
 * @author Henri Tanskanen 
 * @version 19.4.2007
 */
public class Aika implements Serializable {

    /* luokan muuttujat */
    private int pv,kk,vu;

    /**
     * Aika
     * <p>Luokan alustaja. Lue uuden Aika-olion annetuilla tiedoilla.
     * 
     * @param   pv  p‰iv‰
     * @param   kk  kuukausi
     * @param   vu  vuosi
     */
    public Aika(int pv, int kk, int vu) {
            this.pv=pv;
            this.kk=kk;
            this.vu=vu;
    }

    /**
     * tarkistaPvm
     * <p>Tarkistaa p‰iv‰m‰‰r‰n kelvollisuuden. Saa syˆtteen‰‰n p‰iv‰m‰‰r‰n tiedot
     * ja tarkistaa ett‰ p‰iv‰m‰‰r‰ on kelvollinen. Tarkistaa karkausvuodet,
     * p‰ivien m‰‰r‰t jokaisessa kuukaudessa, kuukauden ja vuoden oikeellisuuden.
     * Palauttaa tiedon p‰iv‰m‰‰r‰n oikeellisuudesta tosi tai ep‰tosi.
     * 
     * @param   pv  p‰iv‰
     * @param   kk  kuukausi
     * @param   vu  vuosi
     * 
     * @return true     jos p‰iv‰m‰‰r‰ on kelvollinen, muuten false
     */
    public static boolean tarkistaPvm(int pv, int kk, int vu) {
        boolean kelpo=false;
        
        if (vu >= 2007) {
            if (kk >=1 && kk <=12) {
                if (kk==2) {
                    if (onKarkaus(vu) && (pv>=1 && pv <=29))
                        kelpo=true;
                    else if (!onKarkaus(vu) && (pv>=1 && pv <=28))
                        kelpo=true;
                }
                else if ((kk==1 || kk==3 || kk==5 || kk==7 || kk==8 || kk==10 
                            || kk==12) && (pv>=1 && pv<=31)) 
                    kelpo=true;
                else if ((kk==4 || kk==6 || kk==9 || kk==11) && (pv>=1 && pv<=30)) 
                    kelpo=true;
                }
                else kelpo=false;
            }
        else kelpo = false;
        return kelpo;
    }
    
    /**
     * onKarkaus
     * <p>Tarkistaa onko syˆtteen‰‰n saama vuosi karkausvuosi, palauttaa totuusarvon
     * 
     * @param   vu      vuosi
     * @return  true    jos vuosi on karkausvuosi, muuten false
     */
    public static boolean onKarkaus(int vu) {
        
        if ((vu % 4 == 0) && (vu % 400 == 0)) 
            return true; 
        else if ((vu % 4 == 0) && (vu % 100 != 0)) 
            return true;
        else return false;
    }
    
    /**
     * paiviaKk
     * <p>Tarkistaa ja palauttaa syˆtteen‰ saamansa kuukauden p‰ivien m‰‰r‰n
     * Saa syˆtteen‰‰n myˆs vuoden karkausvuoden tarkistamiseen.
     * T‰t‰ k‰ytet‰‰n laskun er‰p‰iv‰n tarkistamiseen luokassa Lasku
     * 
     * @param   kk        kuukausi jonka p‰ivien m‰‰r‰ halutaan
     * @param   vu        vuosi, karkausvuoden tarkistamiseen
     * @return  paivia    halutun kuukauden p‰ivien m‰‰r‰
     */
    public static int paiviaKk(int kk, int vuosi) {
        int paivia = 0;
        if (kk >=1 && kk <=12) {
                if (kk==2) {
                    if (onKarkaus(vuosi)) paivia=29;
                    else if (!onKarkaus(vuosi)) paivia=28;
                }
                else if (kk==1 || kk==3 || kk==5 || kk==7 || kk==8 || kk==10 || kk==12)
                    paivia=31;
                else if (kk==4 || kk==6 || kk==9 || kk==11) 
                    paivia=30;
                }
        else paivia=0;
        return paivia;
        }
    
    public int laskeEroTaydetKk(Aika alku, Aika loppu) {
        int kuukaudet = 0;
        kuukaudet = (loppu.vu - alku.vu) * 12;
        return kuukaudet;
    }
        
    /**
     * palautaPaiva
     * <p>Palauttaa p‰iv‰n.
     * 
     * @return this.pv
     */
    public int palautaPv() {
        return this.pv;
    }
    
    /**
     * palautaKk
     * <p>Palauttaa kuukauden.
     * 
     * @return this.kk
     */
    public int palautaKk() {
        return this.kk;
    }
    
    /**
     * palautaVu
     * <p>Palauttaa vuoden.
     * 
     * @return this.vu
     */
    public int palautaVu() {
        return this.vu;
    }

    /**
     * toString
     * <p>Palauttaa p‰iv‰m‰‰r‰n yhten‰ merkkijonona.
     * 
     */
    public String toString() {
        return this.pv+"."+this.kk+"."+this.vu;
    }
}
