package datos;

import java.util.HashSet;
import java.util.Set;

public record Usuario(String nombre, Double actividad, Set<String> aficiones) {
	public static Usuario ofFormat(String[] formato) {
		String nombre = formato[0];
		Double actividad = Double.parseDouble(formato[1]);
		Set<String> aficiones = new HashSet<>(); 	
		for (String aficion : formato[2].split(";")) {
			aficiones.add(aficion.trim()); 
		}
		return new Usuario(nombre,actividad, aficiones);
	}
	
	public static Usuario of(String nombre, Double actividad, Set<String> aficiones) {
		return new Usuario(nombre,actividad,aficiones);
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
