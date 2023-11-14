package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ejercicios.Ejercicio3;
import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.common.Preconditions;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;

public class TestEjercicio3 {

	private static final Predicate<Integer> PAR = x-> x%2==0;
	private static final Predicate<Integer> PRIMO = x-> Math2.esPrimo(x);

	public static void main(String[] args) {
		testsEjercicio3();		
	}
	
	
	
	private static List<Character> stringListToCharList(String s) {
		
		String letras = s.replace(",", "").replace("[", "").replace("]","");
		List<Character> res = new ArrayList<>(letras.length());
		for(int i=0; i<letras.length(); i++) 
			res.add(letras.charAt(i));
		return res;
	}
	
	public static void testsEjercicio3() {
		
		String file = "ficheros_ejemplo/p2/ejemplo4.txt";
		
		List<Pair<BinaryTree<Character>,List<Character>>> inputs =
			Files2.streamFromFile(file).map(linea -> {
				String[] aux = linea.split("#");
				Preconditions.checkArgument(aux.length==2);
				return Pair.of(BinaryTree.parse(aux[0], s -> s.charAt(0)), stringListToCharList(aux[1]));
				}
			).toList();
		
		
		System.out.println("\n.....................................................................................................");
		System.out.println("EJERCICIO 3");
		System.out.println(".....................................................................................................\n");

		inputs.stream().forEach(par -> {
			
			BinaryTree<Character> tree = par.first();
			List<Character> chars = par.second();
			
			System.out.println("Arbol: "+tree+
					"\tSecuencia: "+chars+"\t["+
					Ejercicio3.solucion_recursiva(tree,chars)+"]");
			
		}
		);
	}
	
	
	

}
