package main;

public class escuela {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		estudiante luis= new estudiante();
		luis.mostrarInfo();
		estudiante pepe= new estudiante("Pepe", 18, 666999666, 8, 13, 14);
		pepe.mostrarInfo();
		estudiante javi= new estudiante("Javier", 29, 965256699, 5, 9, 10);
		javi.mostrarInfo();
		estudiante jaimito= new estudiante("Jaime", 21, 697236785, 6, 10, 11);
		jaimito.mostrarInfo();
	
		estudiante luis2= new estudiante();
		luis2.mostrarInfo();
		luis2.setid("Luighi");
		luis2.mostrarInfo();
		luis2.setid("Luighi 3");
		luis2.mostrarInfo();
		
		estudiante juanico= new estudiante();
		juanico.mostrarInfo();
		juanico.setedad(28);
		juanico.setid("Juan Pérez");
		juanico.mostrarInfo();
	}
}