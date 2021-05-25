import java.io.*;
import java.util.StringTokenizer;

public class BOJ21771 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int rg = Integer.parseInt(st.nextToken());
        int cg = Integer.parseInt(st.nextToken());
        int rp = Integer.parseInt(st.nextToken());
        int cp = Integer.parseInt(st.nextToken());

        int rcp = rp * cp;
        int pcnt = 0;
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                char chr = s.charAt(j);
                if (chr == 'P') {
                    pcnt++;
                }
            }
        }

        if (rcp != pcnt) {
            bw.write("1");
        } else {
            bw.write("0");
        }

        bw.close();
        br.close();
    }
}

