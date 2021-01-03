import java.util.Arrays;

public class Lifeboat {
    public static void main(String[] args) {
//        int[] people = {70, 50, 80, 50};
        int[] people = {10, 20, 30, 40, 50};
//        int[] people = {70, 80, 50};

        int limit = 100;

        Lifeboat b = new Lifeboat();
        System.out.println(b.solution(people, limit));

    }

    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int count = 0;
        int left = 0;
        int right = people.length - 1;

        int weight = 0;
        while (right - left >= 0) {
            weight = people[left] + people[right--];

            if (weight <= limit) {
                while (weight <= limit) {
                    weight += people[++left];
                }
            }

            count += 1;
        }
        return count;
    }
}
