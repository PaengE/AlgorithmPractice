import java.util.ArrayList;
import java.util.HashMap;

public class OpenChattingRoom {
    public static void main(String[] args) {
        OpenChattingRoom ocr = new OpenChattingRoom();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        ocr.solution(record);
    }

    public String[] solution(String[] record) {
        var hm = new HashMap<String, String>();
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        for (String s : record) {
            String[] t = s.split(" ");

            if (!t[0].equals("Leave")) {
                hm.put(t[1], t[2]);
            }

            if (t[0].equals("Enter") || t[0].equals("Leave")) {
                list.add(new ArrayList<>(){{
                    add(t[0]);
                    add(t[1]);
                }});
            }
        }

        String[] answer = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = hm.get(list.get(i).get(1)) + "님이 ";
            if (list.get(i).get(0).equals("Enter")) {
                answer[i] += "들어왔습니다.";
            } else {
                answer[i] += "나갔습니다.";
            }
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
        return answer;
    }
}
