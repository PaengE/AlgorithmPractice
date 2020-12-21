import java.util.ArrayList;

public class FunctionalDevelopment {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] a = solution(progresses, speeds);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] +" ");
        }
    }

    static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();

        int idx = 0;
        while (idx < speeds.length) {
            int count = 0;

            for (int i = idx; i < speeds.length; i++) {
                progresses[i] += speeds[i];
            }

            while (progresses[idx] >= 100) {
                idx += 1;
                count += 1;
                if (idx == speeds.length) {
                    break;
                }
            }

            if (count != 0) {
                list.add(count);
            }
        }

        int[] answer = list.stream().mapToInt(i -> i).toArray();

        return answer;
    }
}
