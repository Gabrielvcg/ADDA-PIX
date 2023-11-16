package ejercicios;


	import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;


public class Ejercicio3 {

	/*
	 * PI3 - Ejemplo 4
	 * 
	 * Implemente una función booleana que, dados un árbol binario de caracteres y
	 * una lista de caracteres, determine si existe un camino en el árbol de la raíz
	 * a una hoja que sea igual a la lista.
	 * 
	 * Resolver de forma recursiva
	 */

	
	public static  Boolean isBinaryEquilibrated (BinaryTree<Character> tree) {
		List<Boolean> res=new ArrayList<>();
		return isBinaryEquilibratedAux(tree,res,0);	
	}
	
	public static  Boolean isBinaryEquilibratedAux(BinaryTree<Character> tree, List<Boolean> res, int nivel) {
		if(res.size() <= nivel) res.add(true);
		return switch (tree) {
		case BEmpty<Character> t -> true;
		case BLeaf<Character> t -> true;
		case BTree<Character> t -> (t.left().height()-t.right().height()<=1) &&(isBinaryEquilibratedAux(t.left(), res, nivel+1) && isBinaryEquilibratedAux(t.right(), res, nivel+1));
		};
	}
	/*
	public static  Boolean solucion_recursiva (Tree<Character> tree){
		List<Boolean> res=new ArrayList<>();
		return recursivo (tree,0,res);
	}
	
	
	private static  Boolean recursivo(Tree<Character> tree, int nivel, List<Boolean> res) {
		if(res.size() <= nivel) res.add(true);
		List<Integer> ac=new ArrayList<>();
		return switch (tree) {
		case TEmpty<Character> t -> true;
		case TLeaf<Character> t -> true;
		case TNary<Character> t -> 
		t.children().forEach(tc -> ac.add(tc.height()));
		
		
		};
	}*/
	
}

