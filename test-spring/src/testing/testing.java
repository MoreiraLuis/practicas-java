package testing;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class testing {
	public static void main(String[] args) {
		
		Statement instruccion = null;
		ResultSet resultados = null;
		Connection conexion = null;
		String usuario="root";
		String pass="";
		String baseDeDatos="jdbc:mysql://localhost:3306/world";
		
		
		try {
			//abrimos las conexiones (Usuario, contraseña, etc)
			conexion = DriverManager.getConnection(baseDeDatos,usuario , pass);
			conexion.setAutoCommit(false); //Establecemos la transaccion
			// lanzamos las consultas
			PreparedStatement ps1 = conexion.prepareStatement("Insert into country (Code, Name, Continent) Values (?,?,?)");
			ps1.setString(1, "PR2");
			ps1.setString(2, "MiPais2");
			ps1.setString(3, "Europe");
			
			PreparedStatement ps2 = conexion.prepareStatement("Insert into city (Name, CountryCode, Population) Values (?,?,?)");
				ps2.setString(1, "Invernalia2");
				ps2.setString(2, "PR2");
				ps2.setString(3, "3213434");
				
			PreparedStatement ps3 = conexion.prepareStatement("Insert into city (Name, CountryCode, Population) Values (?,?,?)");
				ps3.setString(1, "La Otra2");
				ps3.setString(2, "PR2");
				ps3.setString(3, "434");
				
			PreparedStatement ps4 = conexion.prepareStatement("Insert into city (Name, CountryCode, Population) Values (?,?,?)");
				ps4.setString(1, "La de Más allá2");
				ps4.setString(2, "PR2");
				ps4.setString(3, "434234");
			
			ps1.execute();
			ps2.execute();
			ps3.execute();
			ps4.execute();
			
			conexion.commit();
			
			PreparedStatement ps = conexion.prepareStatement("select city.Name as Ciudad, city.Population as \"Población\", country.Name as \"País\" from city join country on city.CountryCode=country.Code where city.CountryCode=? order by Población DESC limit 10;");
			ps.setString(1, "PR2");
			boolean funciona=ps.execute();	
			if (funciona) {
				ResultSet loteResultados = ps.getResultSet();
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