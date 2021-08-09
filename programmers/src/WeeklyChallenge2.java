import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeeklyChallenge2 {
	public String solution(int[][] scores) {
		int peopleCnt = scores.length;
		int[][] arr = new int[3][peopleCnt];    //0:min, 1:max, 2:sum
		Arrays.fill(arr[0], Integer.MAX_VALUE);
		Arrays.fill(arr[1], Integer.MIN_VALUE);

		for (int i = 0; i < peopleCnt; i++) {
			for (int j = 0; j < peopleCnt; j++) {
				if (i == j) {
					continue;
				}

				arr[0][j] = Math.min(arr[0][j], scores[i][j]);
				arr[1][j] = Math.max(arr[1][j], scores[i][j]);
				arr[2][j] += scores[i][j];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < peopleCnt; i++) {
			int myScore = scores[i][i];
			// 최저점 <= 자기 자신을 평가한 점수 <= 최고점 이면
			if (arr[0][i] <= myScore && myScore <= arr[1][i]) {
				arr[2][i] += myScore;
				sb.append(makeAnswer(arr[2][i], peopleCnt));
			} else {
				sb.append(makeAnswer(arr[2][i], peopleCnt - 1));
			}
		}

		return sb.toString();
	}

	static String makeAnswer(int sum, int people) {
		int mod = sum / people;

		if (mod >= 90) {
			return "A";
		} else if (mod >= 80) {
			return "B";
		} else if (mod >= 70) {
			return "C";
		} else if (mod >= 50) {
			return "D";
		} else {
			return "F";
		}
	}

	@Test
	void test() {
		assertEquals("FBABD", solution(new int[][]{{100, 90, 98, 88, 65}, {50, 45, 99, 85, 77}, {47, 88, 95, 80, 67}, {61, 57, 100, 80, 65}, {24, 90, 94, 75, 65}}));
		assertEquals("DA", solution(new int[][]{{50, 90}, {50, 87}}));
		assertEquals("CFD", solution(new int[][]{{70, 49, 90}, {68, 50, 38}, {73, 31, 100}}));
	}
}
