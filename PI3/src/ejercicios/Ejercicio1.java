package ejercicios;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import datos.Relacion;
import datos.Usuario;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

import us.lsi.graphs.views.SubGraphView;

public class Ejercicio1 {
	
	
	// Crea vista del grafo
	public static void ejercicio1A(String file, SimpleDirectedWeightedGraph<Usuario,Relacion> g, Predicate<Usuario> pv, 
			Predicate<Relacion> pa, String nombreVista) {
		
		Graph<Usuario, Relacion> vista = SubGraphView.of(g, pv, pa);

		GraphColors.toDot(g,"resultados/ejercicio1/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.colorIf(Color.red, vista.containsVertex(v)),
				e->GraphColors.colorIf(Color.red, vista.containsEdge(e)));
		
		System.out.println(file + nombreVista + ".gv generado en " + "resultados/ejercicio1");
	}
	
	private static Color asignaColor(Usuario u, List<Set<Usuario>> ls, 
			ConnectivityInspector<Usuario, Relacion> alg) {
		Color[] vc = Color.values();
		Set<Usuario> s = alg.connectedSetOf(u);
		return vc[ls.indexOf(s)];
	}
	
	public static void ejercicio1B(SimpleDirectedWeightedGraph<Usuario, Relacion> g, String file) {	
		
		var alg = new ConnectivityInspector<>(g);
		List<Set<Usuario>> ls = alg.connectedSets();
		System.out.println("Hay " + ls.size() + " componentes conexas." );
		
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + "B.gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.color(asignaColor(v,ls,alg)),
				e->GraphColors.color(asignaColor(g.getEdgeSource(e), ls, alg)));
		
		System.out.println(file + "B.gv generado en " + "resultados/ejercicio1");
	}
	public static void ejercicio1C(SimpleDirectedWeightedGraph<Usuario, Relacion> g, String file) {
		
		var alg = new GreedyVCImpl<>(g);
		Set<Usuario> vc = alg.getVertexCover();		
		
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + "B.gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.colorIf(Color.red, vc.contains(v)),
				e->GraphColors.color(Color.red));
		
		System.out.println(file + "C.gv generado en " + "resultados/ejercicio1");
	}
}
