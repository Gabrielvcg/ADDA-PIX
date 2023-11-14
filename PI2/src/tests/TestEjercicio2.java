package tests;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.Random;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;
import us.lsi.math.Math2;
import us.lsi.recursivos.problemasdelistas.ProblemasDeListas;


public class TestEjercicio2 {
	
	private static Integer nMin = 1000; // n mínimo para el cálculo 
	private static Integer nMax = 10000; // n máximo para el cálculo 
	private static Integer nIncr = 530; // incremento en los valores de n del cálculo 
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 1000; // número de iteraciones para warmup
	
	public static List<Integer> cargarLista(Integer t) {
		Integer i=0;
		List<Integer> ac=new ArrayList<Integer>();
		while(i<t) {
			ac.add(Math2.getRandom().nextInt());
			i++;
		}
		return ac;
	}

	public static void genDataE2() {
		
		
		String file = "ficheros_generados/E2.txt";
		Function<Integer,Long> f1 = GenData.time(t -> ProblemasDeListas.mergeSort(cargarLista(t),7000));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
	public static void showE2() {
		String file = "ficheros_generados/E2.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
		
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		genDataE2();
		showE2();

}
	}
