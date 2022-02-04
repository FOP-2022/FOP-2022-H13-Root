package h13;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * @author Ruben Deisenroth
 */
public class TwoSimpleWindows {
    // FlatDarkLaf.setup();
    static Zeichenfenster zeichenfenster = new Zeichenfenster();
    static Steuerungsfenster steuerungsfenster = new Steuerungsfenster(zeichenfenster);

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Zeichenfenster zeichenfenster = new Zeichenfenster();
                Steuerungsfenster steuerungsfenster = new Steuerungsfenster(zeichenfenster);
                zeichenfenster.init();
                steuerungsfenster.init();
            }
        });
    }
}
