import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * klasa odpowiedzialna za funkcjonalność planszy
 */
public class SzachownicaGrafika extends JFrame implements ActionListener {

    /**
     * tablica przycisków
     */
    public static JButton[][] tablica;
    /**
     * tablica wartości
     */
    public static Integer[][] tablicaWartości;
    /**
     * ta zmienna określa, czy dany gracz musi wykonać bicie (bicia są przymusowe)
     */
    public static boolean przymusoweBicie;
    /**
     * określa czyja jest tura -gracza czy komputera
     */
    public static boolean czyKolejKomputera = false;
    /**
     * współrzędne, na które chcemy postawić pion
     */
    public static int XTo, YTo;
    /**
     * zmienna odpowiedzialna za wczytanie obrazów
     */
    public static Image omg;
    /**
     * zmienna odpowiedzialna za wczytanie obrazów
     */
    public static Image img;
    /**
     * licznik pionów czerwonych
     */
    public static int pionyCzerwone = 12;
    /**
     * licznik pionów białych
     */
    public static int pionyBiale = 12;

    /**
     * metoda odpowiedzialna za efekty graficzne naszej gry
     */
    public SzachownicaGrafika() {

        super("Warcaby");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(730, 755);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        tablica = new Przycisk[4][8];
        tablicaWartości = new Integer[4][8];
        int k = 0;
        int l = 0;
        for (int i = 0; i < 8; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7) {
                l = 90;
            }
            for (int j = 0; j < 4; j++) {
                Przycisk button = new Przycisk(0, j, i);
                button.setBounds(l, k, 90, 90);
                tablicaWartości[j][i] = 0;
                add(button);
                button.addActionListener(this);
                l = l + 180;
                button.setBackground(Color.BLACK);
                if (i == 0 || i == 1 || i == 2) {
                    try {
                        img = ImageIO.read(getClass().getResource("pionCzerwony.bmp"));
                        button.setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    button.setPion(1);
                    tablicaWartości[j][i] = 1;
                } else if (i == 5 | i == 6 | i == 7) {
                    try {
                        omg = ImageIO.read(getClass().getResource("pionBialy.bmp"));
                        button.setIcon(new ImageIcon(omg));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    button.setPion(2);
                    tablicaWartości[j][i] = 2;
                }
                button.setFocusPainted(false);
                tablica[j][i] = button;
            }
            l = 0;
            k = k + 90;
        }

    }

    /**
     * metoda określa, czy dany pion ma możliwość bicia
     * @param XFrom
     * @param YFrom
     * @return czy dany pion ma możliwość bicia
     */
    public static boolean czyJestBicie(int XFrom, int YFrom) {
        int gracz;
        gracz = tablicaWartości[XFrom][YFrom];
        int przeciwnik;
        if(gracz == 1 || gracz == 3) przeciwnik = 2;
        else przeciwnik = 1;
        if(YFrom%2 == 0){
            outer:
            {
                if (gracz == 1 || gracz == 3 || gracz == 4) {
                    if (YFrom == 6) break outer;
                    if (XFrom != 0 && (tablicaWartości[XFrom - 1][YFrom + 1] == przeciwnik || tablicaWartości[XFrom - 1][YFrom + 1] == przeciwnik + 2) && tablicaWartości[XFrom - 1][YFrom + 2] == 0) {
                        return true;
                    }
                    if (XFrom != 3 && (tablicaWartości[XFrom][YFrom + 1] == przeciwnik || tablicaWartości[XFrom][YFrom + 1] == przeciwnik + 2) && tablicaWartości[XFrom + 1][YFrom + 2] == 0) {
                        return true;
                    }
                }
            }
            if(gracz == 2 || gracz == 3 || gracz == 4){
                if(YFrom == 0) return  false;
                if(XFrom != 0 && (tablicaWartości[XFrom-1][YFrom-1] == przeciwnik || tablicaWartości[XFrom-1][YFrom-1] == przeciwnik + 2) && tablicaWartości[XFrom-1][YFrom-2] == 0){
                    return true;
                }
                if(XFrom != 3 && (tablicaWartości[XFrom][YFrom-1] == przeciwnik || tablicaWartości[XFrom][YFrom-1] == przeciwnik + 2) && tablicaWartości[XFrom+1][YFrom-2] == 0){
                    return true;
                }
            }
        }
        else{
            outer2:
            {
                if (gracz == 1 || gracz == 3 || gracz == 4) {
                    if (YFrom == 7) break outer2;
                    if (XFrom != 0 && (tablicaWartości[XFrom][YFrom + 1] == przeciwnik || tablicaWartości[XFrom][YFrom + 1] == przeciwnik + 2) && tablicaWartości[XFrom - 1][YFrom + 2] == 0) {
                        return true;
                    }
                    if (XFrom != 3 && (tablicaWartości[XFrom + 1][YFrom + 1] == przeciwnik || tablicaWartości[XFrom + 1][YFrom + 1] == przeciwnik + 2) && tablicaWartości[XFrom + 1][YFrom + 2] == 0) {
                        return true;
                    }
                }
            }
            if(gracz == 2 || gracz == 3 || gracz == 4){
                if(YFrom == 1) return false;
                if(XFrom != 0 && (tablicaWartości[XFrom][YFrom-1] == przeciwnik || tablicaWartości[XFrom][YFrom-1] == przeciwnik + 2) && tablicaWartości[XFrom-1][YFrom-2] == 0){
                    return true;
                }
                if(XFrom != 3 && (tablicaWartości[XFrom+1][YFrom-1] == przeciwnik || tablicaWartości[XFrom+1][YFrom-1] == przeciwnik +2) && tablicaWartości[XFrom+1][YFrom-2] ==0){
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * metoda sprawdza możliwość bicia dla wszystkich pionów danego gracza
     * @param gracz określa, czy kolej jest gracza czy komputera
     */
    public void czyJestBiciePlansza(int gracz) {
        przymusoweBicie = false;
        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 4; i++){
                if(tablicaWartości[i][j] == gracz || tablicaWartości[i][j] == gracz + 2){
                    if(czyJestBicie(i, j)){
                        przymusoweBicie = true;
                        return;
                    }
                }
            }
        }
    }

    /**
     * metoda sprawdza, który przycisk został nacisnięty przez użytkownika
     * @param e objekt typu ActionEvent
     */

    public static void czytanieZrodla(ActionEvent e){
        Object zrodlo = e.getSource();
        XTo = 0;
        YTo = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablica[j][i] == zrodlo) {
                    XTo = j;
                    YTo = i;
                    break;
                }

            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        czyJestBiciePlansza(2);
        czytanieZrodla(e);
        Gracz.ruch(XTo, YTo);
        if(pionyCzerwone==0){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    try {
                        omg = ImageIO.read(getClass().getResource("pionBialy.bmp"));
                        tablica[j][i].setIcon(new ImageIcon(omg));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
        while(czyKolejKomputera && pionyCzerwone != 0){
            Komputer.najlepszyRuch();
            czyJestBiciePlansza(1);
            Komputer.ruch();
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            {
                if (i == 1 | i == 3 | i == 5 | i == 7) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 4; j++)
                    System.out.print(tablicaWartości[j][i] + "  ");
            }
            System.out.println();
        }
        System.out.println(czyJestBicie(XTo, YTo));

        if(pionyBiale==0) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    try {
                        img = ImageIO.read(getClass().getResource("pionCzerwony.bmp"));
                        tablica[j][i].setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    }
}

