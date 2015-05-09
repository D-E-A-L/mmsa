/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ComponenteDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Richard
 */
public abstract class BD {
     public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bdsistema";
            String user = "root";
            String password = "";
            cn= DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            cn=null;
            System.out.println("Error no se puede cargar el driver:" + e.getMessage());
        } catch (SQLException e) {
            cn=null;
            if(e.getErrorCode() == 0)
            {
                JOptionPane.showMessageDialog(null, "El servidor de base de datos no responde.\n"
                                                    + "Verifique que el servidor de base de datos este activo.\n"
                                                    + "También Verifique que exista la base de datos bdsistemas", 
                                                    "Error: Coneccion Fallida.", JOptionPane.ERROR_MESSAGE);
            }
        }
        return cn;
    }
}
