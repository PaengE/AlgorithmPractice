/*
    no.15651 : N and M(3)
 */
import java.io.*;
import java.util.StringTokenizer;

public class BOJ15651 {

    static int n, m;
    static int[] arr;
    static BufferedReader br;
    static BufferedWriter bw;

    static void solution(int idx) throws IOException{
        if(idx == m){
            for(int i = 0; i < m; i++){
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
            bw.flush();
        } else {
            for (int j = 1; j <= n; j++){
                arr[idx] = j;
                solution(idx + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        solution(0);

        br.close();
        bw.close();
    }
}
