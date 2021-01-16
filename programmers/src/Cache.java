import java.util.*;

public class Cache {
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};

        Cache c = new Cache();
        System.out.println(c.solution(cacheSize, cities));

    }

    public int solution(int cacheSize, String[] cities) {
        var list = new ArrayList<String>();

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int time = 0;
        for (String s : cities) {
            s = s.toLowerCase();
            if (list.contains(s)) {
                list.remove(list.indexOf(s));
                time += 1;
            } else if (list.size() == cacheSize) {
                list.remove(0);
                time += 5;
            } else if (!list.contains(s)) {
                time += 5;
            }
            list.add(s);
        }

        return time;
    }
}
