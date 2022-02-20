package h13;

import org.slf4j.Logger;
import org.sourcegrade.jagr.launcher.env.Jagr;

public class TutorSystem {
    static boolean exitCalled = false;

    public static void exit(int statusCode) {
        exitCalled = true;
        var logger = Jagr.Default.getInjector().getInstance(Logger.class);
        logger.warn("You shall not run anymore.");
    }
}
