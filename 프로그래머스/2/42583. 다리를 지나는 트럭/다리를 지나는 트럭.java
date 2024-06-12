import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // 트럭의 정보를 저장할 트럭 객체
    static class Truck {
        int weight;
        int position;

        public Truck(int weight) {
            this.weight = weight;
            this.position = 1;
        }

        public void move() {
            position++;
        }
    }

    static Queue<Truck> bridgeQueue = new LinkedList<>();    // 다리에 올라오는 트럭을 저장할 큐
    static int currentBridgeWeight = 0; // 현재 다리에 하중된 무게
    static int truckIdx = 0; // 현재 트럭 번호
    static int answer = 0; // 현재까지 걸린 초

    // 다리에 다음 트럭 올리는 메소드
    public static void newTruckToBridge(int weight) {
        bridgeQueue.offer(new Truck(weight));    // 다리에 트럭을 올리고
        currentBridgeWeight += weight;   // 현재 다리의 무게를 증가시킨다.
        truckIdx++; // 다음 트럭 지정
    }

    /**
     * 모든 트럭이 다리가 견딜 수 있는 무게를 고려하여 건너는데 걸린 최소 시간 구하기
     * @param bridgeLength 다리 길이
     * @param bridgeLimitWeight    다리가 견딜 수 있는 무게
     * @param truckWeightList 각 트럭의 무게 배열
     * @return 트럭이 다리를 모두 건너는데 걸린 최소 시간
     */
    public static int solution(int bridgeLength, int bridgeLimitWeight, int[] truckWeightList) {
        // 먼저 다리에 첫 트럭 올리기
        newTruckToBridge(truckWeightList[truckIdx]);
        answer++;   // 초 증가

        while(!bridgeQueue.isEmpty()) {
            // 현재 다리의 상태를 파악하여 트럭이 지나갈지, 올라올지를 결정한다.
            // 다리의 끝에 도달한 트럭 제거
            if(bridgeQueue.peek().position == bridgeLength) {
                currentBridgeWeight -= bridgeQueue.peek().weight;   // 다리에서 트럭의 무게 제거
                bridgeQueue.poll();                                 // 트럭 제거
            }
            // 다리 위에 올려져있는 트럭들을 한 칸 전진
            for(Truck truck : bridgeQueue) {
                truck.move();
            }
            answer++;   // 초 증가

            // 만약 남은 트럭이 있고, 다리에 여유 무게가 있고, 다음 트럭의 무게도 감당할 수 있다면 새로운 트럭을 올린다.
            if(truckIdx < truckWeightList.length && currentBridgeWeight + truckWeightList[truckIdx] <= bridgeLimitWeight) {
                newTruckToBridge(truckWeightList[truckIdx]);
            }
        }
        return answer;
    }
}