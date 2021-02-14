import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LittleFriendsSacheonseong {
    static ArrayList<Tile> list;
    static int m, n;
    static char[][] b;
    public String solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        b = new char[m][n];
        for (int i = 0; i < board.length; i++) {
            b[i] = board[i].toCharArray();
        }

        initArrayList(b);

        StringBuilder sb = new StringBuilder();
        int i = 0;
        loop:
        for (i = 0; i < list.size(); i += 2) {
            Tile t1 = list.get(i);
            Tile t2 = list.get(i + 1);
            if (canRemove(t1, t2)) {
                sb.append(t1.c);
                list.remove(i);
                list.remove(i);
                b[t1.x][t1.y] = '.';
                b[t2.x][t2.y] = '.';
                i = -2;
                continue loop;
            }
        }
        return i == 0 ? sb.toString() : "IMPOSSIBLE";
    }

    private boolean canRemove(Tile t1, Tile t2) {
        // 두 타일의 y좌표가 같지 않은 이상, t1이 왼쪽
        // y좌표가 같으면 t1이 위쪽
        if (t1.x == t2.x) {   // 가로 일직선 상에 있는 경우
            // 직선 상에 장애물이 없는지 검사
            for (int i = t1.y + 1; i <= t2.y - 1; i++) {
                if (b[t1.x][i] != '.') {
                    return false;
                }
            }
            return true;
        } else if (t1.y == t2.y) {    // 세로 일직선 상에 있는 경우
            // 직선 상에 장애물이 없는지 검사
            for (int i = t1.x + 1; i <= t2.x - 1; i++) {
                if (b[i][t1.y] != '.') {
                    return false;
                }
            }
            return true;
        } else {    // 위의 두 경우가 아닌경우 (좌상우하 와 우상좌하의 2가지 경우를 나눠야함
            boolean check1 = true, check2 = true;

            if (t1.x < t2.x) {  // 두 타일이 좌상, 우하에 있는 경우
                // 우->하 직선경로 검사
                for (int i = t1.y + 1; i <= t2.y; i++) {
                    if (b[t1.x][i] != '.') {
                        check1 = false;
                        break;
                    }
                }
                for (int i = t1.x + 1; i <= t2.x - 1; i++) {
                    if (!check1) {
                        break;
                    }
                    if (b[i][t2.y] != '.') {
                        check1 = false;
                        break;
                    }
                }

                // 하->우 직선경로 검사
                for (int i = t1.x + 1; i <= t2.x; i++) {
                    if (b[i][t1.y] != '.') {
                        check2 = false;
                        break;
                    }
                }
                for (int i = t1.y + 1; i <= t2.y - 1; i++) {
                    if (!check2) {
                        break;
                    }
                    if (b[t2.x][i] != '.') {
                        check2 = false;
                        break;
                    }
                }

            } else {    // 두 타일이 좌하, 우상에 위치해있는 경우
                // 우->상 직선경로 검사
                for (int i = t1.y + 1; i <= t2.y; i++) {
                    if (b[t1.x][i] != '.') {
                        check1 = false;
                        break;
                    }
                }
                for (int i = t1.x; i >= t2.x + 1; i--) {
                    if (!check1) {
                        break;
                    }
                    if (b[i][t2.y] != '.') {
                        check1 = false;
                        break;
                    }
                }
                // 상->우 직선경로 검사
                for (int i = t1.x - 1; i >= t2.x; i--) {
                    if (b[i][t1.y] != '.') {
                        check2 = false;
                        break;
                    }
                }
                for (int i = t1.y + 1; i <= t2.y - 1; i++) {
                    if (!check2) {
                        break;
                    }
                    if (b[t2.x][i] != '.') {
                        check2 = false;
                        break;
                    }
                }
            }

            // 두 경로중 하나라도 가능한 경로라면
            if (check1 || check2) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void initArrayList(char[][] b) {
        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Character c = b[i][j];
                if (Character.isUpperCase(c)) {
                    list.add(new Tile(c, i, j));
                }
            }
        }

        // 알파벳 작은순으로 정렬
        // 알파벳이 같으면 왼쪽에 있는 타일 먼저
        Collections.sort(list, ((o1, o2) -> {
            if (o1.c == o2.c) {
                if (o1.x == o2.x) {
                    return Integer.compare(o1.y, o2.y);
                } else if (o1.y == o2.y) {
                    return Integer.compare(o1.x, o2.x);
                } else {
                    return Integer.compare(o1.y, o2.y);
                }
            }
            return Character.compare(o1.c, o2.c);
        }));
    }

    private class Tile {
        char c;
        int x, y;

        Tile(char c, int x, int y) {
            this.c = c;
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals("ABCD", solution(3, 3, new String[]{"DBA", "C*A", "CDB"}));
        Assertions.assertEquals("RYAN", solution(2, 4, new String[]{"NRYN", "ARYA"}));
        Assertions.assertEquals("MUZI", solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}));
        Assertions.assertEquals("IMPOSSIBLE", solution(2, 2, new String[]{"AB", "BA"}));
    }
}
