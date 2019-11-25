package bank;

import java.util.Scanner;
import java.io.*;
import java.sql.*;

public class People {

    Scanner input = new Scanner(System.in);
    FileWriter WriteToFile;

    protected String FirstName, LastName, Email, Password;
    protected int Age, Balance;

    People() throws IOException {
	//Nothing For What!
    }

    //DB Configuration
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/users";
    String USER = "root";
    String PASSWORD = "";
    Connection Cursor = null;
    Statement State = null;

    //DB Conection
   
    
   public void UpdateBal() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
        Cursor = DriverManager.getConnection(URL, USER, PASSWORD);
	   
	//This alert to Know the connection has been succeeded
        if (Cursor != null) {
            System.out.println("Connected3!");
        }
	   
        State = Cursor.createStatement();
        float NewBalance=input.nextFloat();
        String email=input.next();
	   
	//Command SQL Here 
        String query ="UPDATE account SET `Balance` = '"+NewBalance+"' WHERE email = '" +email+"'";
        State.executeUpdate(query);

	}
    //DB Close connection
    public void closeconnection() throws SQLException, Exception {
        State.close();
        Cursor.close();
    }

 
}
