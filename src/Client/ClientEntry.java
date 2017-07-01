/*
 * CN Project : Devloped By Jignesh 
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientEntry
extends JFrame {
    JLabel lbl1;
    JLabel lbl2;
    JLabel lbl3;
    JTextField portno = new JTextField(4);
    JTextField ip = new JTextField(20);
    JButton jb = new JButton("Connect");

    public ClientEntry() {
        super("Thambola v1.0 - Client");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout(1));
        this.lbl1 = new JLabel("Port");
        this.lbl2 = new JLabel("IP Adress");
        this.lbl3 = new JLabel();
        c.add(this.lbl1);
        c.add(this.portno);
        c.add(this.lbl2);
        c.add(this.ip);
        c.add(this.jb);
        c.add(this.lbl3);
        this.setDefaultCloseOperation(3);
        this.jb.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                String s1 = ClientEntry.this.portno.getText();
                String s2 = ClientEntry.this.ip.getText();
                try {
                    Client c = new Client(s2, Integer.parseInt(s1));
                    c.setSize(600, 250);
                    c.setVisible(true);
                }
                catch (IOException ex) {
                    System.out.println("Connection Failed");
                    ClientEntry.this.lbl3.setText("Connection failed");
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ClientEntry ce = new ClientEntry();
        ce.setSize(450, 200);
        ce.setVisible(true);
    }

}

