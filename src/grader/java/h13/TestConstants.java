package h13;

import java.awt.Rectangle;

/**
 * Test Constants that are commonly used throughout the tests
 *
 * @author Ruben Deisenroth
 */
public class TestConstants {
    /**
     * The testing resolution
     */
    public static Rectangle screenResolution = new Rectangle(1920, 1080);
    /**
     * Whether to verify the shape or just the bounds on scale Tests
     */
    public static Boolean TEST_CORRECT_SHAPES_ON_SCALE_TESTS = true;
    /**
     * The Tolerance when verifying bounds.
     *
     * @see TestUtils#assertBoundsEqualInRange(java.awt.geom.Rectangle2D,
     *      java.awt.geom.Rectangle2D, double)
     */
    public static double BOUND_TOLERANCE = 2;
    /**
     * The Minimum similarity when comparing Shapes
     *
     * @see TestUtils#assertImagesEqual(java.awt.image.BufferedImage,
     *      java.awt.image.BufferedImage, double)
     */
    public static double MIN_SHAPE_SIMILARITY = 0.98;

    /**
     * Get the virtual screen Width used for testing
     *
     * @return the screen Width
     */
    public static int getScreenWidth() {
        return (int) screenResolution.getWidth();
    }

    /**
     * Get the virtual screen height used for testing
     *
     * @return the screen height
     */
    public static int getScreenHeight() {
        return (int) screenResolution.getHeight();
    }
}
