import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class Server implements Runnable {

    static private ServerSocket server;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    private static String tekstyk;

    public static void main(String[] args){
        Server ser = new Server();
        ser.run();
    }

    @Override
    public void run() {
        try{
            server = new ServerSocket(5678, 10);
            while (true){
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                tekstyk = (String)input.readObject();
                Connection connectionnn = new Connection();
                String[] data = tekstyk.split("\n");
                connectionnn.connecting(data);
            }
        }catch (UnknownHostException e){
        }catch (IOException e){
        }catch (ClassNotFoundException e){
        }catch (ParserConfigurationException e){
        }catch (SAXException e){
        }catch (XMLStreamException e){
        }catch (SQLException e){
        }

    }
    public void logIn(boolean log){
        if(log==true){
            try{
                String[] data = {"Successful login"};
                output.writeObject(data);
            }catch (IOException e){ }
        }else{
            System.out.println("Not successful login");
            try{
                String[] data = {"Not successful login"};
                output.writeObject(data);
            }
            catch (IOException e){ }
        }
    }

    public void tableData(String[] tab){
        try{
            output.writeObject(tab);
        }
        catch (IOException e){ }
    }
}
