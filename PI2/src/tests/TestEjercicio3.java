package tests;

import java.util.List;

import ejercicios.Ejercicio3;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class TestEjercicio3 {


	public static void main(String[] args) {
		testsEjercicio3();		
	}
	
	
	public static void testsEjercicio3() {
		
		String file = "ficheros/PI2Ej3DatosEntradaBinary.txt";
		
		List<BinaryTree<Character>> inputs =
				Files2
				.streamFromFile(file)
				.map(linea ->BinaryTree.parse(linea,s->s.charAt(0))).toList();
		
		System.out.println("\n.....................................................................................................");
		System.out.println("EJERCICIO 3");
		System.out.println(".....................................................................................................\n");

		
		System.out.println("\n¿Esta el arbol equilibrado?:\n");	
		inputs.stream()
		.forEach(x->System.out.println(x+": "+Ejercicio3.isBinaryEquilibrated(x)));
			
		
		
	}
	
	
	

}
