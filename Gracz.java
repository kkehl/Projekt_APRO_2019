import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


/**
 * klasa do sterowania guzikami przez gracza
 */
public class Gracz {
    /**
     * zmienna odpowiedzialna za tworzenie przycisku pionka (graficznie)
     */
    public static Image omg;
    /**
     * zmienna odpowiedzialna za tworzenie przycisku damki (graficznie)
     */
    public static Image damka2;

    /**
     * współrzędna guzika
     */
    public static int XFrom;
    /**
     * współrzędna guzika
     */
    public static int YFrom;

    /**
     * metoda odpowiedzialna za wykonanie ruchu
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    public static void ruch(int XTo, int YTo) {
        if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 2) {
            XFrom = XTo;
            YFrom = YTo;
        }
        ruchPionaZPola(XTo,  YTo);
        biciePionemZPola( XTo, YTo);
        if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 4) {
            XFrom = XTo;
            YFrom = YTo;
        }
        ruchDamkiZPola(XTo,YTo);
        bicieDamkąZPola(XTo,YTo);
    }

    /**
     * metoda odpowiedzialna za wykonanie ruchu pionem
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchPionaZPola(int XTo, int YTo){
        if (czyPoleParzyste(2)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 1 && (XTo == XFrom - 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                ruchPiona(XTo, YTo);
            }
        }
        else if (czyPoleNieparzyste(2)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 1 && (XTo == XFrom + 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                if (YTo == 0) {
                    tworzenieDamki(XTo, YTo);
                } else {
                    ruchPiona(XTo, YTo);
                }
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia pionem
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void biciePionemZPola(int XTo, int YTo){
//parzyste lewo
        if (czyPoleParzyste(2)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 3)) {
                if (YTo == 0) {
                    tworzenieDamkiZBiciemParzysteLewo(XTo, YTo);
                }
                else {
                    ruchPionaZBiciemParzysteLewo(XTo,YTo);
                }
            }
//parzyste prawo
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 3)) {
                if (YTo == 0) {
                    tworzenieDamkiZBIciemParzystePrawo(XTo,YTo);
                }
                else {
                    ruchPionaZBiciemParzystePrawoNieparzysteLewo(XTo,YTo);
                }
            }
        }
//nieparzyste lewo
        if (czyPoleNieparzyste(2)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom - 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 3)) {
                ruchPionaZBiciemParzystePrawoNieparzysteLewo(XTo,YTo);
            }
//nieparzyste prawo

            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 3)) {
                ruchPionaZBiciemNieparzystePrawo(XTo,YTo);
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie ruchu pionem
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchPiona(int XTo, int YTo) {
        try {
            omg = ImageIO.read(SzachownicaGrafika.class.getResource("pionBialy.bmp"));
            SzachownicaGrafika.tablica[XTo][YTo].setIcon(new ImageIcon(omg));
            SzachownicaGrafika.tablica[XFrom][YFrom].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
            SzachownicaGrafika.tablicaWartości[XTo][YTo] = 2;
            SzachownicaGrafika.czyKolejKomputera = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * metoda odpowiedzialn za wykonanie bicia pionem z parzystego lewa
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchPionaZBiciemParzysteLewo(int XTo, int YTo){
            ruchPiona(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom - 1][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia pionem z parzystego prawa i nieparzystego lewa
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchPionaZBiciemParzystePrawoNieparzysteLewo(int XTo, int YTo){
           ruchPiona(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie  bicia pionem z nieparzystego prawa
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchPionaZBiciemNieparzystePrawo(int XTo, int YTo){
            ruchPiona(XTo,YTo);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.tablica[XFrom + 1][YFrom - 1].setIcon(null);
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
     }

    /**
     * metoda odpowiedzialna za tworzenie damki
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void tworzenieDamki(int XTo, int YTo) {
        try {
            damka2 = ImageIO.read(SzachownicaGrafika.class.getResource("damkaP.bmp"));
            SzachownicaGrafika.tablica[XTo][YTo].setIcon(new ImageIcon(damka2));
            SzachownicaGrafika.tablicaWartości[XTo][YTo] = 4;
            SzachownicaGrafika.tablica[XFrom][YFrom].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * metoda odpowiedzilana za tworzenie damki, po wykonaniu bicia pionem z parzystego lewa
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void tworzenieDamkiZBiciemParzysteLewo(int XTo, int YTo) {
           tworzenieDamki(XTo,YTo);
            SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] = 0;
            SzachownicaGrafika.tablica[XFrom-1][YFrom -1].setIcon(null);
            SzachownicaGrafika.pionyCzerwone--;

    }

    /**
     * metoda odpowiedzialna za tworzenie damki po wykonaniu bicia pionem z parzystego prawa
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void tworzenieDamkiZBIciemParzystePrawo(int XTo,int YTo){
            tworzenieDamki(XTo,YTo);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] = 0;
            SzachownicaGrafika.tablica[XFrom][YFrom - 1].setIcon(null);
            SzachownicaGrafika.pionyCzerwone--;
    }

    /**
     * metoda odpowiedzialna za wykonanie ruchu damki
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchDamkiZPola(int XTo, int YTo){
 //ruch z pola parzystego
        if (czyPoleParzyste(4)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 &&  (YTo == YFrom - 1 || YTo == YFrom + 1) && (XTo == XFrom - 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                ruchDamki(XTo,YTo);
            }
//ruch z pola nieparzystego
        } else if (czyPoleNieparzyste(4)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && (YTo == YFrom - 1 || YTo == YFrom + 1) && (XTo == XFrom + 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                ruchDamki(XTo,YTo);
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąZPola(int XTo, int YTo){
//lewo z parzystego dół
        if (czyPoleParzyste(4)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom + 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 3)) {
                bicieDamkąLewoZParzystegoDół(XTo,YTo);
            }
//prawo z parzystego dół
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom + 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 3)) {
                bicieDamkąPrawoZParzystegoDółLewoNieparzysteDół( XTo, YTo);
            }
        }
//lewo z parzystego góra
        if (czyPoleParzyste(4)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom - 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 3)) {
                bicieDamkąLewoZParzystegoGóra(XTo,YTo);
            }
//prawo z parzystego góra
            if ((SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == 4) && (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0) && (SzachownicaGrafika.tablica[XTo][YTo] != SzachownicaGrafika.tablica[XFrom][YFrom]) && (YTo == YFrom - 2) && (XTo == XFrom + 1) && ((SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 1) || (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 3))) {
                bicieDamkąPrawoZParzystegoGóraLewoNieparzystegoGóra(XTo,YTo);
            }
        }
//lewo z nieparzystego dół
        if (czyPoleNieparzyste(4)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom + 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 3)) {
                bicieDamkąPrawoZParzystegoDółLewoNieparzysteDół(XTo,YTo);
            }
//prawo z nieparzystego dół
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom + 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 3)) {
                bicieDamkąPrawoZNieparzystegoDół(XTo,YTo);
            }
        }
//lewo z nieparzystego góra
        if (czyPoleNieparzyste(4)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 3)) {
                bicieDamkąPrawoZParzystegoGóraLewoNieparzystegoGóra(XTo,YTo);
            }
//prawo z nieparzystego góra
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0  && YTo == YFrom - 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 1 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 3)) {
                bicieDamkąPrawoZNiearzystegoGóra(XTo,YTo);
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie ruchu damki
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void ruchDamki (int XTo, int YTo){
        try {
            damka2 = ImageIO.read(SzachownicaGrafika.class.getResource("damkaP.bmp"));
            SzachownicaGrafika.tablica[XTo][YTo].setIcon(new ImageIcon(damka2));
            SzachownicaGrafika.tablica[XFrom][YFrom].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
            SzachownicaGrafika.tablicaWartości[XTo][YTo] = 4;
            SzachownicaGrafika.czyKolejKomputera = true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z lewego parzystego dołu
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąLewoZParzystegoDół(int XTo, int YTo){
           ruchDamki(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom - 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawego parzystego dołu
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąPrawoZParzystegoDółLewoNieparzysteDół(int XTo,int YTo){
            ruchDamki(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z lewej parzystej góry
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąLewoZParzystegoGóra(int XTo, int YTo) {
        ruchDamki(XTo, YTo);
        SzachownicaGrafika.tablica[XFrom - 1][YFrom - 1].setIcon(null);
        SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] = 0;
        SzachownicaGrafika.pionyCzerwone--;
        SzachownicaGrafika.czyKolejKomputera = false;
        if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawej parzystej góry i lewej nieparzystej góry
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąPrawoZParzystegoGóraLewoNieparzystegoGóra(int XTo, int YTo){
            ruchDamki(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawej nieparzystej góry
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąPrawoZNiearzystegoGóra(int XTo, int YTo){
            ruchDamki(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom + 1][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prewego nieparzystego dołu
     * @param XTo współrzędna guzika
     * @param YTo współrzędna guzika
     */
    static void bicieDamkąPrawoZNieparzystegoDół(int XTo, int YTo){
           ruchDamki(XTo,YTo);
            SzachownicaGrafika.tablica[XFrom + 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] = 0;
            SzachownicaGrafika.pionyCzerwone--;
            SzachownicaGrafika.czyKolejKomputera = false;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = true;
    }

    /**
     * metoda, która sprawdza, czy dane pole jest parzyste
     * @param wartośćPola określa czy ma to być pionek, czy damka
     * @return zwraca, czy pole jest parzyste
     */
    static boolean czyPoleParzyste(int wartośćPola){
        if (YFrom % 2 == 0 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == wartośćPola) return true;
        else  return false;
    }

    /**
     * metoda, która sprawdza, czy dane pole jest nieparzyste
     * @param wartośćPola określa czy ma to być pionek, czy damka
     * @return zwraca, czy pole jest nieparzyste
     */
    static boolean czyPoleNieparzyste(int wartośćPola){
        if (YFrom % 2 == 1 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == wartośćPola) return true;
        else  return false;
    }

}


