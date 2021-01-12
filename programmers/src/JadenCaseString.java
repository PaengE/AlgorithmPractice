public class JadenCaseString {
    public static void main(String[] args) {
        JadenCaseString jcs = new JadenCaseString();

        String s = "for  the last week";

        System.out.println(jcs.solution(s));

    }

    public String solution(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();

        sb.append(String.valueOf(s.charAt(0)).toUpperCase());
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == ' ') {
                sb.append(String.valueOf(s.charAt(i)).toUpperCase());
            } else {
                sb.append(String.valueOf(s.charAt(i)));
            }
        }
        return sb.toString();
    }


//    연속된 공백을 못잡는 코드
//    public String solution(String s) {
//        String[] str = s.toLowerCase().split("[ ]");
//        for (int i = 0; i < str.length; i++) {
//            System.out.println("str[i] = " + str[i]);
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (String t : str) {
//            String c = String.valueOf(t.charAt(0)).toUpperCase();
//            sb.append(c).append(t.substring(1, t.length()) + " ");
//        }
//
//        return sb.substring(0, sb.length() - 1).toString();
//    }
}
