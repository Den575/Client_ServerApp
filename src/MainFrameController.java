import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController {
    private MainFrame mainFrame;
    private JButton welcomeBtn;
    private JTextArea welcomeTATextArea;

    public MainFrameController(){
        initComponents();
        initLisiner();
    }

    public void showMF(){
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrameController mainFrameController = new MainFrameController();
        mainFrameController.showMF();
    }

    private void initComponents(){
        mainFrame = new MainFrame();
        welcomeBtn = mainFrame.getWelcomeBtn();
        welcomeTATextArea = mainFrame.getWelcomeTATextArea();
    }

    private void initLisiner() {
        welcomeBtn.addActionListener(new WelcomeBtnListener());
    }

    private class WelcomeBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            welcomeTATextArea.append("Denis\n");
        }
    }
}
