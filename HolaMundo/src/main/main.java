package main;
import java.util.Scanner;
public class main {
	static void mostrarMenu() {
		System.out.println("1-Gestores");
		System.out.println("2-Clientes");
		System.out.println("3-Transferencias");
		System.out.println("4-Mensajes");
		System.out.println("5-Préstamos");
		System.out.println("6-Salir");			
	}
	static void cabecera() {
		System.out.println("// BIENVENIDO AL SISTEMA //");
	}
	static void gestores() {
		System.out.println("Lista de gestores");
	}
	static void pie() {
		System.out.println("------------------------------");
	}
	static void clientes() {
		System.out.println("Lista de clientes...");
	}
	static void transferencias() {
		System.out.println("Número de transferencias");
	}
	static void mensajes() {
		System.out.println("Lista de mensajes...");
		System.out.println("*Mensaje número 1*");
		System.out.println("*Mensaje número 2*");
		System.out.println("*Mensaje número 3*");
	}
	static void prestamos() {
		System.out.println("Lista de préstamos...");
	}
	static void salir() {
		System.out.println("GRACIAS POR UTILIZAR NUESTRO SISTEMA...");
	}
	public static void main(String[] args) {
	
		Scanner keyboard = new Scanner(System.in);
		 int numero=0;
		
		do {
			main.mostrarMenu();			
			try {
				numero = keyboard.nextInt();		
			}
			catch (Exception e) {
				System.out.println("El valor introducido no es válido");
			}
			switch (numero) {
		    case 1:
		    	main.cabecera();
		    	main.gestores();
		    	main.pie();
		     break;
		    case 2:
		    	main.cabecera();
		    	main.clientes();
		    	main.pie();
		     break;
		    case 3 :
		    	main.cabecera();
		    	main.transferencias();
		    	main.pie();
		     break;
		    case 4 :
		    	main.cabecera();
		    	main.mensajes();
		    	main.pie();
		     break;
		    case 5 :
		    	main.cabecera();
		    	main.prestamos();
		    	main.pie();
		     break;
		    case 6 :
		    	main.salir();
		    	main.pie();
		     break;
		    default:		    	
		  }			
		}while ((numero>=1)&&(numero>=6));
		keyboard.close();
	}
}