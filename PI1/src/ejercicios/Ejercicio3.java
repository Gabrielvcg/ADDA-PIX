package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio3 {

	public static List<String> ejercicio3Iterativo(List<String> a, List<String> b) {
		List<String> ac = new ArrayList<>();
		Integer i = 0;
		Integer j = 0;
		while (i < a.size() - 1 || j < b.size() - 1) {
			if (i <= a.size() - 2 && j <= b.size() - 2) {

				ac.add(a.get(i));
				ac.add(a.get(i + 1));
				ac.add(b.get(j));
				ac.add(b.get(j + 1));
				i += 2;
				j += 2;
				
			} else if (i == a.size() - 1) {
				ac.add(a.get(i));
				ac.addAll(b.subList(j, b.size()));
				i++;
				j=b.size();

			} else if (j == b.size() - 1) {
				ac.add(b.get(j));
				ac.addAll(a.subList(j, a.size()));
				j++;
				i=a.size();
			}

			else if (i == a.size()) {
				ac.addAll(b.subList(j, b.size()));
				j = b.size();

			} else if (j == b.size()) {
				ac.addAll(a.subList(i, a.size()));
				i = a.size();

			}

		}
		return ac;

	}

	public static List<String> ejercicio3Recursivo(List<String> a, List<String> b) {
		List<String> ac = new ArrayList<>();
		return ejercicio3RecursivoAux(a, b, ac, 0, 0);
	}

	public static List<String> ejercicio3RecursivoAux(List<String> a, List<String> b, List<String> ac, Integer i,
			Integer j) {

		if (i < a.size() - 1 || j < b.size() - 1) {
			if (i == a.size()) {
				ac.addAll(b.subList(j, b.size()));
				j=b.size();
			} else if (j == b.size()) {

				ac.addAll(a.subList(i, a.size()));
				i=a.size();
				
			} else if (i == a.size() - 1) {
				ac.add(a.get(i));
				ac.addAll(b.subList(j, b.size()));
				i++;
				j=b.size();
				
			} else if (j == b.size() - 1) {
				ac.add(b.get(j));
				ac.addAll(a.subList(j, a.size()));
				j++;
				i=a.size();
				
			} else if (i <= a.size() - 2 && j <= b.size() - 2) {

				ac.add(a.get(i));
				ac.add(a.get(i + 1));
				ac.add(b.get(j));
				ac.add(b.get(j + 1));
				ejercicio3RecursivoAux(a, b, ac, i = i + 2, j = j + 2);

			}

		}
		return ac;

	}

	private static record Tupla(List<String> a, List<String> b, List<String> ac, Integer i, Integer j) {
		public static Tupla of(List<String> a, List<String> b, List<String> ac, Integer i, Integer j) {
			return new Tupla(a, b, ac, i, j);
		}

		public static Tupla first(List<String> a, List<String> b) {
			return of(a, b, new ArrayList<>(), 0, 0);
		}

		public Tupla next1() {

			ac.add(a.get(i));
			ac.add(a.get(i + 1));
			ac.add(b.get(j));
			ac.add(b.get(j + 1));
			Tupla t= of(a, b, ac, i , j);
			t= of(a, b, ac, i +2, j+2);
			return t;

		}

		public Tupla next2() {
		
			if (i == a.size()) {
				ac.addAll(b.subList(j, b.size()));
				return of(a, b, ac, a.size(), b.size());
			} else if (j == b.size()) {
				ac.addAll(a.subList(i, a.size()));
				return of(a, b, ac, a.size(), b.size());

			} else if (i == a.size() - 1) {
				ac.add(a.get(i));
				ac.addAll(b.subList(j, b.size()));
				return of(a, b, ac, a.size(), b.size());
			} else if (j == b.size() - 1) {
				ac.add(b.get(j));
				ac.addAll(a.subList(i, a.size()));
				return of(a, b, ac, a.size(), b.size());
			}
			return of(a,b,ac,0,0);

		}

	}
	public static List<String> ejercicio3Funcional(List<String> a, List<String> b) {
		Tupla t = Stream
				.iterate(Tupla.first(a, b),
						e -> e.i() < e.a().size() - 1 && e.j() < e.b().size() -1  ? e.next1() : e.next2())
				.filter(e -> !(e.i() < e.a().size() -1  || e.j() < e.b().size()-1 )).findFirst().get();

		return t.ac();
	}



}
