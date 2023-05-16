package poblacion;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
public class poblacion {
	public static void main(String[] args) {
		Statement instruccion = null;
		boolean resultados = null;
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
			// muestra información del tipo de sistema de base de datos (MySQL)
			System.out.println("Base de datos: " +
			conexion.getMetaData().getDatabaseProductName());
		
			System.out.println("Versión: " + conexion.getMetaData().getDatabaseProductVersion());
			// muestra información del diver MySQL (MySQL Connector/J)
			System.out.println("Driver: " + conexion.getMetaData().getDriverName());
			// muestra información de la versión del driver MySQL (mysql-connector-java-8.0.18)
			System.out.println("Versión del driver: " + conexion.getMetaData().getDriverVersion());
			
			instruccion = conexion.createStatement();
			String query = "select city.Name as Ciudad, city.Population as Población, country.Name as País";
			resultados= instruccion.executeQuery(query);
			
			while (resultados.next()) {
				System.out.println("LISTADO DE PAÍSES DE ÁFRICA MÁS POBLADOS");
				System.out.println("PAÍS 1" + resultados.getString("País"));
				System.out.println("PAÍS 2" + resultados.getString("País"));
				System.out.println("PAÍS 3" + resultados.getString("País"));
				System.out.println("PAÍS 4" + resultados.getString("País"));
				System.out.println("...");
				}
			boolean resultados1 = instruccion.execute(query);

			if (resultados) {
			ResultSet resultados11 = instruccion.getResultSet();
			System.out.println("Listado de países: ");
			
			while (resultados11.next()) {
			System.out.println("PAÍS 1" + resultados11.getString("País"));
			System.out.println("PAÍS 2" + resultados11.getString("País"));
			System.out.println("PAÍS 3" + resultados11.getString("País"));
			System.out.println("PAÍS 4" + resultados11.getString("País"));
			System.out.println("...");
			}
			}
		}
			catch (SQLException e) {
			e.printStackTrace();
			}
		 finally {
			 //System.out.println("No Liberamos ");
			 try {
			 // libera los resultados
			 if (resultados != null) {
			 resultados.close();
			 }
			 // libera la instrucción
			 if (instruccion != null) {
			 instruccion.close();
			 }
			 // libera la conexión
			 if (conexion != null) {
			 conexion.close();
			 }
			
			 } catch (Exception e) {
				 e.printStackTrace();
				 }
				
		
	}
	}}












