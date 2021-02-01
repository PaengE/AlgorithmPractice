import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> indexHm = new HashMap<>();     // 장르 - 넘버링 매핑
        HashMap<String, Integer> playtimeHm = new HashMap<>();  // 장르의 총 플레이타임 계산
        int size = genres.length;
        int[][] songTime = new int[size][3];    // 0: 장르, 1: 곡의 플레이타임, 2: 곡의 순서

        int idx = 0;
        for (int i = 0; i < size; i++) {
            String song = genres[i];
            Integer playtime = plays[i];

            // indexHm에 key로 해당 장르가 없으면 인덱스 추가
            indexHm.putIfAbsent(song, idx++);
            // playtimeHm에 같은 장르의 총 playtime을 갱신
            playtimeHm.computeIfPresent(song, (key, value) -> value + playtime);
            playtimeHm.putIfAbsent(song, playtime);

            // 해당 곡의 정보저장
            songTime[i][0] = indexHm.get(song);
            songTime[i][1] = playtime;
            songTime[i][2] = i;
        }

        // 플레이타임을 기준으로 내림차순 정렬(같으면 곡의 번호로 오름차순 정렬)
        Arrays.sort(songTime, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });

        // 플레이타임 기준 장르를 내림차순 정렬
        ArrayList<String> keySetList = new ArrayList<>(playtimeHm.keySet());
        Collections.sort(keySetList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return playtimeHm.get(o2) - playtimeHm.get(o1);
            }
        });

        // 완전탐색
        ArrayList<Integer> ans = new ArrayList<>();
        for (String genre : keySetList) {
            int index = indexHm.get(genre);
            int count = 0;

            for (int i = 0; i < songTime.length; i++) {
                if (index == songTime[i][0]) {
                    ans.add(songTime[i][2]);
                    count++;
                    if (count == 2) {
                        break;
                    }
                }
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void test() {
        Assertions.assertArrayEquals(new int[]{4, 1, 3, 0}
                , solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }
}
