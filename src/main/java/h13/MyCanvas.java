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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author Ruben Deisenroth
 */
public class MyCanvas extends java.awt.Canvas {
    private double transparency = 0.2d;
    private double saturation = 0.5d;
    private double zoom = 1d;
    private String text = "Tand ist das Gebilde von Menschenhand!";
    private Font font = new Font("Default", Font.PLAIN, 16);
    private boolean displayGreenEllipse = true;
    private boolean displayYellowRectangle = true;
    private boolean displayBlueString = true;

    // -- Getters and Setters --//

    public double getTransparency() {
        return this.transparency;
    }

    public void setTransparency(double transparency) {
        if (transparency < 0d || transparency > 1d) {
            throw new IllegalArgumentException("Transparency must be in range [0..1]");
        }
        this.transparency = transparency;
    }

    public double getSaturation() {
        return this.saturation;
    }

    public void setSaturation(double saturation) {
        if (saturation < 0d || saturation > 1d) {
            throw new IllegalArgumentException("Saturation must be in range [0..1]");
        }
        this.saturation = saturation;
    }

    public double getZoom() {
        return this.zoom;
    }

    public void setZoom(double zoom) {
        if (zoom <= 0d) {
            throw new IllegalArgumentException("Zoom must be positive.");
        }
        this.zoom = zoom;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) throws IllegalArgumentException {
        if (text == null || text == "") {
            throw new IllegalArgumentException("IInvalid Text.");
        }
        this.text = text;
    }

    public Font getFont() {
        return this.font;
    }

    public void setFont(Font font) throws IllegalArgumentException {
        this.font = font;
    }

    // -- Other Methods--//

    public void addGreenEllipse() {
        displayGreenEllipse = true;
    }

    public void removeGreenEllipse() {
        displayGreenEllipse = false;
    }

    public void addYellowRectangle() {
        displayYellowRectangle = true;
    }

    public void removeYellowRectangle() {
        displayYellowRectangle = false;
    }

    public void addBlueString() {
        displayBlueString = true;
    }

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
        g2d.setColor(interiorColor);
        g2d.fill(s);
        var oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.setColor(borderColor);
        g2d.draw(s);
        g2d.setStroke(oldStroke);
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
                    20,
                    new Ellipse2D.Double(),
                    0.9 * zoom,
                    0.9 * zoom);
        }

        if (displayYellowRectangle) {
            // Yellow Rectangle
            fillDrawCentered(g2d,
                    colorWithAlpha(Color.YELLOW, 0.5f),
                    Color.YELLOW,
                    (int)(20* zoom),
                    new Rectangle2D.Double(),
                    0.8 * zoom,
                    0.8 * zoom);
        }

        if (displayBlueString) {
            System.out.println("hi");
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
