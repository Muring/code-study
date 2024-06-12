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
    }
    /**
     * 모든 트럭이 다리가 견딜 수 있는 무게를 고려하여 건너는데 걸린 최소 시간 구하기
     * @param bridgeLength 다리 길이
     * @param bridgeLimitWeight    다리가 견딜 수 있는 무게
     * @param truckWeightList 각 트럭의 무게 배열
     * @return 트럭이 다리를 모두 건너는데 걸린 최소 시간
     */
    public static int solution(int bridgeLength, int bridgeLimitWeight, int[] truckWeightList) {
        int answer = 0;
        Queue<Truck> bridgeQueue = new LinkedList<>();    // 다리에 올라오는 트럭을 저장할 큐
        int currentBridgeWeight = 0;    // 현재 다리의 무게

        // 먼저 다리에 첫 트럭 올리기
        int truckIdx = 0;
        bridgeQueue.offer(new Truck(truckWeightList[truckIdx]));    // 다리에 트럭을 올리고
        currentBridgeWeight += truckWeightList[truckIdx];   // 현재 다리의 무게를 증가시킨다.
        truckIdx++;
        answer++;

        while(!bridgeQueue.isEmpty()) {
            // 현재 다리의 상태를 파악하여 트럭이 지나갈지, 올라올지를 결정한다.
            // 다리의 끝에 도달한 트럭 제거
            if(bridgeQueue.peek().position == bridgeLength) {
                currentBridgeWeight -= bridgeQueue.peek().weight;   // 다리에서 트럭의 무게 제거
                bridgeQueue.poll();                                 // 트럭 제거
            }
            // 다리 위에 올려져있는 트럭들을 한 칸 전진
            for(Truck truck : bridgeQueue) {
                truck.position++;
            }
            answer++;

            // 만약 남은 트럭이 있고, 다리에 여유 무게가 있고, 다음 트럭의 무게도 감당할 수 있다면
            if(truckIdx < truckWeightList.length && currentBridgeWeight + truckWeightList[truckIdx] <= bridgeLimitWeight) {
                bridgeQueue.offer(new Truck(truckWeightList[truckIdx])); // 다리에 새로운 트럭을 올리고
                currentBridgeWeight += truckWeightList[truckIdx];   // 현재 다리의 무게를 증가시킨다.
                truckIdx++;
            }
        }
        return answer;
    }
}