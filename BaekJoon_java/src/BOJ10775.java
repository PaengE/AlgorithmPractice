import java.io.*;

/**
 *  No.10775: 공항
 *  URL: https://www.acmicpc.net/problem/10775
 *  Hint: Union-find or Greedy
 */

public class BOJ10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] arr = new int[G + 1];

        int ans = 0;
        loop:
        for (int i = 0; i < P; i++) {
            int gate = Integer.parseInt(br.readLine());

            for (; gate > 0 ; gate--) {
                if (arr[gate] == 0) {
                    arr[gate] = -1;
                    ans++;
                    continue loop;
                }
            }
            if (gate == 0) {
                break;
            }
        }

        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
}

// 유니온 파인드 이용한 코드

//public class BOJ10775 {
//    static int G, P;
//    static int[] g, parent;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        G = Integer.parseInt(br.readLine());
//        P = Integer.parseInt(br.readLine());
//        init();
//
//        int ans = 0;
//        for (int i = 0; i < P; i++) {
//            int plane = Integer.parseInt(br.readLine());
//            int possibleGate = find(plane);
//
//            if (possibleGate == 0) {
//                break;
//            }
//            ans++;
//
//            union(possibleGate, possibleGate - 1);
//        }
//
//        bw.write(String.valueOf(ans));
//        bw.close();
//        br.close();
//    }
//
//    static void init() {
//        parent = new int[G + 1];
//        for (int i = 1; i <= G; i++) {
//            parent[i] = i;
//        }
//    }
//
//    static void union(int a, int b) {
//        a = find(a);
//        b = find(b);
//
//        if (a != b) {
//            parent[a] = b;
//        }
//    }
//
//    static int find(int x) {
//        if (parent[x] == x) {
//            return x;
//        }
//
//        return parent[x] = find(parent[x]);
//    }
//}
