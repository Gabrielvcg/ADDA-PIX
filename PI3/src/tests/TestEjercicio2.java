package tests;

import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Atraccion;
import datos.Vecindad;
import ejercicios.Ejercicio2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio2 {
	
	public static void main(String[] args) {
		testsEjercicio2("ejercicio2_1");
		System.out.println("========================================================================================");
		testsEjercicio2("ejercicio2_2");
		System.out.println("========================================================================================");
		testsEjercicio2("ejercicio2_3");
	
		}
	
	public static void testsEjercicio2(String file) {
		SimpleWeightedGraph<Atraccion, Vecindad> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Atraccion::ofFormat, //factoria para construir los vertices
							Vecindad::ofFormat, //factoria para crear las aristas
							Graphs2::simpleWeightedGraph,
							Vecindad::distancia); //creador del grafo
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		Ejercicio2.ejercicio2A(file, g,"Coches de choque","Raton Vacilon", "A");
		
	}
}
