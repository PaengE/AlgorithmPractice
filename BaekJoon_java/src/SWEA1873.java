import java.io.*;
import java.util.StringTokenizer;

/**
 *  Difficulty: D3
 *  URL: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc&categoryId=AV5LyE7KD2ADFAXc&categoryType=CODE&problemTitle=1873&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */

public class SWEA1873 {
    static int h, w, n, curR, curC;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];

            for (int i = 0; i < h; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = tmp.charAt(j);
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '>' || map[i][j] == '<') {
                        curR = i;
                        curC = j;
                    }
                }
            }

            n = Integer.parseInt(br.readLine());
            String command = br.readLine();

            for (int i = 0; i < n; i++) {
                operation(command.charAt(i));
            }
            print(tc);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void operation(char c) {
        if (c == 'U') {
            if (inRange(curR - 1, curC) && map[curR - 1][curC] == '.') {
                map[curR][curC] = '.';
                curR--;
            }
            map[curR][curC] = '^';
        } else if (c == 'D') {
            if (inRange(curR + 1, curC) && map[curR + 1][curC] == '.') {
                map[curR][curC] = '.';
                curR++;
            }
            map[curR][curC] = 'v';
        } else if (c == 'L') {
            if (inRange(curR, curC - 1) && map[curR][curC - 1] == '.') {
                map[curR][curC] = '.';
                curC--;
            }
            map[curR][curC] = '<';
        } else if (c == 'R') {
            if (inRange(curR, curC + 1) && map[curR][curC + 1] == '.') {
                map[curR][curC] = '.';
                curC++;
            }
            map[curR][curC] = '>';
        } else if (c == 'S') {
            shooting(map[curR][curC]);
        }
    }

    static void shooting(char c) {
        if (c == '>') {
            for (int i = curC + 1; i < w; i++) {
                if (map[curR][i] == '*') {
                    map[curR][i] = '.';
                    break;
                }
                if (map[curR][i] == '#') {
                    break;
                }
            }

        } else if (c == '<') {
            for (int i = curC - 1; i >= 0; i--) {
                if (map[curR][i] == '*') {
                    map[curR][i] = '.';
                    break;
                }
                if (map[curR][i] == '#') {
                    break;
                }
            }
        } else if (c == '^') {
            for (int i = curR - 1; i >= 0; i--) {
                if (map[i][curC] == '*') {
                    map[i][curC] = '.';
                    break;
                }
                if (map[i][curC] == '#') {
                    break;
                }
            }
        } else if (c == 'v') {
            for (int i = curR + 1; i < h; i++) {
                if (map[i][curC] == '*') {
                    map[i][curC] = '.';
                    break;
                }
                if (map[i][curC] == '#') {
                    break;
                }
            }
        }
    }

    static boolean inRange(int r, int c) {
        if (r >= 0 && r < h && c >= 0 && c < w) {
            return true;
        } else {
            return false;
        }
    }

    static void print(int tc) {
        sb.append("#" + tc + " ");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
    }

}
