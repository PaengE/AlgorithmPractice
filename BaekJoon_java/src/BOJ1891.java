import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.1891: 사분면
 *  Hint: Divide & Conquer + 구현
 */

public class BOJ1891 {
    static int d;
    static long tx, ty;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        arr = new int[d];
        String num = st.nextToken();
        for (int i = 0; i < d; i++) {
            arr[i] = num.charAt(i) - '0';
        }
        st = new StringTokenizer(br.readLine());
        long moveH = Long.parseLong(st.nextToken());    // 가로 이동
        long moveV = Long.parseLong(st.nextToken());    // 세로 이동

        long n = 1L << d;
        findLoc(0,0,0, n, n);
        tx -= moveV;
        ty += moveH;

        if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
            findNum(d, tx, ty);
        } else {
            bw.write("-1");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // 주어진 위치로 사분면 찾기
    static void findNum(int depth, long tx, long ty) {
        if (depth == 0) {
            return;
        }

        long half = 1L << (depth - 1);

        if (tx < half && ty >= half) {
            sb.append("1");
            findNum(depth - 1, tx, ty - half);
        } else if (tx < half && ty < half) {
            sb.append("2");
            findNum(depth - 1, tx, ty);
        } else if (tx >= half && ty < half) {
            sb.append("3");
            findNum(depth - 1, tx - half, ty);
        } else if (tx >= half && ty >= half) {
            sb.append("4");
            findNum(depth - 1, tx - half, ty - half);
        }
    }


    // 주어진 사분면 숫자의 위치 찾기
    static void findLoc(int numIdx, long lx, long ly, long rx, long ry) {
        if (numIdx == d) {
            tx = lx;
            ty = ly;
            return;
        }

        int num = arr[numIdx];
        if (num == 1) { // 1사분면
            findLoc(numIdx + 1, lx, (ly + ry) / 2, (lx + rx) / 2, ry);
        } else if (num == 2) {  // 2사분면
            findLoc(numIdx + 1, lx, ly, (lx + rx) / 2, (ly + ry) / 2);
        } else if (num == 3) {  // 3사분면
            findLoc(numIdx + 1, (lx + rx) / 2, ly, rx, (ly + ry) / 2);
        } else if (num == 4) {  // 4사분면
            findLoc(numIdx + 1, (lx + rx) / 2, (ly + ry) / 2, rx, ry);
        }
    }
}
