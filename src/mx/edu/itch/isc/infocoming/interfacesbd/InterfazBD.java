package mx.edu.itch.isc.infocoming.interfacesbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

public class InterfazBD {
    
    private String url="jdbc:mysql://localhost:3306/Infocoming?serverTimezone="+TimeZone.getDefault().getID();
    private String usuario;
    private String contrasena;
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    
    public InterfazBD(String usuario, String contrasena) throws ClassNotFoundException, SQLException{
        this.usuario=usuario;
        this.contrasena=contrasena;
        
        this.con=null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,this.usuario,this.contrasena);
        
    }
}
