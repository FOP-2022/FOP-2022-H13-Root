package h13;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Ruben Deisenroth
 */
public class ControlFrame extends JFrame {
    private MainFrame zf;
    private JButton addElipseButton = new JButton("Add ellipse");
    private JButton addRectangleButton = new JButton("Add rectangle");
    private JButton addStringButton = new JButton("Add string");
    private JButton removeElipseButton = new JButton("Remove ellipse");
    private JButton removeRectangleButton = new JButton("Remove rectangle");
    private JButton removeStringButton = new JButton("Remove string");
    private JButton changeTransparencyButton = new JButton("Change transparency");
    private JButton changeSaturationButton = new JButton("Change saturation");
    private JButton changeBorderWidthButton = new JButton("Change border width");
    private JButton changeFontButton = new JButton("Change font");
    private JButton changeZoomButton = new JButton("Change zoom");
    private JButton exitButton = new JButton("Exit");
    private final PropertyChangeDialogue pcd = new PropertyChangeDialogue();

    public ControlFrame(MainFrame zf) {
        super("Steuerungsfenster");
        this.zf = zf;
    }

    public void init() {

        setLayout(new GridLayout(4, 3));
        add(addElipseButton);
        add(addRectangleButton);
        add(addStringButton);
        add(removeElipseButton);
        add(removeRectangleButton);
        add(removeStringButton);
        add(changeSaturationButton);
        add(changeTransparencyButton);
        add(changeBorderWidthButton);
        add(changeFontButton);
        add(changeZoomButton);
        add(exitButton);

        MyPanel mp = zf.getPanel();

        // Listeners
        addElipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.addGreenEllipse();
                mp.repaint();
            }
        });
        addRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.addYellowRectangle();
                mp.repaint();
            }
        });
        addStringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.addBlueString();
                mp.repaint();
            }
        });
        removeElipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.removeGreenEllipse();
                mp.repaint();
            }
        });
        removeRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.removeYellowRectangle();
                mp.repaint();
            }
        });
        removeStringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.removeBlueString();
                mp.repaint();
            }
        });
        changeSaturationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.init(
                        "Change saturation",
                        "Saturation (0-255)",
                        0,
                        1,
                        (int) (mp.getZoom() * 100),
                        true,
                        10,
                        50,
                        (n) -> {
                            mp.setZoom((double) n / 100);
                            mp.repaint();
                        });
            }
        });
        changeFontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.init(
                        "Change zoom",
                        "Zoom (%)",
                        1,
                        151,
                        (int) (mp.getZoom() * 100),
                        true,
                        10,
                        50,
                        (n) -> {
                            mp.setZoom((double) n / 100);
                            mp.repaint();
                        });
            }
        });
        changeZoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.init(
                        "Change zoom",
                        "Zoom (%)",
                        1,
                        151,
                        (int) (mp.getZoom() * 100),
                        true,
                        10,
                        50,
                        (n) -> {
                            mp.setZoom((double) n / 100);
                            mp.repaint();
                        });
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
        setVisible(true);
        setFocusable(true);
        requestFocus();
        setLocationRelativeTo(null);
    }
}
