package org.example;

public class Calculator {
    private String expression;
    private int position;

    public double evaluer(String expression) {
        if (expression == null || expression.isEmpty()) return 0;
        this.expression = expression.replaceAll(" ", "");
        this.position = 0;
        return lireExpression();
    }

    private double lireExpression() {
        double resultat = lireTerme();
        while (nonTermine()) {
            char operateur = lireCaractere();
            if (operateur == '+') {
                resultat += lireTerme();
            } else if (operateur == '-') {
                resultat -= lireTerme();
            } else {
                reculer();
                break;
            }
        }
        return resultat;
    }

    private double lireTerme() {
        double valeur = lireFacteur();
        while (nonTermine()) {
            char operateur = lireCaractere();
            if (operateur == '*') {
                valeur *= lireFacteur();
            } else if (operateur == '/') {
                valeur /= lireFacteur();
            } else {
                reculer();
                break;
            }
        }
        return valeur;
    }

    private double lireFacteur() {
        ignorerEspaces();

        if (caractereActuel() == '-') {
            avancer();
            return -lireFacteur();
        }

        if (caractereActuel() == '(') {
            avancer();
            double valeur = lireExpression();
            avancer(); // saute la parenthèse fermante
            return valeur;
        }

        return lireNombre();
    }

    private double lireNombre() {
        StringBuilder constructeur = new StringBuilder();
    
        while (nonTermine() && (estChiffre(caractereActuel()) || caractereActuel() == '.')) {
            constructeur.append(caractereActuel());
            avancer();
        }

        try {
            return Double.parseDouble(constructeur.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "❌ Erreur : un nombre mal formé a été rencontré à la position " 
                + position + ". Vérifiez votre expression : \"" + expression + "\""
            );
        }
    }

    private boolean estChiffre(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean nonTermine() {
        return position < expression.length();
    }

    private char caractereActuel() {
        return expression.charAt(position);
    }

    private void avancer() {
        position++;
    }

    private void reculer() {
        position--;
    }

    private char lireCaractere() {
        return expression.charAt(position++);
    }

    private void ignorerEspaces() {
        while (nonTermine() && expression.charAt(position) == ' ') {
            position++;
        }
    }
}
