package main;

public class estudiante {
	
	private static estudiante nuevoEstudiante;
	//atributos
	private String id;
	int edad;
	int telefono;
	private int numeroDeNotas;
	private int sumaDeNotas;
	private int notaMedia;
	
	//métodos
	public static estudiante crearEstudiante() {
		estudiante.nuevoEstudiante = new estudiante();
		return nuevoEstudiante;
	}
	
	void mostrarInfo() {
		System.out.println("*Informacion del estudiante*");
		System.out.println(this.id);
		System.out.println(this.edad);
		System.out.println(this.telefono);
		System.out.println(this.numeroDeNotas);
		System.out.println(this.sumaDeNotas);
		System.out.println(this.notaMedia);
	}
	
	void agregarNuevaNota() {
		System.out.println("Nueva nota");
	}
	
	public estudiante() {
	this.id = "Luis Moreira";
	this.edad = 27;
	this.telefono = 622052509;
	this.numeroDeNotas = 7;
	this.sumaDeNotas = 14;
	this.notaMedia = 15;
	}

public estudiante(String id, int edad, int telefono, int numeroDeNotas, int sumaDeNotas, int notaMedia) {
	this.id = id;
	this.edad = edad;
	this.telefono = telefono;
	this.numeroDeNotas = numeroDeNotas;
	this.notaMedia = notaMedia;
	System.out.println("Ejecutando el constructor...");
}

public void setid(String id) {
	this.id = id;
}

public void setedad(int edad) {
	this.edad = edad;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
