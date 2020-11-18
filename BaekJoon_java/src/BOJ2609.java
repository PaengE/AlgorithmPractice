import java.io.*;
/*
    no.12609 : 최대공약수와 최소공배수
    hint : 두 자연수의 곱 = 최대 공약수(gcd) * 최소 공배수(lcm)
 */

public class BOJ2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");

        int gcd = 1, lcm = 1;
        int num1 = Integer.parseInt(s[0]);
        int num2 = Integer.parseInt(s[1]);

        for (int i = 2; i <= Math.min(num1, num2); i++) {
            if(num1 % i == 0 && num2 % i == 0){
                gcd = i;
            }
        }
        lcm = (num1 * num2) / gcd;

        bw.write(gcd+"\n"+lcm);
        bw.flush();
        br.close();
        bw.close();

    }
}
