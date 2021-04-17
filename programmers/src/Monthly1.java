import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Monthly1 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;

        int size = absolutes.length;
        for (int i = 0; i < size; i++){
            if(signs[i]){
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }
        return answer;
    }

    @Test
    public void test() {
        Assertions.assertEquals(9, solution(new int[]{4, 7, 12}, new boolean[]{true, false, true}));
        Assertions.assertEquals(0, solution(new int[]{1, 2, 3}, new boolean[]{false, false, true}));
    }
}