package ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Atraccion;
import datos.Vecindad;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio2 {

	public static Atraccion atraccion(Graph<Atraccion,Vecindad> graph, String nombre) {
		return graph.vertexSet().stream().filter(c->c.nombre().equals(nombre)).findFirst().get();
	}
	
	public static void ejercicio2A(String file, SimpleWeightedGraph<Atraccion,Vecindad> g,String a1, String a2, String nombreVista) {
		
		var alg = new DijkstraShortestPath<>(g);
		Atraccion origen = atraccion(g,a1);
		Atraccion destino = atraccion(g,a2);
		GraphPath<Atraccion, Vecindad> gp = alg.getPath(origen, destino);
	

		GraphColors.toDot(g,"resultados/ejercicio2/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.distancia().toString(),
				v->GraphColors.colorIf(Color.red,gp.getVertexList().contains(v)),
				e->GraphColors.colorIf(Color.red,gp.getEdgeList().contains(e)));
		
		List<String> camino=new ArrayList<>();
		gp.getVertexList().forEach(a->camino.add(a.nombre()));
		System.out.println("\nEJERCICIO 2A ======================================================");
		System.out.print("Camino más corto en distancia desde "+a1+" hasta "+a2+":"+camino.toString());
		System.out.println("\n"+file + nombreVista + ".gv generado en " + "resultados/ejercicio2");
	}
	
	public static <E> void ejercicio2B(String file, SimpleWeightedGraph<Atraccion,Vecindad> g, String nombreVista) {
	
		var alg =new HeldKarpTSP<Atraccion, Vecindad>();
		GraphPath<Atraccion,Vecindad> gp=alg.getTour(g);
		
		GraphColors.toDot(g,"resultados/ejercicio2/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.tiempo().toString(),
				v->GraphColors.colorIf(Color.red,gp.getStartVertex().equals(v)),
				e->GraphColors.colorIf(Color.red, gp.getEdgeList().contains(e)));
		List<String> camino=new ArrayList<>();
		gp.getVertexList().forEach(c->camino.add(c.nombre()));
		System.out.println("\nEJERCICIO 2B ======================================================");
		System.out.print("Camino más corto en tiempo que pasa por todas las atracciones:"+camino);
		System.out.println("\n"+file + nombreVista + ".gv generado en " + "resultados/ejercicio2");
	}
	public static <E> void ejercicio2C(String file, SimpleWeightedGraph<Atraccion,Vecindad> g, String nombreVista, Integer horas) {
		
		Atraccion puntoInicio= obtenerVerticeMasPopular(g);
		
		List<Atraccion> camino=new ArrayList<>();
		Set<Atraccion> atraccionesVisitadas = new HashSet<>();
	    Atraccion atraccionActual = puntoInicio;
	    Double tiempoTotal = atraccionActual.duracion() + atraccionActual.tiempoEspera();
	    Integer aHoras=horas*60;
	    
	    while (true) {
            camino.add(atraccionActual);
            atraccionesVisitadas.add(atraccionActual);

            // Obtener vecinos no visitados
            Set<Atraccion> vecinosNoVisitados = Graphs.neighborSetOf(g, atraccionActual)
                    .stream()
                    .filter(v -> !atraccionesVisitadas.contains(v))
                    .collect(Collectors.toSet());

            // Si no hay vecinos no visitados, salir del bucle
            if (vecinosNoVisitados.isEmpty()) {
                break;
            }

            // Seleccionar el vecino más popular
            Atraccion vecinoMasPopular = vecinosNoVisitados
                    .stream()
                    .max(Comparator.comparingDouble(Atraccion::popularidad))
                    .orElse(null);

            if (vecinoMasPopular != null) {
                // Calcular el tiempo total para llegar al vecino más popular
                Vecindad edge = g.getEdge(atraccionActual, vecinoMasPopular);
                tiempoTotal += edge.tiempo() + vecinoMasPopular.tiempoEspera()+ vecinoMasPopular.duracion();

                // Si el tiempo total supera las horas disponibles, salir del bucle
                if (tiempoTotal > aHoras) {
                    break;
                }

                atraccionActual = vecinoMasPopular;
            } else {
                // No hay vecinos no visitados, salir del bucle
                break;
            }
        }
	    
		GraphColors.toDot(g,"resultados/ejercicio2/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.tiempo().toString(),
				v->GraphColors.colorIf(Color.red,v.equals(puntoInicio)),
				  e -> {
			            Atraccion source = g.getEdgeSource(e);
			            Atraccion target = g.getEdgeTarget(e);
			            int sourceIndex = camino.indexOf(source);
			            int targetIndex = camino.indexOf(target);
			            
			            // Colorear solo si ambas atracciones están en el camino y la arista está entre ellas
			            return GraphColors.colorIf(Color.red, sourceIndex >= 0 && targetIndex >= 0 &&
			                    Math.abs(sourceIndex - targetIndex) == 1);
			        });
		
        List<String> nombresCamino = camino.stream().map(Atraccion::nombre).collect(Collectors.toList());
		System.out.println("\nEJERCICIO 2C ======================================================");
		System.out.print("Camino:"+nombresCamino.toString());
		System.out.println("\n"+file + nombreVista + ".gv generado en " + "resultados/ejercicio2");
	}
	
	private static Atraccion obtenerVerticeMasPopular(Graph<Atraccion, Vecindad> g) {
	    Atraccion verticeMasPopular = null;
	    Double popularidadMaxima =0.;

	    Set<Atraccion> vertices = g.vertexSet();
	    for (Atraccion a : vertices) {
	       	Double popularidadActual = a.popularidad();
	        if (popularidadActual > popularidadMaxima) {
	            popularidadMaxima = popularidadActual;
	            verticeMasPopular = a;
	        }
	    }

	    return verticeMasPopular;
	}
	
	
}
