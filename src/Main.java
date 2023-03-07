import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.x());

        JFrame var1 = new JFrame("My First GUI");
        var1.setDefaultCloseOperation(3);
        var1.setSize(300, 300);
        JButton var2 = new JButton("Press");
        var1.getContentPane().add(var2);
        var1.setVisible(true);
    }
}