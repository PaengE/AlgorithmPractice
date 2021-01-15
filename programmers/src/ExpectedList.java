public class ExpectedList {
    public static void main(String[] args) {
        ExpectedList el = new ExpectedList();
        int n = 8;
        int a = 4;
        int b = 7;

        System.out.println(el.solution(n, a, b));

    }

    public int solution(int n, int a, int b) {
        int count = 1;

        while ((a + 1) / 2 != (b + 1) / 2) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            count++;
        }

        return count;
    }
}
