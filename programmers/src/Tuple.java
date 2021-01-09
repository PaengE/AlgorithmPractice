import java.util.*;

public class Tuple {
    public static void main(String[] args) {

        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";

        Tuple t = new Tuple();
//        t.mySolution(s);
        t.simpleSolution(s);
    }

    public int[] simpleSolution(String s) {
        var set = new HashSet<String>();
        String[] str = s.replaceAll("[\\{\\}]", " ")
                .trim().split(" , ");

        Arrays.sort(str, Comparator.comparingInt(o -> o.length()));

        int[] answer = new int[str.length];
        int idx = 0;
        for (String s1 : str) {
            for (String s2 : s1.split(",")) {
                if (set.add(s2)) {
                    answer[idx++] = Integer.parseInt(s2);
                }
            }
        }

        return answer;
    }

    public int[] mySolution(String s) {

        s = s.substring(1, s.length() - 1)
                .replaceAll("[\\{\\}]", "\"");

        String[] str = s.split("\"");

        Arrays.sort(str, Comparator.comparingInt(o -> o.length()));

        var list = new ArrayList<String[]>();
        for (int i = 1; i < str.length; i++) {
            String[] z = str[i].replace(" ", "")
                    .split(",");

            if (z.length != 0) {
                list.add(z);
            }
        }

        var set = new TreeSet<String>();
        int[] answer = new int[list.size()];
        set.add(list.get(0)[0]);
        answer[0] = Integer.parseInt(list.get(0)[0]);

        for (int i = 1; i < list.size(); i++) {
            String[] t = list.get(i);

            for (int j = 0; j < t.length; j++) {
                if (!set.contains(t[j])) {
                    set.add(t[j]);
                    answer[i] = Integer.parseInt(t[j]);
                }
            }
        }

//        for (int a : answer) {
//            System.out.print(a + " ");
//        }
//        System.out.println();

        return answer;
    }
}
