package codingtest_java.greedy;

import java.util.*;

class GymClothes {
    public int solution(int n, int[] lost, int[] reserve) {

        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for(int l : lost ) lostSet.add(l);
        for(int r : reserve ) reserveSet.add(r);


        // 1. 여벌의 옷이 있는데 도난 당한경우

        for(int r : reserve ) {
            if (lostSet.contains(r)) {
                lostSet.remove(r);
                reserveSet.remove(r);


            }
        }
        /* 아래처럼 lost로 잡고 해도 무방
        for (int l : lost) {
            if(reserveSet.contains(l)) {
                lostSet.remove(l);
                reserveSet.remove(l);
            }
        }
        */
        // 2. 도난 학생에게 빌려주기 (그리디)

        Arrays.sort(lost);

        for(int l : lost) {
            // lostSet에선
            if (!lostSet.contains(l)) continue;

            if (reserveSet.contains(l-1)) {
                lostSet.remove(l);
                reserveSet.remove(l-1);

            } else if (reserveSet.contains(l+1))  {
                lostSet.remove(l);
                reserveSet.remove(l+1);
            }
        }


        return n - lostSet.size();
    }

    public static void main(String[] args) {
        GymClothes gc = new GymClothes();

// 기본 예제
        System.out.println(gc.solution(5, new int[]{2,4}, new int[]{1,3,5}));  // 5
        System.out.println(gc.solution(5, new int[]{2,4}, new int[]{3}));       // 4
        System.out.println(gc.solution(3, new int[]{3}, new int[]{1}));         // 2

// 교집합 (여벌 있는데 도난)
        System.out.println(gc.solution(3, new int[]{1}, new int[]{1}));         // 2
        System.out.println(gc.solution(5, new int[]{2,3}, new int[]{3,4}));     // 4

// 정렬 관련 (이번에 발견한 버그)
        System.out.println(gc.solution(5, new int[]{3,1}, new int[]{2,4}));     // 5

// 경계값
        System.out.println(gc.solution(2, new int[]{1}, new int[]{2}));         // 2
        System.out.println(gc.solution(2, new int[]{1,2}, new int[]{1,2}));     // 2

// 아무도 못 빌리는 경우
        System.out.println(gc.solution(5, new int[]{1,3,5}, new int[]{2,4}));   // 5
        System.out.println(gc.solution(4, new int[]{1,4}, new int[]{2,3}));     // 4
    }
}