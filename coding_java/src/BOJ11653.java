import java.io.*;
/*
    no.11653 : 소인수분해
 */

public class BOJ11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Integer.parseInt(br.readLine());

        for(int i = 2; i <= n; i++){
            if(n != 1){
                while(n % i == 0){
                    n /= i;
                    bw.write(i +"\n");
                }
            } else {
                break;
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
