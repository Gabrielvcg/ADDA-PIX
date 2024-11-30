package ejercicios;

import java.util.ArrayList;
import java.util.List;

public class SVPNaive {
	 public static List<List<Integer>> generateRandomBase(int dim) {
	        List<List<Integer>> base = new ArrayList<>();
	        for (int i = 0; i < dim; i++) {
	            List<Integer> vector = new ArrayList<>();
	            for (int j = 0; j < dim; j++) {
	                vector.add((int) (Math.random() * 10) - 5); // Valores aleatorios entre -5 y 5
	            }
	            base.add(vector);
	        }
	        return base;
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
	    
	    public static double calculateNorm(List<Integer> vector) {
	        return Math.sqrt(vector.stream().mapToDouble(x -> x * x).sum());
	    }

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
}
