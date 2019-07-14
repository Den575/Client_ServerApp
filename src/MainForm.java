import javax.swing.*;
import java.awt.*;

public class MainForm{

    static JPanel jPanel = new JPanel();
    static JButton jButton = new JButton("NEXT");
    static JFrame jMain = getFrame();

    public static void main(String[] args) {

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
        ImageIcon imageIcon = new ImageIcon("C://Users//Denis//Desktop//Mateusz/clapperboard.png");
        jMain.setIconImage(imageIcon.getImage());
        jMain.add(jPanel);
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(null);
        jButton.setBounds(90,0,60,20);
        jPanel.add(jButton);
        return jMain;
    }

}
