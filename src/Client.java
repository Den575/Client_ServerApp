import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame implements Runnable {

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    static JFrame jFrame = getFrame();
    static JPanel jPanel = new JPanel();

    static JTextField jTextField = new JTextField(30);
    static JPasswordField jPasswordField = new JPasswordField(30);

    public static void main(String[] args){
        new Thread(new Client("Test")).start();
        new Thread(new Server()).start();
    }

    public Client(String name){
        super(name);

        jFrame.add(jPanel);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.WHITE);

        JLabel jLabel = new JLabel("Log in");
        jLabel.setBounds(90,0,60,20);
        jLabel.setFont (jLabel.getFont ().deriveFont (14.0f));
        jPanel.add(jLabel);

        JLabel jLabel1 = new JLabel("User");
        jLabel1.setBounds(25,27,30,30);
        jPanel.add(jLabel1);

        JLabel jLabel2 = new JLabel("Password");
        jLabel2.setBounds(25,57,80,30);
        jPanel.add(jLabel2);

        jTextField.setBounds(95,35,110,18);
        jPanel.add(jTextField);

        jPasswordField.setBounds(95,65,110,18);
        jPanel.add(jPasswordField);

        JButton jButton = new JButton("Sign In");
        jButton.setBounds(25,100,179,20);
        jPanel.add(jButton);

        jPanel.revalidate();


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==jButton){
                    if (jTextField.getText().equals("") && jPasswordField.getText().equals("")){
                        sendData("0\n0");
                    }
                    else{
                        sendData(jTextField.getText()+"\n"+jPasswordField.getText());
                    }
                }
            }
        });
    }

    @Override
    public void run() {
        try{
            while (true){
                connection = new Socket(InetAddress.getByName("127.0.0.1"),5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                String a = (String)input.readObject();
                JOptionPane.showMessageDialog(null,a);
                if(a.equals("Successful login")){
                    jFrame.dispose();
                    MainForm mainForm = new MainForm();
                }
                else if(a.equals("Not successful login")){
                    jTextField.setForeground(Color.RED);
                    jPasswordField.setForeground(Color.RED);
                }

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
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setSize(240,180);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("FilmWeb");
        ImageIcon imageIcon = new ImageIcon("C://Users//Denis//Desktop//Mateusz/clapperboard.png");
        jFrame.setIconImage(imageIcon.getImage());
        return jFrame;
    }

}
