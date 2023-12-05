package tests;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import datos.Relacion;
import datos.Usuario;
import ejercicios.Ejercicio1;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {


	public static void main(String[] args) {
		testsEjercicio1A("ejercicio1_1");
		testsEjercicio1A("ejercicio1_2");
		testsEjercicio1A("ejercicio1_3");
	
		}
	
	public static void testsEjercicio1A(String file) {
		SimpleDirectedWeightedGraph<Usuario, Relacion> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Usuario::ofFormat, //factoria para construir los vertices
							Relacion::ofFormat, //factoria para crear las aristas
							Graphs2::simpleDirectedWeightedGraph,
							Relacion::interaccion); //creador del grafo
		
		/*
		 * //Para mostrar el grafo original
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + ".gv",
				v->v.nombre(), //que etiqueta mostrar en vertices y aristas
				e->e.interaccion().toString(),
				v->GraphColors.color(Color.black), //color o estilo de vertices y aristas
				e->GraphColors.color(Color.black));
		*/
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
// A: usuarios que siga a mas de 3 personas y media de las aristas dirigidas a los que sigue mayor que 2.5	
		
		Predicate<Usuario> pv1 = u -> {
		    Set<Relacion> edges = g.outgoingEdgesOf(u);
		    // Filtrar las aristas por el índice de interacción
		    List<Double> interacciones = edges.stream()
		            .map(r -> r.interaccion())  // Suponiendo que hay un método Relacion.interaccion() que devuelve el índice de interacción
		            .collect(Collectors.toList());
		    Double total = interacciones.stream().reduce(0.0, Double::sum);
		    // Verificar la condición
		    return edges.size() > 3 && total/interacciones.size() > 2.5;
		};
		Predicate<Relacion> pa1= null;
		
		Ejercicio1.ejercicio1A(file, g,pv1,pa1,"A");
		
		// B: Cantidad de componentes conexas y pintarlas cada una con distinto color
		Ejercicio1.ejercicio1B(g, file);
	}
		
		
		
	
}
