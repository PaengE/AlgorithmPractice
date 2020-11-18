import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * No.11723: 집합
 * description: 비트마스크 DP에 대해 다루기 전에, 일단 비트마스크부터 다뤄 봅시다.
 */

public class BOJ11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        MySet set = new MySet();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            int x = 0;

            switch (s) {
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    set.add(x);
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    set.remove(x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    sb.append(set.check(x) + "\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    set.toggle(x);
                    break;
                case "all":
                    set.all();
                    break;
                case "empty":
                    set.empty();
                    break;
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static class MySet {
        public HashSet<Integer> set;

        MySet() {
            set = new HashSet<>();
        }

        public void add(int x) {
            set.add(x);
        }

        public void remove(int x) {
            set.remove(x);
        }

        public int check(int x) {
            if (!set.isEmpty()) {
                if (set.contains(x)) {
                    return 1;
                }
            }
            return 0;
        }

        public void toggle(int x) {
            if (!set.isEmpty()) {
                if (set.contains(x)) {
                    set.remove(x);
                    return;
                }
            }
            set.add(x);
        }

        public void all() {
            for (int i = 1; i <= 20; i++) {
                set.add(i);
            }
        }

        public void empty() {
            if (!set.isEmpty()) {
                set.removeIf(e -> e > 0);
            }
        }
    }
}

