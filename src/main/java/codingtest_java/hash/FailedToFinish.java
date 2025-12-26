package codingtest_java.hash;

import java.util.*;

class FailedToFinish {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        // 참가자 명단 getOrDefault 함수를 통해 카운트
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        // 완주자를 명단에서 꺼내고 map.get(c) -> 카운트 감소 ( -1 )
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }

        // value가 1 남아있는 사람이 완주 못한 사람
        // map.keySet()을 통해 iterable인 key값을 꺼내어 향상된 for문
        for (String key : map.keySet()) {
            // key값 0이 아닐 경우(해당 멤버는 완주하지 못한 것)
            // 그 멤버(key)를 return
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
