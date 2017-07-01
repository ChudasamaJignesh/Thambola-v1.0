/*
 * CN Project : 201412077[Jignesh] 
 *       		201412063[Jagdish]
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThreadRunner
extends JFrame {
    public ThreadRunner() throws UnknownHostException {
        super("Thambola Server");
        Container c = this.getContentPane();
        this.setDefaultCloseOperation(3);
        c.setLayout(new GridLayout(3, 1));
        JPanel p = new JPanel(new FlowLayout());
        JLabel l = new JLabel();
        String temp = "Server IP: ";
        temp = temp + InetAddress.getLocalHost();
        l.setText(temp);
        l.setHorizontalAlignment(0);
        c.add(l);
        JLabel l1 = new JLabel("Port: ");
        final JTextField jtf = new JTextField(4);
        p.add(l1);
        p.add(jtf);
        c.add(p);
        JButton b = new JButton("Start Game Server");
        c.add(b);
        b.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int t = Integer.parseInt(jtf.getText());
                MultiThreadedServer mts = new MultiThreadedServer(t);
                Thread tmt = new Thread(mts);
                tmt.start();
            }
        });
    }

    public static void main(String[] args) {
        try {
            ThreadRunner tr = new ThreadRunner();
            tr.setVisible(true);
            tr.setSize(300, 300);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

