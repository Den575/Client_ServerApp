import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainForm{

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    static JPanel jPanel = new JPanel();
    static JButton jButton = new JButton("NEXT");
    static JFrame jMain = getFrame();

    public static void main(String[] args) {
        //new Thread(new Server()).start();
    }

    public MainForm(String name){
    }



    public void run() {
        try{
            while (true){
                connection = new Socket(InetAddress.getByName("127.0.0.1"),5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                String a = (String)input.readObject();
                JOptionPane.showMessageDialog(null,a);
            }
        }catch (UnknownHostException e){
        }catch (IOException e){
        }catch (ClassNotFoundException e){
        }

    }
    private static void sendData(Object obj){
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e){

        }
    }


    static JFrame getFrame(){
        jMain = new JFrame();
        jMain.setVisible(true);
        jMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jMain.setResizable(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jMain.setSize((int)dimension.getWidth(),(int)dimension.getHeight());
        jMain.setLocationRelativeTo(null);
        jMain.setTitle("FilmWeb");
        ImageIcon imageIcon = new ImageIcon("Resurses/clapperboard.png");
        jMain.setIconImage(imageIcon.getImage());
        jMain.add(jPanel);
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);
        jButton.setBounds(90,0,60,20);
        jPanel.add(jButton);
        return jMain;
    }

}
