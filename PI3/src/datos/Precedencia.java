package datos;

public record Precedencia(Integer id) {
private static int num =0;
	
	public static Precedencia ofFormat(String[] formato) {
		Integer id = num;
		num++;
		return new Precedencia(id);
	}

	@Override
	public String toString() {
		return "Precedencia [id=" + id + "]";
	}
	
	
}
