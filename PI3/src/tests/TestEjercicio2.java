package tests;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Atraccion;
import datos.Relacion;
import datos.Usuario;
import datos.Vecindad;
import ejercicios.Ejercicio2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio2 {
	
	public static void main(String[] args) {
		testsEjercicio2F1("ejercicio2_1");
		System.out.println("========================================================================================");
		testsEjercicio2F2("ejercicio2_2");
		System.out.println("========================================================================================");
		testsEjercicio2F3("ejercicio2_3");
	
		}
	
	public static void testsEjercicio2F1(String file) {
		SimpleWeightedGraph<Atraccion, Vecindad> gd = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Atraccion::ofFormat, //factoria para construir los vertices
							Vecindad::ofFormat, //factoria para crear las aristas
							Graphs2::simpleWeightedGraph,
							Vecindad::distancia); //creador del grafo
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + gd);
		
		Ejercicio2.ejercicio2A(file, gd,"Coches de choque","Raton Vacilon", "A");
	
		SimpleWeightedGraph<Atraccion, Vecindad> gt = GraphsReader
				.newGraph("ficheros/" + file + ".txt", //fichero de datos
						Atraccion::ofFormat, //factoria para construir los vertices
						Vecindad::ofFormat, //factoria para crear las aristas
						Graphs2::simpleWeightedGraph,
						Vecindad::tiempo); //creador del grafo
		Ejercicio2.ejercicio2B(file, gt,"B");
		
		Ejercicio2.ejercicio2C(file, gt,"C", 5);
		
	}
	public static void testsEjercicio2F2(String file) {
		SimpleWeightedGraph<Atraccion, Vecindad> gd = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Atraccion::ofFormat, //factoria para construir los vertices
							Vecindad::ofFormat, //factoria para crear las aristas
							Graphs2::simpleWeightedGraph,
							Vecindad::distancia); //creador del grafo
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + gd);
		
		Ejercicio2.ejercicio2A(file, gd,"Coches de choque","Patitos", "A");
		
		SimpleWeightedGraph<Atraccion, Vecindad> gt = GraphsReader
				.newGraph("ficheros/" + file + ".txt", //fichero de datos
						Atraccion::ofFormat, //factoria para construir los vertices
						Vecindad::ofFormat, //factoria para crear las aristas
						Graphs2::simpleWeightedGraph,
						Vecindad::tiempo); //creador del grafo
		
		Ejercicio2.ejercicio2B(file, gt,"B");

		
		Ejercicio2.ejercicio2C(file, gt,"C", 2);

	}
	public static void testsEjercicio2F3(String file) {
		SimpleWeightedGraph<Atraccion, Vecindad> gd = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Atraccion::ofFormat, //factoria para construir los vertices
							Vecindad::ofFormat, //factoria para crear las aristas
							Graphs2::simpleWeightedGraph,
							Vecindad::distancia); //creador del grafo
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + gd);
		
		
		Ejercicio2.ejercicio2A(file, gd,"Casa del Terror","Pim pam pum", "A");
		
		SimpleWeightedGraph<Atraccion, Vecindad> gt = GraphsReader
				.newGraph("ficheros/" + file + ".txt", //fichero de datos
						Atraccion::ofFormat, //factoria para construir los vertices
						Vecindad::ofFormat, //factoria para crear las aristas
						Graphs2::simpleWeightedGraph,
						Vecindad::tiempo); //creador del grafo
		Ejercicio2.ejercicio2B(file, gt,"B");
		
		
		Ejercicio2.ejercicio2C(file, gt,"C", 3);

		
	}
}
