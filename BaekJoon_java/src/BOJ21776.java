import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21776 {
    static int n, c;
    static ArrayList<Integer>[] order;
    static String[] cards;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        order = new ArrayList[n + 1];
        cards = new String[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                order[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 1; i <= c; i++) {
            cards[i] = br.readLine();
        }
        
    }
}
