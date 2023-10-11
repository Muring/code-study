package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author sehyeon.eom
 * 
 * BFS를 활용하여 푼다.
 * 1. 테스트 케이스의 수 totalCase가 주어진다.
 * 2. 각 케이스의 첫 번째 줄에는 정수 방의 한쪽 길이가 주어진다.
 * 3. 방의 입력
 * 4. 이후 각 방을 확인하며 출발해야하는 방 번호와 최대 몇 개의 방을 이동할 수 있는지 구한다.
 *	    이때 출발 지점을 확인하면서 출발 지점 근처의 수가 출발 지점보다 1 작은 곳이 있다면 해당 출발 지점은 스킵
 *	  -> 왜냐하면 현재 출발 지점보다 1 작은 곳에서 시작하면 길이가 더 길어지기 때문이다.
 * 5. 그렇게 구한 출발 지점과 최대 길이를 출력한다.
 *
 */
public class SWEA_1861 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int minStartNumber;	// 최대 길이를 얻을 때의 시작 숫자
	static int maxCount;		// 최대 길이
	static int roomSize;		// 방의 한 변 길이
	static int[][] rooms;		// 방 배열
	
	// bfs를 위한 변위 인덱스
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	
	// 해당 룸 정보를 저장할 객체
	static class Room {
		int row;
		int col;
		int startNumber;
		int count;
		
		public Room(int row, int col, int startNumber, int count) {
			this.row = row;
			this.col = col;
			this.startNumber = startNumber;
			this.count = count;
		}
	}
	
	static void bfs(int row, int col) {
		// 현재 방 근처에 1작은 다른 방이 있으면 안해도 되기 때문에 스킵
		if(hasSmallerNearBy(row, col))
			return;
		
		// 룸 객체를 저장하기 위한 큐 선언
		Queue<Room> que = new LinkedList<>();
		// 가장 처음에 먼저 큐 초기화
		que.offer(new Room(row, col, rooms[row][col], 1));	// 해당 룸이 들어가면 카운트 1해줘야 한다.
		
		while(!que.isEmpty()) {
			Room room = que.poll();	// 현재 방 정보 빼서 저장
			
			// 만약 현재까지 센 방의 수가 현재 저장된 최대 방의 개수보다 크다면 바꿔 저장한다.
			if(room.count > maxCount) {
				maxCount = room.count;
				minStartNumber = room.startNumber;
			}
			// 만약 현재까지 센 방의 수가 현재 저장된 최대 방의 개수와 같다면
			else if(room.count == maxCount) {
				minStartNumber = Math.min(room.startNumber, minStartNumber);
			}
			
			// 다음 이동 좌표 구하기
			for(int idx = 0; idx < dx.length; idx++) {
				int nextRow = room.row + dy[idx];
				int nextCol = room.col + dx[idx];
				
				// 만약 다음 방이 전체 방의 범위 안이고 번호 차이가 1이라면
				if(inRange(nextRow, nextCol) && 
						rooms[nextRow][nextCol] - rooms[room.row][room.col] == 1) {
					que.offer(new Room(nextRow, nextCol, room.startNumber, room.count + 1));
				}
			}
		}
	}
	
	// 주변에 현재 방보다 1 작은 값이 존재하는지 확인하는 메서드
	static boolean hasSmallerNearBy(int currentRow, int currentCol) {
		for(int idx = 0; idx < dx.length; idx++) {
			// 델타 배열을 이용한 다음 방 찾기
			int nextRow = currentRow + dy[idx];	// 다음 행
			int nextCol = currentCol + dx[idx];	// 다음 열
			
			// 전체 방 범위 밖으로 나가지 않고 다음 방이 현재 방보다 1 작다면
			if(inRange(nextRow, nextCol) &&	
					rooms[nextRow][nextCol] - rooms[currentRow][currentCol] == -1) {
				return true;
			}
		}
		return false;
	}
	
	// 해당 방이 전체 방 범위 안에 있는지 확인하는 메서드
	static boolean inRange(int row, int col) {
		return row >= 0 && row < roomSize && col >= 0 && col < roomSize;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 숫자 입력
		int totalCase = Integer.parseInt(br.readLine().trim());	// 전체 케이스 수 입력
		
		// 테스트 케이스 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			roomSize = Integer.parseInt(br.readLine().trim());	// 전체 룸 사이즈 입력

			// 테스트 케이스마다 초기화하는 변수들
			maxCount = 0;
			minStartNumber = 0;
			rooms = new int[roomSize][roomSize];
			
			// 전체 방 정보 저장
			for(int row = 0; row < roomSize; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < roomSize; col++) {
					rooms[row][col] = Integer.parseInt(st.nextToken());	// 맵에 방 번호 저장
				}
			}
			
			// 방 번호와 최대 도는 방의 개수 구하기
			// 각 방들을 시작 지점으로써 돌아본다.
			for(int row = 0; row < roomSize; row++) {
				for(int col = 0; col < roomSize; col++) 
					bfs(row, col);
			}
			
			// 결과 저장
			sb.append("#").append(tc).append(" ").append(minStartNumber)
			.append(" ").append(maxCount).append("\n");
			
			
		}	// tc end
		
		// 결과 출력
		System.out.println(sb);
	}
}
