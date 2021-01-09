import java.util.HashMap;

public class A {
    public static void main(String[] args) {
        var map = new HashMap<String,Integer>(){{//초기값 지정
            put("사과", 1);
            put("바나나", 2);
            put("사과", 4);
        }};

        System.out.println("map = " + map);
        map.computeIfAbsent("감", key -> 40);
        System.out.println("map = " + map);
        map.computeIfPresent("감", (key, value) -> value + 2000);
        System.out.println("map = " + map);


        System.out.println();
        System.out.println("map.put(\"사과\", 10) = " + map.put("사과", 10));
        System.out.println("map = " + map + "\n");
        System.out.println("map.putIfAbsent(\"사과\", 50) = " + map.putIfAbsent("사과", 50));
        System.out.println("map = " + map);
    }
}
