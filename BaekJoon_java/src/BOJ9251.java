import java.io.*;
/*
    no.9251 : LCS(Longest Common Subsequence, 최장 공통 부분수열) 문제
    hint : D[i][j] = { D[i-1][j-1] + 1              if X[i] == Y[j]
                     { max(D[i][j-1], D[i-1][j]     if X[i] != Y[j]
 */

public class BOJ9251 {
    public static void dp(String s1, String s2) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i = 0; i < s1.length(); i++){
            for(int j = 0; j < s2.length(); j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        bw.write(dp[s1.length()][s2.length()] + "");
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        dp(s1, s2);

        br.close();
    }
}
