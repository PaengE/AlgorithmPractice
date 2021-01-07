public class TargetNumber {
    static int count = 0;
    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        TargetNumber t = new TargetNumber();
        System.out.println(t.solution(numbers, target));
    }

    public int solution(int[] numbers, int target) {
        dfs(0, 0, target, numbers);

        return count;
    }

    static void dfs(int x, int index, int target, int[] numbers) {
        if (index == numbers.length) {
            if (x == target) {
                count += 1;
            }
            return;
        }

        dfs(x + numbers[index], index + 1, target, numbers);
        dfs(x - numbers[index], index + 1, target, numbers);
    }
}
