import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TravelRoute {
    static String ans;
    static boolean[] visited;
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        ans = "";
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));

        dfs("ICN", 0, "ICN ", tickets);
        return ans.split(" ");
    }

    private void dfs(String cur, int count, String res, String[][] tickets) {
        if (count == tickets.length) {
            if (!ans.equals("")) {
                return;
            }
            ans = res.substring(0, res.length() - 1);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (cur.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], count + 1, res + tickets[i][1] + " ", tickets);
                visited[i] = false;
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new String[]{"ICN", "A", "D", "B", "A", "C"}
                , solution(new String[][]{{"ICN", "A"}, {"A", "C"}, {"A", "D"}, {"D", "B"}, {"B", "A"}}));
        Assertions.assertArrayEquals(new String[]{"ICN", "JFK", "HND", "IAD"}
                , solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}));
        Assertions.assertArrayEquals(new String[]{"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"}
                , solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}));

    }

}
