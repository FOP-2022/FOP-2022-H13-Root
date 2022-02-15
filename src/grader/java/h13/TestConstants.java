package h13;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class TestConstants {
    public static Rectangle screenResolution = new Rectangle(1920, 1080);

    public static int getScreenWidth() {
        return (int) screenResolution.getWidth();
    }

    public static int getScreenHeight() {
        return (int) screenResolution.getHeight();
    }
}
