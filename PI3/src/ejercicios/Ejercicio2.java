package ejercicios;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import datos.Atraccion;
import datos.Vecindad;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

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
	
}
