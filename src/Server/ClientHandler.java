/*
 * CN Project : Devloped By Jignesh 
 */
import java.awt.Container;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JLabel;

class ClientHandler
implements Runnable {
    Socket client;
    int clientID;
    JLabel l = new JLabel();
    Container c;
    MultiThreadedServer ms;

    public ClientHandler(Socket client, int clientID, JLabel l, MultiThreadedServer ms) {
        this.client = client;
        this.clientID = clientID;
        this.l = l;
        this.ms = ms;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(this.client.getInputStream());
            PrintWriter pw = new PrintWriter(this.client.getOutputStream(), true);
            String s = scanner.nextLine();
            this.l.setText(s + " joined game");
            String tkt = "";
            int[] itkt = this.create();
            for (int i = 0; i < itkt.length; ++i) {
                tkt = tkt + itkt[i];
                tkt = tkt + " ";
            }
            pw.println(tkt);
            do {
                if ((s = scanner.nextLine()).length() == 1) {
                    int n = Integer.parseInt(s);
                    pw.println(this.ms.randomNumber(n));
                    continue;
                }
                String[] temp = s.split(" ");
                int[] check = new int[temp.length - 1];
                for (int i2 = 0; i2 < temp.length - 1; ++i2) {
                    check[i2] = Integer.parseInt(temp[i2]);
                }
                int type = Integer.parseInt(temp[temp.length - 1]);
                int res = this.ms.check(check, type);
                if (res == 0) {
                    pw.println(res);
                    continue;
                }
                if (res == -1) {
                    pw.println(res);
                    continue;
                }
                pw.println(res);
            } while (true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public int[] create() {
        int[] tckt = new int[17];
        Arrays.fill(tckt, 0);
        Random r1 = new Random();
        Random r2 = new Random();
        int i1 = r1.nextInt(2);
        if (i1 == 0) {
            tckt[0] = r2.nextInt(10);
            tckt[1] = 10 + r2.nextInt(10);
            tckt[2] = 10 + r2.nextInt(10);
            while (tckt[2] == tckt[1]) {
                tckt[2] = 10 + r2.nextInt(10);
            }
            tckt[3] = 20 + r2.nextInt(10);
            tckt[4] = 30 + r2.nextInt(10);
            tckt[5] = 30 + r2.nextInt(10);
            while (tckt[5] == tckt[4]) {
                tckt[5] = 30 + r2.nextInt(10);
            }
            tckt[6] = 40 + r2.nextInt(10);
            tckt[7] = 50 + r2.nextInt(10);
            tckt[8] = 50 + r2.nextInt(10);
            while (tckt[8] == tckt[7]) {
                tckt[8] = 50 + r2.nextInt(10);
            }
            tckt[9] = 60 + r2.nextInt(10);
            tckt[10] = 70 + r2.nextInt(10);
            tckt[11] = 70 + r2.nextInt(10);
            while (tckt[11] == tckt[10]) {
                tckt[11] = 70 + r2.nextInt(10);
            }
            tckt[12] = 80 + r2.nextInt(10);
            tckt[13] = 90 + r2.nextInt(10);
            tckt[14] = 90 + r2.nextInt(10);
            while (tckt[14] == tckt[13]) {
                tckt[14] = 90 + r2.nextInt(10);
            }
        } else {
            tckt[0] = r2.nextInt(10);
            tckt[1] = r2.nextInt(10);
            while (tckt[1] == tckt[0]) {
                tckt[1] = r2.nextInt(10);
            }
            tckt[2] = 10 + r2.nextInt(10);
            tckt[3] = 20 + r2.nextInt(10);
            tckt[4] = 20 + r2.nextInt(10);
            while (tckt[4] == tckt[3]) {
                tckt[4] = 20 + r2.nextInt(10);
            }
            tckt[5] = 30 + r2.nextInt(10);
            tckt[6] = 40 + r2.nextInt(10);
            tckt[7] = 40 + r2.nextInt(10);
            while (tckt[7] == tckt[6]) {
                tckt[7] = 40 + r2.nextInt(10);
            }
            tckt[8] = 50 + r2.nextInt(10);
            tckt[9] = 60 + r2.nextInt(10);
            tckt[10] = 60 + r2.nextInt(10);
            while (tckt[10] == tckt[9]) {
                tckt[10] = 60 + r2.nextInt(10);
            }
            tckt[11] = 70 + r2.nextInt(10);
            tckt[12] = 80 + r2.nextInt(10);
            tckt[13] = 80 + r2.nextInt(10);
            while (tckt[13] == tckt[12]) {
                tckt[13] = 80 + r2.nextInt(10);
            }
            tckt[14] = 90 + r2.nextInt(10);
        }
        tckt[15] = i1;
        tckt[16] = this.clientID;
        return tckt;
    }
}

