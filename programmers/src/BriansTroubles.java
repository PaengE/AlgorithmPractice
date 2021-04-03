import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class BriansTroubles {
    static HashSet<Character> set;
    public String solution(String sentence) {
        initSet();

        int length = sentence.length();
        int idx = 0;
        int lastIdx = 0;
        StringBuilder ans = new StringBuilder();
//        System.out.println("sentence = " + sentence);

        if (sentence.indexOf(" ") != -1) {
            return "invalid";
        }

        while (idx < length){
            if (idx == length - 1) {
                if (Character.isUpperCase(sentence.charAt(idx))) {
                    ans.append(sentence.charAt(idx));
                    return ans.toString().trim();
                } else {
                    return "invalid";
                }
            }

            // 첫문자가 소문자일 때
            if (Character.isLowerCase(sentence.charAt(idx))) {
                char c = sentence.charAt(idx);
                // 사용한 소문자이면 invalid
                if (!set.remove(c)) {
                    System.out.println("sshhhsjh");
                    return "invalid";
                }

                // 규칙 2가 적용된 것이므로 무조건 2개여야함
                String s = sentence.substring(idx, length);
                if (countChar(s, c) != 2) {
                    return "invalid";
                }

                lastIdx = sentence.lastIndexOf(c);
                s = sentence.substring(idx + 1, lastIdx);
                // 주어진 단어 rule 1 check
                if (!checkRule1(s)) {
                    return "invalid";
                }

                // ans 에 붙이기
                for (int i = 0; i < s.length(); i++) {
                    if (Character.isUpperCase(s.charAt(i))) {
                        ans.append(s.charAt(i));
                    }
                }

                // 단어의 끝이므로 공백 추가 및 인덱스 재조정
                ans.append(" ");
                idx = lastIdx + 1;
            }
            // 첫문자가 대문자일 때
            else {
                // 소문자가 나올때까지 단어로 취급하고 ans 에 추가
                while (idx + 1 < length && Character.isUpperCase(sentence.charAt(idx + 1))) {
                    ans.append(sentence.charAt(idx) + " ");
                    idx++;

                    // sentence 의 마지막문자 처리
                    if (idx == length - 1) {
                        ans.append(sentence.charAt(idx));
                        return ans.toString();
                    }
                }

                // idx번째 문자는 대문자, idx+1번째 문자는 소문자인 상태
                char c = sentence.charAt(idx + 1);
                // 사용된 소문자면 invalid
                if (!set.remove(c)) {
                    return "invalid";
                }

                lastIdx = sentence.lastIndexOf(c);
                String s = sentence.substring(idx, length);
                int cnt = countChar(s, s.charAt(1));

                // idx+1번째 소문자가 idx~끝 문자열에서 총 2개일경우
                if (cnt == 2) {
                    // 소문자가 붙어있으면 invalid
                    if (lastIdx - idx == 2) {
                        return "invalid";
                    }
                    // idx번째 대문자를 ans에 붙이고 규칙 2를 적용하기 위해 idx 재조정
                    ans.append(s.charAt(0) + " ");
                    idx++;
                }
                // cnt가 1이거나 3이상이면 규칙 1을 검사
                else {

                    // 문자열의 끝이라면
                    if (lastIdx + 1 == length) {
                        return "invalid";
                    }

                    // 아니라면 대소 ~ 소대 부분문자열로 나눈 후 규칙1 검사
                    s = sentence.substring(idx, lastIdx + 2);
                    if (!checkRule1(s)) {
                        return "invalid";
                    }

                    // 규칙1을 만족한다면 대문자들을 ans에 붙임
                    for (int i = 0; i < s.length(); i++) {
                        if (Character.isUpperCase(s.charAt(i))) {
                            ans.append(s.charAt(i));
                        }
                    }
                    // 단어의 끝이므로 공백 추가 및 인덱스 재조정
                    ans.append(" ");
                    idx = lastIdx + 2;
                }
            }
        }
        return ans.toString().trim();
    }

    private boolean checkRule1(String s) {
        // 주어진 문자열이 빈문자열이면 false
        if (s.length() == 0) {
            return false;
        }
        // 1번째 문자가 소문자면 false
        if (Character.isLowerCase(s.charAt(0))) {
            return false;
        }

        // 길이가 2이하이면 모두 대문자여야함.
        if (s.length() <= 2) {
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLowerCase(s.charAt(i))) {
                    return false;
                }
            }
        }
        // 길이가 3이상이라면 대소대소대소대.. 형식이거나, 대대대대 형식이어야함
        else {
            // 2번째 문자가 소문자면 대소대소대 형식이어야 함 (s의 길이가 홀수여야함)
            if (Character.isLowerCase(s.charAt(1))) {
                if (s.length() % 2 != 1) {
                    return false;
                }

                for (int i = 0; i < s.length(); i++) {
                    if (i % 2 == 0 && Character.isLowerCase(s.charAt(i))) {
                        return false;
                    } else if (i % 2 == 1 && s.charAt(1) != s.charAt(i)) {
                        return false;
                    }
                }
            }
            // 2번째 문자가 대문자면 대대대대 형식이어야 함
            else {
                for (int i = 2; i < s.length(); i++) {
                    if (Character.isLowerCase(s.charAt(i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int countChar(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count += 1;
            }
        }
        return count;
    }

    private void initSet() {
        set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            set.add((char) ('a' + i));
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals("invalid", solution("AbAaAbAaCa"));
        Assertions.assertEquals("A BBBB CC D D D", solution("aAaBbBbBbBcCCcDdDdD"));
//        Assertions.assertEquals("HELLO", solution("aHELLOa"));
//        Assertions.assertEquals("A A A A AA", solution("AAAAxAbAx"));
//        Assertions.assertEquals("HELLO", solution("aHbEbLbLbOa"));
//        Assertions.assertEquals("HELLO WORLD", solution("HaEaLaLaObWORLDb"));
//        Assertions.assertEquals("SIGONG J O A", solution("SpIpGpOpNpGJqOqA"));
//        Assertions.assertEquals("A", solution("A"));
//        Assertions.assertEquals("H E L L O W O R L D", solution("HELLOWORLD"));
//        Assertions.assertEquals("HELLO WORLD", solution("aHbEbLbLbOacWdOdRdLdDc"));
//        Assertions.assertEquals("HELL O WORLD", solution("HaEaLaLObWORLDb"));
//        Assertions.assertEquals("A A A", solution("AAA"));
//        Assertions.assertEquals("HELLOWORLD", solution("aHELLOWORLDa"));
//        Assertions.assertEquals("A A A B A BBBB C BBBB C BB GG G G G RRRRRR"
//                , solution("AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR"));
//        Assertions.assertEquals("I A M", solution("aIaAM"));
//        Assertions.assertEquals("AO", solution("bAaOb"));
//        Assertions.assertEquals("invalid", solution("a"));
//        Assertions.assertEquals("invalid", solution("Aa"));
//        Assertions.assertEquals("invalid", solution("aA"));
//        Assertions.assertEquals("invalid", solution("HaEaLaLaOWaOaRaLaD"));
//        Assertions.assertEquals("invalid", solution("abHELLObaWORLD"));
//        Assertions.assertEquals("invalid", solution("aHELLOa bWORLDb"));
//        Assertions.assertEquals("invalid", solution("TxTxTxbAb"));
//        Assertions.assertEquals("invalid", solution("bTxTxTaTxTbkABaCDk"));
//        Assertions.assertEquals("invalid", solution("baHELLOabWORLD"));
//        Assertions.assertEquals("invalid", solution("A B"));
//        Assertions.assertEquals("AAAA BBBB", solution("AxAxAxABcBcBcB"));
//        Assertions.assertEquals("BB A", solution("oBBoA"));
//        Assertions.assertEquals("AAAA", solution("AxAxAxA"));
//        Assertions.assertEquals("HELLO WORLD SIGONG J O A GWFD GWL BB A A A AAAA A"
//                , solution("HaEaLaLaObWORLDbSpIpGpOpNpGJqOqAdGcWcFcDdeGfWfLeoBBoAAAAxAxAxAA"));
//        Assertions.assertEquals("BA DE A E EEEE", solution("aBcAadDeEdvAvlElmEEEEm"));
//        Assertions.assertEquals("A A A B B B", solution("AcAcABaBaB"));
//        Assertions.assertEquals("GWFD GWL", solution("aGbWbFbDakGnWnLk"));
//        Assertions.assertEquals("X XX X", solution("XcXbXcX"));
//        Assertions.assertEquals("invalid", solution("aCaCa"));
//        Assertions.assertEquals("invalid", solution("AxAxAxAoBoBoB"));
//        Assertions.assertEquals("invalid", solution("aaA"));
//        Assertions.assertEquals("invalid", solution("Aaa"));
//        Assertions.assertEquals("invalid", solution("xAaAbAaAx"));
//        Assertions.assertEquals("invalid", solution("AsCsWsQsQsEEEEEEEEeEeEe"));
//        Assertions.assertEquals("A B C D E F GH", solution("ABCaDaEFGbH"));
//        Assertions.assertEquals("A B B B AAA", solution("aAaBBBcAeAeAc"));
        Assertions.assertEquals("A B C DEF H I", solution("ABCbDaEaFbHI"));
        Assertions.assertEquals("invalid", solution("AacacaA"));
        Assertions.assertEquals("invalid", solution("AaBcBcBcBcB"));
        Assertions.assertEquals("invalid", solution("aAAA"));
        Assertions.assertEquals("invalid", solution("AAAa"));
        Assertions.assertEquals("invalid", solution("aAbBBbAa"));
        Assertions.assertEquals("invalid", solution("aAbBBbAa"));
        Assertions.assertEquals("invalid", solution("aAAbBbAAa"));
        Assertions.assertEquals("invalid", solution("aAcAbAbAcAcAcAa"));
        Assertions.assertEquals("invalid", solution("acAcAcAa"));
        Assertions.assertEquals("invalid", solution("aAcAcAca"));
        Assertions.assertEquals("A AAA A", solution("AdAeAeAdA"));
        Assertions.assertEquals("invalid", solution("dAAeAd"));
        Assertions.assertEquals("invalid", solution("dAeAAd"));
        Assertions.assertEquals("ABA", solution("cAbBbAc"));
        Assertions.assertEquals("invalid", solution("AbbA"));
        Assertions.assertEquals("invalid", solution("aAaaBa"));
        Assertions.assertEquals("A B", solution("aAacBc"));
        Assertions.assertEquals("A B", solution("AB"));
        Assertions.assertEquals("A B", solution("AcBc"));
        Assertions.assertEquals("A B", solution("aAaB"));
        Assertions.assertEquals("AAAA BBBB", solution("aAbAbAbAacBdBdBdBc"));
        Assertions.assertEquals("AAAA BBBB", solution("AbAbAbABdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", solution("AbAbAbAcBBBBc"));
        Assertions.assertEquals("AAAA BBBB", solution("aAbAbAbAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", solution("aAbAbAbAacBBBBc"));
        Assertions.assertEquals("AAAA BBBB", solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", solution("aAAAAacBBBBc"));
        Assertions.assertEquals("AAAA BBBB", solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", solution("aAAAAacBdBdBdBc"));
        Assertions.assertEquals("AAAA BBBB", solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("AAAA BBBB", solution("AbAbAbAcBdBdBdBc"));
        Assertions.assertEquals("AAAA BBBB", solution("aAAAAaBdBdBdB"));
        Assertions.assertEquals("IM M M", solution("IaMMbMb"));
        Assertions.assertEquals("invalid", solution("AaAaAabBBb"));
        Assertions.assertEquals("A A AA", solution("AaAaAcA"));
        Assertions.assertEquals("A B", solution("aAabBb"));
        Assertions.assertEquals("B HEE", solution("bBbcHdEdEc"));
        Assertions.assertEquals("AA A", solution("AaAA"));
        Assertions.assertEquals("J OOO A", solution("JaOOOaA"));
        Assertions.assertEquals("J O O O A", solution("aJaOOOcAc"));
        Assertions.assertEquals("I AM", solution("IaAMa"));
        Assertions.assertEquals("I A M", solution("aIaAM"));
        Assertions.assertEquals("SIGONG J OOO A", solution("SpIpGpOpNpGJqOOOqA"));
        Assertions.assertEquals("invalid", solution("AxAxAxAoBoBoB"));
        Assertions.assertEquals("HELLO WORLD", solution("HaEaLaLaOWbObRbLbD"));
        Assertions.assertEquals("AAAA B B B", solution("AxAxAxABoBoB"));
        Assertions.assertEquals("B", solution("aBa"));
        Assertions.assertEquals("invalid", solution("baHELLOabWORLD"));
        Assertions.assertEquals("invalid", solution("aAbAba"));
        Assertions.assertEquals("AO", solution("bAaOb"));
        Assertions.assertEquals("A A A B B B BB", solution("AAAaBaBBBbB"));
    }
}
