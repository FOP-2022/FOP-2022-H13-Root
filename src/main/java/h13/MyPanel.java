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

    // -- Attributes -- //

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

    // -- Constructors -- //

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

    // -- Other Methods -- //

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

    /**
     * Fills a Given Shape and also draws a border with the given Colors.
     *
     * @param g2d           the specified Graphics context
     * @param interiorColor the Color of the filled Area
     * @param borderColor   the border Color
     * @param borderWidth   the Width of the Border
     * @param s             the Shape to draw
     */
    private void fillDraw(Graphics2D g2d, Color interiorColor, Color borderColor, int borderWidth, Shape s) {
        var oldColor = g2d.getColor();
        var oldStroke = g2d.getStroke();
        g2d.setColor(interiorColor);
        g2d.fill(s);
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.setColor(borderColor);
        g2d.draw(s);
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
     * Centers a rectangular Shape
     *
     * @param <T>         The Dynamic Type of the RectangularShape
     * @param s           the RectangularShape
     * @param scaleX      how much horizontal screen space the shape will take
     *                    (1.0d -> full screen)
     * @param scaleY      how much vertical screen space the shape will take
     *                    (1.0d -> full screen)
     * @param borderWidth the Border width to consider
     * @return the centered Shape
     */
    private <T extends RectangularShape> void centerShape(T s, double scaleX, double scaleY, int borderWidth) {
        Rectangle bounds = getBounds();
        s.setFrameFromCenter(
                bounds.getCenterX(),
                bounds.getCenterY(),
                bounds.getCenterX() - bounds.getCenterX() * scaleX + borderWidth / 2,
                bounds.getCenterY() - bounds.getCenterY() * scaleY + borderWidth / 2);
    }

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint all Three Figures to the Center of the Screen
        Rectangle bounds = getBounds();
        if (displayGreenEllipse) {
            // Green Ellipse
            fillDrawCentered(g2d,
                    colorWithAlpha(Color.GREEN, 0.5f),
                    Color.GREEN,
                    borderWidth,
                    new Ellipse2D.Double(),
                    0.9 * zoom,
                    0.9 * zoom);
        }

        if (displayYellowRectangle) {
            // Yellow Rectangle
            fillDrawCentered(g2d,
                    colorWithAlpha(Color.YELLOW, 0.5f),
                    Color.YELLOW,
                    borderWidth,
                    new Rectangle2D.Double(),
                    0.8 * zoom,
                    0.8 * zoom);
        }

        if (displayBlueString) {
            // Blue String
            g.setColor(Color.BLUE);
            g.setFont(font);
            double fontWidth = g.getFontMetrics(font).getStringBounds(text, g2d).getWidth();
            double fontSize = Math.max((bounds.width * zoom) / fontWidth * font.getSize(), 1);
            var newFont = g.getFont().deriveFont((float) fontSize);
            g.setFont(newFont);
            var newFontMetrics = g.getFontMetrics(newFont);
            this.font = newFont;
            // g2d.scale
            g2d.drawString(
                    text,
                    (int) bounds.getCenterX() - (int) newFontMetrics.getStringBounds(text, g).getCenterX(),
                    (int) bounds.getCenterY() + (int) newFontMetrics.getStringBounds(text, g).getCenterY());
        }
    }
}
