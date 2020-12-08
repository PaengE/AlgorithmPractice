import java.io.*;
import java.util.*;

/**
 *  No.1991: 트리 순회
 *  URL: https://www.acmicpc.net/problem/1991
 *  Description: 이진트리 순회(전위, 중위, 후위 순회)
 */

public class BOJ1991 {
    static Map<String, List<String>> map = new HashMap<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<String> list = new ArrayList<>();

            String p = st.nextToken();
            list.add(st.nextToken());
            list.add(st.nextToken());
            map.put(p, list);
        }

        preOrder("A");
        System.out.println();
        inOrder("A");
        System.out.println();
        postOrder("A");
    }

    static void preOrder(String s) {
        if (s.equals(".")) {
            return ;
        }
        System.out.print(s);
        preOrder(map.get(s).get(0));
        preOrder(map.get(s).get(1));
    }

    static void inOrder(String s) {
        if (s.equals(".")) {
            return ;
        }
        inOrder(map.get(s).get(0));
        System.out.print(s);
        inOrder(map.get(s).get(1));
    }

    static void postOrder(String s) {
        if (s.equals(".")) {
            return ;
        }
        postOrder(map.get(s).get(0));
        postOrder(map.get(s).get(1));
        System.out.print(s);
    }
}
