package transacciones;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class transacciones {
	
	public static void main(String[] args) {
		
		Statement instruccion = null;
		ResultSet resultados = null;
		Connection conexion = null;
		
		String usuario="root";
		String pass="";
		String baseDeDatos="jdbc:mysql://localhost:3306/world";
		
		try {
			//abrimos las conexiones (Usuario, contraseña, etc)
			conexion = DriverManager.getConnection(baseDeDatos,usuario,pass);
			//desactivando el autocommit
			conexion.setAutoCommit(false);
			// lanzamos las consultas
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO country (Name, Code, Population) VALUES (?,?,?)");
			ps.setString(1, "Disneylandia2");
			ps.setString(2, "DL2");
			ps.setString(3, "1000000");
			ps.execute();
			//se ejecuta la transacción
			conexion.commit();
			
			boolean funciona=ps.execute();	
			if (funciona) {
				ResultSet loteResultados = ps.getResultSet();
				while (loteResultados.next()) {
					System.out.printf("%s \t\t%s \t\t %s \t\n", loteResultados.getString("País"),loteResultados.getString("Ciudad"),loteResultados.getString("Población"));
				}
			}
			else
				System.out.println("La consulta no ha devuelto resultados");

			if (funciona) {
				ResultSet loteResultados = ps.getResultSet();
				System.out.printf("Cabecera de los resultados\nPaís \t\tCiudad \t\tPoblacion \t\n");
				while (loteResultados.next()) {
					System.out.printf("%s \t\t%s \t\t %s \t\n", loteResultados.getString("País"),loteResultados.getString("Ciudad"),loteResultados.getString("Población"));
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
}