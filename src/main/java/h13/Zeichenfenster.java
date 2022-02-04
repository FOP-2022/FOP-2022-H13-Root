package h13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * @author Ruben Deisenroth
 */
public class Zeichenfenster extends JFrame {
    private final MyCanvas canvas = new MyCanvas();

    public Zeichenfenster() {
        super("H13");
    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    public void init() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 300));
        setLayout(new BorderLayout());
        // var contentPanel = new JPanel(new BorderLayout());
        // add(contentPanel, BorderLayout.CENTER);
        canvas.setBackground(new Color(0f, 0f, 0f, 0f));
        // contentPanel.add(canvas);
        add(canvas, BorderLayout.CENTER);
        // add(new JButton("test"), BorderLayout.CENTER);
        pack();
        repaint();
        invalidate();
        setLocationRelativeTo(null);

        setVisible(true);
        setFocusable(true);
        requestFocus();
        // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // System.out.println(dim.toString());
        // this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // this.setLocation(1920,1080);
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
                    canvas.setZoom(Math.min(canvas.getZoom() + 0.2, 1));
                    canvas.repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    System.out.println("minus");
                    canvas.setZoom(Math.max(canvas.getZoom() - 0.2, 0.2));
                    canvas.repaint();
                }
            }
        });
    }
}
