import java.io.*;

/**
 * no.11401 : 이항계수 3
 * title : 분할 정복을 사용한 거듭제곱과 페르마의 소정리를 이용해 곱셈의 역원을 구하는 문제
 * hint : 페르마의 소정리 -> p가 소수이고 a와 p가 서로소이면, a^p-1 ≡ 1 (mod p)가 성립함
 * 구하고자 하는 식 : (AB^-1) % p, 페르마의 소정리 B^p-1 & p = 1 을 이용하여
 * (AB^-1) % p = (AB^-1 * 1) % p
 *             = (AB^-1 * B^p-1) % p
 *             = (AB^p-2) % p
 *             = ((A % p)(B^p-2 % p)) % p
 */

public class BOJ11401 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        long p = 1000000007;

        long[] factorial = new long[n+1];
        factorial[0] = 1;
        factorial[1] = 1;
        // factorial 구하기
        for(int i=2; i<=n; i++) factorial[i] = (factorial[i-1]*i)%p;
        long denominator = (factorial[k]*factorial[n-k])%p;

        // fermatNum(denominator 의 K-2 제곱 구하기)
        long index = p-2;
        long fermatNum = 1;
        while(index > 0){
            if(index%2 == 1){
                fermatNum *= denominator;
                fermatNum %= p;
            }
            denominator = (denominator * denominator) % p;
            index /= 2;
        }
        long result = ((factorial[n]%p) * (fermatNum%p)) % p;

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
