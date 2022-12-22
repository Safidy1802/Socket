package fenetre;

import javax.swing.*;

import function.Fonction;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author safidy ETU1802
 */

public class Frame extends JFrame implements ActionListener{
    JTextField message;
    public Box vertical = Box.createVerticalBox();
    JPanel pane2;
    private DataOutputStream output;
    Fonction f = new Fonction();
    
    public Frame(){
        //declarena n Frame
        setLayout(null);
        //panel an'ny nom server
        JPanel pane1 = new JPanel();
        pane1.setBackground(Color.BLUE);
        pane1.setBounds(0, 0, 450, 70);
        pane1.setLayout(null);
        add(pane1);
        
        //server_nom
        JLabel nom = new JLabel("CLIENT_nom");
        nom.setBounds(35, 15, 100, 18);
        nom.setForeground(Color.YELLOW);
        nom.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        pane1.add(nom);
        
        pane2 = new JPanel();
        pane2.setBounds(5, 75, 425, 530);
        add(pane2);
        
        message = new JTextField();
        message.setBounds(5, 615, 310, 40);
        message.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        add(message);
        
        //button mandefa anle getText
        JButton lasa = new JButton("MANDEFA");
        lasa.setBounds(320, 615, 123, 40);
        lasa.setBackground(Color.red);
        lasa.setForeground(Color.WHITE);
        lasa.addActionListener(this);
        add(lasa);
        
        setSize(450, 700);
        setTitle("ClientSocket");
        setLocation(650, 32);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        try {
            String mes = message.getText();
            JPanel pane3 = f.form(mes);
            pane2.setLayout(new BorderLayout());

            JPanel vol = new JPanel(new BorderLayout());
            vol.add(pane3, BorderLayout.LINE_END);
            vertical.add(vol);
            vertical.add(Box.createVerticalStrut(15));

            pane2.add(vertical, BorderLayout.PAGE_START);
            output.writeUTF(mes);

            message.setText("");
            repaint();
            invalidate();
            validate();
        } catch(Exception ex) {
            ex.printStackTrace();
        }    
    }
    public void socksender(){
        try {
            Socket s = new Socket("localhost", 9999);
            DataInputStream input = new DataInputStream(s.getInputStream());
            output = new DataOutputStream(s.getOutputStream());
            while(true) {
                pane2.setLayout(new BorderLayout());
                String messages = input.readUTF();
                JPanel content = f.form(messages);
                JPanel g = new JPanel(new BorderLayout());
                g.add(content, BorderLayout.LINE_START);
                vertical.add(g);
                vertical.add(Box.createVerticalStrut(15));
                pane2.add(vertical, BorderLayout.PAGE_START);
                validate();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}