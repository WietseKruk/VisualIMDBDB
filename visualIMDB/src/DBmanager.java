import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DBmanager {
    // JBDC driver name and DB url
    static private final String URL = "jdbc:mysql://localhost:3306/";

    // DB credentials
    static private final String DB_USER = "root";
    static private final String DB_PASS = "password";
    static private final String DB_NAME = "IMDBDB";

    private Connection dbConnection = null;

    public DBmanager(){
        createConn();
    }  

    /**
     * 
     * @return True als verbinding al bestaat of is gemaakt, false als er geen verbinding kan worden gemaakt.
     */
    public boolean createConn(){
        if(dbConnection != null)
            return true;
        try {  
            dbConnection = DriverManager.getConnection(URL + DB_NAME, DB_USER, DB_PASS);
  
            if (dbConnection != null) {
                System.out.println("Successfully connected to MySQL database: " + DB_NAME);
                return true;
            }
        } 
        catch (SQLException ex) {
            System.out.println("An error occurred while connecting to MySQL databse: " + DB_NAME);
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public ResultSet executeSQL(String query) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return rs;
    }

    public void printExecuteSQL(String query) throws SQLException{
        ResultSet rs = null;
        try {
            Statement stmt = dbConnection.createStatement();
            rs = stmt.executeQuery(query);
            //System.out.println("received statement");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            while(rs.next()){
                for(int i = 1; i < cols; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }


    public void executeScript(String url) throws SQLException{
    String thisLine, sqlQuery;
    BufferedReader d;
    Statement stmt = dbConnection.createStatement();
    try {
        File thisFile = new File(url);
        d = new BufferedReader(new FileReader(thisFile.getAbsolutePath()));

    sqlQuery = "";
    while ((thisLine = d.readLine()) != null) 
    {  
        //Skip comments and empty lines
        if(thisLine.length() > 0 && thisLine.charAt(0) == '-' || thisLine.length() == 0 ) 
            continue;
        sqlQuery = sqlQuery + " " + thisLine;
        //If one command complete
        if(sqlQuery.charAt(sqlQuery.length() - 1) == ';') {
            sqlQuery = sqlQuery.replace(';' , ' '); //Remove the ; since jdbc complains
            try {
                stmt.execute(sqlQuery);
            }
            catch(SQLException ex) {
                System.out.println(ex);
            }
            catch(Exception ex) {
                System.out.println(ex);
            }
            sqlQuery = "";
        }   
    }
}
catch(IOException ex) {
}
    }
 
}
