import java.io.*;

/**
 * no.2630 : 색종이 만들기
 * hint : 분할정복 사용, 다르면 4개로 쪼개기
 */

public class BOJ2630 {
    static int[][] arr;
    static int countWhite = 0;  // 0
    static int countBlue = 0;   // 1
    public static void dq(int startI, int startJ, int size){
        // 색종이사이즈가 최소 일 때
        if(size == 1){
            if (arr[startI][startJ] == 1){
                countBlue++;
            } else{
                countWhite++;
            }
        }
        // 색종이사이즈가 최소보다 클 때
        else{
            boolean check = true;
            int element = arr[startI][startJ];

            // 일정 크기의 색종이 안에 다른 색이 존재하는지 판별
            for (int i = startI; i < startI + size; i++) {
                for (int j = startJ; j < startJ + size; j++) {
                    if(element != arr[i][j]){
                        check = false;
                        break;
                    }
                }
                if(!check)
                    break;
            }
            // 다른 색이 존재하지 않다면
            if(check){
                if (element == 1){
                    countBlue++;
                } else {
                    countWhite++;
                }
            }
            // 다른 색이 존재 한다면 4등분으로 나눠서 다시 검사
            else {
                dq(startI, startJ, size / 2);   // 2 사분면
                dq(startI, startJ + size / 2, size / 2); // 1 사분면
                dq(startI + size / 2, startJ, size / 2); // 3 사분면
                dq(startI + size / 2, startJ + size / 2, size / 2);   // 4 사분면
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        dq(0, 0, n);

        bw.write(countWhite + "\n"+ countBlue);
        bw.flush();
        br.close();
        bw.close();

    }
}
