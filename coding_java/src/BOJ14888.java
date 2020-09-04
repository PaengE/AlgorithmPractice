import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    no.14888 : 연산자 끼워넣기
 */

public class BOJ14888 {
    static int n, min, max;
    static int[] arr, op;
    static BufferedReader br;
    static String s;
    static StringTokenizer st;

    public static void cal(int res, int idx){
        if (idx == n - 1){
            if(res < min)
                min = res;
            if(res > max)
                max = res;
        }
        for(int j = 0; j < 4; j++){
            if(op[j] != 0){
                op[j]--;
                if (j == 0){
                    cal(res + arr[idx+1], idx+1);
                } else if (j == 1){
                    cal(res - arr[idx+1], idx+1);
                } else if (j == 2){
                    cal(res * arr[idx+1], idx+1);
                } else {
                    cal(res / arr[idx+1], idx+1);
                }
                op[j]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        n = Integer.parseInt(s);
        arr = new int[n];

        s = br.readLine();
        st = new StringTokenizer(s);
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        s = br.readLine();
        st = new StringTokenizer(s);
        for(int i = 0; i < 4; i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        min = 1000000001;
        max = -1000000001;

        cal(arr[0], 0);

        System.out.printf("%d\n%d", max, min);
    }
}
