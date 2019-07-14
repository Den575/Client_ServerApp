import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton welcomeBtn;
    private JTextArea welcomeTATextArea;

    public MainFrame(){
        setSize(300,300);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        getWelcomeBtn();
        getWelcomeTATextArea();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

    }

    public JButton getWelcomeBtn() {
        return welcomeBtn;
    }

    public JTextArea getWelcomeTATextArea() {
        return welcomeTATextArea;
    }
}
