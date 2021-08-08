import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeeklyChallenge1 {
	public long solution(int price, int money, int count) {
		long myMoney = (long) money;
		long fee = (long) price * ((long) count * ((long) count + 1) / 2L);
		return myMoney - fee > 0 ? 0 : fee - myMoney;
	}

	@Test
	void test() {
		Assertions.assertEquals(10, solution(3, 20, 4));
	}
}
