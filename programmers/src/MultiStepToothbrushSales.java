import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MultiStepToothbrushSales {
	static HashMap<String, Integer> map;
	static int[] parent, res;
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		init(enroll, referral);
		res = new int[enroll.length];

		for (int i = 0; i < seller.length; i++) {
			calculateSell(seller[i], amount[i] * 100);
		}

		return res;
	}

	// 판매이익 배분
	static void calculateSell(String seller, int amount) {
		int sellerIdx = map.get(seller);

		while (sellerIdx != -1) {
			int tenPercent = amount / 10;
			if (tenPercent < 1) {
				res[sellerIdx] += amount;
				break;
			} else {
				res[sellerIdx] += (amount - tenPercent);
				amount = tenPercent;
				sellerIdx = parent[sellerIdx];
			}
		}
	}

	// 구조도 초기화
	static void init(String[] enroll, String[] referral) {
		map = new HashMap<>();
		int idx = 0;
		parent = new int[enroll.length];
		Arrays.fill(parent, -1);

		// <판매자, 번호> 인덱싱
		for (int i = 0; i < enroll.length; i++) {
			map.put(enroll[i], idx++);
		}

		// 판매자 i를 추천해준 사람을 저장
		for (int i = 0; i < referral.length; i++) {
			if (referral[i].equals("-")) {
				continue;
			}
			parent[i] = map.get(referral[i]);
		}
	}

	@Test
	void test() {
		assertArrayEquals(new int[]{360, 958, 108, 0, 450, 18, 180, 1080},
				solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
						, new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
						, new String[]{"young", "john", "tod", "emily", "mary"}
						, new int[]{12, 4, 2, 5, 10}));
		assertArrayEquals(new int[]{0, 110, 378, 180, 270, 450, 0, 0},
				solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
						, new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
						, new String[]{"sam", "emily", "jaimie", "edward"}
						, new int[]{2, 3, 5, 4}));
	}
}
