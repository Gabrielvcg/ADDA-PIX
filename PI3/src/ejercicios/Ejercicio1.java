package ejercicios;

import java.util.function.Predicate;

import org.jgrapht.Graph;

import datos.Relacion;
import datos.Usuario;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio1 {
	// Crea vista del grafo
	public static void crearVista(String file, Graph<Usuario,Relacion> g, Predicate<Usuario> pv, 
			Predicate<Relacion> pa, String nombreVista) {
		
		Graph<Usuario, Relacion> vista = SubGraphView.of(g, pv, pa);

		GraphColors.toDot(g,"resultados/ejercicio1/" + file + nombreVista + ".gv",
				x->x.nombre(), x->x.interaccion().toString(),
				v->GraphColors.colorIf(Color.red, vista.containsVertex(v)),
				e->GraphColors.colorIf(Color.red, vista.containsEdge(e)));
		
		System.out.println(file + nombreVista + ".gv generado en " + "resultados/ejercicio1");
	}
	
	
}
