package codingtest_java.greedy;

class Joystick {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();

        // 1. 상하 조작
        for (int i=0; len > i; i++) {
            char target = name.charAt(i);
            int upCount = target - 'A';
            int downCount = 'Z'-target+1;

            answer += Math.min(upCount, downCount);
        }
        // 2. 좌우 이동 *name에서 A가 있을 경우 건너 뛰어야 하므로 이를 고려하여 최수 횟수 계산

        int minMove = len-1; // 기본값 설정(처음에서 오른쪽으로 끝까지 가는 경우)
        for (int i=0; len>i; i++) {
            int next = i+1;

            while ( len > next && name.charAt(next) == 'A') {
                next++;
            }
            // 오른쪽 i까지 갔다가 = i*2
            // 왼쪽으로 돌아가는 경우(len-next)
            int goLight = i*2 + (len-next);
            // 왼쪽 i까지 갔다가 =>(len-next)*2
            // 오른쪽 돌아오는 경우 => i
            int goLeft = (len-next)*2 +i;

            minMove = Math.min(minMove, Math.min(goLight, goLeft));


        }
        answer += minMove;

        return answer;
    }

    // 테스트
    public static void main(String[] args) {
        Joystick sol = new Joystick();

        System.out.println(sol.solution("JEROEN"));  // 56
        System.out.println(sol.solution("JAN"));     // 23
        System.out.println(sol.solution("JAZ"));     // 11
        System.out.println(sol.solution("BBBBAAAABA")); // 12
    }
}

/*
class Solution {
    public int solution(String name) {
        int answer = 0;
        int[] diff={0,1,2,3,4,5,6,7,8,9,10,11,12,13,12,11,10,9,8,7,6,5,4,3,2,1};
        for(char c:name.toCharArray())
            answer+=diff[c-'A'];

        int length=name.length();
        int min=length-1;

        for(int i=0;i<length;i++){
            int next=i+1;
            while(next<length && name.charAt(next)=='A'){
                next++;
            }
            min=Math.min(min,i+length-next+Math.min(i,length-next));
        }

        return answer+min;
    }
}
*/