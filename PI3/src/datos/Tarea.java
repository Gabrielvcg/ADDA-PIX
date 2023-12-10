package datos;

public record Tarea(String nombre) {
	
	
	public static Tarea ofFormat(String[] formato) {
		String nombre = formato[0];
	return new Tarea(nombre);
	}
	
	public static Tarea of(String nombre) {
		return new Tarea(nombre);
	}

	@Override
	public String toString() {
		return "Tarea [nombre=" + nombre + "]";
	}
	
}
