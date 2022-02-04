package h13;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Ruben Deisenroth
 */
public class Steuerungsfenster extends JFrame {
    private Zeichenfenster zf;
    private JButton addElipseButton = new JButton("Add ellipse");
    private JButton addRectangleButton = new JButton("Add rectangle");
    private JButton addStringButton = new JButton("Add string");
    private JButton removeElipseButton = new JButton("Remove ellipse");
    private JButton removeRectangleButton = new JButton("Remove rectangle");
    private JButton removeStringButton = new JButton("Remove string");
    private JButton exitButton = new JButton("Exit");

    public Steuerungsfenster(Zeichenfenster zf) {
        super("Steuerungsfenster");
        this.zf = zf;
    }

    public void init() {

        setLayout(new GridLayout(3, 3));
        add(addElipseButton);
        add(addRectangleButton);
        add(addStringButton);
        add(removeElipseButton);
        add(removeRectangleButton);
        add(removeStringButton);
        add(new JPanel());
        add(new JPanel());
        add(exitButton);

        MyCanvas mc = zf.getCanvas();

        // Listeners
        addElipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.addGreenEllipse();
                mc.repaint();
            }
        });
        addRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.addYellowRectangle();
                mc.repaint();
            }
        });
        addStringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.addBlueString();
                mc.repaint();
            }
        });
        removeElipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.removeGreenEllipse();
                mc.repaint();
            }
        });
        removeRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.removeYellowRectangle();
                mc.repaint();
            }
        });
        removeStringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mc.removeBlueString();
                mc.repaint();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        repaint();
        invalidate();
        setVisible(true);
        setFocusable(true);
        requestFocus();
        setLocationRelativeTo(null);
    }
}
