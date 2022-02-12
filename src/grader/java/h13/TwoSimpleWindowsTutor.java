package h13;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * @author Ruben Deisenroth
 */
public class TwoSimpleWindowsTutor {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrameTutor zeichenfenster = new MainFrameTutor(new MyPanelTutor());
                ControlFrameTutor steuerungsfenster = new ControlFrameTutor(zeichenfenster);
                zeichenfenster.init();
                steuerungsfenster.init();
            }
        });
    }
}
