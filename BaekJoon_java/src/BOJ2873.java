import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.2873: 롤러코스터
 *  Hint: Greedy
 */

public class BOJ2873 {
    static int r, c, minR, minC;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][c];
        int min = 1001;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 시작지점이 흰색인 체스판이라 가정했을 때,
                // 검은 지점 중 가장 작은 숫자가 위치한 곳을 찾음
                if ((i + j) % 2 == 1 && arr[i][j] < min) {
                    min = arr[i][j];
                    minR = i;
                    minC = j;
                }
            }
        }

        String ans;
        // r 과 c 중 하나라도 홀수라면 전체 탐색이 가능
        if (r % 2 == 1 || c % 2 == 1) {
            ans = notEven();
        } else {
            ans = even();
        }

        bw.write(ans);
        bw.close();
        br.close();
    }

    static String even() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        int sr = 0, sc = 0;
        int er = r - 1, ec = c - 1;
        
        // er 과 sr 사이에 행 2개만 남을 때까지
        while (er - sr > 1) {
            // sr 기준 현재 행과 다음 행에 최소값 검은 지점이 없다면 ㄹ 모양으로 방문
            if (sr / 2 < minR / 2) {    
                for (int i = 0; i < c - 1; i++) {
                    sb1.append("R");
                }
                sb1.append("D");

                for (int i = 0; i < c - 1; i++) { 
                    sb1.append("L");
                }
                sb1.append("D");
                sr += 2;
            }

            // er 기준 현재 행과 이전 행에 최소값 검은 지점이 없다면 ㄹ 모양으로 방문
            if (er / 2 > minR / 2) {    
                for (int i = 0; i < c - 1; i++) {
                    sb2.append("R");
                }
                sb2.append("D");

                for (int i = 0; i < c - 1; i++) {
                    sb2.append("L");
                }
                sb2.append("D");
                er -= 2;
            }
        }

        // ec 와 sc 사이에 열 2개만 남을 때까지
        while (ec - sc > 1) {
            // sc 기준 현재 열과 다음 열에 최소값 검은 지점이 없다면 ㄹ 을 y=x 대칭 시킨 모양으로 방문
            if (sc / 2 < minC / 2) {
                sb1.append("DRUR");
                sc += 2;
            }

            // ec 기준 현재 열과 다음 열에 최소값 검은 지점이 없다면 ㄹ 을 y=x 대칭 시킨 모양으로 방문
            if (ec / 2 > minC / 2) {
                sb2.append("DRUR");
                ec -= 2;
            }
        }

        // 위 과정을 거치면 2 x 2 부분만 남음
        if (sc == minC) {
            sb1.append("RD");
        } else {
            sb1.append("DR");
        }

        return sb1.append(sb2.reverse()).toString();
    }

    static String notEven() {
        StringBuilder sb = new StringBuilder();

        if (r % 2 == 1) {  // 행이 홀수라면 ㄹ 모양으로 방문
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c - 1; j++) {
                    if (i % 2 == 0) {
                        sb.append("R");
                    } else if (i % 2 == 1) {
                        sb.append("L");
                    }
                }
                if (i != r - 1) {
                    sb.append("D");
                }
            }
        } else if (c % 2 == 1) {  // 열이 홀수라면 ㄹ y=x 기준 대칭 시킨 모양으로 방문
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < r - 1; j++) {
                    if (i % 2 == 0) {
                        sb.append("D");
                    } else if (i % 2 == 1) {
                        sb.append("U");
                    }
                }
                if (i != c - 1) {
                    sb.append("R");
                }
            }
        }

        return sb.toString();
    }
}
