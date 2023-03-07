import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        /*Test test = new Test();
        System.out.println(test.x());
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(3);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTextArea textField = new JTextArea("test");
        textField.setBounds(10,30,200,200);
        textField.setForeground(Color.black);
        textField.setBackground(Color.white);

        panel1.add(textField);
        frame.add(panel1);
        frame.setVisible(true);*/
        new TextAreaListener();
    }
}
