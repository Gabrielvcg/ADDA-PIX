package ejercicios;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntPair;

public class Ejercicio4 {
	
	public static String ejercicio4RecursivoSinMemoria(Integer a, Integer b) { // Definicion del enunciado
		String r = null;	
		if (a <= 4) {
			r=(a.toString()+"."+b.toString());
		} else if(b <= 4) {

			r=(b.toString()+"-"+a.toString());

		}else {
			r = ejercicio4RecursivoSinMemoria(a/2, b-2).toString() +","+ 
					ejercicio4RecursivoSinMemoria(a-2, b/2)+","+ejercicio4RecursivoSinMemoria(a-1, b-1);

		}
		return r;
	}

	public static String ejercicio4RecursivaConMemoria(Integer a, Integer b) {
		return gRecConMem(a, b, new HashMap<>());
	}
	private static String gRecConMem(Integer a, Integer b, Map<IntPair, String> m) {
		String r = null;
		IntPair key = IntPair.of(a, b);
		if(m.containsKey(key)) {
			r = m.get(key);
		} else {
			if (a <= 4) {
				r=(a.toString()+"."+b.toString());
			} else if(b <= 4) {

				r=(b.toString()+"-"+a.toString());

			}else {
				r = gRecConMem(a/2, b-2,m).toString() +","+ 
						gRecConMem(a-2, b/2,m)+","+gRecConMem(a-1, b-1,m);
		
		}
			m.put(IntPair.of(a, b), r);
		}
		return r;
	}

	public static String ejercicio4Iterativo(Integer a, Integer b) {
		Map<IntPair, String> map = new HashMap<>();
		
		String v = null;
		for (Integer i = 0; i <= a; i++) {
			for (Integer j = 0; j <= b; j++) {
				if (i <= 4) {
					v = i.toString()+"."+j.toString();
				}else if (j <= 4) {
					v = j.toString()+"-"+i.toString();
				} else {
					v = map.get(IntPair.of(i/2, j-2)) +","+ map.get(IntPair.of(i-2, j/2))+","+ map.get(IntPair.of(i-1, j-1));
				}
				map.put(IntPair.of(i, j), v);
			}
		}
		return map.get(IntPair.of(a, b));
	}
}
