package tests;
import java.util.List;

import ejercicios.Ejercicio1;
import us.lsi.common.Files2;
public class TestEjercicio1 {

	public static void main(String[] args) {
	
            // Ruta del archivo de entrada
            String archivoEntrada = "../PI1/ficheros/PI1Ej1DatosEntrada.txt";

            List<String> lineas = Files2.linesFromFile(archivoEntrada);
			for (String linea: lineas) {
			String[] valores =linea.split(",");
			Integer varA = Integer.parseInt(valores[0]);
			Integer varB = Integer.parseInt(valores[1]);   
			
            System.out.println("Test con valores " + linea );
            
			// Llamar a tus métodos con los valores leídos
            String resultadoFuncional = Ejercicio1.ejercicio1(varA, varB);
            String resultadoIterativo = Ejercicio1.ejercicio1Iterativo(varA, varB);
            String resultadoRecursivo = Ejercicio1.ejercicio1Recursivo(varA, varB);

            // Imprimir los resultados
            System.out.println("Resultado Funcional: " + resultadoFuncional);
            System.out.println("Resultado Iterativo: " + resultadoIterativo);
            System.out.println("Resultado Recursivo: " + resultadoRecursivo);
            System.out.println();
			}
           
       
    }
	}


