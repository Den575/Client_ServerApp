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

    static JLabel id = new JLabel("Id");
    static JLabel title = new JLabel("Title");
    static JLabel name = new JLabel("Name");
    static JLabel surName = new JLabel("Surname");
    static JLabel date = new JLabel("Date");

    static JTextField idTF = new JTextField();
    static JTextField titleTF = new JTextField();
    static JTextField nameTF = new JTextField();
    static JTextField surNameTF = new JTextField();
    static JTextField dateTF = new JTextField();

    static JButton add = new JButton("Add");
    static JButton delete = new JButton("Delete");
    static JButton update = new JButton("Update");

    static Object[] headers = {"Id", "Title", "Name"};


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

        //Log in
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

        //Button Add Action
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

    //Connecting to server
    @Override
    public void run() {
        try {
            while (true) {
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
               try{
                   TimeUnit.SECONDS.sleep(2);
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
                    jTabPeople = new JTable(getObform(a), headers);
                    newPanel.add(jTabPeople);
                    JScrollPane jscrlp = new JScrollPane(jTabPeople);
                    newPanel.setPreferredSize(new Dimension(230, 100));
                    newPanel.add(jscrlp);
                    jscrlp.setBounds(230, 5, 500, 100);
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
        jFrame.setTitle("MOVIE Rental");
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
        newPanel.setBackground(new Color(135, 206, 250));

        newPanel.add(id);
        newPanel.add(title);
        newPanel.add(name);
        newPanel.add(surName);
        newPanel.add(date);

        jFrame.validate();

        id.setBounds(12, 12, 50, 20);
        title.setBounds(12, 46, 50, 20);
        name.setBounds(12, 79, 50, 20);
        surName.setBounds(12, 113, 50, 20);
        date.setBounds(12, 147, 50, 20);

        newPanel.add(idTF);
        newPanel.add(titleTF);
        newPanel.add(nameTF);
        newPanel.add(surNameTF);
        newPanel.add(dateTF);

        idTF.setBounds(70, 12, 120, 20);
        titleTF.setBounds(70, 46, 120, 20);
        nameTF.setBounds(70, 79, 120, 20);
        surNameTF.setBounds(70, 113, 120, 20);
        dateTF.setBounds(70, 147, 120, 20);

        newPanel.add(add);
        newPanel.add(delete);
        newPanel.add(update);

        add.setBounds(12, 181, 178, 20);
        update.setBounds(12, 215, 178, 20);
        delete.setBounds(12, 249, 178, 20);



        Object[][] objects={};
        jTabPeople = new JTable(objects, headers);
        jTabPeople.setBackground(Color.LIGHT_GRAY);
        jTabPeople.setForeground(Color.BLACK);
        newPanel.add(jTabPeople);
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        newPanel.setPreferredSize(new Dimension(230, 100));
        newPanel.add(jscrlp);
        jscrlp.setBounds(230, 5, 500, 100);

    }

    public Object[][] getObform(String[] serverData) {
        String a = "";
        for (int i = 0; i < serverData.length; i++) {
            a += serverData[i];
        }
        String[] newServerData = a.split(" ");
        String[][] table = new String[newServerData.length / 3][3];
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<table.length;j++){
                table[j][i]=newServerData[count];
                count++;
            }
        }
    return table;
    }
}