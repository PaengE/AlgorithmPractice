import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    no.1931 : 회의실 배정
    hint : 회의가 끝나는 시점을 기준으로 정렬 후 시작시간과 종료시점을 비교
 */

public class BOJ1931 {
    private static void greedy(int n, int[][] arr){
        int count = 0;
        int endTime = 0;

        for(int i = 0; i < n; i++){
            if(arr[i][0] >= endTime){
                count++;
                endTime = arr[i][1];
            }
        }
        System.out.println(count);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        StringTokenizer st;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(arr, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        greedy(n, arr);
    }
}
