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

public class Ejercicio4 {

	
	public static <E> List<List<E>> caminoDivisibleB (BinaryTree<Integer> tree) {
		return caminoDivisibleBAux(tree,new ArrayList<List<E>>(),new ArrayList<E>(),0);	
		}
	public static <E> List<List<E>> caminoDivisibleBAux(BinaryTree<Integer> tree,List<List<E>> res,List<E> ac, int i) {
		
		return switch (tree) {
		case BEmpty<Integer> t -> {
			ac.add((E)"_");
			Integer total=  ac.stream()
	                .mapToInt(elemento -> {
	                    try {
	                        return Integer.parseInt(elemento.toString());
	                    } catch (NumberFormatException e) {
	                        return 0; // Tratar elementos no válidos como 0
	                    }
	                })
	                .sum();
		  if(total % i ==0) res.add(new ArrayList<>(ac));
           ac.remove(ac.size() - 1);
           yield res;
		}
		case BLeaf<Integer> t -> {
			ac.add((E)t.label());
			Integer total=  ac.stream()
		                .mapToInt(elemento -> {
		                    try {
		                        return Integer.parseInt(elemento.toString());
		                    } catch (NumberFormatException e) {
		                        return 0; // Tratar elementos no válidos como 0
		                    }
		                })
		                .sum();
			  if(total % i ==0) res.add(new ArrayList<>(ac));
	           ac.remove(ac.size() - 1);
	           yield res;
		}
		case BTree<Integer> t -> {
			 ac.add((E)t.label());
             caminoDivisibleBAux(t.left(), res, ac, i + 1);
             caminoDivisibleBAux(t.right(), res, ac, i + 1);
             ac.remove(ac.size() - 1);
             yield res;
		}
		};
	}
	public static<E> List<List<E>> caminoDivisibleN (Tree<Integer> tree) {
		return caminoDivisibleNAux(tree,new ArrayList<List<E>>(),new ArrayList<E>(),0);	
		}
	public static <E> List<List<E>> caminoDivisibleNAux(Tree<Integer> tree,List<List<E>> res,List<E> ac, int i) {
		
		return switch (tree) {
		case TEmpty<Integer> t -> {
			ac.add((E)"_");
			Integer total=  ac.stream()
	                .mapToInt(elemento -> {
	                    try {
	                        return Integer.parseInt(elemento.toString());
	                    } catch (NumberFormatException e) {
	                        return 0; // Tratar elementos no válidos como 0
	                    }
	                })
	                .sum();
		  if(total % i ==0) res.add(new ArrayList<>(ac));
           ac.remove(ac.size() - 1);
           yield res;
		}
		case TLeaf<Integer> t -> {
			ac.add((E)t.label());
			Integer total=  ac.stream()
		                .mapToInt(elemento -> {
		                    try {
		                        return Integer.parseInt(elemento.toString());
		                    } catch (NumberFormatException e) {
		                        return 0; // Tratar elementos no válidos como 0
		                    }
		                })
		                .sum();
			  if(total % i ==0) res.add(new ArrayList<>(ac));
	           ac.remove(ac.size() - 1);
	           yield res;
        
		}
		case TNary<Integer> t -> {
			 ac.add((E)t.label());
             t.children().forEach(tc->caminoDivisibleNAux(tc, res, ac, i + 1));		
             ac.remove(ac.size() - 1);
             yield res;
		}
		};
	}
	
	
}
