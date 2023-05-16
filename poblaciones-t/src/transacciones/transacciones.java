package transacciones;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class transacciones {

	public static void main(String[] args) {
		Statement instruccion = null;
		ResultSet resultados = null;
		Connection conexion = null;
		
		String usuario = "root";
		String conector = "jdbc:mysql://localhost:3306/world";
		String pass = "";
		
		try {
			conexion = DriverManager.getConnection(conector, usuario, pass);
			System.out.println("Base de datos: " + conexion.getMetaData().getDatabaseProductName());
			System.out.println("Versión: " + conexion.getMetaData().getDatabaseProductVersion());
			System.out.println("Driver: " + conexion.getMetaData().getDriverName());
			System.out.println("Versión del driver: " + conexion.getMetaData().getDriverVersion());
			
			PreparedStatement ps = conexion.prepareStatement("select city.Name as Ciudad, city.Population as \"Población\", country.Name as \"País\" from city join country on city.CountryCode=country.Code where country.Continent=? order by Población DESC limit 10;");
			ps.setString(1, "Europe");
			boolean resultado=ps.execute();

		if (resultado) {
			ResultSet loteResultados = ps.getResultSet();
			while (loteResultados.next()) {
				System.out.printf("%s \t\t%s \t\t %s \t\n", loteResultados.getString("País"),loteResultados.getString("Ciudad"),loteResultados.getString("Población"));
			}
		}
		else
			System.out.println("La consulta no ha devuelto resultados");
		
		//insertamos 2 ciudades
		ps = conexion.prepareStatement("INSERT INTO country (Name, Code, Population) VALUES(?,?,?)");
		
		ps.setString(1, "Disneylandia");
		ps.setString(2, "DLN");
		ps.setString(3, "3");
		resultado=ps.execute();
		
		if (resultado) {
			ResultSet loteResultados = ps.getResultSet();
			while (loteResultados.next()) {
				System.out.printf("%s \t\t%s \t\t %s \t\n", loteResultados.getString("País"),loteResultados.getString("Ciudad"),loteResultados.getString("Población"));
			}
		}
		
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