import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MenuMain {

    String name1;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    static JFrame jFrame = getFrame();
    static JPanel jPanel = new JPanel();

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

    static Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
    static Object[][] info = {
            { "John", "Smith", "1112221" },
            { "Ivan", "Black", "2221111" },
            { "George", "White", "3334444" },
            { "Bolvan", "Black", "2235111" },
            { "Serg", "Black", "2221511" },
            { "Pussy", "Black", "2221111" },
            { "Tonya", "Red", "2121111" },
            { "Elise", "Green", "2321111" },
    };

    //Объект таблицы
    static JTable jTabPeople;

    public MenuMain(String name1){
        this.name1=name1;
    }


    public static void main(String[] args) {
        MenuMain menuMain = new MenuMain("2");

        try{
            while (true){
                connection = new Socket(InetAddress.getByName("127.0.0.1"),5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                JOptionPane.showMessageDialog(null,(String)input.readObject());
            }
        }catch (UnknownHostException e){
        }catch (IOException e){
        }catch (ClassNotFoundException e){
        }

        jFrame.add(jPanel);
        jPanel.setLayout(null);
        jPanel.setBackground(Color.CYAN);


        jPanel.add(id);
        jPanel.add(name);
        jPanel.add(data);
        jPanel.add(status);

        id.setBounds(12,12,40,20);
        name.setBounds(12,46,40,20);
        data.setBounds(12,79,40,20);
        status.setBounds(12,113,40,20);

        jPanel.add(idTF);
        jPanel.add(nameTF);
        jPanel.add(dataTF);
        jPanel.add(statusTF);

        idTF.setBounds(55,12,120,20);
        nameTF.setBounds(55,46,120,20);
        dataTF.setBounds(55,79,120,20);
        statusTF.setBounds(55,113,120,20);

        jPanel.add(add);
        jPanel.add(delete);
        jPanel.add(update);

        add.setBounds(12,147,162,20);
        update.setBounds(12,181,162,20);
        delete.setBounds(12,215,162,20);

        jPanel.revalidate();

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData("root\n1");
                jTabPeople = new JTable(info,headers);
                jPanel.add(jTabPeople);
                JScrollPane jscrlp = new JScrollPane(jTabPeople);
                jTabPeople.setPreferredScrollableViewportSize(new Dimension(250, 100));
                jPanel.add(jscrlp);
                jscrlp.setBounds(200,5,500,100);
            }
        });
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
        jFrame.setResizable(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setSize(dimension);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("FilmWeb");
        ImageIcon imageIcon = new ImageIcon("Resurses/clapperboard.png");
        jFrame.setIconImage(imageIcon.getImage());
        return jFrame;
    }
}


