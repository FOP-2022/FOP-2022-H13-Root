package h13;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h13")
public class Tutor_Test_H2_1 {
    @Test
    public void testMainFrameComponents() {
        var mp = spy(new MyPanel());
        var mf = spy(new MainFrame(mp));
        doNothing().when(mf).setVisible(ArgumentMatchers.anyBoolean());
        mf.init();
        var components = TestUtils.getAllComponents(mf);
        assertTrue(components.contains(mf.panel));
    }

    @Test
    public void testMainFrameKeyListeners_Plus() throws AWTException {
        var mp = spy(new MyPanel());
        var mf = spy(new MainFrame(mp));
        doNothing().when(mf).setVisible(ArgumentMatchers.anyBoolean());
        mf.init();

        assertTrue(
                Stream.concat(Stream.of(mf), TestUtils.getAllComponents(mf).stream()).anyMatch(x -> {
                    return Arrays.stream(x.getKeyListeners()).anyMatch(y -> {
                        // Simulate a key press
                        mf.setFocusable(true);
                        mf.requestFocus();

                        mp.zoom = 1;
                        y.keyPressed(
                                new KeyEvent(mf, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PLUS, '+',
                                        KeyEvent.KEY_LOCATION_STANDARD));
                        if (mp.zoom == 1.2) {
                            return true;
                        }

                        mp.zoom = 1;
                        y.keyReleased(
                                new KeyEvent(mf, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PLUS, '+',
                                        KeyEvent.KEY_LOCATION_STANDARD));
                        if (mp.zoom == 1.2) {
                            return true;
                        }

                        mp.zoom = 1;
                        y.keyTyped(
                                new KeyEvent(mf, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PLUS, '+',
                                        KeyEvent.KEY_LOCATION_STANDARD));
                        if (mp.zoom == 1.2) {
                            return true;
                        }
                        return false;
                    });
                }));
    }

    @Test
    public void testMainFrameKeyListeners_Minus() throws AWTException {
        var mp = spy(new MyPanel());
        var mf = spy(new MainFrame(mp));
        doNothing().when(mf).setVisible(ArgumentMatchers.anyBoolean());
        mf.init();

        assertTrue(
                Stream.concat(Stream.of(mf), TestUtils.getAllComponents(mf).stream()).anyMatch(x -> {
                    return Arrays.stream(x.getKeyListeners()).anyMatch(y -> {
                        // Simulate a key press
                        mf.setFocusable(true);
                        mf.requestFocus();

                        mp.zoom = 1;
                        y.keyPressed(
                                new KeyEvent(mf, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_MINUS, '-',
                                        KeyEvent.KEY_LOCATION_STANDARD));
                        if (mp.zoom == 0.8) {
                            return true;
                        }

                        mp.zoom = 1;
                        y.keyReleased(
                                new KeyEvent(mf, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_MINUS, '-',
                                        KeyEvent.KEY_LOCATION_STANDARD));
                        if (mp.zoom == 0.8) {
                            return true;
                        }

                        mp.zoom = 1;
                        y.keyTyped(
                                new KeyEvent(mf, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_MINUS, '.',
                                        KeyEvent.KEY_LOCATION_STANDARD));
                        if (mp.zoom == 0.8) {
                            return true;
                        }
                        return false;
                    });
                }));
    }
}
