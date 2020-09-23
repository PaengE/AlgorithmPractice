import java.io.*;

/**
 * no.1780 : 종이의 개수
 * hint : 9등분 분할 정복
 */

public class BOJ1780 {
    static int arr[][];
    static int countMinus, countZero, countPlus;

    public static void dq(int startX, int startY, int size){
        int element = arr[startX][startY];
        if(size == 1){
            addNum(element);
        } else {
            boolean check = true;
            for (int i = startX; i < startX + size; i++) {
                for (int j = startY; j < startY + size; j++) {
                    if(element != arr[i][j]){
                        check = false;
                        break;
                    }
                }
                if(!check)
                    break;
            }
            if(check){
                addNum(element);
            } else {
                dq(startX, startY, size / 3);
                dq(startX + size / 3, startY, size / 3);
                dq(startX + 2 * size / 3, startY, size / 3);
                dq(startX, startY + size / 3, size / 3);
                dq(startX + size / 3, startY + size / 3, size / 3);
                dq(startX + 2 * size / 3, startY + size / 3, size / 3);
                dq(startX, startY + 2 * size / 3, size / 3);
                dq(startX + size / 3, startY + 2 * size / 3, size / 3);
                dq(startX + 2 * size / 3, startY + 2 * size / 3, size / 3);
            }

        }
    }
    public static void addNum(int element){
        switch(element){
            case -1:
                countMinus++;
                break;
            case 0:
                countZero++;
                break;
            case 1:
                countPlus++;
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        String[] s;

        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        dq(0, 0, n);

        bw.write(countMinus + "\n" + countZero + "\n" + countPlus);
        bw.flush();
        bw.close();
        br.close();
    }
}
