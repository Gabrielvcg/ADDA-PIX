package tests;

import java.util.List;
import java.util.function.Predicate;

import ejercicios.Ejercicio4;
import us.lsi.common.Files2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.Tree;

public class TestEjercicio4 {
	
	private static final Predicate<Integer> PAR = x-> x%2==0;
	private static final Predicate<Integer> PRIMO = x-> Math2.esPrimo(x);

	
	public static void main(String[] args) {
		testsEjercicio4();		
	}
		public static void testsEjercicio4() {
			
			String file = "ficheros/PI2Ej4DatosEntradaBinary.txt";
			
			List<Tree<Integer>> inputs = Files2
					.streamFromFile(file)
					.map(linea -> Tree.parse(linea,s->Integer.parseInt(s)))
					.toList();
			
			System.out.println("\n.....................................................................................................");
			System.out.println("EJERCICIO 4");
			System.out.println(".....................................................................................................\n");
			
			System.out.println("\nSOLUCION RECURSIVA-PAR:\n");	
			inputs.stream()
			.forEach(x->System.out.println(x+": "+Ejercicio4.solucion_recursiva(x, PAR)));

		/*	System.out.println("\nSOLUCION RECURSIVA-PRIMOS:\n");	
			inputs.stream()
				.forEach(x->System.out.println(x+": "+Ejercicio4.solucion_recursiva(x, PRIMO)));

*/
		}
}
