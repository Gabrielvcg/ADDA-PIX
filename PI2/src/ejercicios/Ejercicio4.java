package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ejercicio4 {

    //############################################
	public static List<List<Integer>> caminoDivisibleB (BinaryTree<Integer> tree) {
		
		List<List<Integer>> crudo= caminoDivisibleBAux(tree,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),0);	
		  List<List<Integer>> filtrado= crudo.stream()
	                .filter(path -> {
	                    int sum = path.stream().mapToInt(Integer::intValue).sum();
	                    return sum % path.size()-1 == 0;
	                })
	                .toList();
		 return filtrado;
	}
	
	
public static List<List<Integer>> caminoDivisibleBAux(BinaryTree<Integer> tree,List<List<Integer>> res,List<Integer> ac, int i) {
		
		return switch (tree) {
		case BEmpty<Integer> t -> res;
		case BLeaf<Integer> t -> {
			  ac.add(t.label());
              res.add(new ArrayList<>(ac));
              ac.remove(ac.size() - 1);
              yield res;
        
		}
		case BTree<Integer> t -> {
			 ac.add(t.label());
             caminoDivisibleBAux(t.left(), res, ac, i + 1);
             caminoDivisibleBAux(t.right(), res, ac, i + 1);
             ac.remove(ac.size() - 1);
             yield res;
		}
		};
	}
	/*
public static List<List<Integer>> caminoDivisibleBAux(BinaryTree<Integer> tree, List<List<Integer>> res,List<Integer> ac, int i) {
		
		return switch (tree) {
		case BEmpty<Integer> t -> res;
		case BLeaf<Integer> t ->{
			for (List<Integer> x : res) {
				Integer total=x.stream().reduce(0, (a, b) -> a + b);
				if (!(total+t.label()% x.size()==0)) {
					res.remove(x);
				}x.add(t.label());
			}
		yield res;}
		case BTree<Integer> t -> {
			
			Integer sumaCamino=ac.stream().reduce(0, (a, b) -> a + b)+t.label();
			if(sumaCamino % i==0 ) {
			ac.add(0,t.label());
		}
			caminoDivisibleBAux(t.left(), res,ac, i+1); 
			caminoDivisibleBAux(t.right(), res,ac, i+1); 
			res.add(ac);
							
		yield res;
		}
		};
	}*/
	
}
