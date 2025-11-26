package codingtest_java;

import java.util.*;

class FailedToFinish {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        // 참가자 명단 카운트
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        // 완주자 명단 카운트 감소
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }

        // value가 1 남아있는 사람이 완주 못한 사람
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                return key;
            }
        }

        return ""; // 임시
    }
    // 간단한 테스트용 main
    public static void main(String[] args) {
        FailedToFinish sol = new FailedToFinish();

        String[] participant1 = {"leo", "kiki", "eden"};
        String[] completion1 = {"eden", "kiki"};
        System.out.println(sol.solution(participant1, completion1)); // 출력: leo

        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        System.out.println(sol.solution(participant2, completion2)); // 출력: vinko

        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion3 = {"stanko", "ana", "mislav"};
        System.out.println(sol.solution(participant3, completion3)); // 출력: mislav
    }
}


/*
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        for (int i=0; i<completion.length; i++) {
            if (!participant[i].equals(completion[i]))
                return participant[i];
        }
        return participant[participant.length-1];
    }
}
 */


/*
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        for (String p : participant)
            map.put(p, map.getOrDefault(p, 0) + 1);

        for (String c : completion)
            map.put(c, map.get(c) - 1);

        return map.entrySet().stream()
                .filter(e -> e.getValue() != 0)
                .findFirst()
                .get()
                .getKey();
    }
}
*/
