package h13;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Ruben Deisenroth
 */
public class Tutor_Test_H1_4 {
    @Test
    public void testBlank() {
        var img = new BufferedImage(TestConstants.getScreenWidth(), TestConstants.getScreenHeight(),
                BufferedImage.TYPE_INT_ARGB);
        var imgTutor = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        var g2d = img.createGraphics();
        var g2dTutor = imgTutor.createGraphics();

        MyPanel mp = new MyPanel();
        MyPanelTutor mpt = new MyPanelTutor();

        mp.setBounds(img.getRaster().getBounds());
        mpt.setBounds(imgTutor.getRaster().getBounds());

        try {
            mp.removeYellowRectangle();
            mp.removeGreenEllipse();
            mp.removeBlueString();
        } catch (Exception e) {
        }
        mp.figuresToDisplay.clear();
        mpt.figuresToDisplay.clear();

        mp.paint(g2d);
        mpt.paint(g2dTutor);
        TestUtils.assertImagesEqual(imgTutor, img);
    }

    @Test
    public void testYellowRectangle() {
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

        mp.figuresToDisplay.add(MyPanel.Figure.YELLOW_RECTANGLE);
        mpt.figuresToDisplay.add(MyPanelTutor.Figure.YELLOW_RECTANGLE);

        mp.paint(g2d);
        mpt.paint(g2dTutor);
        // TestUtils.saveImage(imgTutor);
        // TestUtils.saveImage(img);
        // TestUtils.saveImageDiff(imgTutor, img);
        TestUtils.assertImagesEqual(imgTutor, img);
    }
}
