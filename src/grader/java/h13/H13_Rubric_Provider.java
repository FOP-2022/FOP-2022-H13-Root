package h13;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import static org.sourcegrade.jagr.api.rubric.JUnitTestRef.*;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

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
                                                    () -> Tutor_Test_H1_1.class.getMethod(
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
                                                            () -> Tutor_Test_H1_1.class.getMethod(
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
                                                                    () -> Tutor_Test_H1_1.class.getMethod(
                                                                            "testAddShapeMethods")),
                                                            ofMethod(
                                                                    () -> Tutor_Test_H1_1.class.getMethod(
                                                                            "testAddShapeMethods_alt"))),
                                                    ofMethod(
                                                            () -> Tutor_Test_H1_1.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_2.class.getMethod(
                                                            "testCenterShape_All")))
                                            .pointsPassedMax()
                                            .pointsFailedMin()
                                            .build())
                            .build(),
                    Criterion.builder()
                            .shortDescription(
                                    "Die FillDraw liefert ein korrektes Ergebnis, wenn ein Rahmen gezeichnet werden muss.")
                            .grader(Grader.testAwareBuilder().requirePass(ofMethod(
                                    () -> Tutor_Test_H1_2.class.getMethod(
                                            "testFillDraw_Border")))
                                    .pointsPassedMax()
                                    .pointsFailedMin()
                                    .build())
                            .build(),
                    Criterion.builder()
                            .shortDescription(
                                    "Die FillDraw liefert ein korrektes Ergebnis, wenn kein ein Rahmen in einer anderen Farbe als die Füllung gezeichnet werden muss.")
                            .grader(Grader.testAwareBuilder().requirePass(ofMethod(
                                    () -> Tutor_Test_H1_2.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                    () -> Tutor_Test_H1_3.class.getMethod(
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
                                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                                    "testYellowRectangle")),
                                                                    ofMethod(
                                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                                    "testYellowRectangle_alt"))),
                                                            or(
                                                                    ofMethod(
                                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                                    "testGreenEllipse")),
                                                                    ofMethod(
                                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                                    "testGreenEllipse_alt"))),
                                                            or(
                                                                    ofMethod(
                                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                                    "testBlueString")),
                                                                    ofMethod(
                                                                            () -> Tutor_Test_H1_3.class.getMethod(
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
                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                    "testThreeFigures")),
                                                    ofMethod(
                                                            () -> Tutor_Test_H1_3.class.getMethod(
                                                                    "testThreeFigures_alt"))))
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
                    H1_2
            // H1_3,
            // H1_4,
            )
            .build();

    @Override
    public Rubric getRubric() {
        return Rubric.builder()
                .title("h13")
                .addChildCriteria(H1
                // H2,
                )
                .build();
    }

}
