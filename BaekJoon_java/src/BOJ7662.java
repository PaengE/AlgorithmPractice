import java.io.*;
import java.util.*;

public class BOJ7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            StringTokenizer st;
            TreeMap<Integer, Integer> tmap = new TreeMap<>();

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (op.equals("I")) {
                    tmap.computeIfPresent(n, (key, value) -> value + 1);
                    tmap.computeIfAbsent(n, key -> 1);
                } else {
                    if (tmap.isEmpty()) {
                        continue;
                    }

                    if (n == 1) {
                        if (tmap.get(tmap.lastKey()) == 1) {
                            tmap.pollLastEntry();
                        } else {
                            tmap.computeIfPresent(tmap.lastKey(), (key, value) -> value - 1);
                        }
                    } else {
                        if (tmap.get(tmap.firstKey()) == 1) {
                            tmap.pollFirstEntry();
                        } else {
                            tmap.computeIfPresent(tmap.firstKey(), (key, value) -> value - 1);
                        }
                    }
                }
            }

            if (tmap.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(tmap.lastKey() + " " + tmap.firstKey() + "\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
