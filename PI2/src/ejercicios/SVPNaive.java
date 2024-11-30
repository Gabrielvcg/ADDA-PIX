package ejercicios;

import java.util.ArrayList;
import java.util.List;

public class SVPNaive {
    
    // Generar combinaciones lineales enteras de la base en el rango [-k, k]
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

    // Calcular la norma Euclidiana de un vector
    public static double calculateNorm(List<Integer> vector) {
        return Math.sqrt(vector.stream().mapToDouble(x -> x * x).sum());
    }

    // Encontrar el vector más corto no nulo
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

    public static void main(String[] args) {
        // Base de ejemplo (dimensión 2)
        List<List<Integer>> base = List.of(
            List.of(2, 3, 9),
            List.of(1, 4, 2),
            List.of(4,5,1)
        );

        int k = 5; // Rango de búsqueda [-k, k]
        List<List<Integer>> latticePoints = generateLatticePoints(base, k);
        System.out.println("Lattice points: " + latticePoints);

        List<Integer> shortestVector = findShortestVector(latticePoints);
        System.out.println("Shortest vector: " + shortestVector);
        System.out.println("Norm: " + calculateNorm(shortestVector));
    }
}
