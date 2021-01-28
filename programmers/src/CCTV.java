import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CCTV {
    public int solution(int[][] routes) {
        // 진입지점 기준 오름차순
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);

        // 모든 차량이 카메라를 한 번이상 만날려면 놓아야할 범위를 저장
        ArrayList<int[]> list = new ArrayList<>();
        // 첫번째 차량을 list에 추가
        list.add(routes[0]);

        for (int i = 1; i < routes.length; i++) {
            int left = routes[i][0];    // 카메라를 놓아야 할 범위의 왼쪽 끝을 저장 (초기값은 i번째 차량의 진입 지점)
            int right = routes[i][1];   // 카메라를 놓아야 할 범위의 오른쪽 끝을 저장 (초기값은 i번째 차량이 나간 지점)
            int[] willRemove = {};      // 리스트에서 지울 구간을 저장

            // 카메라를 놓아야할 범위와 이미 존재하는 카메라 범위가 겹칠 경우,
            // 이미 존재하는 카메라 범위는 삭제하고, 겹치는 부분만 새로이 list에 추가
            // 카메라를 놓아야할 범위와 이미 존재하는 카메라 범위가 겹치지 않을 경우.
            // 새로 놓아야 할 카메라의 범위만 list에 추가(여기서는 빈 범위({})를 삭제함)
            for (int[] temp : list) {
                // 새로 놓아야 할 카메라 범위의 왼쪽 끝이 이미 존재하는 카메라 범위의 오른쪽 끝보다 작을 때,
                if (left <= temp[1]) {
                    // 새로 놓아야 할 카메라 범위의 오른쪽 끝이 이미 존재하는 카메라의 범위의 오른쪽 끝보다 크면
                    // 두 범위가 겹치므로 새로 놓아야 할 카메라의 오른쪽 끝을 갱신
                    if (right > temp[1]) {
                        right = temp[1];
                    }
                    // 지워야할 카메라 범위를 지정
                    willRemove = temp;
                }
            }
            list.remove(willRemove);
            list.add(new int[]{left, right});
        }

        // 최종 list에 [-14, -13], [-5, -3]이라면
        // -14, -13 중 한 곳과 -5, -4, -3 중 한 곳(총 두곳)에 카메라를 놔야한다는 뜻
        return list.size();
    }

    @Test
    public void test() {
        Assertions.assertEquals(2, solution(new int[][]{{-20, 15}, {-18, -13}, {-14, -5}, {-5, -3}}));
    }
}
