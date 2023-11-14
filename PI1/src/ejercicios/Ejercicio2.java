package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;



public class Ejercicio2 {



	public static List<Integer> ejercicio2RecursivoNoFinal(Integer a, Integer b) {
		List<Integer>ls = new ArrayList<>();

		if (a < 2 || b < 2) {
			ls.add(a * b);
		} else if (a > b) {
			ls = ejercicio2RecursivoNoFinal(a % b, b - 1);
			ls.add(a);
		} else {
			ls = ejercicio2RecursivoNoFinal(a - 2, b / 2);
			ls.add(b);
		}
		return ls;
	}

	public static List<Integer> ejercicio2RecursivoFinal(Integer a, Integer b) {
		List<Integer> ls = new ArrayList<>();
		return ejercicio2RecursivoFinalAux(a, b, ls);
	}

	public static List<Integer> ejercicio2RecursivoFinalAux(Integer a, Integer b, List<Integer> ls) {

		if (a < 2 || b < 2) {
			ls.add(0, a * b);
		} else if (a > b) {
			ls.add(0, a);
			ls = ejercicio2RecursivoFinalAux(a % b, b - 1, ls);
		} else {
			ls.add(0, b);
			ls = ejercicio2RecursivoFinalAux(a - 2, b / 2, ls);

		}

		return ls;
	}

	public static List<Integer> ejercicio2Iterativo(Integer a, Integer b) {
		List<Integer> ls = new ArrayList<>();
		while (!(a < 2 || b < 2)) {
			if (a > b) {
				ls.add(0, a);
				a = a % b;
				b = b - 1;
			} else {
				ls.add(0, b);
				a = a - 2;
				b = b / 2;
			}
		}
		ls.add(0, a * b);
		return ls;
	}
	private static record Tupla(List<Integer> ls, Integer a, Integer b) {
		public static Tupla of(List<Integer> ls, Integer a, Integer b) {
			return new Tupla(ls, a, b);
		}
		
		public static Tupla first(Integer a, Integer b) {
			return of( new ArrayList<>(), a, b);
		}
		
		public Tupla next1() {
			
			ls.add(0,a);
			return of(ls, a%b, b-1);
		}
		public Tupla next2() {
			ls.add(0,b);
			return of(ls, a-2, b/2);
		}
	}
	
	public static List<Integer> ejercicio2Funcional(Integer a, Integer b){
		Tupla t= Stream.iterate(Tupla.first(a, b), e->e.a()>e.b()? e.next1( ) : e.next2())
				.filter(e->e.a()<2 || e.b()<2).findFirst().get();
		t.ls().add(0,t.a()*t.b());
		return t.ls();
	}

}
