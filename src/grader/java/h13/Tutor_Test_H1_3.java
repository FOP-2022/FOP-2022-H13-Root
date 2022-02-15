package h13;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class Tutor_Test_H1_3 {
    @Test
    public void testScaleTextToWidth_No_Tf_Needed() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        var f = new Font("Arial", Font.ITALIC, 42);

        var r = new Random(694201337);
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(),
                TestUtils.createRandomString(r, 5, 15)).getVisualBounds();

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                img.getWidth() / fontBounds.getWidth(),
                0,
                text,
                f);
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                img.getWidth() / fontBounds.getWidth(),
                0,
                text,
                f);

        TestUtils.assertShapesEqual(tutorShape, studentShape, true);
    }

    @Test
    public void testScaleTextToWidth_CenterOnly() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        var f = new Font("Arial", Font.ITALIC, 42);

        var r = new Random(694201337);
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(),
                TestUtils.createRandomString(r, 5, 15)).getVisualBounds();

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                img.getWidth() / fontBounds.getWidth(),
                0,
                text,
                f);
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                img.getWidth() / fontBounds.getWidth(),
                0,
                text,
                f);

        TestUtils.assertShapesEqual(tutorShape, studentShape, false);
    }

    @Test
    public void testScaleTextToWidth_ScaleOnly_NoBorder() {
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

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));

        TestUtils.assertShapesEqual(tutorShape, studentShape, true);
    }

    @Test
    public void testScaleTextToWidth_ScaleOnly_FullWidth() {
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

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));

        TestUtils.assertShapesEqual(tutorShape, studentShape, true);
    }

    @Test
    public void testScaleTextToWidth_ScaleOnly() {
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

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                .34 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                .34 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));

        TestUtils.assertShapesEqual(tutorShape, studentShape, true);
    }

    @Test
    public void testScaleTextToWidth_ScaleAndCenter_NoBorder() {
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

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                .5 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                .5 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));

        TestUtils.assertShapesEqual(tutorShape, studentShape, false);
    }

    @Test
    public void testScaleTextToWidth_ScaleAndCenter() {
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

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                .254 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                .254 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 42));

        TestUtils.assertShapesEqual(tutorShape, studentShape, false);
    }

    @Test
    public void testScaleTextToWidth_ScaleAndCenter_BiggerThanScreen() {
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

        var text = TestUtils.createRandomString(r, 5, 10);

        var studentShape = mp.scaleTextToWidth(g2d,
                1.2 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 420));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor,
                1.2 * img.getWidth(),
                5,
                text,
                new Font("Arial", Font.ITALIC, 420));

        TestUtils.assertShapesEqual(tutorShape, studentShape, false);
    }
}
