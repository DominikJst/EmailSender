package Oberflaeche;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

public class MailEditor extends JFrame {

    private JPanel panel1;
    private JTextPane textPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton sendButton;
    public ArrayList<String> daten = new ArrayList();

    public MailEditor() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMails();
            }
        });
    }

    public void mailEditor(){

        add(panel1);
        setSize(700, 500);
        setTitle("Send Mails");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void fillArray(String mail, String pass){

        daten.add(mail);
        daten.add(pass);
    }

    public void sendMails(){

        String messagePane = textPane1.getText();
        String to = textField1.getText();
        String subject = textField2.getText();
        String host = "smtp.gmail.com";

        String from = daten.get(0);
        String passwordFrom = daten.get(1);

        try{
            Properties properties = System.getProperties();

            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.user", from);
            properties.put("mail.smtp.password", passwordFrom);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(properties);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setText(messagePane);
            message.setSubject(subject);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, passwordFrom);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "Sent message successfully!");

            textPane1.setText("");
            textField1.setText("");
            textField2.setText("");


        }catch(Exception e){

            JOptionPane.showMessageDialog(null, "Error!");
            System.out.println(e);
        }
    }
}
