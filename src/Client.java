import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

public class Client extends JFrame implements Runnable {

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    static JTable jTabPeople;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension dimension = toolkit.getScreenSize();

    static JFrame jFrame = getFrame();
    static JPanel jPanel = new JPanel();
    static JPanel newPanel = new JPanel();
    static JPanel tablePanel = new JPanel();

    static JTextField jTextField = new JTextField(30);
    static JPasswordField jPasswordField = new JPasswordField(30);

    static JLabel id = new JLabel("ID");
    static JLabel name = new JLabel("Name");
    static JLabel data = new JLabel("Data");
    static JLabel status = new JLabel("Status");

    static JTextField idTF = new JTextField();
    static JTextField nameTF = new JTextField();
    static JTextField dataTF = new JTextField();
    static JTextField statusTF = new JTextField();

    static JButton add = new JButton("Add");
    static JButton delete = new JButton("Delete");
    static JButton update = new JButton("Update");

    static Object[] headers = {"Name", "Surname", "Telephone"};


    public static void main(String[] args) {
        new Thread(new Client("Test")).start();
        new Thread(new Server()).start();
    }

    public Client(String name) {
        super(name);
        jFrame.add(jPanel);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.WHITE);

        JLabel jLabel = new JLabel("Log in");
        jLabel.setBounds(90, 0, 60, 20);
        jLabel.setFont(jLabel.getFont().deriveFont(14.0f));
        jPanel.add(jLabel);

        JLabel jLabel1 = new JLabel("User");
        jLabel1.setBounds(25, 27, 30, 30);
        jPanel.add(jLabel1);

        JLabel jLabel2 = new JLabel("Password");
        jLabel2.setBounds(25, 57, 80, 30);
        jPanel.add(jLabel2);

        jTextField.setBounds(95, 35, 110, 18);
        jPanel.add(jTextField);

        jPasswordField.setBounds(95, 65, 110, 18);
        jPanel.add(jPasswordField);

        JButton jButton = new JButton("Sign In");
        jButton.setBounds(25, 100, 179, 20);
        jPanel.add(jButton);

        jPanel.revalidate();


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jButton) {
                    if (jTextField.getText().equals("") && jPasswordField.getText().equals("")) {
                        return;
                    } else {
                        sendData(jTextField.getText() + "\n" + jPasswordField.getText());
                    }
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == add) {
                    sendData("Table\nData");
                    /*if (idTF.getText().equals("") && nameTF.getText().equals("")){
                        return;
                    }
                    else{
                        sendData(idTF.getText()+"\n"+nameTF.getText());
                    }*/
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            while (true) {
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
               try{
                   TimeUnit.SECONDS.sleep(3);
               }
               catch (InterruptedException e){
               }
                sendData("Table\nData");
                String[] a = (String[]) input.readObject();
                //JOptionPane.showMessageDialog(null,a);
                if (a[0].equals("Successful login")) {
                    newPanel();
                } else if (a[0].equals("Not successful login")) {
                    jTextField.setForeground(Color.RED);
                    jPasswordField.setForeground(Color.RED);
                } else if (a.length > 1) {
                    jTabPeople = new JTable(getIbform(a), headers);
                    newPanel.add(jTabPeople);
                    JScrollPane jscrlp = new JScrollPane(jTabPeople);
                    newPanel.setPreferredSize(new Dimension(250, 100));
                    newPanel.add(jscrlp);
                    jscrlp.setBounds(200, 5, 500, 100);
                }

            }
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }

    }

    //Ta metoda wysyla object na server
    private static void sendData(Object obj) {
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {

        }
    }

    //Tworzymy Frame
    static JFrame getFrame() {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(240, 180);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("FilmWeb");
        ImageIcon imageIcon = new ImageIcon("Resurses/clapperboard.png");
        jFrame.setIconImage(imageIcon.getImage());
        return jFrame;
    }

    //Tworzymy nowy Panel
    public static void newPanel() {
        jFrame.setResizable(true);
        jFrame.getContentPane().removeAll();
        jFrame.setSize(dimension);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(newPanel);
        newPanel.setLayout(null);
        newPanel.setBackground(Color.CYAN);
        newPanel.add(id);
        newPanel.add(name);
        id.setBounds(12, 12, 40, 20);
        name.setBounds(12, 46, 40, 20);
        jFrame.validate();
        newPanel.add(data);
        newPanel.add(status);

        id.setBounds(12, 12, 40, 20);
        name.setBounds(12, 46, 40, 20);
        data.setBounds(12, 79, 40, 20);
        status.setBounds(12, 113, 40, 20);

        newPanel.add(idTF);
        newPanel.add(nameTF);
        newPanel.add(dataTF);
        newPanel.add(statusTF);

        idTF.setBounds(55, 12, 120, 20);
        nameTF.setBounds(55, 46, 120, 20);
        dataTF.setBounds(55, 79, 120, 20);
        statusTF.setBounds(55, 113, 120, 20);

        newPanel.add(add);
        newPanel.add(delete);
        newPanel.add(update);

        add.setBounds(12, 147, 162, 20);
        update.setBounds(12, 181, 162, 20);
        delete.setBounds(12, 215, 162, 20);
        newPanel.setBounds(0,0,700,500);


        Object[][] objects={};
        jTabPeople = new JTable(objects, headers);
        newPanel.add(jTabPeople);
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        newPanel.setPreferredSize(new Dimension(250, 100));
        newPanel.add(jscrlp);
        jscrlp.setBounds(200, 5, 500, 100);

        jFrame.add(tablePanel);
        tablePanel.setLayout(new BasicScrollBarUI());
        tablePanel.setBackground(Color.RED);
        tablePanel.setBounds(700,2,300,300);
        JButton jButton = new JButton("Den");
        tablePanel.add(jButton);




    }

    public Object[][] getIbform(String[] q) {
        String a = "";
        for (int i = 0; i < q.length; i++) {
            a += q[i];
        }
        String[] qq = a.split(" ");
        String[][] tab = new String[qq.length / 3][3];
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<tab.length;j++){
                tab[j][i]=qq[count];
                count++;
            }
        }
    return tab;
    }
}