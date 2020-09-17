import java.io.*;
/*
    no.5086 : 배수와 약수
 */

public class BOJ5086 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]);
        int n2 = Integer.parseInt(s[1]);

        while(n1 != 0 && n2 != 0){
            if(n1 % n2 == 0){
                bw.write("multiple\n");
            } else if(n2 % n1 == 0){
                bw.write("factor\n");
            } else {
                bw.write("neither\n");
            }
            s = br.readLine().split(" ");
            n1 = Integer.parseInt(s[0]);
            n2 = Integer.parseInt(s[1]);
        }

        br.close();
        bw.flush();
        bw.close();

    }
}
