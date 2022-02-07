package h13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * The Main Drawing Frame for H13 containing a {@link MyPanel}
 *
 * @author Ruben Deisenroth
 */
public class MainFrame extends JFrame {
    /**
     * The {@link MyPanel}-Object that draws the shapes
     */
    private final MyPanel panel;

    /**
     * Creates a new {@link MainFrame}
     */
    public MainFrame(MyPanel panel) {
        super("H13");
        this.panel = panel;
    }

    /**
     * Getter-Method for the {@link #zoom}-Field
     *
     * @return the value of the {@link #zoom}-Field
     */
    public MyPanel getPanel() {
        return panel;
    }

    /**
     * Initialize and Display the Frame
     */
    public void init() {
        // Frame Properties
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setLayout(new BorderLayout());

        // Add Components
        add(panel, BorderLayout.CENTER);

        // Add Listeners
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

        // Set Dimension and Position
        pack();
        repaint();
        invalidate();
        setLocationRelativeTo(null);

        // Show Frame
        setVisible(true);
        setFocusable(true);
        requestFocus();

        System.out.println("zf:" + getBounds().toString());

    }
}
