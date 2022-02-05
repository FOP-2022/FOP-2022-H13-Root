package h13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * @author Ruben Deisenroth
 */
public class MainFrame extends JFrame {
    private final MyPanel panel = new MyPanel();

    public MainFrame() {
        super("H13");
    }

    public MyPanel getPanel() {
        return panel;
    }

    public void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        repaint();
        invalidate();
        setLocationRelativeTo(null);

        setVisible(true);
        setFocusable(true);
        requestFocus();

        System.out.println("zf:" + getBounds().toString());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11)
                    setExtendedState(
                            getExtendedState() == Frame.MAXIMIZED_BOTH ? Frame.NORMAL
                                    : Frame.MAXIMIZED_BOTH);
                else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
                    System.out.println("plus");
                    panel.setZoom(Math.min(panel.getZoom() + 0.2, 1));
                    panel.repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    System.out.println("minus");
                    panel.setZoom(Math.max(panel.getZoom() - 0.2, 0.2));
                    panel.repaint();
                }
            }
        });
    }
}
