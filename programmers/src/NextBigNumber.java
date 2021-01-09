public class NextBigNumber {
    public static void main(String[] args) {
        int n = 15;
        NextBigNumber nbn = new NextBigNumber();
        System.out.println(nbn.solution(n));
    }

    public int solution(int n) {
        int count = Integer.bitCount(n);

        for (int i = n + 1; ; i++) {
            if (count == Integer.bitCount(i)) {
                return i;
            }
        }
    }
}
