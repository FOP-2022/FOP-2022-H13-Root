package h13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class Tutor_Test_H1_1 {
    @Test
    void testDefaultValues() {
        var mp = new MyPanel();
        var mpt = new MyPanelTutor();
        assertEquals(mp.getAlpha(), mpt.getAlpha());
        assertEquals(mp.getSaturation(), mpt.getSaturation());
        assertEquals(mp.getZoom(), mpt.getZoom());
        assertEquals(mp.getText(), mpt.getText());
        assertEquals(mp.getFont(), mpt.getFont());
        assertEquals(mp.getBorderWidth(), mpt.getBorderWidth());
    }

    @Test
    void testAddShapeMethods() {
        var mp = new MyPanel();
        var mpt = new MyPanelTutor();
        List<MyPanel.Figure> mpStandartFigs = new ArrayList<>(List.of(
                MyPanel.Figure.BLUE_STRING,
                MyPanel.Figure.YELLOW_RECTANGLE,
                MyPanel.Figure.GREEN_ELLIPSE));
        List<MyPanelTutor.Figure> mptStandartFigs = new ArrayList<>(List.of(
                MyPanelTutor.Figure.BLUE_STRING,
                MyPanelTutor.Figure.YELLOW_RECTANGLE,
                MyPanelTutor.Figure.GREEN_ELLIPSE));

        Stream.of(
                new Runnable[] {
                        () -> {
                            mp.addBlueString();
                            mpt.addBlueString();
                        },
                        () -> {
                            mp.addYellowRectangle();
                            mpt.addYellowRectangle();
                        },
                        () -> {
                            mp.addGreenEllipse();
                            mpt.addGreenEllipse();
                        }
                })
                .forEach(addMethods -> {
                    mp.figuresToDisplay = new ArrayList<>();
                    mpt.figuresToDisplay = new ArrayList<>();

                    // Add One Figure
                    addMethods.run();
                    assertEquals(mp.figuresToDisplay.get(0).name(), mpt.figuresToDisplay.get(0).name());

                    // Add Figure Again, whilst having to move it
                    mp.figuresToDisplay = new ArrayList<>(mpStandartFigs);
                    mpt.figuresToDisplay = new ArrayList<>(mptStandartFigs);
                    addMethods.run();
                    assertEquals(mp.figuresToDisplay.size(), mpt.figuresToDisplay.size());
                    assertEquals(mp.figuresToDisplay.get(0).name(), mpt.figuresToDisplay.get(0).name());
                    assertEquals(mp.figuresToDisplay.get(1).name(), mpt.figuresToDisplay.get(1).name());
                    assertEquals(mp.figuresToDisplay.get(2).name(), mpt.figuresToDisplay.get(2).name());

                    Collections.rotate(mpStandartFigs, 1);
                    Collections.rotate(mptStandartFigs, 1);
                });
    }

    @Test
    void testRemoveShapeMethods() {
        var mp = new MyPanel();
        var mpt = new MyPanelTutor();
        List<MyPanel.Figure> mpStandartFigs = new ArrayList<>(List.of(
                MyPanel.Figure.BLUE_STRING,
                MyPanel.Figure.YELLOW_RECTANGLE,
                MyPanel.Figure.GREEN_ELLIPSE));
        List<MyPanelTutor.Figure> mptStandartFigs = new ArrayList<>(List.of(
                MyPanelTutor.Figure.BLUE_STRING,
                MyPanelTutor.Figure.YELLOW_RECTANGLE,
                MyPanelTutor.Figure.GREEN_ELLIPSE));

        Stream.of(
                new Runnable[] {
                        () -> {
                            mp.removeBlueString();
                            mpt.removeBlueString();
                        },
                        () -> {
                            mp.removeYellowRectangle();
                            mpt.removeYellowRectangle();
                        },
                        () -> {
                            mp.removeGreenEllipse();
                            mpt.removeGreenEllipse();
                        }
                })
                .forEach(removeMethods -> {
                    // Remove Figures
                    mp.figuresToDisplay = new ArrayList<>(mpStandartFigs);
                    mpt.figuresToDisplay = new ArrayList<>(mptStandartFigs);
                    removeMethods.run();
                    assertEquals(mp.figuresToDisplay.size(), mpt.figuresToDisplay.size());
                    assertEquals(mp.figuresToDisplay.get(0).name(), mpt.figuresToDisplay.get(0).name());
                    assertEquals(mp.figuresToDisplay.get(1).name(), mpt.figuresToDisplay.get(1).name());

                    // Collections.rotate(mpStandartFigs, 1);
                    // Collections.rotate(mptStandartFigs, 1);
                });
    }
}
