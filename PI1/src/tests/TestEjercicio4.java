package tests;

import java.util.List;

import ejercicios.Ejercicio4;
import us.lsi.common.Files2;

public class TestEjercicio4 {
public static void main(String[] args) {
		
        // Ruta del archivo de entrada
        String archivoEntrada = "../Practicas/ficheros/PI1Ej4DatosEntrada.txt";

        List<String> lineas = Files2.linesFromFile(archivoEntrada);
		for (String linea: lineas) {
		String[] valores =linea.split(",");
		Integer a = Integer.parseInt(valores[0]);
		Integer b = Integer.parseInt(valores[1]);   
		
        System.out.println("Test con valores " + linea );
        
        String resultadoRecursivoSinMemoria = Ejercicio4.ejercicio4RecursivoSinMemoria(a, b);
        String resultadoRecursivoConMemoria = Ejercicio4.ejercicio4RecursivaConMemoria(a, b);
        String resultadoIterativo = Ejercicio4.ejercicio4Iterativo(a, b);

        // Imprimir los resultados
        System.out.println("Resultado Recursivo sin memoria: " + resultadoRecursivoSinMemoria);
        System.out.println("Resultado Recursivo final con memoria: " + resultadoRecursivoConMemoria);
        System.out.println("Resultado Iterativo: " + resultadoIterativo);
      

        System.out.println();
		}
       
   
}
}
