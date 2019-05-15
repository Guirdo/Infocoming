package mx.edu.itch.isc.infocoming.interfacesbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimeZone;

public class InterfazBD {
    
    private String url="jdbc:mysql://localhost:3306/Infocoming?serverTimezone="+TimeZone.getDefault().getID();
    private String usuario;
    private String contrasena;
    
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public ResultSetMetaData rsmd;
    
    public InterfazBD(String usuario, String contrasena) throws ClassNotFoundException, SQLException{
        this.usuario=usuario;
        this.contrasena=contrasena;
        
        this.con=null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,this.usuario,this.contrasena);
        
    }
    
    public Object[][] consultar(String consulta) throws SQLException{
        //Matriz para obtener los registros de la tabla
        Object[][] datos;
        //Array dinamico para obtener los n registros de la tabla
        ArrayList<Object[]> lista = new ArrayList<>();

        //Inicializamos el ResultSet a nulo
        rs = null;
        //A partir del objeto Connection creamos un nuevo Statement
        st = con.createStatement();
        //Inicializamos el ResultSer ejecutando un query con el Statement
        rs = st.executeQuery(consulta);
        //Del ResultSet obtenemos los meta datos de la tabla que estamos
        //consultado
        rsmd = rs.getMetaData();

        while (rs.next()) {//Mientras el ResultSet tenga registros por leer, entonces...
            //Guardaremos la fila en un array
            Object[] array = new Object[rsmd.getColumnCount()];
            
            //Según cuantas columnas devuelva nuestra consulta,
            //comenzamos un ciclo
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                //Guarda columna por columna en el array
                array[i] = rs.getObject(i + 1);
            }
            
            //Añadimos la fila a nuestro array dinamico
            lista.add(array);
        }

        //Iniciaizamos nuestra matriz con el numero de filas
        //y el numero de columnas obtenidas por la consulta
        datos = new Object[lista.size()][rsmd.getColumnCount()];
        
        //Segun cuantas filas obtuvimos, entocnes
        for (int i = 0; i < lista.size(); i++) {
            Object[] array = lista.get(i);//Variable auxiliar
            
            //Vamos agregando dato por dato a la matriz
            for (int j = 0; j < rsmd.getColumnCount(); j++) {
                datos[i][j] = array[j];
            }
        }

        return datos;
    }
    
    public void actualizar(String modificacion) throws SQLException{
        int filasAfectada;
        rs = null;
        st = con.createStatement();
        
        filasAfectada = st.executeUpdate(modificacion);
    }
    
    public void eliminar(String eliminacion) throws SQLException{
        int filasAfectada;
        rs = null;
        st = con.createStatement();
        
        filasAfectada = st.executeUpdate(eliminacion);
    }
}
