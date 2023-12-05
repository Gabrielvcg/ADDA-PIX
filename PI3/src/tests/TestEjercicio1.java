package tests;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graph;

import datos.Relacion;
import datos.Usuario;
import ejercicios.Ejercicio1;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {


	public static void main(String[] args) {
		testsEjemplo1("ejercicio1_1");
	
		
	}
	
	public static void testsEjemplo1(String file) {
		Graph<Usuario, Relacion> g = GraphsReader
					.newGraph("ficheros/" + file + ".txt", //fichero de datos
							Usuario::ofFormat, //factoria para construir los vertices
							Relacion::ofFormat, //factoria para crear las aristas
							Graphs2::simpleDirectedWeightedGraph,
							Relacion::interaccion); //creador del grafo
		
		
		
		
		//Para mostrar el grafo original
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + ".gv",
				v->v.nombre(), //que etiqueta mostrar en vertices y aristas
				e->e.interaccion().toString(),
				v->GraphColors.color(Color.black), //color o estilo de vertices y aristas
				e->GraphColors.color(Color.black));
		
		
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
		
		// a) PrimerPredicado: Ciudades cuyo nombre contiene la letra “e”, y carreteras con menos de 200 km de distancia 
	
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
		Ejercicio1.crearVista(file, g,pv1,pa1," Primer predicado");
		
		
		/* b) SegundoPredicado: Ciudades que poseen menos de 500.000 habitantes, y carreteras cuya ciudad origen o 
		   destino tiene un nombre de más de 5 caracteres y poseen más de 100 km de distancia */
		/*
		Predicate<Ciudad> pv2 = c -> c.habitantes() < 500000;
		Predicate<Carretera> pa2 = ca -> ca.km() > 100 &&		
			(g.getEdgeSource(ca).nombre().length() > 5 || g.getEdgeTarget(ca).nombre().length() > 5);
		
		Ejercicio1.crearVista(file, g,pv2,pa2," Segundo predicado");
		*/
	}
}
