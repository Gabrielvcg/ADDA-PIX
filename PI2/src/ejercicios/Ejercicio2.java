package ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import us.lsi.common.List2;

public class Ejercicio2 {

	public static <E> Collector<E,List<E>,List<E>> mergeSort(Comparator<? super E> cmp) {
		return Collector.of(
				()->new ArrayList<>(), 
				(x,e)->List2.insertOrdered(x,e,cmp), 
				(x,y)->List2.mergeOrdered(x,y,cmp), 
				x->x);
	}
	
	public static <E extends Comparable<? super E>> Collector<E,List<E>,List<E>> mergeSort() {
		return mergeSort(Comparator.naturalOrder());
	}
	
}
