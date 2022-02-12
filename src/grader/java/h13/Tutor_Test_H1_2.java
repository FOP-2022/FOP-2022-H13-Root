package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import h13.MyPanel;

public class Tutor_Test_H1_2 {
    @Test
    public void testColorWithAlpha() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        Stream.generate(() -> new Random().nextFloat()).limit(15).forEach((alpha) -> {
            assertEquals(mpt.colorWithAlpha(Color.MAGENTA, alpha), mp.colorWithAlpha(Color.MAGENTA, alpha),
                    "Die zurückgegebene Farbe ist nicht die geforderte Farbe.");
        });
    }

    @Test
    public void testColorWithSaturation() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        Stream.generate(() -> new Random().nextFloat()).limit(15).forEach((saturation) -> {
            assertEquals(mpt.colorWithSaturation(Color.MAGENTA, saturation),
                    mp.colorWithSaturation(Color.MAGENTA, saturation));
        });
    }

    @Test
    public void testCenterShape_noChanges() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(1920, 1080);
        mpt.setSize(1920, 1080);
        // Clean
        var actualShape = mp.centerShape(new Rectangle2D.Double(0, 0, 1920, 1080), 1, 1, 0);
        var expectedShape = mpt.centerShape(new Rectangle2D.Double(0, 0, 1920, 1080), 1, 1, 0);
        assertEquals(expectedShape, actualShape);
    }

    @Test
    public void testCenterShape_Centering() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(1920, 1080);
        mpt.setSize(1920, 1080);
        // Resizing needed
        assertEquals(mp.centerShape(new Rectangle2D.Double(-420, 1337, 1920, 1080), 1, 1, 42),
                mpt.centerShape(new Rectangle2D.Double(-420, 1337, 1920, 1080), 1, 1, 42));
    }

    @Test
    public void testCenterShape_Resize() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(1920, 1080);
        mpt.setSize(1920, 1080);
        // Scaling
        assertEquals(mp.centerShape(new Rectangle2D.Double(0, 0, 1920, 1080), .5, .5, 0),
                mpt.centerShape(new Rectangle2D.Double(0, 0, 1920, 1080), .5, .5, 0));
        // Scaling 2
        assertEquals(mp.centerShape(new Rectangle2D.Double(0, 0, 2, 2), .5, .5, 0),
                mpt.centerShape(new Rectangle2D.Double(0, 0, 2, 2), .5, .5, 0));
    }

    @Test
    public void testCenterShape_Border() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(1920, 1080);
        mpt.setSize(1920, 1080);
        // No Resizing needed
        assertEquals(mp.centerShape(new Rectangle2D.Double(21, 21, 1878, 1038), 1, 1, 42),
                mpt.centerShape(new Rectangle2D.Double(21, 21, 1878, 1038), 1, 1, 42));
        // Resizing needed for border
        assertEquals(mp.centerShape(new Rectangle2D.Double(0, 0, 1920, 1080), 1, 1, 42),
                mpt.centerShape(new Rectangle2D.Double(0, 0, 1920, 1080), 1, 1, 42));
    }

    @Test
    public void testCenterShape_All() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(1920, 1080);
        mpt.setSize(1920, 1080);
        // Scaling+Border+Resize
        assertEquals(mp.centerShape(new Rectangle2D.Double(-69, 420, 1337, 1353), .666, .0815, 123),
                mpt.centerShape(new Rectangle2D.Double(-69, 420, 1337, 1353), .666, .0815, 123));
    }

    @Test
    public void testFillDraw() {
        var img = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
        var g2d = img.createGraphics();
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(1920, 1080);
        mpt.setSize(1920, 1080);
        // Scaling+Border+Resize
        assertEquals(mp.centerShape(new Rectangle2D.Double(-69, 420, 1337, 1353), .666, .0815, 123),
                mpt.centerShape(new Rectangle2D.Double(-69, 420, 1337, 1353), .666, .0815, 123));
    }
}
