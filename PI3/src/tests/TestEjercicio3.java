package tests;

import org.jgrapht.graph.SimpleDirectedGraph;

import datos.Precedencia;
import datos.Tarea;
import ejercicios.Ejercicio3;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class TestEjercicio3 {

	public static void main(String[] args) {
		testsEjercicio3F1("ejercicio3_1");
		System.out.println("=========================================================================================");
		testsEjercicio3F2("ejercicio3_2");
		System.out.println("===========================================================================================");
		testsEjercicio3F3("ejercicio3_3");
	
		}
	
	public static void testsEjercicio3F1(String file) {
		SimpleDirectedGraph<Tarea, Precedencia> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Tarea::ofFormat, //factoria para construir los vertices
							Precedencia::ofFormat, //factoria para crear las aristas
							Graphs2::simpleDirectedGraph);
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		Ejercicio3.ejercicio3A(file, g,"A");
		Ejercicio3.ejercicio3B(file, g,new Tarea("Tarea5"),"B");		

	}
	public static void testsEjercicio3F2(String file) {
		SimpleDirectedGraph<Tarea, Precedencia> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Tarea::ofFormat, //factoria para construir los vertices
							Precedencia::ofFormat, //factoria para crear las aristas
							Graphs2::simpleDirectedGraph);
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		Ejercicio3.ejercicio3A(file, g,"A");
		Ejercicio3.ejercicio3B(file, g,new Tarea("Tarea8"),"B");		

	}
	public static void testsEjercicio3F3(String file) {
		SimpleDirectedGraph<Tarea, Precedencia> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Tarea::ofFormat, //factoria para construir los vertices
							Precedencia::ofFormat, //factoria para crear las aristas
							Graphs2::simpleDirectedGraph);
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		Ejercicio3.ejercicio3A(file, g,"A");
		Ejercicio3.ejercicio3B(file, g,new Tarea("Tarea8"),"B");		

	}
	
}
