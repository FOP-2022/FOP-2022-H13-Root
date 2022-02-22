package h13;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Deisenroth
 */
public class PCDReplacementTutor {
    public static List<PropertyChangeDialogue> pcdInstances = new ArrayList<>();
    public static List<MainFrame> mfInstances = new ArrayList<>();
    public static List<ControlFrame> cfInstances = new ArrayList<>();

    public static void addPCD(PropertyChangeDialogue pcd) {
        pcdInstances.add(pcd);
    }

    public static void addMF(MainFrame mf) {
        mfInstances.add(mf);
    }

    public static void addCF(ControlFrame cf) {
        cfInstances.add(cf);
    }
}
