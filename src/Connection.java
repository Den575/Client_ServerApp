
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, SQLException, ClassNotFoundException {

        }


    public void connecting(String[] data) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, SQLException, ClassNotFoundException {
        System.out.println("Connecting...");
        String userName = "root";
        String password = "start001";
        String connectiesUrl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.jdbc.Driver");
        try (java.sql.Connection connection = DriverManager.getConnection(connectiesUrl, userName, password);
             Statement statement = connection.createStatement()) {
            System.out.println("Connection");
            Server server = new Server();
            if (data[0].equals("Add")){// Add records
                System.out.println("Dodaje na server");
                statement.executeUpdate("INSERT INTO movies (title,name,surname,date,status) VALUES('"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"');");
                server.addData(true);
                return;
            }
            if(data[0].equals("Table") && data[1].equals("Data")){ //Otrzymujemy wszystkie dane z tabeli
                ResultSet resultSet = statement.executeQuery("select * from movies;");
                String id="";
                String title="";
                String name="";
                String surname="";
                String date="";
                String status="";
                while (resultSet.next()) {
                    id += resultSet.getString("id")+"\n";
                    title += resultSet.getString("title")+"\n";
                    name += resultSet.getString("name")+"\n";
                    surname += resultSet.getString("surname")+"\n";
                    date += resultSet.getString("date")+"\n";
                    status += resultSet.getString("status")+"\n";
                }
                String[] tab ={id,title,name,surname,date,status};
                server.tableData(tab);
                return;
            }
            try {
                ResultSet resultSet = statement.executeQuery("select * from users where user = '"+data[0]+"' and pass = '"+data[1]+"';");
                server.logIn(resultSet.next());
            }catch (Exception e){
            }

        }

    }
}