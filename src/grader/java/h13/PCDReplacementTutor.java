package h13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Deisenroth
 */
public class PCDReplacementTutor {
    public static List<PropertyChangeDialogue> instances = new ArrayList<>();

    public static void addPCD(PropertyChangeDialogue pcd) {
        instances.add(pcd);
    }
}
