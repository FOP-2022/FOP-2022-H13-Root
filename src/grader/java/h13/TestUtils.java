package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.opentest4j.AssertionFailedError;

/**
 * Utility Class for the Tutor tests of H13
 *
 * @author Ruben Deisenroth
 */
public class TestUtils {
    /**
     * Corporate needs you to find the difference between these two images.
     *
     * @param expected The first image
     * @param actual   The second image
     * @returns an Image Containing the difference
     */
    public static BufferedImage imageDiff(BufferedImage expected, BufferedImage actual) {
        var diff = new BufferedImage(
                expected.getWidth(),
                expected.getHeight(),
                expected.getType());

        // Loop over all Pixels
        int unequal = 0;
        for (int y = 0; y < expected.getHeight(); y++) {
            for (int x = 0; x < expected.getWidth(); x++) {
                var expectedColor = new Color(expected.getRGB(x, y), true);
                var actualColor = new Color(actual.getRGB(x, y), true);
                if (!expectedColor.equals(actualColor)) {
                    unequal++;
                    var fcolor = Color.GREEN.getRGB();
                    if (expectedColor.getRGB() == 0) {
                        fcolor = Color.RED.getRGB();
                    } else if (actualColor.getRGB() == 0) {
                        fcolor = Color.BLUE.getRGB();
                    }
                    diff.setRGB(x, y, fcolor);
                }
            }
        }
        System.out.println(unequal);
        if (unequal == 0) {
            System.out.println("They're the same picture.");
        }
        return diff;
    }

    /**
     * Saves the Diff Image in the screenshots/ directory
     *
     * @param expected The first image
     * @param actual   The second image
     *                 exactly
     */
    public static void saveImageDiff(BufferedImage expected, BufferedImage actual) {
        saveImage(imageDiff(expected, actual), "FOP-H13-Diff");
    }

    /**
     * Saves a given BufferedImage to the screenshots directory
     *
     * @param img        the Image to save
     * @param namePrefix the Image Name Prefix
     */
    public static void saveImage(BufferedImage img, String namePrefix) {
        // Save Screenshot
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSS");
        File outputfile = new File(
                "screenshots/" + (namePrefix != null && namePrefix.length() > 0 ? namePrefix + "-" : "")
                        + dateFormat.format(new Date()) + ".png");
        System.out.println("Saving DIFF to: " + outputfile.getAbsolutePath());
        try {
            try {
                Files.createDirectories(outputfile.toPath());
            } catch (FileAlreadyExistsException e2) {
                // Directory Already Exists
            }
            if (!outputfile.exists()) {
                outputfile.createNewFile();
            }
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Saves a given BufferedImage to the screnshots directory
     *
     * @param img the Image to save
     */
    public static void saveImage(BufferedImage img) {
        saveImage(img, (String) null);
    }

    /**
     * Asserts that two images match exactly
     *
     * @param expected           The first image
     * @param actual             The second image
     * @param requiredSimilarity the percentage of pixels whose colors need to match
     *                           exactly
     * @returns an Image Containing the difference
     */
    public static void assertImagesEqual(BufferedImage expected, BufferedImage actual,
            double requiredSimilarity) {

        assertEquals(expected.getColorModel(), actual.getColorModel(), "Wrong ColorModel.");
        // The Images have different sizes
        assertEquals(expected.getRaster().getBounds(),
                actual.getRaster().getBounds(), "Wrong Image Bounds.");

        // Loop over all Pixels
        int unequal = 0;
        for (int y = 0; y < expected.getHeight(); y++) {
            for (int x = 0; x < expected.getWidth(); x++) {
                var expectedColor = new Color(expected.getRGB(x, y), true);
                var actualColor = new Color(actual.getRGB(x, y), true);
                if (!expectedColor.equals(actualColor)) {
                    if (requiredSimilarity >= 1) {
                        assertEquals(expectedColor,
                                actualColor,
                                String.format("Wrong Color at (%s,%s). (expected alpha: %s, actual alpha: %s)", x, y,
                                        expectedColor.getAlpha(), actualColor.getAlpha()));
                    } else {
                        unequal++;
                    }
                }
            }
        }
        System.out.println(unequal);
        var maxAllowed = (1 - requiredSimilarity) * expected.getWidth() *
                expected.getHeight();
        assertTrue(unequal <= maxAllowed,
                "Too many different Pixels. Expected equal or less than " + (int) maxAllowed
                        + " but was " + unequal);
        // They're the same picture.
    }

    /**
     * Asserts that two images match exactly
     *
     * @param expected The first image
     * @param actual   The second image
     */
    public static void assertImagesEqual(BufferedImage expected, BufferedImage actual) {
        assertImagesEqual(expected, actual, 1);
    }

    public static String createRandomString(Random random, int minLength, int maxLength) {
        final int size = random.nextInt(minLength, maxLength + 1);
        final char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = (char) random.nextInt('a', 'z' + 1);
        }
        return new String(chars);
    }

    /**
     * Asserts that a shape is centered
     *
     * @param s         the Shape to verify
     * @param bounds    the Bounds the shape should be centered in
     * @param tolerance the allowed tolerance
     */
    public static void assertShapeCentered(Shape s, Rectangle2D bounds, double tolerance) {
        var shapeBounds = s.getBounds2D();
        if (tolerance > 0) {
            assertEqualInRange(shapeBounds.getX(), bounds.getCenterX() - shapeBounds.getWidth() / 2, tolerance);
            assertEqualInRange(shapeBounds.getY(), bounds.getCenterY() - shapeBounds.getHeight() / 2, tolerance);
        } else {
            // Since this isn't that hard to do exact, we don't use the BoundTolerance here
            // but pixel precision instead
            assertEqualFloored(shapeBounds.getX(), bounds.getCenterX() - shapeBounds.getWidth() / 2);
            assertEqualFloored(shapeBounds.getY(), bounds.getCenterY() - shapeBounds.getHeight() / 2);
        }
    }

    /**
     * Asserts that a shape is centered
     *
     * @param s      the Shape to verify
     * @param bounds the Bounds the shape should be centered in
     */
    public static void assertShapeCentered(Shape s, Rectangle2D bounds) {
        assertShapeCentered(s, bounds, 0);
    }

    /**
     * Asserts that the Bounds of two given rectangles are equal when floored
     *
     * @param expected the expected Rectangle
     * @param actual   the Rectangle to verify
     */
    public static void assertBoundsEqualFloored(Rectangle2D expected, Rectangle2D actual) {
        assertEqualFloored(expected.getX(), actual.getX());
        assertEqualFloored(expected.getY(), actual.getY());
        assertEqualFloored(expected.getWidth(), actual.getWidth());
        assertEqualFloored(expected.getHeight(), actual.getHeight());
    }

    /**
     * Asserts that the Bounds of two given rectangles are equal within the given
     * tolerance
     *
     * @param expected     the expected Rectangle
     * @param actual       the Rectangle to verify
     * @param tolerance    The allowed deviation
     * @param ignoreCoords whether or not to ignore the x and y coords
     */
    public static void assertBoundsEqualInRange(Rectangle2D expected, Rectangle2D actual, double tolerance,
            boolean ignoreCoords) {
        if (!ignoreCoords) {
            assertEqualInRange(expected.getX(), actual.getX(), tolerance);
            assertEqualInRange(expected.getY(), actual.getY(), tolerance);
        }
        assertEqualInRange(expected.getWidth(), actual.getWidth(), tolerance);
        assertEqualInRange(expected.getHeight(), actual.getHeight(), tolerance);
    }

    /**
     * Asserts that the Sizes and Coordinates of two given rectangles are equal
     * within the given
     * tolerance
     *
     * @param expected  the expected Rectangle
     * @param actual    the Rectangle to verify
     * @param tolerance The allowed deviation
     */
    public static void assertBoundsEqualInRange(Rectangle2D expected, Rectangle2D actual, double tolerance) {
        assertBoundsEqualInRange(expected, actual, tolerance, false);
    }

    /**
     * Assert that two given shapes are Equal
     *
     * @param expected          the expected Shape
     * @param actual            the actual Shape
     * @param ignorePositioning whether or not to correct for x and y positioning
     * @param ignoreScale       whether or not to correct for different scales (only
     *                          works when {@code ignorePositioning} is set to
     *                          {@code true})
     * @param saveDiff          whether or not to save the Difference as an Image
     *                          using
     *                          {@link #saveImageDiff(BufferedImage, BufferedImage)}
     */
    public static void assertShapesEqual(Shape expected, Shape actual, boolean ignorePositioning, boolean ignoreScale,
            boolean saveDiff) {
        var img = new BufferedImage(
                (int) expected.getBounds2D().getWidth(),
                (int) expected.getBounds2D().getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        if (ignorePositioning) {
            if (ignoreScale) {
                var scaleTF = new AffineTransform();
                scaleTF.scale(
                        expected.getBounds2D().getWidth() / actual.getBounds2D().getWidth(),
                        expected.getBounds2D().getHeight() / actual.getBounds2D().getHeight());
                actual = scaleTF.createTransformedShape(actual);
            }
            // Center Shapes
            var at = new AffineTransform();
            at.translate(-actual.getBounds2D().getX(), -actual.getBounds2D().getY());
            actual = at.createTransformedShape(actual);

            var atTutor = new AffineTransform();
            atTutor.translate(-expected.getBounds2D().getX(), -expected.getBounds2D().getY());
            expected = atTutor.createTransformedShape(expected);

            // assertEquals(expected.getBounds(), actual.getBounds());
        } else {
            g2d.translate(-expected.getBounds2D().getX(), -expected.getBounds2D().getY());
            g2dTutor.translate(-expected.getBounds2D().getX(), -expected.getBounds2D().getY());
        }

        // Draw Shapes
        var outlineColor = Color.RED;
        var fillColor = new Color(0, 255, 0, 100);

        // Actual
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(fillColor);
        g2d.fill(actual);
        g2d.setColor(outlineColor);
        g2d.draw(actual);

        // Expected
        g2dTutor.setStroke(new BasicStroke(2));
        g2dTutor.setColor(fillColor);
        g2dTutor.fill(expected);
        g2dTutor.setColor(outlineColor);
        g2dTutor.draw(expected);

        // Compare
        if (saveDiff) {
            saveImageDiff(imgTutor, img);
        }
        assertImagesEqual(imgTutor, img, TestConstants.MIN_SHAPE_SIMILARITY);
    }

    /**
     * Assert that two given shapes are Equal
     *
     * @param expected          the expected Shape
     * @param actual            the actual Shape
     * @param ignorePositioning whether or not to correct for x and y positioning
     * @param ignoreScale       whether or not to correct for different scales (only
     *                          works when {@code ignorePositioning} is set to
     *                          {@code true})
     */
    public static void assertShapesEqual(Shape expected, Shape actual, boolean ignorePositioning, boolean ignoreScale) {
        assertShapesEqual(expected, actual, ignorePositioning, ignoreScale, false);
    }

    /**
     * Assert that two given shapes are Equal
     *
     * @param expected          the expected Shape
     * @param actual            the actual Shape
     * @param ignorePositioning whether or not to correct for x and y positioning
     */
    public static void assertShapesEqual(Shape expected, Shape actual, boolean ignorePositioning) {
        assertShapesEqual(expected, actual, ignorePositioning, false, false);
    }

    /**
     * Assert that two given shapes are Equal
     *
     * @param expected the expected Shape
     * @param actual   the actual Shape
     */
    public static void assertShapesEqual(Shape expected, Shape actual) {
        assertShapesEqual(expected, actual, false, false, false);
    }

    /**
     * https://stackoverflow.com/questions/6495769/how-to-get-all-elements-inside-a-jframe
     * This seems to fix the H2 Tests somehow xD
     *
     * @param c The Source Component
     * @return a List containing all the nested SubComponents
     */
    public static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        if (comps == null) {
            return compList;
        }
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container)
                compList.addAll(getAllComponents((Container) comp));
        }
        return compList;
    }

    /**
     * Calculate the absolute difference between two doubles
     *
     * @param d1 the first {@code double}
     * @param d2 the second {@code double}
     * @return the absolute difference of the given doubles
     */
    public static double difference(double d1, double d2) {
        return Math.abs(d1 - d2);
    }

    /**
     * Check whether two doubles equal with a given tolerance
     *
     * @param d1        The first {@code double}
     * @param d2        The second {@code double}
     * @param tolerance The allowed tolerance
     * @return {@code true}, if the two doubles equal with the given tolerance
     */
    public static boolean equalWithTolerance(double d1, double d2, double tolerance) {
        return difference(d1, d2) <= tolerance;
    }

    /**
     * Assert that two doubles equal with a given tolerance
     *
     * @param expected  The expected {@code double}
     * @param actual    The actual {@code double}
     * @param tolerance The allowed tolerance
     * @param message   an optional Message, or {@code null}
     */
    public static void assertEqualInRange(double expected, double actual, double tolerance, String message) {
        if (tolerance <= 0) {
            assertEquals(expected, actual);
        } else {
            if (!equalWithTolerance(expected, actual, tolerance)) {

                throw new AssertionFailedError(
                        message + String.format("expected number in range: [%f,%f] but was: <%f>", expected - tolerance,
                                expected + tolerance, actual),
                        expected, actual);
            }
        }
    }

    /**
     * Assert that two doubles equal with a given tolerance
     *
     * @param expected  The expected {@code double}
     * @param actual    The actual {@code double}
     * @param tolerance The allowed tolerance
     */
    public static void assertEqualInRange(double expected, double actual, double range) {
        assertEqualInRange(expected, actual, range, (String) null);
    }

    /**
     * Assert that two doubles equal when floored
     *
     * @param expected The expected {@code double}
     * @param actual   The actual {@code double}
     * @param message  an optional Message, or {@code null}
     */
    public static void assertEqualFloored(double expected, double actual, String message) {
        assertEquals(Math.floor(expected), Math.floor(actual));
    }

    /**
     * Assert that two doubles equal when floored
     *
     * @param expected The expected {@code double}
     * @param actual   The actual {@code double}
     */
    public static void assertEqualFloored(double expected, double actual) {
        assertEqualFloored(expected, actual, (String) null);
    }
}
