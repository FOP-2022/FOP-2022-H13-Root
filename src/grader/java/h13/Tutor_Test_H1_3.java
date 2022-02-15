package h13;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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

    @Test
    public void testDrawColoredString_NoBorder() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        var f = new Font("Default", Font.PLAIN, 16);

        MyPanel mpSpy = spy(mock(MyPanel.class, Answers.CALLS_REAL_METHODS));
        MyPanelTutor mpt = new MyPanelTutor();
        try {
            mpSpy.setBounds(img.getRaster().getBounds());
        } catch (Exception e) {
            // Do nothing
        }
        mpt.setBounds(imgTutor.getRaster().getBounds());

        var r = new Random(694201337);
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(),
                "FOP-2022").getVisualBounds();

        // Assure that scale works correctly
        doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(
                        i.getArgument(0),
                        i.getArgument(1),
                        i.getArgument(2),
                        i.getArgument(3),
                        i.getArgument(4));
            }
        })
                .when(mpSpy).scaleTextToWidth(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.anyFloat(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Font.class));
        mpSpy.drawColoredString(g2d,
                Color.BLUE,
                Color.BLUE,
                0,
                "FOP-2022",
                f,
                img.getWidth() / fontBounds.getWidth());
        mpt.drawColoredString(g2dTutor,
                Color.BLUE,
                Color.BLUE,
                0,
                "FOP-2022",
                f,
                img.getWidth() / fontBounds.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testDrawColoredString_Border() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        var f = new Font("Default", Font.PLAIN, 16);

        MyPanel mpSpy = spy(mock(MyPanel.class, Answers.CALLS_REAL_METHODS));
        MyPanelTutor mpt = new MyPanelTutor();
        try {
            mpSpy.setBounds(img.getRaster().getBounds());
        } catch (Exception e) {
            // Do nothing
        }
        mpt.setBounds(imgTutor.getRaster().getBounds());

        var r = new Random(694201337);
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(),
                "FOP-2022").getVisualBounds();

        // Assure that scale works correctly
        doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(
                        i.getArgument(0),
                        i.getArgument(1),
                        i.getArgument(2),
                        i.getArgument(3),
                        i.getArgument(4));
            }
        })
                .when(mpSpy).scaleTextToWidth(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.anyFloat(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Font.class));
        mpSpy.drawColoredString(g2d,
                Color.BLUE,
                Color.BLUE,
                5,
                "FOP-2022",
                f,
                img.getWidth() / fontBounds.getWidth());
        mpt.drawColoredString(g2dTutor,
                Color.BLUE,
                Color.BLUE,
                5,
                "FOP-2022",
                f,
                img.getWidth() / fontBounds.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testDrawColoredString_TextFontColor() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        var r = new Random(694201337);
        var f = new Font("Arial", Font.ITALIC, 16);
        var text = TestUtils.createRandomString(r, 5, 35);

        MyPanel mpSpy = spy(mock(MyPanel.class, Answers.CALLS_REAL_METHODS));
        MyPanelTutor mpt = new MyPanelTutor();
        try {
            mpSpy.setBounds(img.getRaster().getBounds());
        } catch (Exception e) {
            // Do nothing
        }
        mpt.setBounds(imgTutor.getRaster().getBounds());

        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(),
                text).getVisualBounds();

        // Assure that scale works correctly
        doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(
                        i.getArgument(0),
                        i.getArgument(1),
                        i.getArgument(2),
                        i.getArgument(3),
                        i.getArgument(4));
            }
        })
                .when(mpSpy).scaleTextToWidth(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.anyFloat(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Font.class));
        mpSpy.drawColoredString(g2d,
                Color.RED,
                Color.YELLOW,
                0,
                text,
                f,
                img.getWidth() / fontBounds.getWidth());
        mpt.drawColoredString(g2dTutor,
                Color.RED,
                Color.YELLOW,
                0,
                text,
                f,
                img.getWidth() / fontBounds.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testDrawColoredString_All() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        var r = new Random(694201337);
        var f = new Font("Arial", Font.ITALIC, 16);
        var text = TestUtils.createRandomString(r, 5, 35);

        MyPanel mpSpy = spy(mock(MyPanel.class, Answers.CALLS_REAL_METHODS));
        MyPanelTutor mpt = new MyPanelTutor();
        try {
            mpSpy.setBounds(img.getRaster().getBounds());
        } catch (Exception e) {
            // Do nothing
        }
        mpt.setBounds(imgTutor.getRaster().getBounds());

        // Assure that scale works correctly
        doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(
                        i.getArgument(0),
                        i.getArgument(1),
                        i.getArgument(2),
                        i.getArgument(3),
                        i.getArgument(4));
            }
        })
                .when(mpSpy).scaleTextToWidth(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.anyFloat(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Font.class));
        mpSpy.drawColoredString(g2d,
                Color.RED,
                Color.YELLOW,
                4,
                text,
                f,
                .3 * img.getWidth());
        mpt.drawColoredString(g2dTutor,
                Color.RED,
                Color.YELLOW,
                4,
                text,
                f,
                .3 * img.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }
}
