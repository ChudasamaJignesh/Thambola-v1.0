/*
 * CN Project : 201412077[Jignesh] 
 *       		201412063[Jagdish]
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MultiThreadedServer
extends JFrame
implements Runnable {
    static int nclients = 0;
    static int[] arr = new int[101];
    static int[] rnos = new int[101];
    static int[] round = new int[5];
    static int rno = 0;
    static int[] claims = new int[4];
    int portn;

    public MultiThreadedServer(int portn) {
        this.portn = portn;
    }

    @Override
    public void run() {
        try {
            JFrame f = new JFrame("Thambola Server");
            ServerSocket ssocket = new ServerSocket(this.portn);
            Container c = this.getContentPane();
            f.add(c);
            c.setLayout(new GridLayout(6, 1));
            JLabel lbl = new JLabel();
            lbl.setText("Server is ready");
            lbl.setHorizontalAlignment(0);
            c.add(lbl);
            f.setVisible(true);
            f.setSize(300, 300);
            this.setDefaultCloseOperation(3);
            System.out.println("Server is ready.....");
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
            Arrays.fill(arr, 0);
            Arrays.fill(round, 0);
            Arrays.fill(rnos, 0);
            Arrays.fill(claims, 0);
            MultiThreadedServer mts = new MultiThreadedServer(this.portn);
            do {
                Socket cs = ssocket.accept();
                String stemp = "Accepted Client";
                stemp = stemp + (nclients + 1);
                stemp = stemp + " Request.....";
                JLabel lbtemp = new JLabel();
                lbtemp.setText(stemp);
                lbtemp.setHorizontalAlignment(0);
                c.add(lbtemp);
                int clientID = ++nclients;
                ClientHandler client = new ClientHandler(cs, clientID, lbtemp, mts);
                executor.execute(client);
            } while (nclients != 5);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int randomNumber(int ID) {
        int max = -1;
        for (int i = 1; i <= nclients; ++i) {
            System.out.println("" + rno + " " + round[i] + " " + ID);
            if (round[i] <= max) continue;
            max = round[i];
        }
        if (round[ID] == max) {
            Random r = new Random();
            int n = r.nextInt(100);
            while (arr[n] != 0) {
                n = r.nextInt(100);
            }
            MultiThreadedServer.arr[n] = 1;
            MultiThreadedServer.rnos[++MultiThreadedServer.rno] = n;
            int[] arrn = round;
            int n2 = ID;
            arrn[n2] = arrn[n2] + 1;
            return n;
        }
        int[] arrn = round;
        int n = ID;
        arrn[n] = arrn[n] + 1;
        return rnos[round[ID]];
    }

    public int check(int[] ch, int type) {
        if (claims[type] != 0) {
            return -1;
        }
        int check = 0;
        for (int i = 0; i < ch.length; ++i) {
            if (arr[ch[i]] == 1) continue;
            ++check;
        }
        if (check == 0) {
            MultiThreadedServer.claims[type] = 1;
        }
        return check;
    }
}

