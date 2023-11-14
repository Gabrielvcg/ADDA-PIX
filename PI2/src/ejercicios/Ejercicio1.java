package ejercicios;

import java.math.BigInteger;

public class Ejercicio1 {

	public static Double ejercicio1RecursivoD(Integer a) {
		Double ac;
		if (a < 6) {
			ac = 10.;
		} else {
			ac = Math.pow(a, 3) * ejercicio1RecursivoD(a - 1);
		}
		return ac;
	}
	

	public static BigInteger ejercicio1RecursivoBI(Integer a) {
		BigInteger ac;
		if (a<6) {
			ac = BigInteger.TEN;
		} else {
			  ac = BigInteger.valueOf((long) Math.pow(a, 3)).multiply(ejercicio1RecursivoBI(a - 1));		
			 }
		return ac;
	}

	public static Double ejercicio1IterativoD(Integer a) {
		Double ac;

		if (a < 6) {
			ac = 10.0;
		} else {
			ac = 1.0;
			while (a >= 6) {
				ac *= Math.pow(a, 3);
				a--;
			}
		}
		return ac;
	}
	public static BigInteger ejercicio1IterativoBI(Integer a) {
		BigInteger ac;

		if (a<6) {
			ac = BigInteger.TEN;
		} else {
			ac = BigInteger.ONE;
			while (a>=6) {
				ac =ac.multiply(BigInteger.valueOf((long) Math.pow(a, 3)));
				a--;
			}
		}
		return ac;
	}
}


