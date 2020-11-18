/*
    no.14889 : 스타트와 링크
    hint: (12, 34)와 (34, 12)는 같음(중복).
        어느 한 사람이 포함된 팀과 포함되지 않은 팀의 합 이 전체 팀 개수
        -> 예를 들어, 1이 포함된 팀 vs 1이 포함되지 않은팀 으로 나눌수있음
        -> 1이 포함된 팀을 구하면 자동적으로 나머지 1이 포함되지 않은 팀이 구해짐
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    static BufferedReader br;
    static int n, sum1, sum2, t1, t2 = 0;
    static String s;
    static StringTokenizer st;
    static int[][] arr;
    static int[] team1, team2, number;
    static int min = -1;
    static boolean[] isUsed;

    public static void cal(int idx){
        if (idx == n / 2){
            sum1 = 0;
            sum2 = 0;
            t2 = 0;

            for (int i = 0; i < n; i++){
                if(!isUsed[i]){
                    team2[t2++] = i;
                }
            }
            for (int k = 0; k < n / 2; k++){
                for (int l = 0; l < n /2; l++){
                    sum1 = sum1 + arr[team1[k]][team1[l]];
                    sum2 = sum2 + arr[team2[k]][team2[l]];
                }
            }
            int diff = Math.abs(sum1 - sum2);
            if (min == -1 || min > diff)
                min  = diff;

        } else {
            for (int j = 1; j < n; j++){
                if(!isUsed[j] && team1[t1] < j ){
                    isUsed[j] = true;
                    team1[++t1] = j;
                    cal(idx + 1);
                    isUsed[j] = false;
                    t1--;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        n = Integer.parseInt(s);
        isUsed = new boolean[n];
        isUsed[0] = true;
        number = new int[n];
        team1 = new int[n / 2];
        team2 = new int[n / 2];
        arr = new int[n][n];
        t1 = 0;

        for (int i = 0; i < n; i++){
            s = br.readLine();
            st = new StringTokenizer(s);
            for (int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            number[i] = i;
        }

        cal(1);

        br.close();

        System.out.println(min);
    }
}
