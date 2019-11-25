package bank;

import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.security.util.Password;

public class Admin extends People {
    //DB Search

    Admin() throws IOException {

    }

    public void search() {
        System.out.print("Enter the email:");
        String email_sel = input.next();
        try {
            Class.forName(DRIVER);
            Cursor = DriverManager.getConnection(URL, USER, PASSWORD);
            if (Cursor != null) {
                System.out.println(" Connected2");
            }
            State = Cursor.createStatement();
            String sader = "SELECT * FROM account WHERE email= '" + email_sel+"'";
            ResultSet result = State.executeQuery(sader);
            while (result.next()) {
                System.out.println(" Name : "+result.getString("FirstName")+" " +
                        result.getString("lastName") + "\n"+
                        " Email : " + result.getString("email") +"\n"+
                        " Age : " + result.getInt("Age") + "\n"+
                        " Balance : " + result.getInt("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean emailvalidator(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public void Activation() throws ClassNotFoundException, SQLException {
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost/users";
        String USER = "root";
        String PASSWORD = "";
        Connection Cursor = null;
        Statement State = null;
        //Email input
        boolean Email_Checker_Flag = true;                         //A flag to make sure the  user enter the email correctly 
        boolean Password_Checker_Flag = true;
        boolean Age_Checker_Flag = true;
        boolean Email_Checker;
        String Entered_Email = null;
        String First_Name;
        String Last_Name;
        String Password = null;
        float Balance;
        int Age = 0;
        System.out.println("Please enter your email");
        Entered_Email = input.next();
        while (Email_Checker_Flag) {
            Email_Checker = emailvalidator(Entered_Email);
            if (Email_Checker) {
                Email_Checker_Flag = false;
                break;
            }
            Entered_Email = input.next();
        }
        // Name input
        System.out.println("Please enter your first name");
        First_Name = input.next();
        System.out.println("Please enter your second name");
        Last_Name = input.next();

        System.out.println("Please enter your age");
        Age = input.nextInt();
        while (Age_Checker_Flag) {

            if (Age < 21) {
                System.out.println("Please make sure you have entered the correct age ");

            } else if (Age >= 21) {

                Age_Checker_Flag = false;
                break;
            }
            Age = input.nextInt();

        }

        System.out.println("Please enter your Password");
        // password input
        Password = input.next();

        while (Password_Checker_Flag) {
            if (Password.length() < 8 || Password.length() > 32) {
                System.out.println("Please enter a password that is  not less than 8 characters and not more  than 32 characters");
            } else {
                Password_Checker_Flag = false;
                break;
            }
            Password = input.next();

        }
        System.out.println("Please Enter the balance");
        Balance = input.nextFloat();
        Class.forName(DRIVER);
        Cursor = DriverManager.getConnection(URL, USER, PASSWORD);

        //This Alert To Verified If The conection Is succeed or Not
        if (Cursor != null) {
            System.out.println("Connected!");
        }

        State = Cursor.createStatement();
        String query = "INSERT INTO `account` (FirstName,lastname,Age,Balance,email,PASSWORD) VALUES ('" + First_Name + "','" + Last_Name + "','" + Age + "','" + Balance + "','" + Entered_Email + "','" + Password + "') ";
        State.executeUpdate(query);
    }

}
