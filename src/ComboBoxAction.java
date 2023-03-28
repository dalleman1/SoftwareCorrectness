import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxAction extends JFrame implements ActionListener {
    JTextField currtf;
    JPanel currPanel;
    public ComboBoxAction(JPanel panel, JTextField tf) {
        JComboBox<String> comboBox = new JComboBox<String>();
        currtf = tf;
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

        //currPanel = panel;
        panel.add(comboBox);
        panel.add(currtf);
    }

    public void actionPerformed(ActionEvent e) {

        JComboBox comboBox = (JComboBox) e.getSource();
        //currArea.append("" + comboBox.getSelectedItem());

        if (comboBox.getSelectedItem() == "Line")
        {
            currtf.setVisible(true);
        }
        else{
            currtf.setVisible(false);
        }
        //  make sure popup is closed when 'isTableCellEditor' is used

        comboBox.hidePopup();
    }
}