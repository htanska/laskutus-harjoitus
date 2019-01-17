import java.io.*;

/**
 * Asiakas.java
 * Sis‰lt‰‰ ja tarvittaessa palauttaa asiakkaasta nimi- ja osoitetiedot.
 * @author Henri Tanskanen 
 * @version 19.4.2007
 */
public class Asiakas implements Serializable {
    
    /* muuttujat asiakkaan tiedodoille. */
    private String nimi;
    private String osoite;

    /**
     * Asiakas.
     * <p>Luokan Asiakas alustaja. Luo uuden Asiakas-olion annetuilla tiedoilla.
     * 
     * @param   nm   asiakkaan nimi
     * @param   os   asiakkaan osoite
     */
    public Asiakas(String nm, String os) {
        // asetetaan asiakkaan tiedot
        this.nimi = nm;
        this.osoite = os;
    }

    /**
     * palautaNimi.
     * <p>Palauttaa asiakkaan nimen. 
     * 
     * @return  this.nimi   asiakkaan nimi
     */
    public String palautaNimi() {
        return this.nimi;
    }
    
    /**
     * palautaOsoite.
     * <p>Palauttaa asiakkaan osoitteen.
     * 
     * @return this.osoite  asiakkaan osoite
     */
    public String palautaOsoite() {
        return this.osoite;
    }
    
    /**
     * toString.
     * <p>Palauttaa asiakastiedot yhten‰ merkkijonona.
     */
    public String toString() {
        return this.nimi + ", " + this.osoite;
    }     

}
