import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class WordConversion {
    public int solution(String begin, String target, String[] words) {
        List<String> wordList = Arrays.asList(words);
        // 주어진 words에 target이 없으면 절대 변환 불가능
        if (!wordList.contains(target)) {
            return 0;
        } else {
            return bfs(begin, target, wordList);
        }
    }

    // BFS 수행
    private int bfs(String begin, String target, List<String> wordList) {
        ArrayList<String> list = new ArrayList<>(wordList);
        Queue<String> q = new LinkedList<>();

        list.add(begin);
        int size = list.size();
        boolean[] visited = new boolean[size];
        int[] count = new int[size];

        q.offer(begin);
        count[size - 1] = 0;
        visited[size - 1] = true;
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (cur.equals(target)) {
                return count[list.indexOf(target)];
            }

            for (int i = 0; i < size - 1; i++) {
                String next = list.get(i);
                if (!visited[list.indexOf(next)]) {
                    int diff = diffCount(cur, next);
                    if (diff == 1) {
                        q.offer(next);
                        visited[list.indexOf(next)] = true;
                        count[list.indexOf(next)] = count[list.indexOf(cur)] + 1;
                    }
                }
            }
        }
        return 0;
    }

    // 두 단어 a, b의 각 자리가 다른 것이 몇개인지를 count
    private int diffCount(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        Assertions.assertEquals(0, solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }
}
