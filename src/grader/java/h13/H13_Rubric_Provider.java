package h13;

import org.sourcegrade.jagr.api.rubric.*;
import org.sourcegrade.jagr.api.testing.RubricConfiguration;

import static org.sourcegrade.jagr.api.rubric.JUnitTestRef.*;

/**
 * @author Ruben Deisenroth
 */
@RubricForSubmission("h13")
public class H13_Rubric_Provider implements RubricProvider {

    // -- H1--//

    // H1.1

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1 Attribute und Steuermethoden")
        .addChildCriteria(Criterion.builder()
                .shortDescription(
                    "Die Default-Werte wurden korrekt eingetragen.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(
                            ofMethod(
                                () -> Tutor_Test_H1_1.class.getDeclaredMethod(
                                    "testDefaultValues")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Setter-Methoden werfen entsprechende Fehlermeldungen bei ungültigen Eingaben.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(
                            ofMethod(
                                () -> Tutor_Test_H1_1.class.getDeclaredMethod(
                                    "testSetters")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Add- und Remove-Methoden der Figuren funktionieren korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(and(
                            or(
                                ofMethod(
                                    () -> Tutor_Test_H1_1.class.getDeclaredMethod(
                                        "testAddShapeMethods")),
                                ofMethod(
                                    () -> Tutor_Test_H1_1.class.getDeclaredMethod(
                                        "testAddShapeMethods_alt"))),
                            ofMethod(
                                () -> Tutor_Test_H1_1.class.getDeclaredMethod(
                                    "testRemoveShapeMethods"))))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build())
        .build();

    // H1.2

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2 Hilfsmethoden zum Zeichnen geometrischer Figuren")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription(
                    "Alle Überladungen der Methode colorWithAlpha sind vollständig korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testColorWithAlpha")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode Methode colorWithSaturation ist vollständig korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testColorWithSaturation")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode Methode centerShape liefert ein korrektes Ergebnis, wenn keine Verschiebung oder Skalierung notwendig ist.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testCenterShape_noChanges")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode Methode centerShape liefert ein korrektes Ergebnis, nur eine Verschiebung notwendig ist.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testCenterShape_Centering")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode Methode centerShape liefert ein korrektes Ergebnis, wenn nur eine Skalierung ohne Rahmen notwendig ist.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testCenterShape_Resize")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode Methode centerShape liefert ein korrektes Ergebnis, wenn nur eine Skalierung mit Rahmen notwendig ist.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testCenterShape_Border")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode Methode centerShape liefert ein korrektes Ergebnis, wenn sowohl Skalierung mit Rahmen als auch eine Verschiebung notwendig sind.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                                "testCenterShape_All")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die FillDraw liefert ein korrektes Ergebnis, wenn ein Rahmen gezeichnet werden muss.")
                .grader(Grader.testAwareBuilder().requirePass(ofMethod(
                        () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                            "testFillDraw_Border")))
                    .pointsPassedMax()
                    .pointsFailedMin()
                    .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die FillDraw liefert ein korrektes Ergebnis, wenn kein ein Rahmen in einer anderen Farbe als die Füllung gezeichnet werden muss.")
                .grader(Grader.testAwareBuilder().requirePass(ofMethod(
                        () -> Tutor_Test_H1_2.class.getDeclaredMethod(
                            "testFillDraw_ALL")))
                    .pointsPassedMax()
                    .pointsFailedMin()
                    .build())
                .build())
        .build();

    // H1.3
    public static final Criterion H1_3 = Criterion.builder()
        .shortDescription("H1.3 Hilfsmethoden zum Zeichnen von Text")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrektes Ergebnis, wenn keine Skalierung oder verschiebung notwendig ist.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_No_Tf_Needed")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt zentriertes Ergebnis, wenn keine Skalierung notwendig ist.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_CenterOnly")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt skaliertes Ergebnis, wenn keine Zentrierung oder notwendig ist und kein Rahmen beachtet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_ScaleOnly_NoBorder")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt skaliertes Ergebnis, wenn auf MyPanel-Breite Skaliert werden soll, und ein Rahmen beachtet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_ScaleOnly_FullWidth")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt skaliertes Ergebnis, wenn auf eine (pseudo-)zufällige MyPanel-Breite Skaliert werden soll, und ein Rahmen beachtet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_ScaleOnly")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt skaliertes und zentriertes Ergebnis, wenn auf eine (pseudo-)zufällige MyPanel-Breite Skaliert werden soll, und kein Rahmen beachtet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_ScaleAndCenter_NoBorder")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt skaliertes und zentriertes Ergebnis, wenn auf eine (pseudo-)zufällige MyPanel-Breite Skaliert werden soll, und ein Rahmen beachtet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_ScaleAndCenter")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode scaleTextToWidth liefert ein korrekt skaliertes und zentriertes Ergebnis, wenn auf eine width, die mindestens 2 * MyPanel-Breite ist, skaliert werden soll, und ein Rahmen beachtet werden muss (kleinere Toleranz).")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testScaleTextToWidth_ScaleAndCenter_BiggerThanScreen")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            // DrawColoredString
            Criterion.builder()
                .shortDescription(
                    "Die Methode drawColoredString liefert ein korrektes Ergebnis, wenn kein Rahmen gezeichnet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testDrawColoredString_NoBorder")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode drawColoredString liefert ein korrektes Ergebnis, wenn kein Rahmen gezeichnet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testDrawColoredString_NoBorder")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode drawColoredString liefert ein korrektes Ergebnis, wenn ein Rahmen in der gleichen Farbe wie die Füllung gezeichnet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testDrawColoredString_Border")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode drawColoredString liefert ein korrektes Ergebnis, wenn die Textfarbe verändert wird und kein Rand gezeichnet werden muss. (also nicht die default-Farbe)")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testDrawColoredString_TextFontColor")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode drawColoredString liefert ein korrektes Ergebnis, wenn die Textfarbe und die Randfarbe verändert wird und ein Rand gezeichnet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_3.class.getDeclaredMethod(
                                "testDrawColoredString_All")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build())
        .build();

    // H1.4
    public static final Criterion H1_4 = Criterion.builder()
        .shortDescription("H1.4 Methode paint")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription(
                    "Die Methode paint zeichnet nichts, wenn alle Figuren entfernt wurden.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                "testBlank")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode paint ruft die entsprechenden draw-Methoden mit den korrekten Parametern auf, wenn nur eine Figur gezeichnet werden muss.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(
                            and(
                                or(
                                    ofMethod(
                                        () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                            "testYellowRectangle")),
                                    ofMethod(
                                        () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                            "testYellowRectangle_alt"))),
                                or(
                                    ofMethod(
                                        () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                            "testGreenEllipse")),
                                    ofMethod(
                                        () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                            "testGreenEllipse_alt"))),
                                or(
                                    ofMethod(
                                        () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                            "testBlueString")),
                                    ofMethod(
                                        () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                            "testBlueString_alt")))))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode paint ruft die entsprechenden draw-Methoden mit den korrekten Parametern in der korrekten Reihenfolge auf, wenn drei Figuren hinzugefügt werden.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(or(
                            ofMethod(
                                () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                    "testThreeFigures")),
                            ofMethod(
                                () -> Tutor_Test_H1_4.class.getDeclaredMethod(
                                    "testThreeFigures_alt"))))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build())
        .build();

    // --H2--//
    // H2.1
    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1 Zeichenfenster")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription(
                    "Die aktuelle My-Panel instanz wird beim initialisieren hinzugefügt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_1.class.getDeclaredMethod(
                                "testMainFrameComponents")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die aktuelle My-Panel instanz wird beim initialisieren dem MainFrame hinzugefügt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_1.class.getDeclaredMethod(
                                "testMainFrameComponents")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Der Listener für das hereinzoomen (+) ist korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_1.class.getDeclaredMethod(
                                "testMainFrameKeyListeners_Plus")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Der Listener für das herauszooomen (-) ist korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_1.class.getDeclaredMethod(
                                "testMainFrameKeyListeners_Minus")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build())
        .build();

    // H2.2
    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("H2.2 Steuerungsfenster mit GridLayout ")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription(
                    "Das GridLayout wurde verwendet und hat die geforderten Zeilen und Spalten.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                "testLayout")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Knöpfe wurden an der korrekten Position hinzugefügt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                "testLayout")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Add-Knöpfe funktionieren korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                "testAddButtons")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Remove-Knöpfe funktionieren korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                "testRemoveButtons")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Knöpfe zum Verändern vom Sättigung, Alpha und Zoom funktionieren korrekt.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(and(ofMethod(
                                () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                    "testChangeSaturationButtons")),
                            ofMethod(
                                () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                    "testChangeAlphaButton")),
                            ofMethod(
                                () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                    "testChangeZoomButtons"))))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Der Knopf zum Verändern der Schriftart funktioniert korrekt und bietet mindestens fünf unterschiedliche Auswahlmöglichkeiten.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                "testChangeFontButton")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Der Exit-Button beendet das Programm.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(or(
                            ofMethod(
                                () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                    "testExitButton")),
                            ofMethod(
                                () -> Tutor_Test_H2_2.class.getDeclaredMethod(
                                    "testExitButton_alt"))))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build())
        .build();

    // H2.3
    public static final Criterion H2_3 = Criterion.builder()
        .shortDescription("H2.3 Der Änderungsdialog PropertyChangeDialogue")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription(
                    "Die Methode showNumberChangeDialog erfüllt die notwendigen Anforderungen der Aufgabenstellung.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_3.class.getDeclaredMethod(
                                "testShowNumberChangeDialog")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build(),
            Criterion.builder()
                .shortDescription(
                    "Die Methode showEnumChangeDialogue erfüllt die notwendigen Anforderungen der Aufgabenstellung.")
                .grader(
                    Grader.testAwareBuilder()
                        .requirePass(ofMethod(
                            () -> Tutor_Test_H2_3.class.getDeclaredMethod(
                                "testShowEnumChangeDialogue")))
                        .pointsPassedMax()
                        .pointsFailedMin()
                        .build())
                .build())
        .build();

    // Tasks:

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1 Eigene JPanel-Klasse")
        .addChildCriteria(
            H1_1,
            H1_2,
            H1_3,
            H1_4)
        .build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2 Programm mit zwei Fenstern")
        .addChildCriteria(
            H2_1,
            H2_2,
            H2_3)
        .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("h13")
            .addChildCriteria(
                H1,
                H2
            )
            .build();
    }

    @Override
    public void configure(final RubricConfiguration configuration) {
        configuration.addTransformer(new TutorTransformer());
    }
}
