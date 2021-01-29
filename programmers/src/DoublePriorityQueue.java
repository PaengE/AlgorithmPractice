import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DoublePriorityQueue {
    public int[] solution(String[] operations) {
        var list = new ArrayList<Integer>();

        for (String str : operations) {
            String[] s = str.split(" ");
            if (s[0].equals("I")) {
                list.add(Integer.parseInt(s[1]));
            } else {
                if (list.isEmpty()) {
                    continue;
                }
                Collections.sort(list);
                if (s[1].equals("1")) {
                    list.remove(list.size() - 1);
                } else {
                    list.remove(0);
                }
            }
        }

        Collections.sort(list);
        return list.isEmpty() ? new int[]{0, 0} : new int[]{list.get(list.size() - 1), list.get(0)};
    }



    // 중복된 숫자는 처리 불가능
//    public int[] solution(String[] operations) {
//        TreeSet<Integer> set = new TreeSet<>();
//
//        for (String str : operations) {
//            String[] s = str.split(" ");
//            if (s[0].equals("I")) {
//                set.add(Integer.parseInt(s[1]));
//            } else {
//                if (s[1].equals("1")) {
//                    set.pollLast();
//                } else {
//                    set.pollFirst();
//                }
//            }
//        }
//        if (set.isEmpty()) {
//            return new int[]{0, 0};
//        } else {
//            return new int[]{set.last(), set.first()};
//        }
//    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[]{7, 5}, solution(new String[]{"I 7", "I 5", "I -5", "D -1"}));
        Assertions.assertArrayEquals(new int[]{0, 0}, solution(new String[]{"I 16", "D 1"}));
    }
}
