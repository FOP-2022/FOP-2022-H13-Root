package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h13")
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
        mp.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        mpt.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        // Clean
        var actualShape = mp.centerShape(
                new Rectangle2D.Double(0, 0, TestConstants.getScreenWidth(), TestConstants.getScreenHeight()), 1, 1, 0);
        var expectedShape = mpt.centerShape(
                new Rectangle2D.Double(0, 0, TestConstants.getScreenWidth(), TestConstants.getScreenHeight()), 1, 1, 0);
        // Since this is not that hard, we don't use Bound Tolerance here
        TestUtils.assertBoundsEqualInRange(expectedShape, actualShape, TestConstants.BOUND_TOLERANCE);
    }

    @Test
    public void testCenterShape_Centering() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        mpt.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        // Resizing needed
        TestUtils.assertShapeCentered(
                mp.centerShape(new Rectangle2D.Double(-420, 1337, TestConstants.getScreenWidth(),
                        TestConstants.getScreenHeight()), 1, 1, 42),
                TestConstants.screenResolution, TestConstants.BOUND_TOLERANCE);
    }

    @Test
    public void testCenterShape_Resize() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        mpt.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        // Scaling
        TestUtils.assertBoundsEqualInRange(
                mpt.centerShape(
                        new Rectangle2D.Double(0, 0, TestConstants.getScreenWidth(), TestConstants.getScreenHeight()),
                        .5, .5, 0),
                mp.centerShape(
                        new Rectangle2D.Double(0, 0, TestConstants.getScreenWidth(), TestConstants.getScreenHeight()),
                        .5, .5, 0),
                TestConstants.BOUND_TOLERANCE, true);
        // Scaling 2
        TestUtils.assertBoundsEqualInRange(mpt.centerShape(new Rectangle2D.Double(0, 0, 2, 2), .5, .5, 0),
                mp.centerShape(new Rectangle2D.Double(0, 0, 2, 2), .5, .5, 0), TestConstants.BOUND_TOLERANCE, true);
    }

    @Test
    public void testCenterShape_Border() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        mpt.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        // Resizing needed for border
        TestUtils.assertBoundsEqualInRange(
                mpt.centerShape(
                        new Rectangle2D.Double(0, 0, TestConstants.getScreenWidth(), TestConstants.getScreenHeight()),
                        1, 1, 42),
                mp.centerShape(
                        new Rectangle2D.Double(0, 0, TestConstants.getScreenWidth(), TestConstants.getScreenHeight()),
                        1, 1, 42),
                TestConstants.BOUND_TOLERANCE, true);
    }

    @Test
    public void testCenterShape_All() {
        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();
        mp.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        mpt.setSize(TestConstants.getScreenWidth(), TestConstants.getScreenHeight());
        // Scaling+Border+Resize
        TestUtils.assertBoundsEqualInRange(
                mpt.centerShape(new Rectangle2D.Double(-69, 420, 1337, 1353), .666, .0815, 32),
                mp.centerShape(new Rectangle2D.Double(-69, 420, 1337, 1353), .666, .0815, 32),
                TestConstants.BOUND_TOLERANCE, false);
    }

    @Test
    public void testFillDraw_NoBorder() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        mp.fillDraw(g2d, Color.GREEN, Color.GREEN, 0, new Ellipse2D.Double(0, 0, img.getWidth(), img.getHeight()));
        mpt.fillDraw(g2dTutor, Color.GREEN, Color.GREEN, 0,
                new Ellipse2D.Double(0, 0, img.getWidth(), img.getHeight()));

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testFillDraw_Border() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        mp.fillDraw(g2d, Color.GREEN, Color.GREEN, 0, mpt.centerShape(new Ellipse2D.Double(), 1, 1, 20));
        mpt.fillDraw(g2dTutor, Color.GREEN, Color.GREEN, 0, mpt.centerShape(new Ellipse2D.Double(), 1, 1, 20));

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testFillDraw_ALL() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        var r = new Random(694201337);
        Stream.of(
                mpt.centerShape(new Ellipse2D.Double(), r.nextDouble(0.1, 1), r.nextDouble(0.1, 1), r.nextInt(1, 100)),
                mpt.centerShape(new Rectangle2D.Double(), r.nextDouble(0.1, 1), r.nextDouble(0.1, 1),
                        r.nextInt(1, 100)),
                mpt.scaleTextToWidth(g2dTutor, r.nextInt(0, imgTutor.getWidth()), r.nextInt(2, 500),
                        TestUtils.createRandomString(r, 5, 40), new Font("Arial", Font.ITALIC, 42)))
                .forEachOrdered(s -> {
                    var interiorColor = mpt.colorWithAlpha(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),
                            r.nextFloat(0.1f, 1f));
                    var borderColor = mpt.colorWithSaturation(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),
                            r.nextFloat(0.1f, 1f));
                    var borderWidth = r.nextInt(1, 100);

                    // Modify g2d
                    var strokeColor = mpt.colorWithAlpha(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),
                            r.nextFloat(0.1f, 1f));
                    g2d.setColor(strokeColor);

                    var stroke = new BasicStroke(r.nextInt(256), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
                    g2d.setStroke(stroke);

                    mp.fillDraw(g2d, interiorColor, borderColor, borderWidth, s);
                    mpt.fillDraw(g2dTutor, interiorColor, borderColor, borderWidth, s);

                    // Compare Images
                    TestUtils.assertImagesEqual(imgTutor, img);
                    assertEquals(strokeColor, g2d.getColor(), "Color of g2d was modified.");
                    assertEquals(stroke, g2d.getStroke(), "Stroke of g2d was modified.");
                });
    }
}
