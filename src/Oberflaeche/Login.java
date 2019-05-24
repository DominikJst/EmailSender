package Oberflaeche;

import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Login extends JFrame {
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel panel1;
    private JTextField textField1;


    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkMail();
            }
        });
    }

    public void login() {

        add(panel1);
        setSize(500, 400);
        setTitle("Login");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void checkMail() {

        MailEditor editor = new MailEditor();

        try {

            String fromEmail = textField1.getText();
            String fromPass = String.valueOf(passwordField1.getPassword());
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.user", fromEmail);
            properties.put("mail.smtp.password", fromPass);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(properties);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, fromEmail, fromPass);
            transport.close();
            editor.fillArray(fromEmail, fromPass);
            editor.mailEditor();
            dispose();


        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Wrong password or email!");
        }
    }

}


