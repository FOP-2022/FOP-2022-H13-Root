package h13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
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
    public final MyPanel panel;

    /**
     * Creates a new {@link MainFrame}
     */
    public MainFrame(MyPanel panel) {
        super("H13");
        this.panel = panel;
    }

    /**
     * Getter-Method for the {@link #panel}-Field
     *
     * @return the value of the {@link #panel}-Field
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
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    setExtendedState(
                            getExtendedState() == Frame.MAXIMIZED_BOTH ? Frame.NORMAL
                                    : Frame.MAXIMIZED_BOTH);
                } else if (e.getKeyCode() == KeyEvent.VK_F12) {
                    var screenshot_size = new Rectangle(7680, 4320);
                    BufferedImage screenShot = new BufferedImage((int) screenshot_size.getWidth(),
                            (int) screenshot_size.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    // Store original size
                    var s = panel.getBounds();

                    var g2d = screenShot.createGraphics();
                    panel.setSize((int) screenshot_size.getWidth(), (int) screenshot_size.getHeight());
                    panel.paint(g2d);

                    // restore bounds
                    panel.setBounds(s);

                    // Save Screenshot
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    File outputfile = new File("screenshots/FOP-H13-" + dateFormat.format(new Date()) + ".png");
                    System.out.println(outputfile.getAbsolutePath());
                    try {
                        var outdir = Files.createDirectories(outputfile.getParentFile().toPath());
                        try {
                            Files.createDirectories(outputfile.toPath());

                        } catch (FileAlreadyExistsException e2) {
                        }
                        if (!outputfile.exists()) {
                            outputfile.createNewFile();
                        }
                        ImageIO.write(screenShot, "png", outputfile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_PLUS) {
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
        // setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets insets = getInsets();
        System.out.println(insets.left);
        setLocation(0, 0);
        setSize((int) screenSize.getWidth(), (int) (screenSize.getHeight() / 2));

        // Show Frame
        setVisible(true);
        setFocusable(true);
        requestFocus();

        System.out.println("zf:" + getBounds().toString());

    }
}
