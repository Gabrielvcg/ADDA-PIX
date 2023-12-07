package datos;

import java.util.HashSet;
import java.util.Set;

public record Atraccion(String nombre, Double tiempoEspera, Double popularidad, Double duracion) {
		public static Atraccion ofFormat(String[] formato) {
			String nombre = formato[0];
			Double tiempoEspera= Double.parseDouble(formato[1]);
			Double popularidad = Double.parseDouble(formato[2]);	
			Double duracion= Double.parseDouble(formato[3]);
			
			return new Atraccion(nombre,tiempoEspera, popularidad,duracion);
		}
		
		public static Atraccion of(String nombre, Double tiempoEspera, Double popularidad, Double duracion) {
			return new Atraccion(nombre,tiempoEspera, popularidad,duracion);
		}
		

		@Override
		public String toString() {
			return "Atraccion [nombre=" + nombre + ", tiempoEspera=" + tiempoEspera + ", popularidad=" + popularidad
					+ ", duracion=" + duracion + "]";
		}
		
	

}
