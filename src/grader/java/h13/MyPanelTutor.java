package h13;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
public class MyPanelTutor extends javax.swing.JPanel {

    // **************** //
    // -- Attributes -- //
    // **************** //

    /**
     * The possible Figures to display
     */
    public enum Figure {
        /**
         * A green ellipse
         */
        GREEN_ELLIPSE,
        /**
         * A yellow rectangle
         */
        YELLOW_RECTANGLE,
        /**
         * A blue string
         */
        BLUE_STRING,
    }

    /**
     * The figures to display ()
     */
    public List<Figure> figuresToDisplay = new ArrayList<>(List.of(
            Figure.BLUE_STRING,
            Figure.YELLOW_RECTANGLE,
            Figure.GREEN_ELLIPSE));

    /**
     * The current Transparency of the inner color of the shapes
     * <br>
     * </br>
     * - 0f => completely transparent, 1f => opaque
     */
    public float alpha = 0.5f;
    /**
     * The Saturation of the border color of the shapes
     */
    public float saturation = 1.0f;
    /**
     * The Zoom of the Shapes
     */
    public double zoom = 1d;
    /**
     * The Text of the blue string
     */
    // public String text = "Tand ist das Gebilde von Menschenhand!";
    public String text = "FOP-2022";
    /**
     * The Font for the blue string
     */
    public Font font = new Font("Default", Font.PLAIN, 16);

    /**
     * The Border width of the green ellipse and the yellow rectangle
     */
    public int borderWidth = 20;

    // ****************** //
    // -- Constructors -- //
    // ****************** //

    /**
     * Creates a new {@link MyPanelTutor}
     */
    public MyPanelTutor() {
        super();
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    // -- Getters and Setters -- //

    /**
     * Getter-Method for the {@link #alpha}-Field
     *
     * @return the value of the {@link #alpha}-Field
     */
    public float getAlpha() {
        return this.alpha;
    }

    /**
     * Setter-Method for the {@link #alpha}-Field.
     * <br>
     * </br>
     * Only values in [0d,1d] are permitted.
     *
     * @param alpha the new Value of the {@link #alpha}-Field
     */
    public void setAlpha(float alpha) {
        if (alpha < 0d || alpha > 1d) {
            throw new IllegalArgumentException("Transparency must be in range [0..1]");
        }
        this.alpha = alpha;
        this.repaint();
    }

    /**
     * Getter-Method for the {@link #saturation}-Field
     *
     * @return the value of the {@link #saturation}-Field
     */
    public float getSaturation() {
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
    public void setSaturation(float saturation) {
        if (saturation < 0d || saturation > 1d) {
            throw new IllegalArgumentException("Saturation must be in range [0..1]");
        }
        this.saturation = saturation;
        this.repaint();
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
        this.repaint();
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
     * Setter-Method for the {@link #text}-Field.
     * <br>
     * </br>
     * Only Strings with at least one and at most 30 characters are permitted.
     *
     * @param text the new Value of the {@link #text}-Field
     */
    public void setText(String text) throws IllegalArgumentException {
        if (text == null || text == "") {
            throw new IllegalArgumentException("IInvalid Text.");
        }
        this.text = text;
        this.repaint();
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
        if (font == null) {
            throw new IllegalArgumentException("Invalid Font.");
        }
        this.font = font;
        this.repaint();
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
     * Only values in [1,20] are permitted.
     *
     * @param borderWidth the new Value of the {@link #borderWidth}-Field
     */
    public void setBorderWidth(int borderWidth) {
        if (saturation < 1 || saturation > 100) {
            throw new IllegalArgumentException("Saturation must be in range [0..1]");
        }
        this.borderWidth = borderWidth;
        this.repaint();
    }

    // ******************* //
    // -- Other Methods -- //
    // ******************* //

    /**
     * Displays the green ellipse if it is not already displayed.
     * If it is already displayed, it will be moved to the end.
     */
    public void addGreenEllipse() {
        figuresToDisplay.remove(Figure.GREEN_ELLIPSE);
        figuresToDisplay.add(Figure.GREEN_ELLIPSE);
        this.repaint();
    }

    /**
     * Removes the green ellipse from the Panel.
     * If it is already hidden, the method will do nothing.
     */
    public void removeGreenEllipse() {
        figuresToDisplay.remove(Figure.GREEN_ELLIPSE);
        this.repaint();
    }

    /**
     * Displays the yellow rectangle if it is not already displayed.
     * If it is already displayed, it will be moved to the end.
     */
    public void addYellowRectangle() {
        figuresToDisplay.remove(Figure.YELLOW_RECTANGLE);
        figuresToDisplay.add(Figure.YELLOW_RECTANGLE);
        this.repaint();
    }

    /**
     * Removes the yellow rectangle from the Panel.
     * If it is already hidden, the method will do nothing.
     */
    public void removeYellowRectangle() {
        figuresToDisplay.remove(Figure.YELLOW_RECTANGLE);
        this.repaint();
    }

    /**
     * Displays the blue string if it is not already displayed.
     * If it is already displayed, it will be moved to the end.
     */
    public void addBlueString() {
        figuresToDisplay.remove(Figure.BLUE_STRING);
        figuresToDisplay.add(Figure.BLUE_STRING);
        this.repaint();
    }

    /**
     * Removes the blue string from the Panel.
     * If it is already hidden, the method will do nothing.
     */
    public void removeBlueString() {
        figuresToDisplay.remove(Figure.BLUE_STRING);
        this.repaint();
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
    public Color colorWithAlpha(Color c, int alpha) {
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
    public Color colorWithAlpha(Color c, float alpha) {
        return colorWithAlpha(c, (int) (alpha * 255 + 0.5));
    }

    /**
     * Returns a Color generated from the original Color with the desired saturation
     *
     * @param c          The Source color
     * @param saturation the desired Saturation
     * @return the generated Color
     */
    public Color colorWithSaturation(Color c, float saturation) {
        int alpha = c.getAlpha();
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        Color colorWithBrightness = Color.getHSBColor(hsb[0], saturation, hsb[2]);
        return colorWithAlpha(colorWithBrightness, alpha);
    }

    /**
     * Centers a rectangular Shape and scales it with the given factors
     *
     * @param <T>         The Dynamic Type of the RectangularShape
     * @param s           the RectangularShape
     * @param scaleX      how much horizontal space of the current
     *                    {@link MyPanelTutor}-instance the shape will take after scaling
     *                    (1.0d -> full width)
     * @param scaleY      how much vertical space of the current
     *                    {@link MyPanelTutor}-instance the shape will take after scaling
     *                    (1.0d -> full height)
     * @param borderWidth the Border width to consider
     * @return the centered Shape (for convenience)
     */
    public <T extends RectangularShape> T centerShape(T s, double scaleX, double scaleY, int borderWidth) {
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
    public void fillDraw(Graphics2D g2d, Color interiorColor, Color borderColor, int borderWidth, Shape s) {
        // Store current g2d Configuration
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

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
    public void fillDrawCentered(Graphics2D g2d, Color interiorColor, Color borderColor, int borderWidth,
            RectangularShape s, double scaleX, double scaleY) {
        centerShape(s, scaleX, scaleY, borderWidth);
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
    @SuppressWarnings("unused")
    public double getOptimalFontSize(Graphics2D g2d, double width, String text, Font f) {
        double fontWidth = f.createGlyphVector(g2d.getFontRenderContext(), text).getVisualBounds().getWidth();
        return Math.max((width / fontWidth) * f.getSize2D(), 1);
    }

    /**
     * Create A shape with the desired Text and the desired width
     *
     * @param g2d         the specified Graphics context to draw the font with
     * @param width       the desired text width
     * @param borderWidth the border width to account for
     * @param text        the string to display
     * @param f           the font used for drawing the string
     * @return The Shape of the outline
     */
    public Shape scaleTextToWidth(Graphics2D g2d, double width, float borderWidth, String text, Font f) {
        // Get current size
        Rectangle bounds = getBounds();

        // Store current g2d Configuration
        Font oldFont = g2d.getFont();

        // graphics configuration
        g2d.setFont(f);

        TextLayout tl = new TextLayout(text, f, g2d.getFontRenderContext());
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(),
                text).getVisualBounds();

        // Calculate scale Factor
        double factor = (width - borderWidth) / fontBounds.getWidth();

        // Calculate new Font Bounds for easy centering
        Double fontBoundsWithBorder = new Rectangle2D.Double(fontBounds.getX() - (borderWidth / factor) / 2,
                fontBounds.getY() - (borderWidth / factor) / 2,
                fontBounds.getWidth() + (borderWidth / factor),
                fontBounds.getHeight() + (borderWidth / factor));

        // Transform
        AffineTransform tf = g2d.getTransform();
        tf.scale(factor, factor);
        tf.translate((bounds.getCenterX() / factor) - (fontBoundsWithBorder.getCenterX()),
                (bounds.getCenterY() / factor) - (fontBoundsWithBorder.getCenterY()));
        Shape outline = tl.getOutline(tf);

        // Restore graphics configuration
        g2d.setFont(oldFont);
        return outline;
    }

    /**
     * Draws a given String with the given Color to the center of the Panel.
     *
     * @param g2d           the specified Graphics context
     * @param interiorColor the Color of the filled Area
     * @param borderColor   the border Color
     * @param borderWidth   the Width of the Border
     * @param text          the text to display
     * @param f             the font to use
     * @param width         the desired text width
     */
    public void drawColoredString(Graphics2D g2d, Color interiorColor, Color borderColor, int borderWidth,
            String text, Font f, double width) {
        // Get a drawable Shape of the Text
        Shape outline = scaleTextToWidth(g2d, width, borderWidth, text, f);
        fillDraw(g2d, interiorColor, borderColor, borderWidth, outline);
    }

    /**
     * Draws a Grid to help With Positioning
     *
     * @param g2d the specified graphics context
     */
    @SuppressWarnings("unused")
    public void drawGrid(Graphics2D g2d) {
        // save g2d configuration
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

        // Get current size
        Rectangle bounds = getBounds();
        double width = bounds.getWidth();
        double height = bounds.getHeight();

        // G2d Configuration
        g2d.setColor(Color.GRAY);

        float outerTicksWidth = Math.min(width, height) < 500 ? 4 : 6;
        float tenTicksWidth = Math.min(width, height) < 500 ? 2 : 3;
        float fiveTicksWidth = Math.min(width, height) < 500 ? 1 : 2;
        float oneTicksWidth = Math.min(width, height) < 500 ? 0 : 1;

        // Vertical Lines
        for (double i = 0, x = 0; x < width; i++, x += width / 100d) {
            float strokeWidth = i % 10 == 0 ? tenTicksWidth : i % 5 == 0 ? fiveTicksWidth : oneTicksWidth;
            if (strokeWidth <= 0) {
                continue;
            }
            g2d.setStroke(new BasicStroke(strokeWidth));
            g2d.drawLine((int) x, 0, (int) x, (int) height);
        }

        // Horizontal Lines
        for (double i = 0, y = 0; y < height; i++, y += height / 100d) {
            float strokeWidth = i % 10 == 0 ? tenTicksWidth : i % 5 == 0 ? fiveTicksWidth : oneTicksWidth;
            if (strokeWidth <= 0) {
                continue;
            }
            g2d.setStroke(new BasicStroke(strokeWidth));
            g2d.drawLine(0, (int) y, (int) width, (int) y);
        }

        // Border
        g2d.setStroke(new BasicStroke(outerTicksWidth));
        g2d.drawRect(
                (int) (.5 * outerTicksWidth),
                (int) (.5 * outerTicksWidth),
                (int) (width - outerTicksWidth),
                (int) (height - outerTicksWidth));

        // Restore g2d Configuration
        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Convert to g2d
        Graphics2D g2d = (Graphics2D) g;

        // Optional: draw a grid that helps with positioning
        // TODO: Remember to disable for submission
        // drawGrid(g2d);

        // Antialiasing
        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        //         RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle bounds = getBounds();

        // Paint the desired Figures to the Center of the Screen
        // STRIIIIIIIIIIIIIIIIIIIIIIEHMMMMMMZ
        IntStream
                .range(0, figuresToDisplay.size())
                .mapToObj(x -> figuresToDisplay.get(figuresToDisplay.size() - x - 1))
                .forEach(f -> {
                    switch (f) {
                        // Green Ellipse
                        case GREEN_ELLIPSE:
                            fillDrawCentered(g2d,
                                    colorWithAlpha(Color.GREEN,
                                            (float) alpha),
                                    colorWithSaturation(
                                            Color.GREEN, (float) saturation),
                                    borderWidth,
                                    new Ellipse2D.Double(),
                                    0.9 * zoom,
                                    0.9 * zoom);
                            break;

                        // Yellow Rectangle
                        case YELLOW_RECTANGLE:
                            fillDrawCentered(g2d,
                                    colorWithAlpha(Color.YELLOW,
                                            (float) alpha),
                                    colorWithSaturation(
                                            Color.YELLOW, (float) saturation),
                                    borderWidth,
                                    new Rectangle2D.Double(),
                                    0.8 * zoom,
                                    0.8 * zoom);
                            break;

                        case BLUE_STRING:
                            // Blue String
                            drawColoredString(g2d,
                                    colorWithAlpha(
                                            Color.BLUE, (float) alpha),
                                    colorWithSaturation(
                                            Color.BLUE, (float) saturation),
                                    5,
                                    text,
                                    font,
                                    bounds.width * zoom);
                            break;
                    }
                });
    }
}
