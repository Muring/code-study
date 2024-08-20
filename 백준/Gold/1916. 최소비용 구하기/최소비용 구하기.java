import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int cityCnt;
    static int busCnt;
    static ArrayList<ArrayList<Bus>> busList;   // 인접 리스트
    static int[] dist;  // 시작점에서 각 정점으로 가는 최단거리
    static boolean[] isVisited; // 방문 확인

    private static class Bus implements Comparable<Bus> {
        int stop;
        int cost;

        public Bus(int stop, int cost) {
            this.stop = stop;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }
    }

    private static int dijkstra(int start, int stop) {
        PriorityQueue<Bus> que = new PriorityQueue<>();
        que.offer(new Bus(start, 0));
        dist[start] = 0;

        while (!que.isEmpty()) {
            // 현재 버스 정보와 정차한 도시 번호를 가져온다.
            Bus currentBus = que.poll();
            int currentCity = currentBus.stop;

            // 만약 아직 현재 도시에 방문한 적이 없다면
            if (!isVisited[currentCity]) {
                isVisited[currentCity] = true;  // 방문처리

                // 버스 리스트에서 해당 도시에 도착할 수 있는 다른 버스가 있는지 확인
                for (Bus bus : busList.get(currentCity)) {
                    // 만약 해당 도시를 방문한 적이 없고, 비용 합계가 기록된 비용보다 작다면
                    if (!isVisited[bus.stop] && dist[bus.stop] > dist[currentCity] + bus.cost) {
                        dist[bus.stop] = dist[currentCity] + bus.cost;
                        que.add(new Bus(bus.stop, dist[bus.stop]));
                    }
                }
            }
        }
        return dist[stop];
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 도시 및 버스 수 입력
        cityCnt = Integer.parseInt(br.readLine());
        busCnt = Integer.parseInt(br.readLine());
        dist = new int[cityCnt + 1];
        isVisited = new boolean[cityCnt + 1];

        // 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);   // dist 배열을 int 최대값으로 초기화한다.
        busList = new ArrayList<>();
        for (int idx = 0; idx <= cityCnt; idx++)
            busList.add(new ArrayList<>());

        // 버스 정보 입력
        for (int idx = 0; idx < busCnt; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int stop = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            busList.get(start).add(new Bus(stop, cost));
        }

        // 목표 입력
        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int stopCity = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(startCity, stopCity));
    }
}