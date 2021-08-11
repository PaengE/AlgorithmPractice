import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RankingSearch {
	static HashMap<String, ArrayList<Integer>> infoMap = new HashMap<>();
	public int[] solution(String[] info, String[] query) {
		makeInfoMap(info);

		int[] ans = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			ans[i] = findAnswer(query[i]);
		}

		return ans;
	}

	static int findAnswer(String query) {
		// query 전처리
		String[] str = query.replaceAll("and ", "").split(" ");
		int score = Integer.parseInt(str[4]);
		query = query.replaceAll("and|[\\h\\d- ]", "");

		// key에 대한 valueList 가져오기
		ArrayList<Integer> valList = infoMap.getOrDefault(query, new ArrayList<>());
		return valList.size() - lowerBound(valList, score);
	}

	static void makeInfoMap(String[] info) {
		// 2^4 개의 key 쌍 생성
		for (String str : info) {
			String[] split = str.split(" ");

			for (int i = 0; i < (1 << 4); i++) {
				StringBuilder key = new StringBuilder();
				for (int j = 0; j < 4; j++) {
					if ((i & (1 << j)) != 0) {
						key.append(split[j]);
					}
				}
				infoMap.computeIfAbsent(key.toString()
						, s -> new ArrayList<>()).add(Integer.parseInt(split[4]));
			}
		}

		// key의 value 오름차순 정렬
		for (Map.Entry<String, ArrayList<Integer>> entry : infoMap.entrySet()) {
			Collections.sort(entry.getValue());
		}
	}

	static int lowerBound(ArrayList<Integer> list, int target) {
		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = (left + right) / 2;
			if (list.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	@Test
	void test() {
		assertArrayEquals(new int[]{1, 1, 1, 1, 2, 4}
				, solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}
						, new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}));
	}
}
