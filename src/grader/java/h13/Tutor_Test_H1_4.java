package h13;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

import java.awt.image.BufferedImage;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

/**
 * @author Ruben Deisenroth
 */
public class Tutor_Test_H1_4 {

    /**
     * Tests the paint() method with the given Figures
     *
     * @param figuresToTest The Figures to test
     * @param altAddMethod  whether to rely on students add Methods
     */
    public void testFigures(Map<MyPanelTutor.Figure, MyPanel.Figure> figuresToTest, boolean altAddMethod) {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = spy(new MyPanel());
        MyPanelTutor mpt = new MyPanelTutor();
        doAnswer(i -> mpt.colorWithAlpha(i.getArgument(0), i.getArgument(1)))
                .when(mp).colorWithAlpha(ArgumentMatchers.any(), (int) ArgumentMatchers.anyDouble());
        doAnswer(i -> mpt.colorWithSaturation(i.getArgument(0), i.getArgument(1)))
                .when(mp).colorWithSaturation(ArgumentMatchers.any(), (int) ArgumentMatchers.anyDouble());
        doAnswer(i -> {
            mpt.fillDraw(
                    i.getArgument(0),
                    i.getArgument(1),
                    i.getArgument(2),
                    i.getArgument(3),
                    i.getArgument(4));
            return null;
        })
                .when(mp).fillDraw(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any(),
                        (int) ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.any());
        doAnswer(i -> {
            mpt.fillDrawCentered(
                    i.getArgument(0),
                    i.getArgument(1),
                    i.getArgument(2),
                    i.getArgument(3),
                    i.getArgument(4),
                    i.getArgument(5),
                    i.getArgument(6));
            return null;
        })
                .when(mp).fillDrawCentered(
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any(),
                        (int) ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.anyDouble(),
                        ArgumentMatchers.anyDouble());

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        mp.alpha = 0.4f;
        mpt.alpha = 0.4f;
        mp.saturation = 0.7f;
        mpt.saturation = 0.7f;
        mp.zoom = 0.2f;
        mpt.zoom = 0.2f;
        mp.borderWidth = 25;
        mpt.borderWidth = 25;

        try {
            mp.removeYellowRectangle();
            mp.removeGreenEllipse();
            mp.removeBlueString();
        } catch (Exception e) {
        }
        mp.figuresToDisplay.clear();
        mpt.figuresToDisplay.clear();
        if (figuresToTest != null && !figuresToTest.isEmpty()) {
            for (var f : figuresToTest.entrySet()) {
                var figure = f.getValue();
                var tutorFigure = f.getKey();
                if (figure != null) {
                    if (altAddMethod) {
                        switch (figure) {
                            case YELLOW_RECTANGLE:
                                mp.addYellowRectangle();
                                break;
                            case GREEN_ELLIPSE:
                                mp.addGreenEllipse();
                                break;
                            case BLUE_STRING:
                                mp.addBlueString();
                                break;
                        }
                    } else {
                        mp.figuresToDisplay.add(figure);
                    }
                }
                if (tutorFigure != null) {
                    if (altAddMethod) {
                        switch (tutorFigure) {
                            case YELLOW_RECTANGLE:
                                mpt.addYellowRectangle();
                                break;
                            case GREEN_ELLIPSE:
                                mpt.addGreenEllipse();
                                break;
                            case BLUE_STRING:
                                mpt.addBlueString();
                                break;
                        }
                    } else {
                        mpt.figuresToDisplay.add(tutorFigure);
                    }
                }
            }
        }

        mp.paint(g2d);
        mpt.paint(g2dTutor);
        // TestUtils.saveImage(imgTutor);
        // TestUtils.saveImage(img);
        // TestUtils.saveImageDiff(imgTutor, img);
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testBlank() {
        testFigures(null, false);
    }

    @Test
    public void testYellowRectangle() {
        testFigures(Map.of(MyPanelTutor.Figure.YELLOW_RECTANGLE, MyPanel.Figure.YELLOW_RECTANGLE), false);
    }

    @Test
    public void testYellowRectangle_alt() {
        testFigures(Map.of(MyPanelTutor.Figure.YELLOW_RECTANGLE, MyPanel.Figure.YELLOW_RECTANGLE), true);
    }

    @Test
    public void testGreenEllipse() {
        testFigures(Map.of(MyPanelTutor.Figure.GREEN_ELLIPSE, MyPanel.Figure.GREEN_ELLIPSE), false);
    }

    @Test
    public void testGreenEllipse_alt() {
        testFigures(Map.of(MyPanelTutor.Figure.GREEN_ELLIPSE, MyPanel.Figure.GREEN_ELLIPSE), true);
    }

    @Test
    public void testBlueString() {
        testFigures(Map.of(MyPanelTutor.Figure.BLUE_STRING, MyPanel.Figure.BLUE_STRING), false);
    }

    @Test
    public void testBlueString_alt() {
        testFigures(Map.of(MyPanelTutor.Figure.BLUE_STRING, MyPanel.Figure.BLUE_STRING), true);
    }

    @Test
    public void testThreeFigures() {
        testFigures(Map.of(
                MyPanelTutor.Figure.BLUE_STRING, MyPanel.Figure.BLUE_STRING,
                MyPanelTutor.Figure.GREEN_ELLIPSE, MyPanel.Figure.GREEN_ELLIPSE,
                MyPanelTutor.Figure.YELLOW_RECTANGLE, MyPanel.Figure.YELLOW_RECTANGLE),
                false);
    }

    @Test
    public void testThreeFigures_alt() {
        testFigures(Map.of(
                MyPanelTutor.Figure.BLUE_STRING, MyPanel.Figure.BLUE_STRING,
                MyPanelTutor.Figure.GREEN_ELLIPSE, MyPanel.Figure.GREEN_ELLIPSE,
                MyPanelTutor.Figure.YELLOW_RECTANGLE, MyPanel.Figure.YELLOW_RECTANGLE),
                true);
    }
}
