import javax.swing.*;
import java.awt.*;

public class FormMain extends JFrame{
    private JPanel panel;
    private JTable table;
    private JTextField status;
    private JTextField date;
    private JTextField id;
    private JTextField name;
    private JButton add;
    private JButton delete;
    private JComboBox comboBox1;
    private JButton update;
    private JButton GOButton;
    private JLabel idText;
    private JLabel nameText;
    private JLabel dataText;
    private JLabel statusText;

    static Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
    static Object[][] data = {
            { "John", "Smith", "1112221" },
            { "Ivan", "Black", "2221111" },
            { "George", "White", "3334444" },
            { "Bolvan", "Black", "2235111" },
            { "Serg", "Black", "2221511" },
            { "Pussy", "Black", "2221111" },
            { "Tonya", "Red", "2121111" },
            { "Elise", "Green", "2321111" },
    };


    public FormMain(){
        setSize(400,400);
        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
        table = new JTable(data,headers);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));
        getAdd();
        getComboBox1();
        getDate();
        getDelete();
        getId();
        getNameT();
        getStatus();
        getUpdate();
        getTable();
        getNameT();
        getIdText();
        getDataText();
        getStatus();
        setTitle("FilmWeb");
        ImageIcon imageIcon = new ImageIcon("Resurses/clapperboard.png");
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        FormMain formMain = new FormMain();

    }


    public JTable getTable() {
        return table;
    }
    public JTextField getStatus() {
        return status;
    }

    public JTextField getDate() {
        return date;
    }

    public JTextField getId() {
        return id;
    }

    public JTextField getNameT() {
        return name;
    }

    public JButton getAdd() {
        return add;
    }

    public JButton getDelete() {
        return delete;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JButton getUpdate() {
        return update;
    }

    public JButton getGOButton() {
        return GOButton;
    }

    public JLabel getIdText() {
        return idText;
    }

    public JLabel getNameText() {
        return nameText;
    }

    public JLabel getDataText() {
        return dataText;
    }

    public JLabel getStatusText() {
        return statusText;
    }
}
