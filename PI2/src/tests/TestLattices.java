package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Exponential;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.graphics.MatPlotLib;

public class TestLattices {

    private static Integer nMin = 2;  // Dimensión mínima
    private static Integer nMax = 7; // Dimensión máxima
    private static Integer nIncr = 1; // Incremento de dimensión
    private static Integer nIter = 100; // Número de iteraciones por medición
    private static Integer range = 5; // Rango de combinaciones [-k, k]
    private static Integer warmup= 1000; //calentamiento para saturar la cache
    
    public static List<List<Integer>> generateLatticePoints(List<List<Integer>> base, int k) {
        List<List<Integer>> latticePoints = new ArrayList<>();
        generateRecursive(base, k, new ArrayList<>(), latticePoints, 0);
        return latticePoints;
    }

    private static void generateRecursive(List<List<Integer>> base, int k, List<Integer> coefficients,
                                          List<List<Integer>> latticePoints, int depth) {
        if (depth == base.size()) {
            List<Integer> point = new ArrayList<>();
            for (int i = 0; i < base.get(0).size(); i++) {
                int coordinate = 0;
                for (int j = 0; j < base.size(); j++) {
                    coordinate += coefficients.get(j) * base.get(j).get(i);
                }
                point.add(coordinate);
            }
            latticePoints.add(point);
            return;
        }
        for (int i = -k; i <= k; i++) {
            List<Integer> newCoefficients = new ArrayList<>(coefficients);
            newCoefficients.add(i);
            generateRecursive(base, k, newCoefficients, latticePoints, depth + 1);
        }
    }

    public static double calculateNorm(List<Integer> vector) {
        return Math.sqrt(vector.stream().mapToDouble(x -> x * x).sum());
    }

    public static List<Integer> findShortestVector(List<List<Integer>> latticePoints) {
        List<Integer> shortestVector = null;
        double shortestNorm = Double.MAX_VALUE;

        for (List<Integer> vector : latticePoints) {
            double norm = calculateNorm(vector);
            if (norm > 0 && norm < shortestNorm) { // Ignorar el vector nulo
                shortestNorm = norm;
                shortestVector = vector;
            }
        }
        return shortestVector;
    }

    public static void genDataSVP() {
        String file = "ficheros_generados/SVP.txt";

        // Función que mide el tiempo de ejecución
        Function<Integer, Long> f1 = dim -> {
            List<List<Integer>> base = generateRandomBase(dim, dim); // Base aleatoria
            List<List<Integer>> latticePoints = generateLatticePoints(base, range);
            long startTime = System.nanoTime();
            findShortestVector(latticePoints);
            long endTime = System.nanoTime();
            return endTime - startTime;
        };

        // Generar datos de tiempo para varias dimensiones
        GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, warmup);
    }

    public static List<List<Integer>> generateRandomBase(int dim, int vectors) {
        List<List<Integer>> base = new ArrayList<>();
        for (int i = 0; i < vectors; i++) {
            List<Integer> vector = new ArrayList<>();
            for (int j = 0; j < dim; j++) {
                vector.add((int) (Math.random() * 10) - 5); // Valores aleatorios entre -5 y 5
            }
            base.add(vector);
        }
        return base;
    }

    public static void showSVP() {
        String file = "ficheros_generados/SVP.txt";
        List<WeightedObservedPoint> data = DataFile.points(file);
        Fit pl = Exponential.of();
        pl.fit(data);
        System.out.println(pl.getExpression());
        System.out.println(pl.getEvaluation().getRMS());
        MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
    }

    public static void main(String[] args) {
        genDataSVP();
        showSVP();
    }
}
