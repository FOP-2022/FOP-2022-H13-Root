package h13;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 * A JPanel to display the following three shapes:
 * <ul>
 * <li>Green ellipse</li>
 * <li>Yellow triangle</li>
 * <li>Blue string</li>
 * </ul>
 *
 * @author Ruben Deisenroth
 */
public class MyPanel extends javax.swing.JPanel {

    // **************** //
    // -- Attributes -- //
    // **************** //

    /**
     * The current Transparency of the Shapes
     */
    private double transparency = 0.2d;
    /**
     * The Saturation of the Shapes
     */
    private double saturation = 0.5d;
    /**
     * The Zoom of the Shapes
     */
    private double zoom = 1d;
    /**
     * The Text of the blue string
     */
    // private String text = "Tand ist das Gebilde von Menschenhand!";
    private String text = "Hallo, Glaser mein Name. Ich wollte mal fragen, ob ihre Öfen ausgelastet sind??";
    /**
     * The Font for the blue string
     */
    private Font font = new Font("Default", Font.PLAIN, 16);
    /**
     * Whether or not to display the green ellipse
     */
    private boolean displayGreenEllipse = true;
    /**
     * Whether or not to display the yellow ellipse
     */
    private boolean displayYellowRectangle = true;
    /**
     * Whether or not to display the blue string
     */
    private boolean displayBlueString = true;
    /**
     * The Border width of the green ellipse and the yellow rectangle
     */
    private int borderWidth = 20;

    // ****************** //
    // -- Constructors -- //
    // ****************** //

    /**
     * Creates a new {@link MyPanel}
     */
    public MyPanel() {
        super();
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    // -- Getters and Setters -- //

    /**
     * Getter-Method for the {@link #transparency}-Field
     *
     * @return the value of the {@link #transparency}-Field
     */
    public double getTransparency() {
        return this.transparency;
    }

    /**
     * Setter-Method for the {@link #transparency}-Field.
     * <br>
     * </br>
     * Only values in [0d,1d] are permitted.
     *
     * @param transparency the new Value of the {@link #transparency}-Field
     */
    public void setTransparency(double transparency) {
        if (transparency < 0d || transparency > 1d) {
            throw new IllegalArgumentException("Transparency must be in range [0..1]");
        }
        this.transparency = transparency;
    }

    /**
     * Getter-Method for the {@link #saturation}-Field
     *
     * @return the value of the {@link #saturation}-Field
     */
    public double getSaturation() {
        return this.saturation;
    }

    /**
     * Setter-Method for the {@link #saturation}-Field.
     * <br>
     * </br>
     * Only values in [0d,1d] are permitted.
     *
     * @param saturation the new Value of the {@link #saturation}-Field
     */
    public void setSaturation(double saturation) {
        if (saturation < 0d || saturation > 1d) {
            throw new IllegalArgumentException("Saturation must be in range [0..1]");
        }
        this.saturation = saturation;
    }

    /**
     * Getter-Method for the {@link #zoom}-Field
     *
     * @return the value of the {@link #zoom}-Field
     */
    public double getZoom() {
        return this.zoom;
    }

    /**
     * Setter-Method for the {@link #zoom}-Field.
     * <br>
     * </br>
     * Only positive values are permitted.
     *
     * @param zoom the new Value of the {@link #zoom}-Field
     */
    public void setZoom(double zoom) {
        if (zoom <= 0d) {
            throw new IllegalArgumentException("Zoom must be positive.");
        }
        this.zoom = zoom;
    }

    /**
     * Getter-Method for the {@link #text}-Field
     *
     * @return the value of the {@link #text}-Field
     */
    public String getText() {
        return this.text;
    }

    /**
     * Setter-Method for the {@link #zoom}-Field.
     * <br>
     * </br>
     * Only positive values are permitted.
     *
     * @param zoom the new Value of the {@link #zoom}-Field
     */
    public void setText(String text) throws IllegalArgumentException {
        if (text == null || text == "") {
            throw new IllegalArgumentException("IInvalid Text.");
        }
        this.text = text;
    }

    /**
     * Getter-Method for the {@link #font}-Field
     *
     * @return the value of the {@link #font}-Field
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Setter-Method for the {@link #font}-Field.
     * <br>
     * </br>
     * Only default java-Fonts are permitted.
     *
     * @param font the new Value of the {@link #font}-Field
     */
    public void setFont(Font font) throws IllegalArgumentException {
        this.font = font;
    }

    /**
     * Getter-Method for the {@link #borderWidth}-Field
     *
     * @return the value of the {@link #borderWidth}-Field
     */
    public int getBorderWidth() {
        return borderWidth;
    }

    /**
     * Setter-Method for the {@link #borderWidth}-Field.
     * <br>
     * </br>
     * Only values in [1,100] are permitted.
     *
     * @param font the new Value of the {@link #borderWidth}-Field
     */
    public void setBorderWidth(int borderWidth) {
        if (saturation < 1 || saturation > 100) {
            throw new IllegalArgumentException("Saturation must be in range [0..1]");
        }
        this.borderWidth = borderWidth;
    }

    // ******************* //
    // -- Other Methods -- //
    // ******************* //

    /**
     * Displays the green ellipse if it is not already displayed.
     * If it is already displayed, the method will do nothing.
     */
    public void addGreenEllipse() {
        displayGreenEllipse = true;
    }

    /**
     * Removes the green ellipse from the Panel.
     * If it is already hidden, the method will do nothing.
     */
    public void removeGreenEllipse() {
        displayGreenEllipse = false;
    }

    /**
     * Displays the yellow rectangle if it is not already displayed.
     * If it is already displayed, the method will do nothing.
     */
    public void addYellowRectangle() {
        displayYellowRectangle = true;
    }

    /**
     * Removes the yellow rectangle from the Panel.
     * If it is already hidden, the method will do nothing.
     */
    public void removeYellowRectangle() {
        displayYellowRectangle = false;
    }

    /**
     * Displays the blue string if it is not already displayed.
     * If it is already displayed, the method will do nothing.
     */
    public void addBlueString() {
        displayBlueString = true;
    }

    /**
     * Removes the blue string from the Panel.
     * If it is already hidden, the method will do nothing.
     */
    public void removeBlueString() {
        displayBlueString = false;
    }

    // Drawing Methods and helpers

    /**
     * Returns a Color generated from the original Color with the desired
     * transparency (alpha).
     *
     * @param c     The Source color
     * @param alpha the desired Alpha
     * @return the generated Color
     */
    private Color colorWithAlpha(Color c, int alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
    }

    /**
     * Returns a Color generated from the original Color with the desired
     * transparency (alpha).
     *
     * @param c     The Source color
     * @param alpha the desired Alpha
     * @return the generated Color
     */
    private Color colorWithAlpha(Color c, float alpha) {
        return colorWithAlpha(c, (int) (alpha * 255 + 0.5));
    }

    /**
     * Centers a rectangular Shape
     *
     * @param <T>         The Dynamic Type of the RectangularShape
     * @param s           the RectangularShape
     * @param scaleX      how much horizontal screen space the shape will take
     *                    (1.0d -> full screen)
     * @param scaleY      how much vertical screen space the shape will take
     *                    (1.0d -> full screen)
     * @param borderWidth the Border width to consider
     * @return the centered Shape (for convenience)
     */
    private <T extends RectangularShape> T centerShape(T s, double scaleX, double scaleY, int borderWidth) {
        // Get current size
        Rectangle bounds = getBounds();
        // Change the Position and scale of the shape
        s.setFrameFromCenter(
                // center
                bounds.getCenterX(),
                bounds.getCenterY(),
                // top left corner
                bounds.getCenterX() - bounds.getCenterX() * scaleX + borderWidth / 2,
                bounds.getCenterY() - bounds.getCenterY() * scaleY + borderWidth / 2);
        return s;
    }

    /**
     * Fills a Given Shape and also draws a border with the given Colors saving and
     * restoring the original stoke and color of g2d.
     *
     * @param g2d           the specified Graphics context
     * @param interiorColor the Color of the filled Area
     * @param borderColor   the border Color
     * @param borderWidth   the Width of the Border
     * @param s             the Shape to draw
     */
    private void fillDraw(Graphics2D g2d, Color interiorColor, Color borderColor, int borderWidth, Shape s) {
        // Store current g2d Configuration
        var oldColor = g2d.getColor();
        var oldStroke = g2d.getStroke();

        // Fill the shape
        g2d.setColor(interiorColor);
        g2d.fill(s);

        // Draw a border on top
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.setColor(borderColor);
        g2d.draw(s);

        // Restore g2d Configuration
        g2d.setStroke(oldStroke);
        g2d.setColor(oldColor);
    }

    /**
     * Fills a Given Shape and also draws a border with the given Colors and Scale
     * to the
     * Center of the Screen.
     *
     * @param g2d           the specified Graphics context
     * @param interiorColor the Color of the filled Area
     * @param borderColor   the border Color
     * @param borderWidth   the Width of the Border
     * @param s             the Shape to draw
     * @param scaleX        how much horizontal screen space the shape will take
     *                      (1.0d -> full screen)
     * @param scaleY        how much vertical screen space the shape will take
     *                      (1.0d -> full screen)
     */
    private void fillDrawCentered(Graphics2D g2d, Color interiorColor, Color borderColor, int borderWidth,
            RectangularShape s, double scaleX, double ScaleY) {
        centerShape(s, scaleX, ScaleY, borderWidth);
        fillDraw(g2d, interiorColor, borderColor, borderWidth, s);
    }

    /**
     * Calculates the optimal Font Size for the desired width
     *
     * @param g2d   the specified Graphics context to draw the font with
     * @param width the desired text width
     * @param text  the string to display
     * @return the optimal calculated font width
     */
    private double getOptimalFontSize(Graphics2D g2d, double width, String text, Font f) {
        double fontWidth = g2d.getFontMetrics(f).getStringBounds(text, g2d).getWidth();
        return Math.max((width / fontWidth) * f.getSize(), 1);
    }

    /**
     * Draws a given String with the given Color to the center of the Panel.
     *
     * @param g2d   the specified Graphics context
     * @param width the desired text width
     * @param c     the text color
     * @param text  the text to display
     * @param f     the font to use
     */
    private void drawColoredString(Graphics2D g2d, double width, Color c, String text, Font f) {
        // Get current size
        Rectangle bounds = getBounds();

        // save g2d configuration
        var oldColor = g2d.getColor();
        var oldFont = g2d.getFont();

        // Calculate optimal Font
        var newFont = f.deriveFont((float) getOptimalFontSize(g2d, width, text, f));
        var newFontMetrics = g2d.getFontMetrics(newFont);

        // g2d Configuration
        g2d.setColor(c);
        g2d.setFont(f);
        g2d.setFont(newFont);

        // Draw the String using g2d
        g2d.drawString(
                text,
                (int) bounds.getCenterX() - (int) newFontMetrics.getStringBounds(text, g2d).getCenterX(),
                (int) bounds.getCenterY() + (int) newFontMetrics.getStringBounds(text, g2d).getCenterY());

        // Restore g2d Configuration
        g2d.setColor(oldColor);
        g2d.setFont(oldFont);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Convert to g2d
        Graphics2D g2d = (Graphics2D) g;

        // Antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint all Three Figures to the Center of the Screen
        Rectangle bounds = getBounds();

        // Green Ellipse
        if (displayGreenEllipse) {
            fillDrawCentered(g2d,
                    colorWithAlpha(Color.GREEN, 0.5f),
                    Color.GREEN,
                    borderWidth,
                    new Ellipse2D.Double(),
                    0.9 * zoom,
                    0.9 * zoom);
        }

        // Yellow Rectangle
        if (displayYellowRectangle) {
            fillDrawCentered(g2d,
                    colorWithAlpha(Color.YELLOW, 0.5f),
                    Color.YELLOW,
                    borderWidth,
                    new Rectangle2D.Double(),
                    0.8 * zoom,
                    0.8 * zoom);
        }

        // Blue String
        if (displayBlueString) {
            drawColoredString(g2d, bounds.width * zoom, Color.BLUE, text, font);
        }
    }
}
