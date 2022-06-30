package util;
import java.sql.*;
/**
 *
 * @author User
 */
public class DBConnection {
    public static Connection createConnection(){
        Connection conn = null;
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/Customer";
        String dbUser = "app", dbPassword="app";
        
        try{
            try{
                Class.forName(driver);
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            
            conn = DriverManager.getConnection(url,dbUser,dbPassword);
            System.out.println("Printing connection object "+conn);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
