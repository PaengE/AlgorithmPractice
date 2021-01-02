import java.util.Arrays;

public class H_Index {
    public static void main(String[] args) {
        int[] citations = {10,9,4,1,1};

        System.out.println(new H_Index().solution(citations));
    }

    public int solution(int[] citations) {
        Arrays.sort(citations);
        int size = citations.length;
        int h_idx = 0;
        int count = 0;

        for (int i = 0; i <= size; i++) {
            count = 0;
            for (int j = 0; j < size; j++) {
                if (i <= citations[j]) {
                    count += 1;
                }
                if (count >= i) {
                    h_idx = i;
                    break;
                }
            }
            if (count < i) {
                break;
            }
        }
        return h_idx;
    }

    /*      테스트케이스와 기댓값

            [12, 11, 10, 9, 8, 1]       5
            [6, 6, 6, 6, 6, 1]          5
            [4, 4, 4]                   3
            [4, 4, 4, 5, 0, 1, 2, 3]    4
            [10, 11, 12, 13]            4
            [3, 0, 6, 1, 5]             3
            [0, 0, 1, 1]                1
            [0, 1]                      1
            [10, 9, 4, 1, 1]            3
     */
}
