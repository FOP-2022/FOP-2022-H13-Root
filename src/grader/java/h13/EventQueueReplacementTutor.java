package h13;

import java.awt.AWTEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Deisenroth
 */
public class EventQueueReplacementTutor {
    static List<AWTEvent> postedEvents = new ArrayList<>();

    public static void postEvent(AWTEvent theEvent) {
        postedEvents.add(theEvent);
    }

    public static void dispatchEvent(AWTEvent theEvent) {
        postEvent(theEvent);
    }
}
