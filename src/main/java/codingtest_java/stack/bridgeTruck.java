package codingtest_java.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class bridgeTruck {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 1. 대기하는 트럭 Deque 초기화
        Deque<Integer> waiting= new ArrayDeque<>();

        for (int truck : truck_weights) {
            waiting.offer(truck);
        }

        // 2. 대기 -> 다리로 건너게 될 트럭의 deque 초기화 ( 각 인덱스값은 0 으로 초기화 )
        Deque<Integer> bridge = new ArrayDeque<>();
        for (int i=0; i < bridge_length; i++) {
            bridge.offer(0);
        }


        // 3. 대기트럭(waiting)을 while문을 통해 다리 위(bridge)로 보내고
        // bridge deque가 0으로 초기화돼있고 현재 무게와 대기 중인 트럭의 무게를 게산하여
        // 그에 따라 bridge(다리 위의 트럭)의 값을 제거하고 추가시키는 과정에서
        // time 카운팅, 최종적으로 경과하는 시간을 계산하여 return
        int time = 0;
        int currentWeight = 0;

        while (!waiting.isEmpty()) {
            currentWeight -= bridge.poll(); // 다리에서 트럭 하나 내보내기
            // 현재 무게 + 대기중인 트럭무게가 무게제한보다 낮으면 대기트럭 -> 다리에 태우기
            if (currentWeight + waiting.peek() <= weight) {
                // 대기 중인 트럭 다리에 태우기
                // 현재 무게 += 다리에 태운 트럭
                int truck = waiting.poll();
                bridge.offer(truck);
                currentWeight += truck;
            } else {
                // 아닐경우 다리에 0 추가해서
                // 무게제한 이하인 다리 위의 트럭(들)이 다리를 건널 동안 time++;할 수 있게 함
                bridge.offer(0);
            }
            // 다리를 건너는 중이므로 시간 ++;
            time ++;
        }

        // 마지막 트럭이 지나가는 시간 계산
        time += bridge_length;
        return time;

    }
    // 테스트 코드
    public static void main(String[] args) {
        bridgeTruck sol = new bridgeTruck();

        // 예제 1
        int bridge_length1 = 2;
        int weight1 = 10;
        int[] truck_weights1 = {7, 4, 5, 6};
        System.out.println("예제 1: " + sol.solution(bridge_length1, weight1, truck_weights1)); // 8

        // 예제 2
        int bridge_length2 = 100;
        int weight2 = 100;
        int[] truck_weights2 = {10};
        System.out.println("예제 2: " + sol.solution(bridge_length2, weight2, truck_weights2)); // 101

        // 예제 3
        int bridge_length3 = 100;
        int weight3 = 100;
        int[] truck_weights3 = {10,10,10,10,10,10,10,10,10,10};
        System.out.println("예제 3: " + sol.solution(bridge_length3, weight3, truck_weights3)); // 110
    }

}
