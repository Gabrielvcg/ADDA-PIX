package ejercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import datos.Precedencia;
import datos.Tarea;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;


public class Ejercicio3 {

	public static void ejercicio3A(String file, SimpleDirectedGraph<Tarea,Precedencia> g, String nombreVista) {

		// Realizar ordenación topológica
		TopologicalOrderIterator<Tarea, Precedencia> ordering = new TopologicalOrderIterator<>(g);

	    // Obtener la lista de tareas en el orden topológico
		List<Tarea> TareasOrdeTopologico=new ArrayList<>();
		ordering.forEachRemaining(v->TareasOrdeTopologico.add(v));
	    
	GraphColors.toDot(g,"resultados/ejercicio3/" + file + nombreVista + ".gv",
			x->x.nombre(), x->"Relacion-"+x.id().toString(),
			v->GraphColors.color(Color.black),
			e->GraphColors.color(Color.black));
	
	List<String> tareas = new ArrayList<>();
	TareasOrdeTopologico.forEach(tot->tareas.add(tot.nombre()));
	System.out.println("\nEJERCICIO 3A ======================================================");
	System.out.println("Orden de tareas: "+tareas);
	System.out.println(file + nombreVista + ".gv generado en " + "resultados/ejercicio3");
	}
	

	public static void ejercicio3B(String file, SimpleDirectedGraph<Tarea, Precedencia> g, Tarea tareaDada, String nombreVista) {
	    // Obtener tareas necesarias para completar la tarea dada
	    Set<Tarea> tareasNecesarias = obtenerTareasNecesarias(g, tareaDada);

	    GraphColors.toDot(g, "resultados/ejercicio3/" + file + nombreVista + ".gv",
	            x -> x.nombre(), x -> "Relacion-" + x.id().toString(),
	            v -> GraphColors.color(tareaDada.equals(v) ? Color.red :(tareasNecesarias.contains(v) ? Color.blue : Color.black)),
	            e -> GraphColors.color(tareasNecesarias.contains(g.getEdgeSource(e)) &&
	                                   tareasNecesarias.contains(g.getEdgeTarget(e)) ? Color.blue : Color.black));

	    List<String> tareas = new ArrayList<>();
	    tareasNecesarias.forEach(tarea -> tareas.add(0,tarea.nombre()));
	    
	    tareas.remove(tareaDada.nombre());
	    
	    System.out.println("\nEJERCICIO 3B ======================================================");
	    System.out.println("Tareas necesarias para completar " + tareaDada.nombre() + ": " + tareas);
	    System.out.println(file + nombreVista + ".gv generado en " + "resultados/ejercicio3");
	}
	private static Set<Tarea> obtenerTareasNecesarias(SimpleDirectedGraph<Tarea, Precedencia> g, Tarea tareaDada) {
	    Set<Tarea> tareasNecesarias = new HashSet<>();
	    Queue<Tarea> cola = new LinkedList<>();
	    cola.offer(tareaDada);

	    while (!cola.isEmpty()) {
	        Tarea tareaActual = cola.poll();
	        tareasNecesarias.add(tareaActual);

	     // Obtener predecesores de la tarea actual
	        Set<Tarea> predecesores = new HashSet<>();
	        for (Precedencia edge : g.incomingEdgesOf(tareaActual)) {
	            predecesores.add(g.getEdgeSource(edge));
	        }

	        // Agregar predecesores a la cola si no han sido visitados
	        predecesores.stream().filter(predecesor -> !tareasNecesarias.contains(predecesor)).forEach(cola::offer);
	    }

	    return tareasNecesarias;
	}
	
}
