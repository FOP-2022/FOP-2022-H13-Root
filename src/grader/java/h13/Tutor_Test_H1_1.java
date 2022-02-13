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

    }
}
