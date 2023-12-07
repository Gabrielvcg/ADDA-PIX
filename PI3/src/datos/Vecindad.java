package datos;

public record Vecindad(Double distancia, Double tiempo) {

		public static Vecindad ofFormat(String[] formato) {
			Double tiempo =Double.parseDouble(formato[3]);
			Double distancia = Double.parseDouble(formato[2]);
			
			return new Vecindad(distancia,tiempo);
		}
		
		public static Vecindad of(Double distancia,Double tiempo) {
			return new Vecindad(distancia,tiempo);
		}

		@Override
		public String toString() {
			return "Vecindad [distancia=" + distancia + ", tiempo=" + tiempo + "]";
		}
		
		
}
