package Assignment_2.src;

import java.sql.*;
public class DB
{
    //Relative path in the folder of project
    private static final String url = "jdbc:ucanaccess://Assignment_2/lib/Assignment2.accdb";
    
    private Connection conn;
    
    private PreparedStatement statement;
    private ResultSet resultSet;
    
    public DB() throws ClassNotFoundException
    {
        try
        {
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(String update) throws SQLException
    {
        statement = conn.prepareStatement(update);
        statement.executeUpdate();
        statement.close();
    }
    
    public ResultSet query(String stmt) throws SQLException
    {
        statement = conn.prepareStatement(stmt);
        resultSet = statement.executeQuery();
        return resultSet;
    }
}
