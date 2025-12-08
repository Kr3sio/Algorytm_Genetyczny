package com.uk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ZGemini {

    // Generator liczb losowych
    private static final Random random = new Random();

    // --- KLASA GENERUJĄCA DANE ---
    static class DataGenerator {
        // Generuje zbiór danych na podstawie wzorcowych współczynników
        // inputs[rows][cols] oraz outputs[rows]
        public static Dataset generate(int numRows, int[] trueCoefficients, int biasRange) {
            int numCols = trueCoefficients.length - 1; // Ostatni to wyraz wolny D
            double[][] x = new double[numRows][numCols];
            double[] y = new double[numRows];

            // Prawdziwy wyraz wolny (D)
            int trueD = trueCoefficients[numCols];

            for (int i = 0; i < numRows; i++) {
                double yVal = 0;
                for (int j = 0; j < numCols; j++) {
                    // Generujemy losowe X z zakresu -100 do 100
                    x[i][j] = random.nextInt(200) - 100;
                    yVal += trueCoefficients[j] * x[i][j];
                }
                yVal += trueD;
                y[i] = yVal;
            }
            return new Dataset(x, y);
        }
    }

    // Struktura przechowująca dane uczące
    static class Dataset {
        double[][] x;
        double[] y;

        public Dataset(double[][] x, double[] y) {
            this.x = x;
            this.y = y;
        }
    }

    // --- DEFINICJA OSOBNIKA ---
    static class Individual {
        // Geny: [A, B, C, ..., D]
        int[] genes;
        double fitness = -1; // -1 oznacza, że fitness nie został jeszcze policzony

        public Individual(int numGenes, int minVal, int maxVal) {
            genes = new int[numGenes];
            for (int i = 0; i < numGenes; i++) {
                genes[i] = random.nextInt(maxVal - minVal + 1) + minVal;
            }
        }

        public Individual(int[] genes) {
            this.genes = genes;
        }

        // Funkcja oceniająca (MSE - Mean Squared Error)
        public void calculateFitness(Dataset data) {
            double sumErrorSquared = 0;
            int numRows = data.y.length;
            int numCols = data.x[0].length;
            // Ostatni gen to wyraz wolny D
            int bias = genes[numCols];

            for (int i = 0; i < numRows; i++) {
                double predictedY = 0;
                for (int j = 0; j < numCols; j++) {
                    predictedY += genes[j] * data.x[i][j];
                }
                predictedY += bias;

                double error = predictedY - data.y[i];
                sumErrorSquared += (error * error);
            }
            // MSE
            this.fitness = sumErrorSquared / numRows;
        }

        @Override
        public String toString() {
            return Arrays.toString(genes) + " | MSE: " + String.format("%.4f", fitness);
        }
    }

    // --- ALGORYTM GENETYCZNY ---
    static class GeneticAlgorithm {
        private int popSize;
        private double mutationRate;
        private int tournamentSize;
        private int geneMin;
        private int geneMax;
        private boolean elitism;

        public GeneticAlgorithm(int popSize, double mutationRate, int tournamentSize, int geneMin, int geneMax, boolean elitism) {
            this.popSize = popSize;
            this.mutationRate = mutationRate;
            this.tournamentSize = tournamentSize;
            this.geneMin = geneMin;
            this.geneMax = geneMax;
            this.elitism = elitism;
        }

        public Individual solve(Dataset data, int numGenerations) {
            int numGenes = data.x[0].length + 1; // współczynniki + wyraz wolny
            List<Individual> population = new ArrayList<>();

            // 1. Inicjalizacja populacji
            for (int i = 0; i < popSize; i++) {
                Individual ind = new Individual(numGenes, geneMin, geneMax);
                ind.calculateFitness(data);
                population.add(ind);
            }

            Individual globalBest = population.get(0);

            // Główna pętla
            for (int gen = 0; gen < numGenerations; gen++) {
                // Znajdź najlepszego w obecnej populacji
                Individual currentBest = getBest(population);
                if (currentBest.fitness < globalBest.fitness) {
                    globalBest = currentBest;
                }

                // Jeśli znaleziono idealne rozwiązanie (MSE = 0), przerwij
                if (globalBest.fitness == 0) break;

                List<Individual> newPopulation = new ArrayList<>();

                // Elityzm - przepisz najlepszego bez zmian
                if (elitism) {
                    newPopulation.add(currentBest);
                }

                // Tworzenie nowej populacji
                while (newPopulation.size() < popSize) {
                    // 2. Selekcja (Turniejowa)
                    Individual parent1 = tournamentSelection(population);
                    Individual parent2 = tournamentSelection(population);

                    // 3. Krzyżowanie (Jednopunktowe)
                    Individual child = crossover(parent1, parent2);

                    // 4. Mutacja
                    mutate(child);

                    child.calculateFitness(data);
                    newPopulation.add(child);
                }
                population = newPopulation;
            }
            return globalBest;
        }

        // Metoda selekcji turniejowej
        private Individual tournamentSelection(List<Individual> pop) {
            Individual best = null;
            for (int i = 0; i < tournamentSize; i++) {
                Individual randomInd = pop.get(random.nextInt(pop.size()));
                if (best == null || randomInd.fitness < best.fitness) {
                    best = randomInd;
                }
            }
            return best;
        }

        // Krzyżowanie jednopunktowe
        private Individual crossover(Individual p1, Individual p2) {
            int length = p1.genes.length;
            int[] newGenes = new int[length];
            int crossoverPoint = random.nextInt(length);

            for (int i = 0; i < length; i++) {
                if (i < crossoverPoint) {
                    newGenes[i] = p1.genes[i];
                } else {
                    newGenes[i] = p2.genes[i];
                }
            }
            return new Individual(newGenes);
        }

        // Mutacja punktowa
        private void mutate(Individual ind) {
            for (int i = 0; i < ind.genes.length; i++) {
                if (random.nextDouble() < mutationRate) {
                    // Wylosuj nową wartość z zakresu
                    ind.genes[i] = random.nextInt(geneMax - geneMin + 1) + geneMin;
                }
            }
        }

        private Individual getBest(List<Individual> pop) {
            Individual best = pop.get(0);
            for (Individual ind : pop) {
                if (ind.fitness < best.fitness) {
                    best = ind;
                }
            }
            return best;
        }
    }

    // --- MAIN: EKSPERYMENTY ---
    public static void main(String[] args) {
        // Konfiguracja eksperymentów
        int[][] scenarios = {
                {15},  // 3 kolumny (zmienne x)
                {20},  // 7 kolumn
                {50}  // 10 kolumn
        };

        // Zakres szukanych współczynników (-10 do 10)
        int minCoef = -10;
        int maxCoef = 10;

        // Parametry AG
        int popSize = 100;
        int generations = 200;
        double mutationRate = 0.05; // 5% szansy na mutację genu
        int tournamentSize = 5;

        for (int[] scenario : scenarios) {
            int numCols = scenario[0];
            System.out.println("\n=== EKSPERYMENT: Liczba zmiennych (kolumn) = " + numCols + " ===");

            // 1. Losowanie "Prawdziwego" wzoru
            int[] trueCoefficients = new int[numCols + 1];
            System.out.print("Poszukiwany wzór (Współczynniki + D): [ ");
            for (int i = 0; i <= numCols; i++) {
                trueCoefficients[i] = random.nextInt(maxCoef - minCoef + 1) + minCoef;
                System.out.print(trueCoefficients[i] + " ");
            }
            System.out.println("]");

            // 2. Generowanie danych uczących (np. 100 wierszy)
            Dataset dataset = DataGenerator.generate(100, trueCoefficients, 100);

            // 3. Uruchomienie AG
            GeneticAlgorithm ga = new GeneticAlgorithm(popSize, mutationRate, tournamentSize, minCoef, maxCoef, true);

            long startTime = System.currentTimeMillis();
            Individual bestFound = ga.solve(dataset, generations);
            long endTime = System.currentTimeMillis();

            // 4. Wyniki
            System.out.println("Znaleziony osobnik: " + bestFound);
            System.out.println("Czas obliczeń: " + (endTime - startTime) + "ms");

            // Weryfikacja
            boolean exactMatch = true;
            for(int i=0; i<trueCoefficients.length; i++){
                if(trueCoefficients[i] != bestFound.genes[i]) exactMatch = false;
            }
            System.out.println("Czy znaleziono idealny wzór? " + (exactMatch ? "TAK" : "NIE"));
        }
    }
}