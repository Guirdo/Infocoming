package mx.edu.itch.isc.infocoming.interfacesbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class InterfazBD {
    
    private String url = "jdbc:mysql://localhost:3306/Infocoming?serverTimezone=Mexico/General";
    private String usuario;
    private String contrasena;
    private Connection cc;
    private Statement st;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private Object[] encabezados;
    private Object[][] datos;

}
