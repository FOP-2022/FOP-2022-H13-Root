package h13;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h13")
public class Tutor_Test_H1_3 {
    BufferedImage img;
    BufferedImage imgTutor;
    Graphics2D g2d;
    Graphics2D g2dTutor;
    MyPanel mp;
    MyPanelTutor mpt;
    Font f;
    Random r;
    static Method sttwStudent;
    static Method dcsStudent;

    @BeforeAll
    static void setupAll() throws NoSuchMethodException, SecurityException {
        try {
            sttwStudent = MyPanel.class.getDeclaredMethod("scaleTextToWidth", Graphics2D.class, double.class,
                    float.class, String.class, Font.class);
        } catch (Exception e) {
            sttwStudent = MyPanel.class.getDeclaredMethod("scaleTextToWidth", Graphics2D.class, double.class, int.class,
                    String.class, Font.class);
        }
        try {
            dcsStudent = MyPanel.class.getDeclaredMethod("drawColoredString", Graphics2D.class, Color.class, Color.class,
                    float.class, String.class, Font.class, double.class);
        } catch (Exception e) {
            dcsStudent = MyPanel.class.getDeclaredMethod("drawColoredString", Graphics2D.class, Color.class, Color.class, int.class,
                    String.class, Font.class, double.class);
        }
    }

    @BeforeEach
    void setupEach() {
        img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        g2d = img.createGraphics();
        g2dTutor = imgTutor.createGraphics();

        mp = new MyPanel();
        mpt = new MyPanelTutor();

        // ethod sttwMethod;
        // try {
        // sttwMethod = MyPanel.class.getDeclaredMethod("scaleTextToWidth",
        // Graphics2D.class, double.class,
        // float.class, String.class, Font.class);
        // } catch (Exception e) {
        // sttwMethod = MyPanel.class.getDeclaredMethod("scaleTextToWidth",
        // Graphics2D.class, double.class, int.class,
        // String.class, Font.class);
        // }
        // final Method sttwMethodFinal = sttwMethod;
        // sttwFunction = new Function6<String, Graphics2D, Number, Number, String,
        // Font, Shape>() {
        // @Override
        // public Shape invoke(String arg0, Graphics2D arg1, Number arg2, Number arg3,
        // String arg4, Font arg5) {
        // // TODO Auto-generated method stub
        // try {
        // return (Shape) sttwMethodFinal.invoke(mp, arg0, arg1, arg2, arg3, arg4,
        // arg5);
        // } catch (IllegalAccessException | IllegalArgumentException |
        // InvocationTargetException e) {
        // fail("Could not find scaleTextToWidthMethod");
        // return null;
        // }
        // }
        // };

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        f = new Font("Arial", Font.PLAIN, 42);

        r = new Random(694201337);
    }

    @AfterEach
    void cleanup() {

    }

    @Test
    public void testScaleTextToWidth_No_Tf_Needed()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(), TestUtils.createRandomString(r, 5, 15))
                .getVisualBounds();

        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, img.getWidth() / fontBounds.getWidth(), 0, text, f);
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, img.getWidth() / fontBounds.getWidth(), 0, text, f);

        TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
    }

    @Test
    public void testScaleTextToWidth_CenterOnly()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(), TestUtils.createRandomString(r, 5, 15))
                .getVisualBounds();

        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, img.getWidth() / fontBounds.getWidth(), 0, text, f);
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, img.getWidth() / fontBounds.getWidth(), 0, text, f);

        TestUtils.assertShapeCentered(studentShape, img.getRaster().getBounds());
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testScaleTextToWidth_ScaleOnly_NoBorder()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, img.getWidth(), 0, text,
                new Font("Arial", Font.PLAIN, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, img.getWidth(), 0, text, new Font("Arial", Font.PLAIN, 42));

        TestUtils.assertBoundsEqualInRange(tutorShape.getBounds2D(), studentShape.getBounds2D(),
                TestConstants.BOUND_TOLERANCE, true);
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testScaleTextToWidth_ScaleOnly_FullWidth()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, img.getWidth(), 5, text, new Font("Arial", Font.PLAIN, 42));

        TestUtils.assertBoundsEqualInRange(tutorShape.getBounds2D(), studentShape.getBounds2D(),
                TestConstants.BOUND_TOLERANCE, true);
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testScaleTextToWidth_ScaleOnly()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, .34 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, .34 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));

        TestUtils.assertBoundsEqualInRange(tutorShape.getBounds2D(), studentShape.getBounds2D(),
                TestConstants.BOUND_TOLERANCE, true);
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testScaleTextToWidth_ScaleAndCenter_NoBorder()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, .5 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, .5 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));
        TestUtils.assertShapeCentered(studentShape, img.getRaster().getBounds());
        TestUtils.assertBoundsEqualInRange(tutorShape.getBounds2D(), studentShape.getBounds2D(),
                TestConstants.BOUND_TOLERANCE, true);
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testScaleTextToWidth_ScaleAndCenter()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, .254 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, .254 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 42));

        TestUtils.assertShapeCentered(studentShape, img.getRaster().getBounds());
        TestUtils.assertBoundsEqualInRange(tutorShape.getBounds2D(), studentShape.getBounds2D(),
                TestConstants.BOUND_TOLERANCE, true);
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testScaleTextToWidth_ScaleAndCenter_BiggerThanScreen()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var text = TestUtils.createRandomString(r, 5, 10);

        Shape studentShape = (Shape) sttwStudent.invoke(mp, g2d, 1.2 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 420));
        var tutorShape = mpt.scaleTextToWidth(g2dTutor, 1.2 * img.getWidth(), 5, text,
                new Font("Arial", Font.PLAIN, 420));

        TestUtils.assertShapeCentered(studentShape, img.getRaster().getBounds());
        TestUtils.assertBoundsEqualInRange(tutorShape.getBounds2D(), studentShape.getBounds2D(),
                TestConstants.BOUND_TOLERANCE, true);
        if (TestConstants.TEST_CORRECT_SHAPES_ON_SCALE_TESTS) {
            TestUtils.assertShapesEqual(tutorShape, studentShape, true, true);
        }
    }

    @Test
    public void testDrawColoredString_NoBorder()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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

        // var r = new Random(694201337);
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(), "FOP-2022").getVisualBounds();

        // Assure that scale works correctly
        sttwStudent.invoke(doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(i.getArgument(0), i.getArgument(1), i.getArgument(2), i.getArgument(3),
                        i.getArgument(4));
            }
        }).when(mpSpy), ArgumentMatchers.any(), ArgumentMatchers.anyDouble(), (int) ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(Font.class));
        dcsStudent.invoke(mpSpy, g2d, Color.BLUE, Color.BLUE, 0, "FOP-2022", f, img.getWidth() / fontBounds.getWidth());
        mpt.drawColoredString(g2dTutor, Color.BLUE, Color.BLUE, 0, "FOP-2022", f,
                img.getWidth() / fontBounds.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testDrawColoredString_Border()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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

        // var r = new Random(694201337);
        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(), "FOP-2022").getVisualBounds();

        // Assure that scale works correctly
        sttwStudent.invoke(doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(i.getArgument(0), i.getArgument(1), i.getArgument(2), i.getArgument(3),
                        i.getArgument(4));
            }
        }).when(mpSpy), ArgumentMatchers.any(), ArgumentMatchers.anyDouble(), (int) ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(Font.class));
        dcsStudent.invoke(mpSpy, g2d, Color.BLUE, Color.BLUE, 5, "FOP-2022", f, img.getWidth() / fontBounds.getWidth());
        mpt.drawColoredString(g2dTutor, Color.BLUE, Color.BLUE, 5, "FOP-2022", f,
                img.getWidth() / fontBounds.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testDrawColoredString_TextFontColor()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        var r = new Random(694201337);
        var f = new Font("Arial", Font.PLAIN, 16);
        var text = TestUtils.createRandomString(r, 5, 35);

        MyPanel mpSpy = spy(mock(MyPanel.class, Answers.CALLS_REAL_METHODS));
        MyPanelTutor mpt = new MyPanelTutor();
        try {
            mpSpy.setBounds(img.getRaster().getBounds());
        } catch (Exception e) {
            // Do nothing
        }
        mpt.setBounds(imgTutor.getRaster().getBounds());

        Rectangle2D fontBounds = f.createGlyphVector(g2d.getFontRenderContext(), text).getVisualBounds();

        // Assure that scale works correctly
        sttwStudent.invoke(doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(i.getArgument(0), i.getArgument(1), i.getArgument(2), i.getArgument(3),
                        i.getArgument(4));
            }
        }).when(mpSpy), ArgumentMatchers.any(), ArgumentMatchers.anyDouble(), (int) ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(Font.class));
        dcsStudent.invoke(mpSpy, g2d, Color.RED, Color.YELLOW, 0, text, f, img.getWidth() / fontBounds.getWidth());
        mpt.drawColoredString(g2dTutor, Color.RED, Color.YELLOW, 0, text, f, img.getWidth() / fontBounds.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testDrawColoredString_All()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        var f = new Font("Arial", Font.PLAIN, 16);
        var text = TestUtils.createRandomString(r, 5, 35);

        MyPanel mpSpy = spy(mock(MyPanel.class, Answers.CALLS_REAL_METHODS));
        MyPanelTutor mpt = new MyPanelTutor();
        try {
            mpSpy.setBounds(img.getRaster().getBounds());
        } catch (Exception e) {
            // Do nothing
        }
        mpt.setBounds(imgTutor.getRaster().getBounds());

        sttwStudent.invoke(doAnswer(new Answer<>() {
            @Override
            public Object answer(InvocationOnMock i) throws Throwable {
                return mpt.scaleTextToWidth(i.getArgument(0), i.getArgument(1), i.getArgument(2), i.getArgument(3),
                        i.getArgument(4));
            }
        }).when(mpSpy), ArgumentMatchers.any(), ArgumentMatchers.anyDouble(), (int) ArgumentMatchers.anyFloat(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(Font.class));
        dcsStudent.invoke(mpSpy, g2d, Color.RED, Color.YELLOW, 4, text, f, .3 * img.getWidth());
        mpt.drawColoredString(g2dTutor, Color.RED, Color.YELLOW, 4, text, f, .3 * img.getWidth());

        // Compare Images
        TestUtils.assertImagesEqual(imgTutor, img);
    }
}
