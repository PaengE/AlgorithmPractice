import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1043 {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        p = new int[n + 1]; // 진실을 알고 있는 사람은 p[tmp] = tmp 인 상태임
        Arrays.fill(p, -1);

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            int temp = Integer.parseInt(st.nextToken());
            p[temp] = temp;
        }

        // 파티 참석 인원들의 접점을 집합으로 묶음
        for (int i = 0; i < m; i++) {
            list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }

            if (list.get(i).size() >= 2) {
                unionize(i);
            }
        }

        // 파티에서 거짓말을 할 수있는지 체크
        int answer = 0;
        for (int i = 0; i < m; i++) {
            if (falseIsPossible(i)) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }

    // 현재 파티에서 거짓말을 할 수있는지 체크
    static boolean falseIsPossible(int i) {
        for (int j = 0; j < list.get(i).size(); j++) {
            int tmp = find(list.get(i).get(j));
            if (p[tmp] == tmp) {    // 파티원 중 진실을 알고 있는사람이 있으면 거짓말을 칠 수 없음
                return false;
            }
        }
        return true;
    }

    // 파티인원을 조사하여 union
    static void unionize(int i) {
        int first = list.get(i).get(0);

        for (int j = 1; j < list.get(i).size(); j++) {
            union(first, list.get(i).get(j));
        }
    }

    // 같은 집합으로 묶는 메소드
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            if (aRoot == p[aRoot]) {
                p[bRoot] = aRoot;
            } else {
                p[aRoot] = bRoot;
            }
        }
    }

    // 숫자 a의 부모가 누군지를 찾는 메소드
    static int find(int a) {
        if (p[a] < 0 || p[a] == a) {
            return a;
        } else {
            return p[a] = find(p[a]);
        }
    }
}
