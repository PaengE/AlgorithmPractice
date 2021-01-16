public class TheSongJustNow {
    public static void main(String[] args) {
        TheSongJustNow ts = new TheSongJustNow();
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        System.out.println(ts.solution(m, musicinfos));

    }

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        m = removeSharp(m);
        int maxTime = 0;

        for (String s : musicinfos) {
            s = removeSharp(s);
            String[] str = s.split(",");
            String[] startTime = str[0].split(":");
            String[] endTime = str[1].split(":");

            int playHour = Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]);
            int playMinute = Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]) + (playHour * 60);

            String sound = makeSound(str[3], playMinute);

            if (sound.contains(m)) {
                if (playMinute > maxTime) {
                    maxTime = playMinute;
                    answer = str[2];
                }
            }
        }
        return answer;
    }

    static String makeSound(String s, int time) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < time; i++) {
            sb.append(s.charAt(i % s.length()));
        }
        return sb.toString();
    }

    static String removeSharp(String s) {
        s = s.replaceAll("A#", "a");
        s = s.replaceAll("C#", "c");
        s = s.replaceAll("D#", "d");
        s = s.replaceAll("F#", "f");
        s = s.replaceAll("G#", "g");
        return s;
    }
}
