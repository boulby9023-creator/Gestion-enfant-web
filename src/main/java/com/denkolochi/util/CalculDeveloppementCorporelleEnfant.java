package com.denkolochi.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.denkolochi.model.Corporelle;

public class CalculDeveloppementCorporelleEnfant {
    /**
     * Calcule le score de développement sur 100%
     * Basé sur l'évolution de l'IMC et sa position par rapport aux normes
     */
    public static double calculerScoreDevelopment(List<Corporelle> mesures, int ageEnMois) {
        if (mesures == null || mesures.isEmpty()) {
            return 0; // Pas de données
        }

        if (ageEnMois < 12) {
            // Trop jeune : moins de 1 an
            return 0;
        }

        // Dernière mesure
        Corporelle dernier = mesures.get(mesures.size() - 1);
        double imcActuel = dernier.getImc();

        // Zone cible IMC selon l'âge (plage simplifiée, adaptée aux jeunes enfants)
        double imcMin, imcMax;

        if (ageEnMois >= 12 && ageEnMois <= 24) { // 1-2 ans
            imcMin = 14.0;
            imcMax = 17.0;
        } else if (ageEnMois <= 60) { // 2-5 ans (24–60 mois)
            imcMin = 14.0;
            imcMax = 18.0;
        } else if (ageEnMois <= 120) { // 6-10 ans
            imcMin = 14.5;
            imcMax = 20.0;
        } else { // 11-19 ans
            imcMin = 16.0;
            imcMax = 24.0;
        }

        double scoreIMC = 0;

        // Score basé sur la position de l'IMC actuel
        if (imcActuel >= imcMin && imcActuel <= imcMax) {
            scoreIMC = 70; // Dans la zone normale = 70%
        } else if (imcActuel < imcMin) {
            double ecart = (imcMin - imcActuel) / imcMin;
            scoreIMC = Math.max(0, 70 - (ecart * 50));
        } else {
            double ecart = (imcActuel - imcMax) / imcMax;
            scoreIMC = Math.max(0, 70 - (ecart * 50));
        }

        // Score basé sur l'évolution (tendance)
        double scoreEvolution = calculerScoreEvolution(mesures);

        // Score final = 70% IMC + 30% Évolution
        double scoreFinal = (scoreIMC * 0.7) + (scoreEvolution * 0.3);

        return Math.min(100, Math.max(0, scoreFinal));
    }

    /**
     * Calcule le score d'évolution basé sur la tendance de l'IMC
     * Si l'IMC se stabilise ou améliore → bon score
     */
    private static double calculerScoreEvolution(List<Corporelle> mesures) {
        if (mesures.size() < 2) {
            return 50; // Pas assez de données pour juger l'évolution
        }

        Corporelle premier = mesures.get(0);
        Corporelle dernier = mesures.get(mesures.size() - 1);

        double imcInitial = premier.getImc();
        double imcFinal = dernier.getImc();

        double variation = (imcFinal - imcInitial) / imcInitial * 100;

        // Variation entre -5% et +10% = stable/bonne évolution
        if (variation >= -5 && variation <= 10) {
            return 100;
        } else if (variation < -5) {
            // IMC qui baisse trop
            return Math.max(0, 100 - (Math.abs(variation) - 5) * 5);
        } else {
            // IMC qui monte trop
            return Math.max(0, 100 - (variation - 10) * 5);
        }
    }

    /**
     * Calcule l'âge de l'enfant en mois entre la date de naissance et la dernière mesure
     */
    public static int calculerAgeEnMois(LocalDate dateNaissance, LocalDate dateMesure) {
        if (dateNaissance == null || dateMesure == null) {
            return 0;
        }
        Period period = Period.between(dateNaissance, dateMesure);
        return period.getYears() * 12 + period.getMonths();
    }

    /**
     * Interprétation du score
     */
    public static String interpreterScore(double score) {
        if (score >= 90) return "Développement TRÈS BON";
        if (score >= 75) return "Développement BON";
        if (score >= 50) return "Développement MOYEN";
        if (score >= 25) return "Développement FAIBLE";
        return "Développement MAUVAIS";
    }

    /**
     * Catégorie détaillée
     */
    public static String getCategorie(double score) {
        if (score >= 90) return "excellent";
        if (score >= 75) return "bon";
        if (score >= 50) return "moyen";
        if (score >= 25) return "faible";
        return "mauvais";
    }
}