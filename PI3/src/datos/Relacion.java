package datos;

public record Relacion(Integer id, Double interaccion) {
	
private static int num =0;
	
	public static Relacion ofFormat(String[] formato) {
		Double interaccion = Double.parseDouble(formato[2]);	
		Integer id = num;
		num++;
		return new Relacion(id, interaccion);
	}
	
	public static Relacion of(Double interacciones) {
		Double interaccion = interacciones;	
		Integer id = num;
		num++;
		return new Relacion(id, interaccion);
	}

	@Override
	public String toString() {
		return "Relacion [id=" + id + ", interaccion=" + interaccion + "]";
	}
	

}
