package practicas;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class instituto2 {

	static void consultarConexion() {
Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			// muestra información del tipo de sistema de base de datos (MySQL)
			System.out.println("Base de datos: " +
			conexion.getMetaData().getDatabaseProductName());
		
			System.out.println("Versión: " + conexion.getMetaData().getDatabaseProductVersion());
			// muestra información del diver MySQL (MySQL Connector/J)
			System.out.println("Driver: " + conexion.getMetaData().getDriverName());
			// muestra información de la versión del driver MySQL (mysql-connector-java-8.0.18)
			System.out.println("Versión del driver: " + conexion.getMetaData().getDriverVersion());
			} catch (SQLException e) {
			e.printStackTrace();
			}
	}
	
	static void mostrarMenu() {
		System.out.println("----- MENU PRINCIPAL -----");
		System.out.println("* 1. Consultar estado de la conexion");
		System.out.println("* 2. Mostrar notas de los alumnos");
		System.out.println("* 3. Insertar nuevo alumno");
		System.out.println("* 4. Borrar alumno");
		System.out.println("* 5. Modificar alumno");
		System.out.println("* 6. Editar alumno [NUEVO] [EN PRUEBAS]");
		System.out.println("--------------------------");
	}
	
	//método para consultar las notas de los alumnos
	static void consultarNotas() {
		
		Statement instruccion = null;
		ResultSet resultados = null;
		Connection conexion = null;
		String usuario="root";
		String pass="";
		String baseDeDatos="jdbc:mysql://localhost:3306/instituto";
		
		try {
			//abrimos las conexiones (Usuario, contraseña, etc)
			conexion = DriverManager.getConnection(baseDeDatos, usuario, pass);
			// lanzamos las consultas
			PreparedStatement ps = conexion.prepareStatement("SELECT Nombres, Apellidos, id_asignatura, id_curso, nota FROM usuarios JOIN notas ON usuarios.id=notas.id_usuario;");
			boolean funciona=ps.execute();
			if (funciona) {
				ResultSet loteResultados = ps.getResultSet();
				while (loteResultados.next()) {
					System.out.printf("Nombres: %s // Apellidos: %s // ID de Asignatura: %s // ID de curso: %s // Nota: %s // \n ---------------------------------------------------------------------------------------------------------------------------------------- \n",loteResultados.getString("Nombres"),loteResultados.getString("Apellidos"),loteResultados.getString("id_asignatura"),loteResultados.getString("id_curso"),loteResultados.getString("nota"));
				}
			}
			else
				System.out.println("La consulta no ha devuelto resultados");
			
			
		}
		catch (SQLException e) {
			//lanzamos las excepciones si algo falla (revisad si el servidor de BBDD esta iniciado y las credenciales son correctas
			e.printStackTrace();
			}
		 finally {
			 //esto se ejecuta SIEMPRE funcione o no la consulta
			 try {
				//aqui intentamos liberar recursos
				if (resultados != null) {
				 resultados.close();
				 }
				 if (instruccion != null) {
				 instruccion.close();
				 }
				 if (conexion != null) {
				 conexion.close();
				 }
			 } catch (Exception e) {
				 //aqui manejamos las excepciones de liberar recursos
				 e.printStackTrace();
				 }
		 }
	}
	
	//método para insertar nuevo alumno
	static void insertarAlumno() {

		Connection conexion = null;
		String usuario="root";
		String pass="";
		String baseDeDatos="jdbc:mysql://localhost:3306/instituto";
		
		try {
			//abrimos las conexiones (Usuario, contraseña, etc)
			conexion = DriverManager.getConnection(baseDeDatos,usuario , pass);
			conexion.setAutoCommit(false); //Establecemos la transaccion
			// lanzamos las actualizaciones de la base de datos
			PreparedStatement ps1 = conexion.prepareStatement("INSERT into usuarios (Nombres, Apellidos, DNI, Direccion, Username, Password, Telefono, Foto, Tipo_de_usuario, Activo) Values (?,?,?,?,?,?,?,?,?,?)");
			ps1.setString(1, "Cristiano");
			ps1.setString(2, "Ronaldo");
			ps1.setInt(3, 123456789);
			ps1.setString(4, "Isla de Madeira");
			ps1.setString(5, "CR7");
			ps1.setString(6, "123456");
			ps1.setInt(7, 0);
			ps1.setString(8, "foto.jpg");
			ps1.setInt(9, 0);
			ps1.setInt(10, 0);
						
			ps1.execute();
			conexion.commit();
			
			if (ps1.executeUpdate() != 1) {
				throw new SQLException("Error en la actualización de los datos");
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 }
			System.out.println("PROGRAMA FINALIZADO");
		}
	
	static void borrarAlumno() {
		Connection conexion = null;
 
		String usuario="root";
		String pass="";
		String baseDeDatos="jdbc:mysql://localhost:3306/instituto";
		String query = "DELETE FROM usuarios WHERE ID=33";
 
		try{
			conexion = DriverManager.getConnection(baseDeDatos, usuario, pass);
			conexion.setAutoCommit(false);
 
			PreparedStatement ps1 = conexion.prepareStatement(query);
			
			ps1.execute();
			conexion.commit();
			
		     System.out.println("Borrado correctamente");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void modificarAlumno() {
		Connection conexion = null;
 
		String usuario="root";
		String pass="";
		String baseDeDatos="jdbc:mysql://localhost:3306/instituto";
		String query = "UPDATE usuarios SET Username = 'luismanuel' WHERE ID=1;";
 
		try{
			conexion = DriverManager.getConnection(baseDeDatos, usuario, pass);
			conexion.setAutoCommit(false);
			
			PreparedStatement ps1 = conexion.prepareStatement(query);
 
			ps1.executeUpdate();
			conexion.commit();
			
		     System.out.println("Datos actualizados correctamente...");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	static void editarAlumno(Connection conexion, Scanner scanner) throws SQLException {
        
		System.out.println("Ingrese el id del alumno:");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nuevo telefono del alumno:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese la nueva direccion del alumno:");
        String direccion = scanner.nextLine();
        Statement instruccion = conexion.createStatement();
        String consulta = "UPDATE usuarios SET Telefono = '" + telefono + "', Direccion = '" + direccion + "' WHERE id = " + ID;
        int filasAfectadas = instruccion.executeUpdate(consulta);
        if (filasAfectadas == 1) {
            System.out.println("Datos del alumno con id " + ID + " actualizados correctamente");
        } else {
            System.out.println("No se pudo actualizar el alumno con ID " + ID);
        }
        instruccion.close();
    }
	
		public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
			Scanner keyboard = new Scanner(System.in);
			int numero=0;
			
			do {
				instituto2.mostrarMenu();			
				try {
					numero = keyboard.nextInt();		
				} 
				catch (Exception e) {
					System.out.println("El valor introducido no es válido");
					
				}
				switch (numero) { 
				case 1:
					instituto2.consultarConexion();
				break;
				case 2:
			    	instituto2.consultarNotas();
			     break;
			    case 3:
			    	instituto2.insertarAlumno();	    	
			     break;
			    case 4:
			    	instituto2.borrarAlumno();
			    break;
			    case 5:
			    	instituto2.modificarAlumno();
			    break;
			    case 6:
					instituto2.editarAlumno(null, keyboard);
			    break;
			    default:		    	
			  }			
			}while ((numero>=1)&&(numero>=2));
			keyboard.close();

	}
}
