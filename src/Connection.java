
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
    public static void main(String[] args){

        }
    public void connecting(String[] data) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, SQLException, ClassNotFoundException {
        System.out.println("Connecting...");
        String userName = "root";
        String password = "start001";
        String connectiesUrl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.jdbc.Driver");
        try (java.sql.Connection connection = DriverManager.getConnection(connectiesUrl, userName, password);
             Statement statement = connection.createStatement()) {
            Server server = new Server();
            if (data[0].equals("Add")){// Add records
                System.out.println("Dodaje na rekord");
                statement.executeUpdate("INSERT INTO movies (title,name,surname,date,status) VALUES('"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"');");
                data[0]="Table";
            }
            else if(data[0].equals("Update")){//Update rekord
                System.out.println("Update rekordu");
                statement.executeUpdate("UPDATE movies SET title='"+data[2]+"',name='"+data[3]+"', surname='"+data[4]+"',date='"+data[5]+"', status='"+data[6]+"' WHERE id ='"+data[1]+"';");
                data[0]="Table";
            }else if(data[0].equals("Delete")){//Delete rekord
                System.out.println("Delete rekord");
                statement.executeUpdate("DELETE FROM movies where id='"+data[1]+"';");
                data[0]="Table";
            }
            if(data[0].equals("Table")){ //Otrzymujemy wszystkie dane z tabeli
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
                System.out.println("Przesylka");
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