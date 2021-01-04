import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    static ArrayList buff = new ArrayList();
    public static void main(String[] args) {
        try {
            System.out.println("Add a table name.");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(System. in)
            );
            String table = input.readLine();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TravelDB","root","");
            String query = "SELECT * FROM "+table;
            PreparedStatement rs = conn.prepareStatement(query);
            ResultSet resultSet = rs.executeQuery();
            ResultSetMetaData resMD = resultSet.getMetaData();
            System.out.println(buff);
            PrintWriter printWriter=new PrintWriter(new File("result.html"));
            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<title>mData</title>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("<h1>les colonnes de la table "+table+"</h1>");
            printWriter.println("<ul>");
            for (int i = 1; i <= resMD.getColumnCount(); i++) {
                String columnName=resMD.getColumnName(i);
                printWriter.println("<li>");
                printWriter.println("le nom de la colonne "+i+" est :"+columnName);
                printWriter.println("</li>");
            }
            printWriter.flush();
            printWriter.close();
            System.out.println("Print writer done. you can open the file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
