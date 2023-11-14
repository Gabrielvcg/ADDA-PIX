package tests;

import java.util.List;

import ejercicios.Ejercicio3;
import us.lsi.common.Files2;

public class TestEjercicio3 {
public static void main(String[] args) {
		
        // Ruta del archivo de entrada
        String archivoEntrada1A = "../Practicas/ficheros/PI1Ej3DatosEntrada1A.txt";
        String archivoEntrada1B = "../Practicas/ficheros/PI1Ej3DatosEntrada1B.txt";
        String archivoEntrada2A = "../Practicas/ficheros/PI1Ej3DatosEntrada2A.txt";
        String archivoEntrada2B = "../Practicas/ficheros/PI1Ej3DatosEntrada2B.txt";
        String archivoEntrada3A = "../Practicas/ficheros/PI1Ej3DatosEntrada3A.txt";
        String archivoEntrada3B = "../Practicas/ficheros/PI1Ej3DatosEntrada3B.txt";
        
        List<String> lineas1A = Files2.linesFromFile(archivoEntrada1A);
        List<String> lineas1B = Files2.linesFromFile(archivoEntrada1B);
        List<String> lineas2A = Files2.linesFromFile(archivoEntrada2A);
        List<String> lineas2B = Files2.linesFromFile(archivoEntrada2B);
        List<String> lineas3A = Files2.linesFromFile(archivoEntrada3A);
        List<String> lineas3B = Files2.linesFromFile(archivoEntrada3B);
	  
        
		List<String> resultadoIterativo1 = Ejercicio3.ejercicio3Iterativo(lineas1A, lineas1B);
        List<String> resultadoIterativo2 = Ejercicio3.ejercicio3Iterativo(lineas2A, lineas2B);
        List<String> resultadoIterativo3 = Ejercicio3.ejercicio3Iterativo(lineas3A, lineas3B);
        
        List<String> resultadoRecursivo1 = Ejercicio3.ejercicio3Recursivo(lineas1A, lineas1B);
        List<String> resultadoRecursivo2 = Ejercicio3.ejercicio3Recursivo(lineas2A, lineas2B);
        List<String> resultadoRecursivo3 = Ejercicio3.ejercicio3Recursivo(lineas3A, lineas3B);
      
        List<String> resultadoFuncional1 = Ejercicio3.ejercicio3Funcional(lineas1A, lineas1B);
        List<String> resultadoFuncional2 = Ejercicio3.ejercicio3Funcional(lineas2A, lineas2B);
        List<String> resultadoFuncional3 = Ejercicio3.ejercicio3Funcional(lineas3A, lineas3B);

        
        System.out.println("Resultado Iterativo 1: " + resultadoIterativo1);
        System.out.println("Resultado Iterativo 2: " + resultadoIterativo2);
        System.out.println("Resultado Iterativo 3: " + resultadoIterativo3);
        
        System.out.println();
        
        System.out.println("Resultado Recursivo 1: " + resultadoRecursivo1);
        System.out.println("Resultado Recursivo 2: " + resultadoRecursivo2);
        System.out.println("Resultado Recursivo 3: " + resultadoRecursivo3);

        System.out.println();
        
         System.out.println("Resultado Funcional 1: " + resultadoFuncional1);
         System.out.println("Resultado Funcional 2: " + resultadoFuncional2);
         System.out.println("Resultado Funcional 3: " + resultadoFuncional3);

        System.out.println();
		}
       
   
}

