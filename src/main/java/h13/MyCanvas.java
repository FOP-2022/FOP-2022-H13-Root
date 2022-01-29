package h13;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

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

    // -- Getters and Setters --//

    public double getTransparenty() {
        return this.transparency;
    }

    public void setTransparenty(double transparency) {
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

    private void addGreenEllipse() {

    }

    private void removeGreenEllipse() {

    }

    private void addYellowRectangle() {

    }

    private void removeYellowRectangle() {

    }

    private void addBlueString() {

    }

    private void removeBlueString() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        // Paint all Three Figures to the Center of the Screen
        Rectangle bounds = getBounds();
        Point2D.Double center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
        System.out.println(bounds.toString());
        // Green Ellipse
        g.setColor(Color.GREEN);
        g2d.draw(
                new Ellipse2D.Double(
                        bounds.getCenterX() - 0.5 * 0.9 * bounds.getWidth() * zoom,
                        bounds.getCenterY() - 0.5 * 0.9 * bounds.getHeight() * zoom,
                        0.9 * bounds.getWidth() * zoom,
                        0.9 * bounds.getHeight() * zoom));

        // Yellow Rectangle
        g.setColor(Color.YELLOW);
        g2d.draw(
                new Rectangle2D.Double(
                        bounds.getCenterX() - 0.5 * 0.8 * bounds.getWidth() * zoom,
                        bounds.getCenterY() - 0.5 * 0.8 * bounds.getHeight() * zoom,
                        0.8 * bounds.getWidth() * zoom,
                        0.8 * bounds.getHeight() * zoom));

        // Blue String
        g.setColor(Color.BLUE);
        g.setFont(font);
        int fontWidth = g.getFontMetrics(font).stringWidth(text);
        var fontSize = (float) ((bounds.width * zoom) / fontWidth) * font.getSize();
        var newFont = g.getFont().deriveFont(fontSize);
        this.font = newFont;
        // g2d.sca
        g2d.drawString(text, 0, 0);
    }
}
