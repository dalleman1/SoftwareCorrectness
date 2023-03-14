import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        Border greenLine = BorderFactory.createLineBorder(Color.black);
        Border yellowLine = BorderFactory.createLineBorder(Color.black);

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(940, 720));
        panel1.setBorder(blackLine);

        JScrollPane panel2 = new JScrollPane();
        panel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel2.setPreferredSize(new Dimension(320, 160));
        panel2.setBorder(yellowLine);


        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(3);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        String[] options = {"Line", "Rectangle", "Circle", "Text-At", "Bounding-Box", "Draw", "Fill"};

        //Panel 1
        final JComboBox<String> cb = new JComboBox<String>(options);

        panel1.add(cb);

        //Panel 2
        JTextArea tf = new JTextArea(5,20);
        tf.append("Errors and commands will be outputted here");
        tf.setEditable(false);
        tf.setVisible(true);
        tf.setCaretPosition(tf.getDocument().getLength());
        panel2.add(tf);

        //Panel 3
        MainPanel panel3 = new MainPanel(16,16);
        panel3.setPreferredSize(new Dimension(930, 560));
        panel3.setBorder(greenLine);

        for(int i = 0; i < 16; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                DrawPanel panel = panel3.panels[i][j];
                panel.setDrawable(new GridObject(1, 1, panel));
            }
        }

        frame.getContentPane().add(panel1, BorderLayout.EAST);
        frame.getContentPane().add(panel2, BorderLayout.SOUTH);
        frame.getContentPane().add(panel3, BorderLayout.WEST);
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.LIGHT_GRAY));
        frame.setVisible(true);
    }
}

