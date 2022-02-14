package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
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
}
