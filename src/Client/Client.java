/*
 * Decompiled with CFR 0_110.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Client
extends JFrame
implements ActionListener {
    JRadioButton upper = new JRadioButton("Upper Line");
    JRadioButton middle = new JRadioButton("Middle Line");
    JRadioButton lower = new JRadioButton("Lower Line");
    JRadioButton full = new JRadioButton("Full Housie");
    JLabel l = new JLabel();
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel();
    JButton claim = new JButton("Claim");
    JButton nextn = new JButton("Next");
    ButtonGroup bg = new ButtonGroup();
    PrintWriter sout;
    int[] up = new int[5];
    int[] mid = new int[5];
    int[] low = new int[5];
    int[] house = new int[15];

    public Client(String s1, int s2) throws IOException {
        super("Thambola v1.0");
        final Socket s = new Socket(s1, s2);
        final Scanner sin = new Scanner(s.getInputStream());
        this.sout = new PrintWriter(s.getOutputStream(), true);
        this.sout.println(InetAddress.getLocalHost().toString());
        String[] temp = sin.nextLine().split(" ");
        int[] tckt = new int[temp.length];
        for (int i = 0; i < temp.length; ++i) {
            tckt[i] = Integer.parseInt(temp[i]);
            if (i >= temp.length - 2) continue;
            this.house[i] = tckt[i];
        }
        final int n = tckt[tckt.length - 1];
        Container c = this.getContentPane();
        JPanel panelstart = new JPanel();
        panelstart.setLayout(new FlowLayout(1));
        JPanel panelleft = new JPanel();
        panelleft.setLayout(new GridLayout(10, 1));
        JPanel panelcenter = new JPanel();
        panelcenter.setLayout(new GridLayout(3, 10));
        JLabel lbl = new JLabel();
        JLabel lbl1 = new JLabel("PORT");
        JLabel lbl2 = new JLabel("IP Address");
        JTextField ip = new JTextField(20);
        JTextField portno = new JTextField(4);
        JButton[] btns = new JButton[30];
        ip.setText(s1);
        Integer temp0 = s2;
        portno.setText(temp0.toString());
        this.bg.add(this.upper);
        this.bg.add(this.middle);
        this.bg.add(this.lower);
        this.bg.add(this.full);
        for (int i2 = 0; i2 < 30; ++i2) {
            btns[i2] = new JButton();
            btns[i2].setBackground(new Color(0, 255, 255));
            panelcenter.add(btns[i2]);
        }
        if (tckt[tckt.length - 2] == 0) {
            int j = 0;
            int p1 = 0;
            int p2 = 0;
            for (int i3 = 0; i3 < tckt.length - 2; ++i3) {
                if (i3 % 3 == 0) {
                    Integer k = tckt[i3];
                    this.mid[p1] = tckt[i3];
                    btns[10 + 2 * (i3 / 3)].setText(k.toString());
                    btns[10 + 2 * (i3 / 3)].addActionListener(this);
                    btns[10 + 2 * (i3 / 3)].setBackground(Color.WHITE);
                    ++p1;
                    continue;
                }
                Integer k1 = tckt[i3];
                Integer k2 = tckt[i3 + 1];
                this.up[p2] = tckt[i3];
                this.low[p2] = tckt[i3 + 1];
                ++p2;
                btns[i3 - j].setText(k1.toString());
                btns[20 + (i3 - j)].setText(k2.toString());
                btns[i3 - j].addActionListener(this);
                btns[i3 - j].setBackground(Color.WHITE);
                btns[20 + (i3 - j)].addActionListener(this);
                btns[20 + (i3 - j)].setBackground(Color.WHITE);
                ++i3;
                ++j;
            }
        } else {
            int p1 = 0;
            int p2 = 0;
            int j = 1;
            for (int i4 = 0; i4 < tckt.length - 2; ++i4) {
                if (i4 % 3 == 0) {
                    Integer k1 = tckt[i4];
                    Integer k2 = tckt[i4 + 1];
                    this.up[p2] = tckt[i4];
                    this.low[p2] = tckt[i4 + 1];
                    ++p2;
                    btns[2 * (i4 / 3)].setText(k1.toString());
                    btns[20 + 2 * (i4 / 3)].setText(k2.toString());
                    btns[2 * (i4 / 3)].addActionListener(this);
                    btns[20 + 2 * (i4 / 3)].addActionListener(this);
                    btns[2 * (i4 / 3)].setBackground(Color.WHITE);
                    btns[20 + 2 * (i4 / 3)].setBackground(Color.WHITE);
                    ++i4;
                    continue;
                }
                Integer k = tckt[i4];
                this.mid[p1] = tckt[i4];
                ++p1;
                btns[10 + (i4 - j)].setText(k.toString());
                btns[10 + (i4 - j)].addActionListener(this);
                btns[10 + (i4 - j)].setBackground(Color.WHITE);
                ++j;
            }
        }
        this.nextn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                Client.this.sout.println(n);
                Integer pno = Integer.parseInt(sin.nextLine());
                Client.this.l.setText(pno.toString());
            }
        });
        this.claim.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                int i;
                String sk = "";
                System.out.println("action");
                boolean fres = false;
                JRadioButton jrbt = new JRadioButton();
                if (Client.this.upper.isSelected()) {
                    System.out.println("upper");
                    jrbt = Client.this.upper;
                    for (i = 0; i < Client.this.up.length; ++i) {
                        sk = sk + Client.this.up[i];
                        sk = sk + " ";
                    }
                    sk = sk + 0;
                } else if (Client.this.middle.isSelected()) {
                    jrbt = Client.this.middle;
                    for (i = 0; i < Client.this.mid.length; ++i) {
                        sk = sk + Client.this.mid[i];
                        sk = sk + " ";
                    }
                    sk = sk + 1;
                } else if (Client.this.lower.isSelected()) {
                    jrbt = Client.this.lower;
                    for (i = 0; i < Client.this.low.length; ++i) {
                        sk = sk + Client.this.low[i];
                        sk = sk + " ";
                    }
                    sk = sk + 2;
                } else if (Client.this.full.isSelected()) {
                    jrbt = Client.this.full;
                    fres = true;
                    for (i = 0; i < Client.this.house.length; ++i) {
                        sk = sk + Client.this.house[i];
                        sk = sk + " ";
                    }
                    sk = sk + 3;
                }
                Client.this.sout.println(sk);
                int res = Integer.parseInt(sin.nextLine());
                if (res == 0) {
                    if (!fres) {
                        Client.this.l2.setText("Won!!!");
                        jrbt.setVisible(false);
                    } else {
                        Client.this.l2.setText("You Won!!! Game Over");
                        jrbt.setVisible(false);
                        try {
                            s.close();
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (res == -1) {
                    if (!fres) {
                        Client.this.l2.setText("Already Claimed");
                        jrbt.setVisible(false);
                    } else {
                        Client.this.l2.setText("Game Over!!!");
                        jrbt.setVisible(false);
                        try {
                            s.close();
                        }
                        catch (IOException ex) {
                            System.out.println("Connection lost");
                        }
                    }
                } else {
                    Client.this.l2.setText("Wrong Claim");
                }
            }
        });
        lbl.setText(new Date().toString());
        this.l.setHorizontalAlignment(0);
        this.l.setVerticalAlignment(0);
        panelstart.add(lbl1);
        panelstart.add(portno);
        panelstart.add(lbl2);
        panelstart.add(ip);
        panelleft.add(this.l);
        panelleft.add(new JLabel());
        panelleft.add(this.nextn);
        panelleft.add(this.l1);
        panelleft.add(this.upper);
        panelleft.add(this.middle);
        panelleft.add(this.lower);
        panelleft.add(this.full);
        panelleft.add(this.l2);
        panelleft.add(this.claim);
        c.add((Component)panelstart, "First");
        c.add((Component)panelleft, "West");
        c.add((Component)panelcenter, "Center");
        c.add((Component)lbl, "Last");
        c.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton)e.getSource();
        LineBorder b = new LineBorder(Color.RED, 3);
        jb.setBorder(b);
    }

}

