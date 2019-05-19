/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesbd;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diann
 */
public class InterfazBDAlumno {
    private InterfazBD intBD;
    private Object[][] datos;
    
    public InterfazBDAlumno(InterfazBD intBD) {
        this.intBD = intBD;
    }
    public Object[][] consultarAlumnos() throws SQLException {
        //Matriz para obtener los registros de la tabla
        Object[][] datos;
        //Array dinamico para obtener los n registros de la tabla
        ArrayList<Object[]> lista = new ArrayList<>();

        //Inicializamos el ResultSet a nulo
        intBD.rs = null;
        //A partir del objeto Connection creamos un nuevo Statement
        intBD.st = intBD.con.createStatement();
        //Inicializamos el ResultSer ejecutando un query con el Statement
        intBD.rs = intBD.st.executeQuery("select * from alumno");
        //Del ResultSet obtenemos los meta datos de la tabla que estamos
        //consultado
        intBD.rsmd = intBD.rs.getMetaData();

        while (intBD.rs.next()) {//Mientras el ResultSet tenga registros por leer, entonces...
            //Guardaremos la fila en un array
            Object[] array = new Object[intBD.rsmd.getColumnCount()];
            
            //Según cuantas columnas devuelva nuestra consulta,
            //comenzamos un ciclo
            for (int i = 0; i < intBD.rsmd.getColumnCount(); i++) {
                //Guarda columna por columna en el array
                array[i] = intBD.rs.getObject(i + 1);
            }
            
            //Añadimos la fila a nuestro array dinamico
            lista.add(array);
        }

        //Iniciaizamos nuestra matriz con el numero de filas
        //y el numero de columnas obtenidas por la consulta
        datos = new Object[lista.size()][intBD.rsmd.getColumnCount()];
        
        //Segun cuantas filas obtuvimos, entocnes
        for (int i = 0; i < lista.size(); i++) {
            Object[] array = lista.get(i);//Variable auxiliar
            
            //Vamos agregando dato por dato a la matriz
            for (int j = 0; j < intBD.rsmd.getColumnCount(); j++) {
                datos[i][j] = array[j];
            }
        }

        return datos;
    }
    
}
