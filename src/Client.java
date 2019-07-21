import com.toedter.calendar.JDateChooser;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.awt.Dimension;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Client extends JFrame implements Runnable {

    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    static JTable jTabPeople;
    static JMenuBar jMenuBar = new JMenuBar();

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension dimension = toolkit.getScreenSize();

    static JFrame jFrame = getFrame();
    static JPanel jPanel = new JPanel();
    static JPanel newPanel = new JPanel();

    static JTextField jTextField = new JTextField(30);
    static JPasswordField jPasswordField = new JPasswordField(30);

    static JLabel id = new JLabel("Id");
    static JLabel title = new JLabel("Title");
    static JLabel name = new JLabel("Name");
    static JLabel surName = new JLabel("Surname");
    static JLabel date = new JLabel("Date");
    static JLabel status = new JLabel("Status");

    static JTextField idTF = new JTextField();
    static JTextField titleTF = new JTextField();
    static JTextField nameTF = new JTextField();
    static JTextField surNameTF = new JTextField();
    static JDateChooser dateTF = new JDateChooser();
    static JComboBox statusCB = new JComboBox();
    static JTextField[] pola = {idTF,titleTF,nameTF,surNameTF};

    static JButton add = new JButton("Add");
    static JButton delete = new JButton("Delete");
    static JButton update = new JButton("Update");

    static Object[] headers = {"Id", "Title", "Name","Surname","Date","Status"};


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

        statusCB.addItem(null);
        statusCB.addItem("Wypożyczony");
        statusCB.addItem("Nie wypożyczony");
        statusCB.addItem("Brak");



        jPanel.revalidate();

        //Log in
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jButton) {
                    if (jTextField.getText().equals("") && jPasswordField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Poprosze uzupewnic wszystkie pola");
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
                    if (checkText()){
                        return;
                    }
                    else{
                        sendDataTable("Add");
                        for(JTextField j:pola){
                            j.setText("");
                        }dateTF.setDate(null);
                    }
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == update) {
                    if(checkText()){
                        return;
                    }
                    else {
                        sendDataTable("Update");
                    }
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == delete) {
                    if(checkText()){
                        return;
                    }
                    else {
                        sendDataTable("Delete");
                    }
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
                    jscrlp.setBounds(230, 12, 1060, 290);
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

    private static void  sendDataTable(String option){
        try {
            output.flush();
            output.writeObject(option+"\n"+idTF.getText()+"\n"+titleTF.getText() +"\n"+ nameTF.getText() +"\n"+ surNameTF.getText() +"\n"+ String.valueOf(dateTF.getDate())+"\n"+String.valueOf(statusCB.getSelectedItem()));
            for(JTextField j:pola){
                j.setText("");
            }dateTF.setDate(null);
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
        newPanel.add(status);

        jFrame.validate();

        id.setBounds(12, 12, 50, 20);
        title.setBounds(12, 46, 50, 20);
        name.setBounds(12, 79, 50, 20);
        surName.setBounds(12, 113, 50, 20);
        date.setBounds(12, 147, 50, 20);
        status.setBounds(12, 181, 50, 20);

        newPanel.add(idTF);
        newPanel.add(titleTF);
        newPanel.add(nameTF);
        newPanel.add(surNameTF);
        newPanel.add(dateTF);
        newPanel.add(statusCB);

        Locale locale = new Locale("pl","PL");
        dateTF.setLocale(locale);

        idTF.setBounds(70, 12, 130, 20);
        titleTF.setBounds(70, 46, 130, 20);
        nameTF.setBounds(70, 79, 130, 20);
        surNameTF.setBounds(70, 113, 130, 20);
        dateTF.setBounds(70, 147, 130, 20);
        statusCB.setBounds(70, 181, 130, 20);


        newPanel.add(add);
        newPanel.add(delete);
        newPanel.add(update);

        add.setBounds(12, 215, 188, 20);
        update.setBounds(12, 249, 188, 20);
        delete.setBounds(12, 283, 188, 20);

        JMenu settings = new JMenu("Settings");
        jMenuBar.add(settings);

        settings.add(new JMenuItem("About")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Aplikacje zrobil ****** *******");
            }
        });
        settings.add(new JMenuItem("Help")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Dzieki aplikacji uzytkownik moze:\n1)Pobierac aktualne dane " +
                        "odnosnie filmu\n" +
                        "2)Dodawac nowe rekordy\n" +
                        "3)Edytowac rekordy\n" +
                        "4)Usuwac rekordy");
            }
        });
        settings.add(new JMenuItem("Color")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Wprowadz kolor", Color.WHITE);
                newPanel.setBackground(c);
            }
        });
        settings.addSeparator();
        settings.add(new JMenuItem("Exit")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        jFrame.setJMenuBar(jMenuBar);
        jFrame.revalidate();

        Object[][] objects={};
        jTabPeople = new JTable(objects, headers);
        jTabPeople.setBackground(Color.LIGHT_GRAY);
        jTabPeople.setForeground(Color.BLACK);
        newPanel.add(jTabPeople);
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        newPanel.setPreferredSize(new Dimension(230, 100));
        newPanel.add(jscrlp);
        jscrlp.setBounds(230, 12, 1060, 290);


    }

    public Object[][] getObform(String[] serverData) {
        String a = "";
        for (int i = 0; i < serverData.length; i++) {
            a += serverData[i];
        }
        String[] newServerData = a.split("\n");
        String[][] table = new String[newServerData.length / 6][6];
        int count=0;
        for(int i=0;i<6;i++){
            for(int j=0;j<table.length;j++){
                table[j][i]=newServerData[count];
                count++;
            }
        }
    return table;
    }

    public Boolean checkText() {//Dopracowac
        /*if (idTF.getText().equals("") || titleTF.getText().equals("") || nameTF.getText().equals("") || surNameTF.getText().equals("")) {
            for (JTextField j : pola) {
                j.setForeground(Color.RED);
            }
            JOptionPane.showMessageDialog(null, "Poprosze uzupewnic wszystkie pola");
            return true;
        } else {
            for (JTextField j : pola) {
                j.setForeground(Color.BLACK);
            }
        }*/
        return false;
    }
}