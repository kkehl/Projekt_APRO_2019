import java.awt.*;

/**
 * metoda odpowiedzilana za testowanie naszego programu
 */
public class Test {

    /**
     * metoda main odpowiedzialna za testowanie naszego prgramu + klasa anonimowa
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new SzachownicaGrafika();
            }
        });
    }
}