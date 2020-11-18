import java.util.Arrays;

public class Level1_1 {
    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};

        int[] tmp = new int[1000001];
        Arrays.fill(tmp, -1);

        tmp[0] = arr[0];
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (tmp[idx] != arr[i]) {
                tmp[++idx] = arr[i];
            }
        }
        int[] answer = new int[idx + 1];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = tmp[i];
            System.out.println(answer[i]);
        }
    }
}
