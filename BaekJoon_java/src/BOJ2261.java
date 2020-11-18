import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * no.2261 : 가장 가까운 두 점
 * title : 가장 가까운 두 점을 구하는 문제. 분할 정복 or 라인 스위핑(Line sweeping)
 * hint : 라인 스위핑 ...
 */

public class BOJ2261 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];
        StringTokenizer st = null;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // x좌표 1순위, y좌표 2순위로 정한 후 정렬
        Arrays.sort(points, (p1, p2) -> {
            if (p1.x == p2.x)
                return Integer.compare(p1.y, p2.y);
            else
                return Integer.compare(p1.x, p2.x);
        });
        
        // TreeSet 에 들어가는 Point 를 x값 오름차순, y값 오름차순이 되도록
        TreeSet<Point> ts = new TreeSet<>((a,b) -> ((a.y == b.y) ? a.x-b.x : a.y - b.y));

        // 기준 값 설정
        ts.add(points[0]);
        ts.add(points[1]);

        // 기준 값 거리 계산
        long ans = distSquare(points[0], points[1]);
        int start = 0;
        for (int i = 2; i < n; i++) {
            Point curP = points[i];

            // TreeSet 에 있는 점들의 유효성을 검사 (검사할 필요가 있는지)
            while (start < i) {
                Point p = points[start];
                long x = curP.x - p.x;
                if (x * x > ans) {
                    ts.remove(p);
                    start++;
                } else {
                    break;
                }
            }
            
            // y 값의 범위를 구함
            int d = (int) Math.sqrt((double) ans) + 1;
            Point from = new Point(-10001, curP.y - d);
            Point to = new Point(10001, curP.y + d);
            
            // 검사 해볼만 한 위치에 있는 점들만을 고려
            for (Point point: ts.subSet(from, to)) {
                long distance = distSquare(curP, point);
                ans = Math.min(ans, distance);
            }
            ts.add(curP);
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();

    }

    // 거리 제곱을 구하는 메소드
    static long distSquare(Point A, Point B) {
        return (long) ((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
    }
}

// Point 클래스
class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}