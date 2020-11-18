import java.io.*;
/*
    no.1676 : 팩토리얼 0의 개수
    hint : 하위 자리수가 0이려면 소인수로 2, 5를 가져야 함. -> 2x5 = 10이므로
           min(소인수2의개수, 소인수5의개수)가 답.
 */

public class BOJ1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for(int i = 1; i <= n; i++){
            if(i % 125 == 0){
                count += 3;
            } else if(i % 25 == 0){
                count += 2;
            } else if(i % 5 == 0){
                count += 1;
            }
        }

        bw.write(count + "");
        bw.close();
        br.close();
    }
}
