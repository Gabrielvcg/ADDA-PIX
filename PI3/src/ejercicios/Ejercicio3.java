package ejercicios;

import java.util.ArrayList;
import java.util.List;

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
}
