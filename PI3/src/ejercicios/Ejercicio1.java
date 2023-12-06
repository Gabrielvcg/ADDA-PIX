package ejercicios;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.AsUndirectedGraph;
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
		
		
		System.out.println("\nEJERCICIO 1A ======================================================");
		System.out.println("Los usuarios que siguen a más de 3 usuarios y con un índice de "
				+ "interacción de media de más de 2.5 son:"+vista.vertexSet().toString());
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
		
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + "B.gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.color(asignaColor(v,ls,alg)),
				e->GraphColors.color(asignaColor(g.getEdgeSource(e), ls, alg)));
	
		
		System.out.println("\nEJERCICIO 1B ======================================================");
		System.out.println("Hay " + ls.size() + " componentes conexas." );

		int i=1;
		for (Set<Usuario> su :ls) {
			System.out.println("Componente conexa "+i+": "+ su.toString());
			i++;
		}

		System.out.println(file + "B.gv generado en " + "resultados/ejercicio1");
	}
	public static void ejercicio1C(SimpleDirectedWeightedGraph<Usuario, Relacion> g, String file) {
		
		Graph<Usuario, Relacion> undirectedGraph = 
	            new AsUndirectedGraph<>(g);
	
		var alg = new GreedyVCImpl<>(undirectedGraph);
		Set<Usuario> vc = alg.getVertexCover();		
		
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + "C.gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.colorIf(Color.red, vc.contains(v)),
				e->GraphColors.color(Color.black));
		
		System.out.println("\nEJERCICIO 1C ======================================================");
		System.out.println("Usuarios seleccionados: "+vc.toString());
		System.out.println(file + "C.gv generado en " + "resultados/ejercicio1");
	}
	public static void ejercicio1D(String file, SimpleDirectedWeightedGraph<Usuario,Relacion> g, Predicate<Usuario> pv, 
			Predicate<Relacion> pa, String nombreVista) {
		
		Graph<Usuario, Relacion> vista = SubGraphView.of(g, pv, pa);
		Map<Usuario,Double> dicMedias=new HashMap<>();

		Set<Usuario> usuarios= vista.vertexSet();
		usuarios.forEach(u->{
		    Set<Relacion> edges = vista.incomingEdgesOf(u);
		    List<Double> interacciones = edges.stream()
		            .map(r -> r.interaccion())  
		            .collect(Collectors.toList());

		    Double total = interacciones.stream().reduce(0.0, Double::sum);
		    Double media = total/interacciones.size();
		    dicMedias.put(u, media);
		});
		
	    List<Usuario> topUsuarios = dicMedias.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
	    
		GraphColors.toDot(g,"resultados/ejercicio1/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.colorIf(Color.red, topUsuarios.contains(v)),
				e->GraphColors.color(Color.black));
		
		System.out.println("\nEJERCICIO 1D ======================================================");
		System.out.println("Los/el "+ topUsuarios.size()+" usuario(s) con mayor interacción media son/es: "+topUsuarios.toString());
		System.out.println(file + nombreVista + ".gv generado en " + "resultados/ejercicio1");
	}
}
