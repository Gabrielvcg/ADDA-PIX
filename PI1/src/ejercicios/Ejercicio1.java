package ejercicios;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.EnteroCadena;

public class Ejercicio1 {

	public static String ejercicio1(Integer varA, Integer varB) {
		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a() + 3, elem.a() % 2 == 0 ? elem.a() + "*" : elem.a() + "!");
		};
		return Stream.iterate(EnteroCadena.of(varA, "A"), elem -> elem.a() < varB, nx)
				.filter(elem -> elem.a() % 10 != 0).map(elem -> elem.s()).collect(Collectors.joining("-"));
	}

	public static String ejercicio1Iterativo(Integer varA, Integer varB) {
		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a() + 3, elem.a() % 2 == 0 ? elem.a() + "*" : elem.a() + "!");
		};

		EnteroCadena e = EnteroCadena.of(varA, "A");
		String result = "";

		while (e.a() < varB) {
			if (e.a() % 10 != 0) {
				if (result.length() > 0) {
					result = result.concat("-");
				}
				result = result.concat(e.s());
			}
			e = nx.apply(e);
		}

		return result;
	}

	public static String ejercicio1Recursivo(Integer varA, Integer varB) {

		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a() + 3, elem.a() % 2 == 0 ? elem.a() + "*" : elem.a() + "!");
		};

		return ejercicio1RecursivoAux(nx, varA, varB, EnteroCadena.of(varA, "A"), "");

	}

	public static String ejercicio1RecursivoAux(UnaryOperator<EnteroCadena> nx, Integer varA, Integer varB,
			EnteroCadena e, String result) {

		if (e.a() >= varB) {
			return result;
		} else {

			if (e.a() % 10 != 0) {

				if (result.length() == 0) {
					result = result + e.s();

				} else {
					result = result + "-" + e.s();

				}
			}
			result = ejercicio1RecursivoAux(nx, varA, varB, nx.apply(e), result);

		}
		return result;
	}
}
