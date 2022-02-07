package h13;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * A Control Frame that contains all the necessary Control Elements for the Main
 * Frame
 *
 * @author Ruben Deisenroth
 */
public class ControlFrame extends JFrame {
    /**
     * The Main Frame to control
     */
    private MainFrame mf;
    /**
     * A button that calls {@link MyPanel#addGreenEllipse()} when clicked
     */
    private JButton addElipseButton = new JButton("Add ellipse");
    /**
     * A button that calls {@link MyPanel#addYellowRectangle()} when clicked
     */
    private JButton addRectangleButton = new JButton("Add rectangle");
    /**
     * A button that calls {@link MyPanel#addBlueString()} when clicked
     *
     */
    private JButton addStringButton = new JButton("Add string");
    /**
     * A button that calls {@link MyPanel#removeGreenEllipse()} when clicked
     */
    private JButton removeElipseButton = new JButton("Remove ellipse");
    /**
     * A button that calls {@link MyPanel#removeYellowRectangle()} when clicked
     */
    private JButton removeRectangleButton = new JButton("Remove rectangle");
    /**
     * A button that calls {@link MyPanel#removeBlueString()} when clicked
     */
    private JButton removeStringButton = new JButton("Remove string");
    /**
     * A button that opens a {@link PropertyChangeDialogue} that controls the
     * transparency via {@link MyPanel#setTransparency(float)} when clicked
     */
    private JButton changeTransparencyButton = new JButton("Change transparency");
    /**
     * A button that opens a {@link PropertyChangeDialogue} that controls the
     * saturation via {@link MyPanel#setSaturation(float)} when clicked
     */
    private JButton changeSaturationButton = new JButton("Change saturation");
    /**
     * A button that opens a {@link PropertyChangeDialogue} that controls the
     * saturation via {@link MyPanel#setBorderWidth(int)} when clicked
     */
    private JButton changeBorderWidthButton = new JButton("Change border width");
    /**
     * A button that opens a {@link PropertyChangeDialogue} that controls the
     * font via {@link MyPanel#setFont(java.awt.Font)} when clicked
     */
    private JButton changeFontButton = new JButton("Change font");
    /**
     * A button that opens a {@link PropertyChangeDialogue} that controls the
     * saturation via {@link MyPanel#setZoom(double)} when clicked
     */
    private JButton changeZoomButton = new JButton("Change zoom");
    /**
     * A button that opens a {@link PropertyChangeDialogue} that exits the Program when clicked
     */
    private JButton exitButton = new JButton("Exit");
    /**
     * The {@link PropertyChangeDialogue} that pops up when a Property needs to be
     * changed and updates the property in real Time
     */
    private final PropertyChangeDialogue pcd = new PropertyChangeDialogue();

    /**
     * Creates a new {@link ControlFrame}-Instance
     *
     * @param mf
     */
    public ControlFrame(MainFrame mf) {
        super("Steuerungsfenster");
        this.mf = mf;
    }

    /**
     * Initializes and shows the Frame
     */
    public void init() {
        // Frame Properties
        setLayout(new GridLayout(4, 3));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Add Components
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

        // Add Listeners
        MyPanel mp = mf.getPanel();

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
                pcd.showNumberChangeDialog(
                        "Change saturation (%)",
                        "Saturation (0-100)",
                        0,
                        100,
                        (int) (mp.getSaturation() * 100),
                        true,
                        10,
                        50,
                        (n) -> {
                            mp.setSaturation((float) n / 100);
                        });
            }
        });
        changeTransparencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.showNumberChangeDialog(
                        "Change transparency (%)",
                        "Transparency (0-100)",
                        0,
                        100,
                        (int) (mp.getTransparency() * 100),
                        true,
                        10,
                        50,
                        (n) -> {
                            mp.setTransparency((float) n / 100);
                        });
            }
        });
        changeBorderWidthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.showNumberChangeDialog(
                        "Change Border Width",
                        "Border Width (0-100)",
                        0,
                        100,
                        mp.getBorderWidth(),
                        true,
                        10,
                        50,
                        (n) -> {
                            mp.setBorderWidth(n);
                        });
            }
        });
        changeFontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.showEnumChangeDialogue(
                        "Change Font",
                        "Font",
                        0,
                        new String[] { "Default", "Arial" },
                        (n) -> {
                            mp.setFont(new Font(n, Font.PLAIN, 16));
                        });
            }
        });
        changeZoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcd.showNumberChangeDialog(
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

        // Set Dimension and Position

        // Show Frame
        pack();
        setVisible(true);
        setFocusable(true);
        requestFocus();
        setLocationRelativeTo(null);
    }
}
