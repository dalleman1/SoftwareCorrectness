import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxAction extends JFrame implements ActionListener {
    JTextField x1, x2, y1, y2, r, t, g, c;
    JTextField inputx1, inputx2, inputy1, inputy2, inputr, inputt, inputg, inputc;
    JTextArea messages;
    JButton but;
    Graphics2D graphics = null;
    DrawPanel mainPanel;
    String currentSelectedItem;
    PixelHelperSC pixelHelper;

    public ComboBoxAction(JPanel panel, JTextArea display, DrawPanel panel2) {
        JComboBox<String> comboBox = new JComboBox<String>();
        currentSelectedItem = "None";
        JButton button = new JButton("Submit");
        mainPanel = panel2;
        button.setBounds(375, 550, 100,60);
        pixelHelper = new PixelHelperSC(mainPanel);
        System.out.println(mainPanel.getGraphics());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (currentSelectedItem) {
                        case "Line":
                            if (inputx1.getText().isEmpty() || inputx2.getText().isEmpty() || inputy1.getText().isEmpty() || inputy2.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with x1: " + inputx1.getText() + ", x2: " + inputx2.getText() + ", y1: " + inputy1.getText() + ", y2: " + inputy2.getText());

                            pixelHelper.DrawLine(Integer.parseInt(inputx1.getText()), Integer.parseInt(inputy1.getText()), Integer.parseInt(inputx2.getText()), Integer.parseInt(inputy2.getText()));

                            mainPanel.revalidate();
                            //Call Scala class that does something

                            break;
                        case "Rectangle":
                            if (inputx1.getText().isEmpty() || inputx2.getText().isEmpty() || inputy1.getText().isEmpty() || inputy2.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with x1: " + inputx1.getText() + ", x2: " + inputx2.getText() + ", y1: " + inputy1.getText() + ", y2: " + inputy2.getText() + "");
                            pixelHelper.DrawRectangle(Integer.parseInt(inputx1.getText()), Integer.parseInt(inputy1.getText()), Integer.parseInt(inputx2.getText()), Integer.parseInt(inputy2.getText()));
                            mainPanel.revalidate();
                            break;
                        case "Circle":
                            if (inputx1.getText().isEmpty() || inputy1.getText().isEmpty() || inputr.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with x1: " + inputx1.getText() + ", y1: " + inputy1.getText() + ", radius: " + inputr.getText());
                            pixelHelper.DrawCircle(Integer.parseInt(inputx1.getText()), Integer.parseInt(inputy1.getText()),Integer.parseInt(inputr.getText()));
                            mainPanel.revalidate();
                            break;
                        case "Text-At":
                            if (inputx1.getText().isEmpty() || inputy1.getText().isEmpty() || inputt.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with x1: " + inputx1.getText() + ", y1: " + inputy1.getText() + ", text-at: " + inputt.getText());
                            break;
                        case "Draw":
                            if (inputc.getText().isEmpty() || inputg.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with colour: " + inputc.getText() + ", object: " + inputg.getText());
                            break;
                        case "Bounding-Box":
                            if (inputx1.getText().isEmpty() || inputx2.getText().isEmpty() || inputy1.getText().isEmpty() || inputy2.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with x1: " + inputx1.getText() + ", x2: " + inputx2.getText() + ", y1: " + inputy1.getText() + ", y2: " + inputy2.getText() + "" + "");
                            pixelHelper.createBoundaryBox(Integer.parseInt(inputx1.getText()), Integer.parseInt(inputy1.getText()), Integer.parseInt(inputx2.getText()), Integer.parseInt(inputy2.getText()));
                            break;
                        case "Fill":
                            if (inputc.getText().isEmpty() || inputg.getText().isEmpty())
                            {
                                throw new Exception("Text field cannot be empty...");
                            }
                            messages.append("\nSubmitted " + currentSelectedItem + " with colour: " + inputc.getText() + ", object: " + inputg.getText() + "");
                            break;
                        default:
                            messages.append("\nAn error occurred when submitting...");
                    }
                }catch (Exception exc){
                    messages.append("\nException: " + exc.getMessage());
                }
            }
        });
        but = button;
        panel.add(button);

        messages = display;
        JTextField tf1 = new JTextField("X1:");
        tf1.setLayout(null);
        tf1.setEditable(false);
        tf1.setBounds(300,150, 35,35);
        tf1.setVisible(true);
        x1 = tf1;
        panel.add(tf1);

        inputx1 = new JTextField();
        inputx1.setLayout(null);
        inputx1.setEditable(true);
        inputx1.setBounds(300,200, 35,35);
        inputx1.setVisible(true);
        panel.add(inputx1);

        JTextField tf2 = new JTextField("X2:");
        tf2.setLayout(null);
        tf2.setEditable(false);
        tf2.setBounds(500,150, 35,35);
        tf2.setVisible(true);
        x2 = tf2;
        panel.add(tf2);

        inputx2 = new JTextField();
        inputx2.setLayout(null);
        inputx2.setEditable(true);
        inputx2.setBounds(500,200, 35,35);
        inputx2.setVisible(true);
        panel.add(inputx2);

        JTextField tf3 = new JTextField("Y1:");
        tf3.setLayout(null);
        tf3.setEditable(false);
        tf3.setBounds(300,250, 35,35);
        tf3.setVisible(true);
        y1 = tf3;
        panel.add(tf3);

        inputy1 = new JTextField();
        inputy1.setLayout(null);
        inputy1.setEditable(true);
        inputy1.setBounds(300,300, 35,35);
        inputy1.setVisible(true);
        panel.add(inputy1);

        JTextField tf4 = new JTextField("Y2:");
        tf4.setLayout(null);
        tf4.setEditable(false);
        tf4.setBounds(500,250, 35,35);
        tf4.setVisible(true);
        y2 = tf4;
        panel.add(tf4);

        inputy2 = new JTextField();
        inputy2.setLayout(null);
        inputy2.setEditable(true);
        inputy2.setBounds(500,300, 35,35);
        inputy2.setVisible(true);
        panel.add(inputy2);

        JTextField tf5 = new JTextField("Radius:");
        tf5.setLayout(null);
        tf5.setEditable(false);
        tf5.setBounds(300,350, 70,70);
        tf5.setVisible(true);
        r = tf5;
        panel.add(tf5);

        inputr = new JTextField();
        inputr.setLayout(null);
        inputr.setEditable(true);
        inputr.setBounds(300,425, 35,35);
        inputr.setVisible(true);
        panel.add(inputr);

        JTextField tf6 = new JTextField("Text-At:");
        tf6.setLayout(null);
        tf6.setEditable(false);
        tf6.setBounds(500,150, 70,70);
        tf6.setVisible(true);
        t = tf6;
        panel.add(tf6);

        inputt = new JTextField();
        inputt.setLayout(null);
        inputt.setEditable(true);
        inputt.setBounds(500,250, 120,35);
        inputt.setVisible(true);
        panel.add(inputt);

        JTextField tf7 = new JTextField("Colour:");
        tf7.setLayout(null);
        tf7.setEditable(false);
        tf7.setBounds(300,150, 70,70);
        tf7.setVisible(true);
        c = tf7;
        panel.add(tf7);

        inputc = new JTextField();
        inputc.setLayout(null);
        inputc.setEditable(true);
        inputc.setBounds(300,250, 35,35);
        inputc.setVisible(true);
        panel.add(inputc);

        JTextField tf8 = new JTextField("Object:");
        tf8.setLayout(null);
        tf8.setEditable(false);
        tf8.setBounds(500,150, 70,70);
        tf8.setVisible(true);
        g = tf8;
        panel.add(tf8);

        inputg = new JTextField();
        inputg.setLayout(null);
        inputg.setEditable(true);
        inputg.setBounds(500,250, 35,35);
        inputg.setVisible(true);
        panel.add(inputg);

        comboBox.addActionListener(this);
        comboBox.addItem("Line");
        comboBox.addItem("Rectangle");
        comboBox.addItem("Circle");
        comboBox.addItem("Text-At");
        comboBox.addItem("Draw");
        comboBox.addItem("Bounding-Box");
        comboBox.addItem("Fill");

        //  This prevents action events from being fired when the
        //  up/down arrow keys are used on the dropdown menu

        comboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        comboBox.setBounds(375 ,20,110,50);
        //currPanel = panel;
        panel.setLayout(null);
        panel.add(comboBox);

    }

    public void actionPerformed(ActionEvent e) {
        // x1, x2, y1, y2, r, t, g, c;
        // inputx1, inputx2, inputy1, inputy2, inputr, inputt, inputg, inputc;
        JComboBox comboBox = (JComboBox) e.getSource();
        //currArea.append("" + comboBox.getSelectedItem());


        if (comboBox.getSelectedItem() == "Line")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(true);
            inputx1.setEditable(true);
            inputx2.setVisible(true);
            inputx2.setEditable(true);
            inputy1.setVisible(true);
            inputy1.setEditable(true);
            inputy2.setVisible(true);
            inputy2.setEditable(true);
            inputr.setVisible(false);
            inputr.setEditable(false);
            inputt.setVisible(false);
            inputt.setEditable(false);
            inputg.setVisible(false);
            inputg.setEditable(false);
            inputc.setVisible(false);
            inputc.setEditable(false);

            x1.setVisible(true);
            x2.setVisible(true);
            y1.setVisible(true);
            y2.setVisible(true);
            r.setVisible(false);
            t.setVisible(false);
            g.setVisible(false);
            c.setVisible(false);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());


        }
        if (comboBox.getSelectedItem() == "Rectangle")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(true);
            inputx1.setEditable(true);
            inputx2.setVisible(true);
            inputx2.setEditable(true);
            inputy1.setVisible(true);
            inputy1.setEditable(true);
            inputy2.setVisible(true);
            inputy2.setEditable(true);
            inputr.setVisible(false);
            inputr.setEditable(false);
            inputt.setVisible(false);
            inputt.setEditable(false);
            inputg.setVisible(false);
            inputg.setEditable(false);
            inputc.setVisible(false);
            inputc.setEditable(false);

            x1.setVisible(true);
            x2.setVisible(true);
            y1.setVisible(true);
            y2.setVisible(true);
            r.setVisible(false);
            t.setVisible(false);
            g.setVisible(false);
            c.setVisible(false);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());
        }
        if (comboBox.getSelectedItem() == "Circle")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(true);
            inputx1.setEditable(true);
            inputx2.setVisible(false);
            inputx2.setEditable(false);
            inputy1.setVisible(true);
            inputy1.setEditable(true);
            inputy2.setVisible(false);
            inputy2.setEditable(false);
            inputr.setVisible(true);
            inputr.setEditable(true);
            inputt.setVisible(false);
            inputt.setEditable(false);
            inputg.setVisible(false);
            inputg.setEditable(false);
            inputc.setVisible(false);
            inputc.setEditable(false);

            x1.setVisible(true);
            x2.setVisible(false);
            y1.setVisible(true);
            y2.setVisible(false);
            r.setVisible(true);
            t.setVisible(false);
            g.setVisible(false);
            c.setVisible(false);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());
        }
        if (comboBox.getSelectedItem() == "Text-At")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(true);
            inputx1.setEditable(true);
            inputx2.setVisible(false);
            inputx2.setEditable(false);
            inputy1.setVisible(true);
            inputy1.setEditable(true);
            inputy2.setVisible(false);
            inputy2.setEditable(false);
            inputr.setVisible(false);
            inputr.setEditable(false);
            inputt.setVisible(true);
            inputt.setEditable(true);
            inputg.setVisible(false);
            inputg.setEditable(false);
            inputc.setVisible(false);
            inputc.setEditable(false);


            x1.setVisible(true);
            x2.setVisible(false);
            y1.setVisible(true);
            y2.setVisible(false);
            r.setVisible(false);
            t.setVisible(true);
            g.setVisible(false);
            c.setVisible(false);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());
        }
        if (comboBox.getSelectedItem() == "Draw")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(false);
            inputx1.setEditable(false);
            inputx2.setVisible(false);
            inputx2.setEditable(false);
            inputy1.setVisible(false);
            inputy1.setEditable(false);
            inputy2.setVisible(false);
            inputy2.setEditable(false);
            inputr.setVisible(false);
            inputr.setEditable(false);
            inputt.setVisible(false);
            inputt.setEditable(false);
            inputg.setVisible(true);
            inputg.setEditable(true);
            inputc.setVisible(true);
            inputc.setEditable(true);

            x1.setVisible(false);
            x2.setVisible(false);
            y1.setVisible(false);
            y2.setVisible(false);
            r.setVisible(false);
            t.setVisible(false);
            g.setVisible(true);
            c.setVisible(true);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());
        }
        if (comboBox.getSelectedItem() == "Bounding-Box")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(true);
            inputx1.setEditable(true);
            inputx2.setVisible(true);
            inputx2.setEditable(true);
            inputy1.setVisible(true);
            inputy1.setEditable(true);
            inputy2.setVisible(true);
            inputy2.setEditable(true);
            inputr.setVisible(false);
            inputr.setEditable(false);
            inputt.setVisible(false);
            inputt.setEditable(false);
            inputg.setVisible(false);
            inputg.setEditable(false);
            inputc.setVisible(false);
            inputc.setEditable(false);

            x1.setVisible(true);
            x2.setVisible(true);
            y1.setVisible(true);
            y2.setVisible(true);
            r.setVisible(false);
            t.setVisible(false);
            g.setVisible(false);
            c.setVisible(false);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());
        }
        if (comboBox.getSelectedItem() == "Fill")
        {
            currentSelectedItem = (String) comboBox.getSelectedItem();
            inputx1.setVisible(false);
            inputx1.setEditable(false);
            inputx2.setVisible(false);
            inputx2.setEditable(false);
            inputy1.setVisible(false);
            inputy1.setEditable(false);
            inputy2.setVisible(false);
            inputy2.setEditable(false);
            inputr.setVisible(false);
            inputr.setEditable(false);
            inputt.setVisible(false);
            inputt.setEditable(false);
            inputg.setVisible(true);
            inputg.setEditable(true);
            inputc.setVisible(true);
            inputc.setEditable(true);

            x1.setVisible(false);
            x2.setVisible(false);
            y1.setVisible(false);
            y2.setVisible(false);
            r.setVisible(false);
            t.setVisible(false);
            g.setVisible(true);
            c.setVisible(true);
            //messages.append("\nSelected: " + comboBox.getSelectedItem());
        }
        //  make sure popup is closed when 'isTableCellEditor' is used

        comboBox.hidePopup();
    }
}