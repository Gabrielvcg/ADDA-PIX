package ejercicios;


import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;
import us.lsi.tiposrecursivos.Tree;



public class Ejercicio3 {

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

