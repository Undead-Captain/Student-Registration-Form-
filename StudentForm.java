import java.sql.*;
import javax.swing.*;

public class StudentForm {
    public static void main(String[] args) {
        JFrame f = new JFrame("Student Registration");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1 = new JLabel("Name:"), l2 = new JLabel("Age:");
        JTextField t1 = new JTextField(), t2 = new JTextField();
        JButton b = new JButton("Submit");

        l1.setBounds(30, 30, 80, 25);
        t1.setBounds(120, 30, 150, 25);
        l2.setBounds(30, 70, 80, 25);
        t2.setBounds(120, 70, 150, 25);
        b.setBounds(120, 110, 100, 30);

        f.add(l1); f.add(t1); f.add(l2); f.add(t2); f.add(b);
        f.setSize(320, 200);
        f.setLayout(null);
        f.setVisible(true);

        b.addActionListener(e -> {
            String name = t1.getText();
            int age = Integer.parseInt(t2.getText());
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root")) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, age) VALUES (?, ?)");
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(f, "Student added!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
