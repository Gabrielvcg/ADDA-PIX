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
	ALGORITMO EN ITERATIVO BINARIO
	public static  Boolean isBinaryEquilibratedAux(BinaryTree<Character> tree, List<Boolean> res, int nivel) {
		if(res.size() <= nivel) res.add(true);
		return switch (tree) {
		case BEmpty<Character> t -> true;
		case BLeaf<Character> t -> true;
		case BTree<Character> t -> (t.left().height()-t.right().height()<=1) &&(isBinaryEquilibratedAux(t.left(), res, nivel+1) && isBinaryEquilibratedAux(t.right(), res, nivel+1));
		};
	}
	  ITERATIVO NARIO
	private static  Boolean isTreeEquilibratedAux(Tree<Character> tree, int nivel, List<Boolean> res) {
		if(res.size() <= nivel) res.add(true);
		return switch (tree) {
		case TEmpty<Character> t -> true;
		case TLeaf<Character> t -> true;
		case TNary<Character> t -> {
			Integer height= 1+t.children().stream().mapToInt(tc->tc.height()).max().getAsInt();
			Integer minorHeight= 1+t.children().stream().mapToInt(tc->tc.height()).min().getAsInt();
			res.set(nivel, height-minorHeight<=1);
		    yield res.get(nivel);
		}
		
		};
	}*/
	
	public static  Boolean isBinaryEquilibrated (BinaryTree<Character> tree) {
		List<Boolean> res=new ArrayList<>();
		return isBinaryEquilibratedAux(tree,res,0);	
	}

	public static Boolean isBinaryEquilibratedAux(BinaryTree<Character> tree, List<Boolean> res, int nivel) {
	    if (res.size() <= nivel) res.add(true);

	    return switch (tree) {
	        case BEmpty<Character> t -> true;
	        case BLeaf<Character> t -> true;
	        case BTree<Character> t -> {
	        	
	        	Boolean r=	 
	        	  isBinaryEquilibratedAux(t.left(), res, nivel + 1) 
	        	&& isBinaryEquilibratedAux(t.right(), res, nivel + 1)
	        	&&   t.left().height() - t.right().height() <= 1;
	        	res.set(nivel, r);
	        	yield res.get(nivel);
	        }
	            
	    };
	}

	public static  Boolean isTreeEquilibrated (Tree<Character> tree){
		List<Boolean> res=new ArrayList<>();
		return isTreeEquilibratedAux (tree,0,res);
	}
	
	private static  Boolean isTreeEquilibratedAux(Tree<Character> tree, int nivel, List<Boolean> res) {
		if(res.size() <= nivel) res.add(true);
		return switch (tree) {
		case TEmpty<Character> t -> true;
		case TLeaf<Character> t -> res.get(nivel);
		case TNary<Character> t -> {
			t.children().forEach(tc-> isTreeEquilibratedAux(tc,nivel+1,res));
			Integer height= 1+t.children().stream().mapToInt(tc->tc.height()).max().getAsInt();
			Integer minorHeight= 1+t.children().stream().mapToInt(tc->tc.height()).min().getAsInt();
			res.set(nivel, height-minorHeight<=1 && !res.contains(false));
			yield res.get(nivel);
		 
		}
		
		};
	}
}

