import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * klasa sterująca ruchami komputera
 */

public class Komputer {
    /**
     * zmienna odpowiedzialna za tworzenie przycisku pionka (graficznie)
     */
    public static Image img;
    /**
     * zmienna odpowiedzialna za tworzenie przycisku damki (graficznie)
     */
    public static Image damka1;

    /**
     * zmienna określająca ruch komputera
     */
    public static int XfinalnyOd;
    /**
     * zmienna określająca ruch komputera
     */
    public static int XfinalnyDo;
    /**
     * zmienna określająca ruch komputera
     */
    public static int YfinalnyOd;
    /**
     * zmienna określająca ruch komputera
     */
    public static int YfinalnyDo;
    /**
     * zmienna określająca, który ruch jest najbardziej opłacalny
     */
    public static int max;


    /**
     * metoda wylicza, który ruch jest najbardziej opłacalny
     */
    public static void najlepszyRuch(){
        max = 0;
        int XFrom, YFrom;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                XFrom = j;
                YFrom = i;
                if(i%2 == 0) {
                    if (SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == 1) {
                        liczeniePionyZParzystego(XFrom, YFrom);
                    }
                    else if (SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == 3) {
                        liczenieDamkaZParzystego(XFrom, YFrom);
                    }
                }
                else{
                    if(SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == 1){
                        liczeniePionZNieparzystego(XFrom, YFrom);
                    }
                    else if (SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == 3) {
                        liczenieDamkiZNieparzystego(XFrom, YFrom);
                    }
                }
            }
        }
    }


    /**
     * metoda licząca wartość ruchu dla pionów
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    public static void liczenieDlaPionow(int XFrom, int YFrom, int XTo, int YTo){
        int punkty = 0;
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 1;
        if(YTo == 7) SzachownicaGrafika.tablicaWartości[XTo][YTo] = 3;
        for(int w = 0; w < 8; w++){
            for(int e = 0; e < 4; e++){
                if(SzachownicaGrafika.tablicaWartości[e][w] == 1) {
                    punkty += 5;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
                if(SzachownicaGrafika.tablicaWartości[e][w] == 3){
                    punkty += 50;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
            }
        }
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 1;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 0;
        if(max < punkty){
            max = punkty;
            XfinalnyOd = XFrom;
            YfinalnyOd = YFrom;
            XfinalnyDo = XTo;
            YfinalnyDo = YTo;
        }
    }

    /**
     * metoda licząca wartość ruchu dla damek
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    public static void liczenieDlaDamek(int XFrom, int YFrom, int XTo, int YTo){
        int punkty = 0;
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 3;

        for(int w = 0; w < 8; w++){
            for(int e = 0; e < 4; e++){
                if(SzachownicaGrafika.tablicaWartości[e][w] == 1) {
                    punkty += 5;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
                if(SzachownicaGrafika.tablicaWartości[e][w] == 3){
                    punkty += 50;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
            }
        }
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 3;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 0;
        if(max < punkty){
            max = punkty;
            XfinalnyOd = XFrom;
            YfinalnyOd = YFrom;
            XfinalnyDo = XTo;
            YfinalnyDo = YTo;
        }
    }

    /**
     * metoda licząca wartość bicia dla pionów
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    public static void liczenieDlaPionowBicie(int XFrom, int YFrom, int XTo, int YTo, int xZbity, int yZbity){
        int punkty = 0;
        int temp = SzachownicaGrafika.tablicaWartości[xZbity][yZbity];
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
        SzachownicaGrafika.tablicaWartości[xZbity][yZbity] = 0;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 1;
        if(YTo == 7) SzachownicaGrafika.tablicaWartości[XTo][YTo] = 3;
        punkty = 30;
        for(int w = 0; w < 8; w++){
            for(int e = 0; e < 4; e++){
                if(SzachownicaGrafika.tablicaWartości[e][w] == 1) {
                    punkty += 5;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
                if(SzachownicaGrafika.tablicaWartości[e][w] == 3){
                    punkty += 50;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
            }
        }
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 1;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 0;
        SzachownicaGrafika.tablicaWartości[xZbity][yZbity] = temp;
        if(max < punkty){
            max = punkty;
            XfinalnyOd = XFrom;
            YfinalnyOd = YFrom;
            XfinalnyDo = XTo;
            YfinalnyDo = YTo;
        }
    }

    /**
     * metoda licząca wartość bicia dla damek
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    public static void liczenieDlaDamekBicie(int XFrom, int YFrom, int XTo, int YTo, int xZbity, int yZbity){
        int punkty = 0;
        int temp = SzachownicaGrafika.tablicaWartości[xZbity][yZbity];
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
        SzachownicaGrafika.tablicaWartości[xZbity][yZbity] = 0;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 3;
        punkty = 30;
        for(int w = 0; w < 8; w++){
            for(int e = 0; e < 4; e++){
                if(SzachownicaGrafika.tablicaWartości[e][w] == 1) {
                    punkty += 5;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
                if(SzachownicaGrafika.tablicaWartości[e][w] == 3){
                    punkty += 50;
                    if((e == 0 && w%2 == 0) || (e == 3 && w%2 == 1) || w == 0 || w == 7) punkty += 4;
                    else if((e == 0 && w%2 == 1) || (e == 3 && w%2 == 0) || w == 1 || w == 6) punkty += 3;
                }
            }
        }
        SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 3;
        SzachownicaGrafika.tablicaWartości[XTo][YTo] = 0;
        SzachownicaGrafika.tablicaWartości[xZbity][yZbity] = temp;
        if(max < punkty){
            max = punkty;
            XfinalnyOd = XFrom;
            YfinalnyOd = YFrom;
            XfinalnyDo = XTo;
            YfinalnyDo = YTo;
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie ruchu
     */
    public static void ruch(){

        int XFrom = XfinalnyOd;
        int YFrom = YfinalnyOd;
        int XTo = XfinalnyDo;
        int YTo = YfinalnyDo;

    ruchPionemZPola(XFrom,YFrom,XTo,YTo);
    biciePionemZPola(XFrom,YFrom,XTo,YTo);
    ruchDamkąZPola(XFrom,YFrom,XTo,YTo);
    bicieDamkąZPola(XFrom,YFrom,XTo,YTo);
    }

    /**
     * ,etoda odpowiedzialna za wykonanie ruchu pionem
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void ruchPionemZPola(int XFrom, int YFrom, int XTo, int YTo){
//ruch pionem z parzystego
        if (czyJestParzyste(XFrom,YFrom,1)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 1 && (XTo == XFrom - 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                if (YTo == 7) {
                    tworzenieDamki(XFrom,YFrom,XTo,YTo);
                }
                else {
                    ruchPionem(XFrom,YFrom,XTo,YTo);
                }
            }

//ruch z nieparzystego
        }
        else if (czyJestNieparzyste(XFrom,YFrom,1)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 1 && (XTo == XFrom + 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                ruchPionem(XFrom,YFrom,XTo,YTo);
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia pionem
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void biciePionemZPola(int XFrom, int YFrom, int XTo, int YTo){
//lewo z parzystego
        if (czyJestParzyste(XFrom,YFrom,1)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 4)) {
                ruchPionaZBiciemZParzystegoLewa(XFrom, YFrom,  XTo,  YTo);
            }
//prawo z parzystego
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4)) {
                ruchPionaZBiciemZParzystegoPrawaNieparzystegoLewa(XFrom,YFrom,XTo,YTo);
            }
        }
//lewo z nieparzystego
        if (czyJestNieparzyste(XFrom,YFrom,1)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4)) {
                if (YTo == 7) {
                    tworzenieDamkiZBiciemZNieparzystegoLewa(XFrom,YFrom,XTo,YTo);
                }
                else {
                    ruchPionaZBiciemZParzystegoPrawaNieparzystegoLewa(XFrom,YFrom,XTo,YTo);
                }
            }
//prawo z nieparzystego
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 4)) {
                if (YTo == 7) {
                    tworzenieDamkiZBiciemZNieparzystegoPrawa(XFrom,YFrom,XTo,YTo);
                }
                else {
                    ruchPionaZBiciemZNieparzystegoPrawa(XFrom,YFrom,XTo,YTo);
                }
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykoannie ruchu pionem
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void ruchPionem(int XFrom, int YFrom, int XTo, int YTo){
        try {
            img = ImageIO.read(SzachownicaGrafika.class.getResource("pionCzerwony.bmp"));
            SzachownicaGrafika.tablica[XTo][YTo].setIcon(new ImageIcon(img));
            SzachownicaGrafika.tablica[XFrom][YFrom].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
            SzachownicaGrafika.tablicaWartości[XTo][YTo] = 1;
            SzachownicaGrafika.czyKolejKomputera = false;

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia pionem z parzystego lewa
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void ruchPionaZBiciemZParzystegoLewa(int XFrom, int YFrom, int XTo, int YTo){
           ruchPionem(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom - 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia pionem zparzystego prawa i nieparzystego lewa
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void ruchPionaZBiciemZParzystegoPrawaNieparzystegoLewa(int XFrom, int YFrom, int XTo, int YTo){
            ruchPionem(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia pionem z nieparzystego prawa
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void ruchPionaZBiciemZNieparzystegoPrawa(int XFrom, int YFrom, int XTo, int YTo){
            ruchPionem(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom + 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if (!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za utworzenie damki
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void tworzenieDamki(int XFrom, int YFrom, int XTo, int YTo){
        try {
            damka1 = ImageIO.read(SzachownicaGrafika.class.getResource("damkaS.bmp"));
            SzachownicaGrafika.tablica[XTo][YTo].setIcon(new ImageIcon(damka1));
            SzachownicaGrafika.tablicaWartości[XTo][YTo] = 3;
            SzachownicaGrafika.tablica[XFrom][YFrom].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
            SzachownicaGrafika.czyKolejKomputera = false;

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * metoda odpowiedzialna za tworzenie damki, po wykonaniu bicia pionem z nieparzystego lewa
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void tworzenieDamkiZBiciemZNieparzystegoLewa(int XFrom, int YFrom, int XTo, int YTo){
            tworzenieDamki(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] = 0;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za tworzenie damki, po wykonaniu bicia pionem z nieparzystego prawa
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void tworzenieDamkiZBiciemZNieparzystegoPrawa(int XFrom, int YFrom, int XTo, int YTo){
            tworzenieDamki(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom + 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] = 0;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za ruch damką
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void ruchDamkąZPola(int XFrom, int YFrom, int XTo, int YTo){
//ruch z parzystego
        if (czyJestParzyste(XFrom,YFrom,3)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && (YTo == YFrom + 1 || YTo == YFrom - 1) && (XTo == XFrom - 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                ruchDamką(XFrom,YFrom,XTo,YTo);
            }
//ruch z nieparzystego
        } else if (czyJestNieparzyste(XFrom,YFrom,3)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && (YTo == YFrom + 1 || YTo == YFrom - 1) && (XTo == XFrom + 1 || XTo == XFrom) && !SzachownicaGrafika.przymusoweBicie) {
                ruchDamką(XFrom,YFrom,XTo,YTo);
            }
        }
    }

    /**
     * metoda odpowiedzialna za bicie damką
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZPola(int XFrom, int YFrom, int XTo, int YTo){

//lewo z parzystego dół
        if (czyJestParzyste(XFrom,YFrom,3)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 4)) {
                bicieDamkąZLewegoParzystegoDół(XFrom,YFrom,XTo,YTo);
            }
//prawo z parzystego dół
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4)) {
                bicieDamkąZPrawegoParzystegoDółLewegoNieparzystegoDół(XFrom,YFrom,XTo,YTo);
            }
        }
//parzyste lewo góra
        if (czyJestParzyste(XFrom,YFrom,3)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 4)) {
                bicieDamkąZLewegoParzystegoGóra(XFrom,YFrom,XTo,YTo);
            }
//parzyste prawo góra
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 4)) {
                bicieDamkąZPrawegoParzystegoGóraLewegoNieparzystegoGóra(XFrom,YFrom,XTo,YTo);
            }
        }
//lewo z nieparzystego dół
        if (czyJestNieparzyste(XFrom,YFrom,3)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4)) {
                bicieDamkąZPrawegoParzystegoDółLewegoNieparzystegoDół(XFrom,YFrom,XTo,YTo);
            }
//prawo z nieparzystego dół
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom + 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 4)) {
                bicieDamkąZPrawegoNieparzystegoDół(XFrom,YFrom,XTo,YTo);
            }
        }
//lewo z nieparzystego góra
        if (czyJestNieparzyste(XFrom,YFrom,3)) {
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom - 1 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 4)) {
                bicieDamkąZPrawegoParzystegoGóraLewegoNieparzystegoGóra(XFrom,YFrom,XTo,YTo);
            }
//nieparzyste prawo góra
            if (SzachownicaGrafika.tablicaWartości[XTo][YTo] == 0 && YTo == YFrom - 2 && XTo == XFrom + 1 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 2) || (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 4)) {
                bicieDamkąZPrawegoNieparzystegoGóra(XFrom,YFrom,XTo,YTo);
            }
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie ruchu damką
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void  ruchDamką(int XFrom, int YFrom, int XTo, int YTo){
        try {
            damka1 = ImageIO.read(SzachownicaGrafika.class.getResource("damkaS.bmp"));
            SzachownicaGrafika.tablica[XTo][YTo].setIcon(new ImageIcon(damka1));
            SzachownicaGrafika.tablica[XFrom][YFrom].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom] = 0;
            SzachownicaGrafika.tablicaWartości[XTo][YTo] = 3;
            SzachownicaGrafika.czyKolejKomputera = false;

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z lewej parzystej góry
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZLewegoParzystegoGóra(int XFrom,int YFrom,int XTo,int YTo){
            ruchDamką(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom - 1][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawej parzystej góry i lewej nieparzystej góry
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZPrawegoParzystegoGóraLewegoNieparzystegoGóra(int XFrom,int YFrom,int XTo,int YTo){
            ruchDamką(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z lewego nieparzystego dołu
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZLewegoParzystegoDół(int XFrom,int YFrom,int XTo,int YTo){
            ruchDamką(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom - 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawego parzystego dołu i lewego nieparzystego dołu
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZPrawegoParzystegoDółLewegoNieparzystegoDół(int XFrom,int YFrom,int XTo,int YTo){
            ruchDamką(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;

    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawej nieparzystej góry
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZPrawegoNieparzystegoGóra(int XFrom,int YFrom,int XTo,int YTo){
            ruchDamką(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom + 1][YFrom - 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za wykonanie bicia damką z prawego nieparzystego dołu
     * @param XFrom współrzędna początkowe przycisku
     * @param YFrom współrzędna początkowe przycisku
     * @param XTo współrzędna końcowe przycisku
     * @param YTo współrzędna końcowe przycisku
     */
    static void bicieDamkąZPrawegoNieparzystegoDół(int XFrom,int YFrom,int XTo,int YTo){
            ruchDamką(XFrom,YFrom,XTo,YTo);
            SzachownicaGrafika.tablica[XFrom + 1][YFrom + 1].setIcon(null);
            SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] = 0;
            SzachownicaGrafika.czyKolejKomputera = true;
            if(!SzachownicaGrafika.czyJestBicie(XTo, YTo)) SzachownicaGrafika.czyKolejKomputera = false;
            SzachownicaGrafika.pionyBiale--;
    }

    /**
     * metoda odpowiedzialna za sprawdzenie, czy dane pole jest parzyste
     * @param XFrom współrzędna począkowe przycisku
     * @param YFrom współrzędna począkowe przycisku
     * @param wartośćPola wartość, któa określa czy na danym polu jest pion czy damka
     * @return
     */
    static boolean czyJestParzyste(int XFrom, int YFrom, int wartośćPola){
        if(YFrom % 2 == 0 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == wartośćPola) return true;
        else return false;
    }

    /**
     * metoda odpowiedzialna za sprawdzenie, czy dane pole jest nieparzyste
     * @param XFrom współrzędna począkowe przycisku
     * @param YFrom współrzędna począkowe przycisku
     * @param wartośćPola wartość, któa określa czy na danym polu jest pion czy damka
     * @return
     */
    static boolean czyJestNieparzyste(int XFrom, int YFrom, int wartośćPola){
        if(YFrom % 2 == 1 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom] == wartośćPola) return true;
        else return false;
    }

    /**
     * metoda odpowiedzialna za wylicznie wartości ruchu dla ruchu pionem z parzystego
     * @param XFrom współrzędna początkowa piona
     * @param YFrom współrzędna początkowa piona
     */
    static void liczeniePionyZParzystego(int XFrom, int YFrom){
//pion

// ruch lewo
            if (XFrom - 1 >= 0 && SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 0) {
                liczenieDlaPionow(XFrom, YFrom, XFrom - 1, YFrom + 1);
            }
//ruch prawo
            if (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 0) {
                liczenieDlaPionow(XFrom, YFrom, XFrom, YFrom + 1);
            }
//bicie lewo
            if (XFrom - 1 >= 0 && YFrom + 2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 2] == 0) {
                liczenieDlaPionowBicie(XFrom, YFrom, XFrom - 1, YFrom + 2, XFrom - 1, YFrom + 1);
            }
//bicie prawo
            if (XFrom + 1 <= 3 && YFrom + 2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 2] == 0) {
                liczenieDlaPionowBicie(XFrom, YFrom, XFrom + 1, YFrom + 2, XFrom, YFrom + 1);
            }

    }

    /**
     * meto odpowiedzialna za policzenie wartości ruchu dla ruchu damką z parzystego
     * @param XFrom współrzędna początkowa piona
     * @param YFrom współrzędna początkowa piona
     */
    static void liczenieDamkaZParzystego(int XFrom, int YFrom){
//damka
//ruch lewo dół
        if (XFrom - 1 >= 0 && SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 0) {
            liczenieDlaDamek(XFrom, YFrom, XFrom - 1, YFrom + 1);
        }
//ruch prawo dół
        if (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 0) {
            liczenieDlaDamek(XFrom, YFrom, XFrom, YFrom + 1);
        }
//ruch lewo góra
        if (XFrom - 1 >= 0 && YFrom - 1 >= 0 && SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 0) {
            liczenieDlaDamek(XFrom, YFrom, XFrom - 1, YFrom - 1);
        }
//ruch prawo góra
        if (YFrom - 1 >= 0 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 0) {
            liczenieDlaDamek(XFrom, YFrom, XFrom, YFrom - 1);
        }
//bicie  lewo dół
        if (XFrom - 1 >= 0 && YFrom + 2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom + 2] == 0) {
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom - 1, YFrom + 2, XFrom - 1, YFrom + 1);
        }
//bicie prawo dół
        if (XFrom + 1 <= 3 && YFrom + 2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 2] == 0) {
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom + 1, YFrom + 2, XFrom, YFrom + 1);
        }
//bicie lewo góra
        if(XFrom - 1 >= 0 && YFrom - 2 >= 0 && (SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom - 1][YFrom - 2] == 0){
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom-1, YFrom-2, XFrom-1, YFrom-1);
        }
//bicie prawo góra
        if(XFrom + 1 <= 3 && YFrom - 2 >= 0 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 2] == 0){
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom+1, YFrom-2, XFrom, YFrom-1);
        }
    }

    /**
     * metoda odpowiedzialna za policzenie wartości ruchu dla piona z nieparzystego
     * @param XFrom współrzędna początkowa piona
     * @param YFrom współrzędna początkowa piona
     */
    static void liczeniePionZNieparzystego(int XFrom, int YFrom){
//pion
//ruch lewo
        if(YFrom+1 < 8 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom+1] == 0){
            liczenieDlaPionow(XFrom, YFrom, XFrom, YFrom+1);
        }
//ruch prawo
        if(XFrom+1 < 4 && YFrom+1 < 8 && SzachownicaGrafika.tablicaWartości[XFrom+1][YFrom+1] == 0){
            liczenieDlaPionow(XFrom, YFrom, XFrom+1, YFrom+1);
        }
//bicie lewo
        if(XFrom-1 >= 0 && YFrom+2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom-1][YFrom+2] == 0){
            liczenieDlaPionowBicie(XFrom, YFrom, XFrom-1, YFrom+2, XFrom, YFrom+1);
        }
//bicie prawo
        if(XFrom+1 <= 3 && YFrom+2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom+1][YFrom+2] == 0){
            liczenieDlaPionowBicie(XFrom, YFrom, XFrom+1, YFrom+2, XFrom + 1, YFrom+1);
        }
    }

    /**
     * metoda odpowiedzialna za policzenie wartości ruchu damki z nieparzystego
     * @param XFrom współrzędna początkowa piona
     * @param YFrom współrzędna początkowa piona
     */
    static void liczenieDamkiZNieparzystego(int XFrom, int YFrom){
//damka
//ruch lewo dół
        if(YFrom+1 < 8 && SzachownicaGrafika.tablicaWartości[XFrom][YFrom+1] == 0){
            liczenieDlaPionow(XFrom, YFrom, XFrom, YFrom+1);
        }
//ruch prawo dół
        if(XFrom+1 < 4 && YFrom+1 < 8 && SzachownicaGrafika.tablicaWartości[XFrom+1][YFrom+1] == 0){
            liczenieDlaPionow(XFrom, YFrom, XFrom+1, YFrom+1);
        }
//ruch lewo góra
        if (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 0) {
            liczenieDlaDamek(XFrom, YFrom, XFrom, YFrom - 1);
        }
//ruch prawo góra
        if (XFrom + 1 <= 3 && SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 0) {
            liczenieDlaDamek(XFrom, YFrom, XFrom + 1, YFrom - 1);
        }
//bicie lewo dół
        if(XFrom-1 >= 0 && YFrom+2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom-1][YFrom+2] == 0){
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom-1, YFrom+2, XFrom, YFrom+1);
        }
//bicie prawo dół
        if(XFrom+1 <= 3 && YFrom+2 <= 7 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom + 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom+1][YFrom+2] == 0){
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom+1, YFrom+2, XFrom + 1, YFrom+1);
        }
//bicie lewo góra
        if(XFrom-1 >= 0 && YFrom-2 >= 0 && (SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom][YFrom - 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom-1][YFrom-2] == 0){
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom-1, YFrom-2, XFrom, YFrom-1);
        }
//bicie prawo góra
        if(XFrom+1 <= 3 && YFrom-2 >= 0 && (SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 2 || SzachownicaGrafika.tablicaWartości[XFrom + 1][YFrom - 1] == 4) && SzachownicaGrafika.tablicaWartości[XFrom+1][YFrom-2] == 0){
            liczenieDlaDamekBicie(XFrom, YFrom, XFrom+1, YFrom-2, XFrom + 1, YFrom-1);
        }
    }
}
