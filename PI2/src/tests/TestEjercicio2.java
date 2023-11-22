package tests;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;
import us.lsi.math.Math2;
import us.lsi.recursivos.problemasdelistas.ProblemasDeListas;


public class TestEjercicio2 {
	
	private static Integer nMin = 1000; // n mínimo para el cálculo 
	private static Integer nMax = 5000; // n máximo para el cálculo 
	private static Integer nIncr = 100; // incremento en los valores de n del cálculo 
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 1000; // número de iteraciones para warmup
	
	public static List<Integer> cargarLista(Integer t) {
		Integer i=0;
		List<Integer> ac=new ArrayList<Integer>();
		while(i<t) {
			ac.add((int)Math.random());
			i++;
		}
		return ac;
	}

	public static void genDataE2a(List<Integer> ls) {
		
		
		String file = "ficheros_generados/E2a.txt";
		Function<Integer,Long> f1 = GenData.time(t -> ProblemasDeListas.mergeSort(ls.subList(0, t),100));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
public static void genDataE2b(List<Integer> ls) {
		
		
		String file = "ficheros_generados/E2b.txt";
		Function<Integer,Long> f1 = GenData.time(t -> ProblemasDeListas.mergeSort(ls.subList(0, t),150));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
	}
public static void genDataE2c(List<Integer> ls) {
	
	String file = "ficheros_generados/E2c.txt";
	Function<Integer,Long> f1 = GenData.time(t -> ProblemasDeListas.mergeSort(ls.subList(0, t),250));
//	Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
	GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
}
public static void genDataE2d(List<Integer> ls) {
	
	
	String file = "ficheros_generados/E2d.txt";
	Function<Integer,Long> f1 = GenData.time(t -> ProblemasDeListas.mergeSort(ls.subList(0, t),500));
//	Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
	GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
}
public static void genDataE2e(List<Integer> ls) {
	
	
	String file = "ficheros_generados/E2e.txt";
	Function<Integer,Long> f1 = GenData.time(t -> ProblemasDeListas.mergeSort(ls.subList(0, t),1000));
//	Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
	GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,nIncr,nIter,nIterWarmup);
}
	public static void showE2a() {
		String file = "ficheros_generados/E2a.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of();
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	
	public static void showE2b() {
		String file = "ficheros_generados/E2b.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of();
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	public static void showE2c() {
		String file = "ficheros_generados/E2c.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of();
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	public static void showE2d() {
		String file = "ficheros_generados/E2d.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of();
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
	public static void showE2e() {
		String file = "ficheros_generados/E2e.txt";
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of();
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}
		
	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/E2a.txt","ficheros_generados/E2b.txt","ficheros_generados/E2c.txt",
						"ficheros_generados/E2d.txt",	"ficheros_generados/E2e.txt"), 
				List.of("umbral 100","umbral 150","umbral 250","umbral 500","umbral 1000"));
//		List.of("ficheros_generados/pr.txt","ficheros_generados/lin.txt"), 
//		List.of("Recursiva","Iterativa"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ls=cargarLista(nMax);
		genDataE2a(ls);
		genDataE2b(ls);
		genDataE2c(ls);
		genDataE2d(ls);
		genDataE2e(ls);
		showE2a();
		showE2b();
		showE2c();
		showE2d();
		showE2e();
		showCombined();
		

}
	}
