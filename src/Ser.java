import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ser {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5678);
        Socket socket = serverSocket.accept();
        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("<h1>Java</h1>");
        writer.flush();


        serverSocket.close();
        socket.close();

    }
}
