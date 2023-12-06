package tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import datos.Relacion;
import datos.Usuario;
import ejercicios.Ejercicio1;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {


	public static void main(String[] args) {
		testsEjercicio1("ejercicio1_1");
		System.out.println("========================================================================================");
		testsEjercicio1("ejercicio1_2");
		System.out.println("========================================================================================");
		testsEjercicio1("ejercicio1_3");
	
		}
	
	public static void testsEjercicio1(String file) {
		SimpleDirectedWeightedGraph<Usuario, Relacion> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Usuario::ofFormat, //factoria para construir los vertices
							Relacion::ofFormat, //factoria para crear las aristas
							Graphs2::simpleDirectedWeightedGraph,
							Relacion::interaccion); //creador del grafo
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
// A: usuarios que siga a mas de 3 personas y media de las aristas dirigidas a los que sigue mayor que 2.5	
		
		Predicate<Usuario> pv1 = u -> {
		    Set<Relacion> edges = g.outgoingEdgesOf(u);
		    List<Double> interacciones = edges.stream()
		            .map(r -> r.interaccion())  
		            .collect(Collectors.toList());
		    Double total = interacciones.stream().reduce(0.0, Double::sum);
		    return edges.size() > 3 && total/interacciones.size() > 2.5;
		};
		Predicate<Relacion> pa1= null;
		
		Ejercicio1.ejercicio1A(file, g,pv1,pa1,"A");
		// B: Cantidad de componentes conexas y pintarlas cada una con distinto color
		Ejercicio1.ejercicio1B(g, file);
		// C: Aplicar algoritmo de cobertura de vertices minima
		Ejercicio1.ejercicio1C(g, file);
		//D  usuarios con 5 o mas seguidores, mas de 3 aficiones y mas de 4 de actividad
		// Además luego dar solo los 2 con mayor media
		
		Predicate<Usuario> pv2 = u -> {
		    Set<Relacion> edges = g.incomingEdgesOf(u);
		    return edges.size() >= 5 && u.aficiones().size()>3 && u.actividad()>4;
		};
		Predicate<Relacion> pa2= null;
		
		Ejercicio1.ejercicio1D(file, g,pv2,pa2,"D");

		
	}
		
		
		
	
}
