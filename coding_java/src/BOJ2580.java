/*
    no.2580 : Sudoku
    hint : 스도쿠의 빈 칸이 존재하면, 그 인덱스를 배열에 저장.
        그 자리에 숫자를 놓아보고 놓을 수 있는지(가로, 세로, 3x3) 검사 후
        놓을 수 있으면 다음 빈 칸 검사, 놓을 수 없으면 backtracking
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2580 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int[] arr = new int[81];     // sudoku input array
    static int[] arr_0 = new int[82];   // array of location 0
    static String s;

    static void sudoku(int i) throws IOException{
        int idx = arr_0[i];

        if (idx == -1){
            for(int a = 0; a < 81; a++){
                bw.write(arr[a] + " ");
                if ((a + 1) % 9 == 0)
                    bw.write("\n");
            }
            bw.flush();
            br.close();
            bw.close();

            System.exit(0);
        } else {
            for (int k = 1; k < 10; k++){   // put num in until it can be possible
                arr[idx] = k;

                if(isPossible(idx)){
                    sudoku(i + 1);
                }
                arr[idx] = 0;
            }
        }
    }
    static boolean isPossible(int idx){

        int row = idx / 9;
        int col = idx % 9;
        for (int k = 0; k < 9; k++){
            // check same col
            if (arr[9*k + col] != 0 && arr[idx] == arr[9*k + col] && idx != (9*k + col)){
//                System.out.println("collapse in col");
                return false;
            }
            // check same row
            if (arr[9*row + k] != 0 && arr[idx] == arr[9*row + k] && idx != (9*row + k)){
//                System.out.println("collapse in row");
                return false;
            }
        }
        // check 3x3 square
        int startI = (idx / 27) * 27 + ((idx % 9) / 3) * 3; // find block's first index
        for (int k = startI; k <= startI + 18; k += 9){
            for (int l = 0; l < 3; l++){
                if(arr[k+l] != 0 && arr[idx] == arr[k+l] && idx != k+l){
//                    System.out.println("collapse in 3x3");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int idx = 0;
        int k = 0;
        int si = 0;

        Arrays.fill(arr_0, -1);

        for(int i = 0; i < 9; i++){
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            for(int j = 0; j < 9; j++){
                si = Integer.parseInt(st.nextToken());
                arr[idx++] = si;
                if (si == 0)
                    arr_0[k++] = idx - 1;
            }
        }

        sudoku(0);

        br.close();

    }
}
