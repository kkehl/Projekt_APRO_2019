import javax.swing.*;

/**
 * klasa odpowiedzialna za funckjonowanie piona
 */
public class Przycisk extends JButton {
    /**
     * ta zmienna określa jaki pion znajduje się na danych współrzędnych (pion czy damka, należy do gracza, czy do komputera)
     */
    int pion;
    /**
     * ta zmienna określa współrzędą piona
     */
    int x;
    /**
     * ta zmienna określa współrzędą piona
     */
    int y;

    /**
     * konstruktor
     * @param pion wartość piona
     * @param x współrzędna piona
     * @param y wspłrzędna piona
     */
    public Przycisk(int pion, int x,int y){
        this.pion = pion;
        this.x = x;
        this.y = y;
    }


    /**
     * metoda odpowiedzialna za ustawianie pionów
     * @param pion wartość piona
     */
    public void setPion(int pion) {
        this.pion = pion;
    }


}
