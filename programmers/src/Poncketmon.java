import java.util.Arrays;
import java.util.HashSet;

public class Poncketmon {
    public static void main(String[] args) {
        int[] number = {3,1,2,3};

        Poncketmon p = new Poncketmon();
        System.out.println(p.solution(number));

    }

    public int solution(int[] number) {
        Integer b[] = Arrays.stream(number).boxed().toArray(Integer[]::new);
        var set = new HashSet<Integer>(Arrays.asList(b));

        if (set.size() < number.length / 2) {
            return set.size();
        } else {
            return number.length / 2;
        }
    }
}
