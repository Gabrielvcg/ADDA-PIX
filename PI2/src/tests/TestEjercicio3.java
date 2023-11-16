package tests;

import java.util.List;

import ejercicios.Ejercicio3;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class TestEjercicio3 {


	public static void main(String[] args) {
		testsEjercicio3();		
	}
	
	
	public static void testsEjercicio3() {
		
		String BinaryTreeFile = "ficheros/PI2Ej3DatosEntradaBinary.txt";
		String TreeFile="ficheros/PI2Ej3DatosEntradaNary.txt";
		List<BinaryTree<Character>> inputs =
				Files2
				.streamFromFile(BinaryTreeFile)
				.map(linea ->BinaryTree.parse(linea,s->s.charAt(0))).toList();
		List<Tree<Character>> inputs2 =
				Files2
				.streamFromFile(TreeFile)
				.map(linea ->Tree.parse(linea,s->s.charAt(0))).toList();
		
		System.out.println("\n.....................................................................................................");
		System.out.println("EJERCICIO 3");
		System.out.println(".....................................................................................................\n");

		System.out.println("\nFICHERO ARBOL BINARIO:\n");	

		inputs.stream()
		.forEach(x->System.out.println(x+": "+Ejercicio3.isBinaryEquilibrated(x)));
		
		System.out.println("\nFICHERO ARBOL NARIO:\n");	
		inputs2.stream()
		.forEach(x->System.out.println(x+": "+Ejercicio3.isTreeEquilibrated(x)));
		
		
	}
	
	
	

}
