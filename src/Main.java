import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        Border greenLine = BorderFactory.createLineBorder(Color.black);
        Border yellowLine = BorderFactory.createLineBorder(Color.black);

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(940, 720));
        panel1.setBorder(blackLine);

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(320, 220));
        panel2.setBorder(yellowLine);

        JTextArea display = new JTextArea("Errors and commands will be shown here",13,172);
        display.setEditable(false); // set textArea non-editable
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JFrame frame = new JFrame("Software Correctness Project");
        frame.setDefaultCloseOperation(3);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Panel 2

        panel2.add(scrollPane);
        //Panel 1


        ComboBoxAction cb = new ComboBoxAction(panel1, display);
        cb.setDefaultCloseOperation( EXIT_ON_CLOSE );
        cb.pack();
        cb.setVisible(true);


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

