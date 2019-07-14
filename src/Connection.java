
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
            if(data[0]=="" && data[1]==""){
                return;
            }
            try {
                ResultSet resultSet = statement.executeQuery("select * from users where user = '"+data[0]+"' and pass = '"+data[1]+"';");
                //statement.executeUpdate("CREATE TABLE `"+a+"` (`user_id` INT(5) NOT NULL AUTO_INCREMENT, `username` VARCHAR(50), PRIMARY KEY(`user_id`), INDEX(`username`));");
                Server server = new Server();
                server.logIn(resultSet.next());
            }catch (Exception e){
            }

        }

    }
}