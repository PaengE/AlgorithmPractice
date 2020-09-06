import java.io.*;
import java.util.StringTokenizer;

public class BOJ1149 {
    static int[][] arr, resArr;
    static int n;

    public static void dp(){
        resArr[0][0] = arr[0][0];
        resArr[0][1] = arr[0][1];
        resArr[0][2] = arr[0][2];
        for(int i = 1; i < n; i++){
            resArr[i][0] = arr[i][0] + Math.min(resArr[i-1][1], resArr[i-1][2]);
            resArr[i][1] = arr[i][1] + Math.min(resArr[i-1][0], resArr[i-1][2]);
            resArr[i][2] = arr[i][2] + Math.min(resArr[i-1][0], resArr[i-1][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        resArr = new int[n][3];

        for(int i = 0; i < n; i++){
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            for(int j = 0; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp();

        bw.write(Math.min(Math.min(resArr[n-1][0], resArr[n-1][1]), resArr[n-1][2]) + "");
        bw.flush();

        br.close();
        bw.close();

    }
}
