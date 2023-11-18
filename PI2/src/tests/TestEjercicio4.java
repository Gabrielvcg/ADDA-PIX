package tests;

import java.util.List;

import ejercicios.Ejercicio4;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class TestEjercicio4 {


	public static void main(String[] args) {
		testsEjercicio4();		
	}
	
	
	public static void testsEjercicio4() {
		
		String BinaryTreeFile = "ficheros/PI2Ej4DatosEntradaBinary.txt";
		String TreeFile="ficheros/PI2Ej4DatosEntradaNary.txt";
		List<BinaryTree<Integer>> inputs =
				Files2
				.streamFromFile(BinaryTreeFile)
				.map(linea ->BinaryTree.parse(linea,s->Integer.parseInt(s))).toList();
		List<Tree<Integer>> inputs2 =
				Files2
				.streamFromFile(TreeFile)
				.map(linea ->Tree.parse(linea,s->Integer.parseInt(s))).toList();
		
		System.out.println("\n.....................................................................................................");
		System.out.println("EJERCICIO 4");
		System.out.println(".....................................................................................................\n");

		System.out.println("\nFICHERO ARBOL BINARIO:\n");	

		inputs.stream()
		.forEach(x->System.out.println(x+": "+Ejercicio4.caminoDivisibleB(x)));
		
		System.out.println("\nFICHERO ARBOL NARIO:\n");	
		inputs2.stream()
		.forEach(x->System.out.println(x+": "+Ejercicio4.caminoDivisibleN(x)));
		
		
	}
	
	
	
}
