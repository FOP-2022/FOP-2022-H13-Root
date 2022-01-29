package h13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * @author Ruben Deisenroth
 */
public class TwoSimpleWindows {
    //  FlatDarkLaf.setup();
    static JFrame zeichenfenster = new JFrame();
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                zeichenfenster.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                zeichenfenster.setLayout(new BorderLayout());
                // var contentPanel = new JPanel(new BorderLayout());
                // zeichenfenster.add(contentPanel, BorderLayout.CENTER);
                var canvas = new MyCanvas();
                canvas.setBackground(new Color(0f, 0f, 0f, 0f));
                // contentPanel.add(canvas);
                zeichenfenster.add(canvas, BorderLayout.CENTER);
                // zeichenfenster.add(new JButton("test"), BorderLayout.CENTER);
                zeichenfenster.pack();
                zeichenfenster.repaint();
                zeichenfenster.invalidate();
                zeichenfenster.setVisible(true);
                zeichenfenster.setLocationRelativeTo(null);
                System.out.println("zf:" + zeichenfenster.getBounds().toString());
            }
        });
    }
}
