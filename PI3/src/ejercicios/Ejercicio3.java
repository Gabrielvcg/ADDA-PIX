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
	
	public static void ejercicio3C(String file, SimpleDirectedGraph<Tarea, Precedencia> g, String nombreVista) {
	    // Encontrar tareas más determinantes
	    Set<Tarea> tareasMasDeterminantes = encontrarTareasMasDeterminantes(g);

	    // Obtener tareas que afectan directa o indirectamente a las más determinantes
	    Set<Tarea> dependientes = new HashSet<>();
	    tareasMasDeterminantes.forEach(tarea -> dependientes.addAll(obtenerTareasDependientes(g, tarea)));

	    GraphColors.toDot(g, "resultados/ejercicio3/" + file + nombreVista + ".gv",
	            x -> x.nombre(), x -> "Relacion-" + x.id().toString(),
	            v -> GraphColors.color(tareasMasDeterminantes.contains(v) ? Color.red : (dependientes.contains(v) ? Color.blue : Color.black)),
	            e -> GraphColors.color(tareasMasDeterminantes.contains(g.getEdgeSource(e)) &&
	                    tareasMasDeterminantes.contains(g.getEdgeTarget(e)) ? Color.red :
	                    (dependientes.contains(g.getEdgeSource(e)) && dependientes.contains(g.getEdgeTarget(e)) ? Color.blue : Color.black)));

	    List<String> nombresTareasMasDeterminantes = tareasMasDeterminantes.stream().map(Tarea::nombre).collect(Collectors.toList());
	    List<String> nombresDependientes = dependientes.stream().map(Tarea::nombre).collect(Collectors.toList());

	    System.out.println("\nEJERCICIO 3C ======================================================");
	    System.out.println("Tareas más determinantes: " + nombresTareasMasDeterminantes);
	    System.out.println("Tareas dependientes de las más determinantes: " + nombresDependientes);
	    System.out.println(file + nombreVista + ".gv generado en " + "resultados/ejercicio3");
	}

	private static Set<Tarea> encontrarTareasMasDeterminantes(SimpleDirectedGraph<Tarea, Precedencia> g) {
	    int maxDependencias = 0;
	    Set<Tarea> tareasMasDeterminantes = new HashSet<>();

	    // Iterar sobre todas las tareas para encontrar las más determinantes
	    for (Tarea tarea : g.vertexSet()) {
	        Set<Tarea> dependientes = new HashSet<>();
	        obtenerTareasDependientesRecursivo(g, tarea, dependientes);

	        if (dependientes.size() > maxDependencias) {
	            maxDependencias = dependientes.size();
	            tareasMasDeterminantes.clear();
	            tareasMasDeterminantes.add(tarea);
	        } else if (dependientes.size() == maxDependencias) {
	            tareasMasDeterminantes.add(tarea);
	        }
	    }

	    return tareasMasDeterminantes;
	}

	private static void obtenerTareasDependientesRecursivo(SimpleDirectedGraph<Tarea, Precedencia> g, Tarea tarea, Set<Tarea> dependientes) {
	    dependientes.add(tarea);

	    for (Precedencia edge : g.outgoingEdgesOf(tarea)) {
	        Tarea sucesor = g.getEdgeTarget(edge);
	        if (!dependientes.contains(sucesor)) {
	            obtenerTareasDependientesRecursivo(g, sucesor, dependientes);
	        }
	    }
	}

	private static Set<Tarea> obtenerTareasDependientes(SimpleDirectedGraph<Tarea, Precedencia> g, Tarea tarea) {
	    Set<Tarea> dependientes = new HashSet<>();
	    obtenerTareasDependientesRecursivo(g, tarea, dependientes);
	    return dependientes;
	}
	private static Tarea obtenerTareaMasDependiente(SimpleDirectedGraph<Tarea, Precedencia> g) {
	    Tarea tareaMasDependiente = null;
	    int maxNumTareas = 0;

	    Set<Tarea> tareas = g.vertexSet();
	    for (Tarea tarea : tareas) {
	        Set<Tarea> tareasNecesarias = obtenerTareasNecesarias(g, tarea);

	        // Verificar si es la tarea más determinante hasta ahora
	        if (tareasNecesarias.size() > maxNumTareas) {
	            maxNumTareas = tareasNecesarias.size();
	            tareaMasDependiente = tarea;
	        }
	    }

	    return tareaMasDependiente;
	}

}
