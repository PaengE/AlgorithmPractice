import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HighestAndLowestRankingInLotto {
	public int[] solution(int[] lottos, int[] win_nums) {
		int same = 0;
		int zero = 0;
		for (int i = 0; i < 6; i++) {
			if (lottos[i] == 0) {
				zero++;
				continue;
			}

			for (int j = 0; j < 6; j++) {
				if (lottos[i] == win_nums[j]) {
					same++;
					break;
				}
			}
		}

		int highRating = same + zero >= 2 ? 7 - (same + zero) : 6;
		int lowRating = same >= 2 ? 7 - same : 6;
		return new int[]{highRating, lowRating};
	}

	@Test
	void test() {
		assertArrayEquals(new int[]{3, 5}, solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19}));
		assertArrayEquals(new int[]{1, 6}, solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25}));
		assertArrayEquals(new int[]{1, 1}, solution(new int[]{45, 4, 35, 20, 3, 9}, new int[]{20, 9, 3, 45, 4, 35}));
	}
}
