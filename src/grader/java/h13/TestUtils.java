package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class TestUtils {
    /**
     * Asserts that two images match exactly
     *
     * @param expected The first image
     * @param actual   The second image
     */
    public static void assertImagesEqual(BufferedImage expected, BufferedImage actual) {
        assertEquals(expected.getColorModel(), actual.getColorModel(), "Wrong Color Model.");
        // The Images have different sizes
        assertEquals(expected.getRaster().getBounds(), actual.getRaster().getBounds(), "Wrong Image Bounds.");
        // Loop over all Pixels
        for (int y = 0; y < expected.getHeight(); y++) {
            for (int x = 0; x < expected.getWidth(); x++) {
                assertEquals(new Color(expected.getRGB(x, y), true),
                        new Color(actual.getRGB(x, y), true),
                        String.format("Wrong Color at (%s,%s).", x, y));
            }
        }
    }

    public static String createRandomString(Random random, int minLength, int maxLength) {
        final int size = random.nextInt(minLength, maxLength + 1);
        final char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = (char) random.nextInt('a', 'z' + 1);
        }
        return new String(chars);
    }

    public static void assertShapesEqual(Shape expected, Shape actual, boolean ignorePositioning) {
        // Bounds
        assertEquals(expected.getBounds(), actual.getBounds());

        var img = new BufferedImage(
                (int) expected.getBounds().getWidth(),
                (int) expected.getBounds().getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        if (ignorePositioning) {
            // Center Shapes
            var at = new AffineTransform();
            at.translate(actual.getBounds().getX(), actual.getBounds().getY());
            actual = at.createTransformedShape(actual);

            var atTutor = new AffineTransform();
            atTutor.translate(expected.getBounds().getX(), expected.getBounds().getY());
            expected = atTutor.createTransformedShape(expected);
        }

        // Draw Shapes
        var outlineColor = Color.RED;
        var fillColor = new Color(0, 255, 0, 100);

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(fillColor);
        g2d.fill(actual);
        g2d.setColor(outlineColor);
        g2d.draw(actual);

        g2dTutor.setStroke(new BasicStroke(2));
        g2dTutor.setColor(fillColor);
        g2dTutor.fill(expected);
        g2dTutor.setColor(outlineColor);
        g2dTutor.draw(expected);

        // Compare
        assertImagesEqual(imgTutor, img);
    }
}
