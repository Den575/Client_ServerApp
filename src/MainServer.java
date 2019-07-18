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

public class MainServer implements Runnable {

    static private ServerSocket server;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;
    private static String tekstyk;

    public static void main(String[] args){
        MainServer mainServer = new MainServer();
        mainServer.run();
    }

    @Override
    public void run() {
        try{
            server = new ServerSocket(6000, 100);
            while (true){
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                tekstyk = (String)input.readObject();
                MainConnection mainConnection = new MainConnection();
                String[] data = tekstyk.split("\n");
                mainConnection.connecting(data);
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
            System.out.println("Successful login");
            try{
                output.writeObject("Successful login");
            }catch (IOException e){ }
        }else{
            System.out.println("Not successful login");
            try{
                output.writeObject("Not successful login");
            }
            catch (IOException e){ }
        }
    }

}
