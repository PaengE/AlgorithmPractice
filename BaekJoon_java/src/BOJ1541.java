import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    no.1541 : 잃어버린 괄호
    hint : 처음 나오는 -를 기준으로, 앞은 다 +일 것이고
           뒤는 괄호를 적절히 쳐서 다 -로 계산할 수 있다
           1. -가 맨 처음 나오는 경우
           2. -가 없는 경우
           3. -가 중간에 위치하는 경우
 */

public class BOJ1541 {
    private static void greedy(String s){
        int idxOfFirstMinus = s.indexOf("-");
        if(idxOfFirstMinus == -1)   // -가 없는 경우
            idxOfFirstMinus = s.length();

        int plus = 0, minus = 0;

        // 처음 나오는 -를 기준으로 두개의 문자열로 분리
        String s1 = s.substring(0, idxOfFirstMinus);
        String s2 = s.substring(idxOfFirstMinus);

        String[] str1 = s1.split("[-+]");
        String[] str2 = s2.split("[-+]");

        for(int i = 0; i < str1.length; i++){
            if(!str1[i].equals(""))
                plus = plus + Integer.parseInt(str1[i]);
        }
        for(int i = 0; i < str2.length; i++){
            if(!str2[i].equals(""))
                minus = minus + Integer.parseInt(str2[i]);
        }
        System.out.println((plus-minus));

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        br.close();
        greedy(s);
    }
}
