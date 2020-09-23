import java.io.*;

/**
 * no.1992 : 쿼드트리
 * hint : 4등분 분할 정복
 */
public class BOJ1992 {
    static int[][] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void dq(int startX, int startY, int size) throws IOException {
        int element = arr[startX][startY];
        if(size == 1){
            bw.write(element + "");
        } else {
            boolean check = true;
            for (int i = startX; i < startX + size; i++) {
                for (int j = startY; j < startY + size; j++) {
                    if(arr[i][j] != element){
                        check = false;
                        break;
                    }
                }
                if(!check)
                    break;
            }
            if(check)
                bw.write(element + "");
            else{
                bw.write("(");
                dq(startX, startY, size / 2);
                dq(startX, startY + size / 2, size / 2);
                dq(startX + size / 2 , startY, size / 2);
                dq(startX + size / 2, startY + size / 2, size / 2);
                bw.write(")");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        String[] s;

        for (int i = 0; i < n; i++) {
            s = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        dq(0, 0, n);

        bw.flush();
        br.close();
        bw.close();
    }
}
