import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  No.1107: 리모컨
 *  URL: https://www.acmicpc.net/problem/1107
 *  Hint: BruteForce
 */

public class BOJ1107 {
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int channel = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        if (m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            while (m-- > 0) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        int min = Math.abs(channel - 100);  // 초기값: +- 클릭으로만 목표채널을 만들 때의 클릭 수

        for (int i = 0; i <= channel * 2 + 100; i++) {  // 최소 100까지는 돌아야함
            int click = isPossibleNumber(i);
            if (click > 0) {
                min = Math.min(min, click + Math.abs(channel - i));
            }
        }

        bw.write(String.valueOf(min));
        bw.close();
        br.close();
    }


    // 리모컨의 숫자만으로 num 채널을 만들 수 있는지 판단
    static int isPossibleNumber(int num) {
        if (num == 0) {
            return list.contains(0) ? 0 : 1;
        }

        int click = 0;
        while (num > 0) {
            if (list.contains(num % 10)) {
                return 0;
            }

            click++;
            num /= 10;
        }
        return click;
    }
}
