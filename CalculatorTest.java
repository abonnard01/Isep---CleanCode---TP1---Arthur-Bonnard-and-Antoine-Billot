package org.example;

public class CalculatorTest {
    private static int total = 0;
    private static int reussis = 0;

    public static void main(String[] args) {
        Calculator calculatrice = new Calculator();

        // Tests de base
        verifier("2 + 3", 5.0, calculatrice);
        verifier("10 - 4", 6.0, calculatrice);
        verifier("3 * 4", 12.0, calculatrice);
        verifier("12 / 4", 3.0, calculatrice);

        // Priorité opérateurs
        verifier("2 + 3 * 4", 14.0, calculatrice);
        verifier("2 * 3 + 4", 10.0, calculatrice);
        verifier("10 - 3 * 2", 4.0, calculatrice);

        // Parenthèses
        verifier("(2 + 3) * 4", 20.0, calculatrice);
        verifier("2 * (3 + 4)", 14.0, calculatrice);
        verifier("10 / (2 + 3)", 2.0, calculatrice);
        verifier("(2 + 3) * (4 + 1)", 25.0, calculatrice);

        // Nombres négatifs
        verifier("-3 + 5", 2.0, calculatrice);
        verifier("4 * -2", -8.0, calculatrice);
        verifier("-2 * -2", 4.0, calculatrice);
        verifier("-(-5)", 5.0, calculatrice);

        // Parenthèses + négatifs
        verifier("-(2 + 3) * 2", -10.0, calculatrice);
        verifier("-((1 + 1) * (2 + 2))", -8.0, calculatrice);
        verifier("((-2 + 3) * 4)", 4.0, calculatrice);

        // Bonus : espaces, vides, null
        verifier("   3 + 4 * 2   ", 11.0, calculatrice);
        verifier("", 0.0, calculatrice);
        verifier(null, 0.0, calculatrice);

        // Résumé
        System.out.println("\n" + reussis + "/" + total + " tests réussis.");
        if (reussis == total) {
            System.out.println("✅ Tous les tests sont passés !");
        } else {
            System.out.println("❌ Certains tests ont échoué.");
        }
    }

    private static void verifier(String expression, double attendu, Calculator calc) {
        total++;
        double resultat = calc.evaluer(expression);
        if (Math.abs(resultat - attendu) < 0.0001) {
            System.out.println("✔ Test OK : \"" + expression + "\" = " + resultat);
            reussis++;
        } else {
            System.out.println("✘ Test ÉCHOUÉ : \"" + expression + "\" => " + resultat + " (attendu " + attendu + ")");
        }
    }
}
