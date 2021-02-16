import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class VisitLength {
    public int solution(String dirs) {
        HashSet<String> set = new HashSet<>();
        int cx = 0, cy = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            int nx = cx;
            int ny = cy;

            if (c == 'U' && cy + 1 <= 5) {
                cy++;
            } else if (c == 'D' && cy - 1 >= -5) {
                cy--;
            } else if (c == 'L' && cx + 1 <= 5) {
                cx++;
            } else if (c == 'R' && cx - 1 >= -5) {
                cx--;
            }

            if (nx == cx && ny == cy) {
                continue;
            }

            set.add(cx + "" + cy + "" + nx + "" + ny);
            set.add(nx + "" + ny + "" + cx + "" + cy);
        }

        return set.size() / 2;
    }


    @Test
    public void test() {
        Assertions.assertEquals(7, solution("ULURRDLLU"));
        Assertions.assertEquals(7, solution("LULLLLLU"));
    }
}
