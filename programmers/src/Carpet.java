public class Carpet {
    public static void main(String[] args) {
        int brown = 12;
        int yellow = 4;

        Carpet c = new Carpet();
        for (int a : c.solution(brown, yellow)) {
            System.out.print(a + " ");
        }
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int size = brown + yellow;

        for (int i = 3; i <= size / 3; i++) {
            if (size % i == 0) {
                if ((i - 2) * ((size / i) - 2) == yellow) {
                    answer[0] = size / i;
                    answer[1] = i;
                    break;
                }
            }
        }
        return answer;
    }
}
