package datos;


public record Vecindad(Integer id,Double distancia, Double tiempo) {

	private static int num =0;

		public static Vecindad ofFormat(String[] formato) {
			Double tiempo =Double.parseDouble(formato[3]);
			Double distancia = Double.parseDouble(formato[2]);
			Integer id = num;
			num++;
			return new Vecindad(id,distancia,tiempo);
		}
		
		public static Vecindad of( Double distancias,Double tiempos) {
			Double distancia= distancias;
			Double tiempo = tiempos;		
			Integer id = num;
			num++;
			return new Vecindad(id, distancia,tiempo);
		}
		public static Vecindad of(Double distancias) {
			Double distancia = distancias;
			Double tiempo = null;	
			Integer id = num;
			num++;
			return new Vecindad(id,distancia, tiempo);
		}
		@Override
		public String toString() {
			return "Vecindad [id=" + id + ", distancia=" + distancia + ", tiempo=" + tiempo + "]";
		}

	
		
		
}
