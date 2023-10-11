package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * NxN 크기의 멕시노스 셀 안에 1개의 코어 혹은 전선이 올 수 있다.
 * 멕시노스의 가장자리에는 전류가 흐르고 있다.
 * 	가장 가장자리 셀에 있는 코어는 전류가 통한다고 본다.
 * 	전선을 가장 끝 셀까지 연결하면 전류가 통한다고 본다.
 * 	전선은 절대 교차해서는 안된다.
 * 최대한 많은 코어에 전원을 연결했을 경우, 전선 길이의 합을 구하라.
 * 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.
 * 
 * [제약 사항]
 * 1. 7 ≤  N ≤ 12
 * 2. Core의 개수는 최소 1개 이상 12개 이하이다.
 * 3. 최대한 많은 Core에 전원을 연결해도, 전원이 연결되지 않는 Core가 존재할 수 있다.
 * 
 * [입력]
 * 입력의 가장 첫 줄에는 총 테스트 케이스의 개수 T가 주어지며 그 다음 줄부터 각 테스트 케이스가 주어진다.
 * 각 테스트 케이스의 첫 줄에는 N값이 주어지며, 다음 N줄에 걸쳐서 멕시노스의 초기 상태가 N x N 배열로 주어진다.
 * 0은 빈 cell을 의미하며, 1은 core를 의미하고, 그 외의 숫자는 주어지지 않는다.
 * 
 * [출력]
 * 각 테스트 케이스마다 '#X'를 찍고, 한 칸 띄고, 정답을 출력한다.
 * (X는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)
 * 
 * ** 필모님의 코드를 참조했다.
 * ** 결과 값을 저장하는 클래스를 만든 것은 매우 신기했다.
 * 
 * @author sehyeon.eom
 *
 */

public class SWEA_1767 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;		// 멕시노스 크기
	static int map[][];		// 멕시노스
	static Result answer;	// 정답 저장 객체 변수
	
	static List<Core> coreList;	// 코어 리스트
	
	// 상 하 좌 우
	static final int[][] DIR = {
			{ -1, 0 },
			{ 1, 0 },
			{ 0, -1 },
			{ 0, 1 }
	};
	
	// 각 코어의 위치를 저장하는 클래스
	static class Core {
		int row;
		int col;
		
		public Core(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
	
	static class Result {
		int coreCount;	// 총 연결된 코어 수
		int wireLen;	// 총 연결된 전선 길이
		
		public Result(int coreCount, int wireLen) {
			this.coreCount = coreCount;
			this.wireLen = wireLen;
		}
		
		// 두 Result 객체를 비교하기 위한 메소드
		// 코어가 더 많은 결과 값을 반환한다. 이때, 코어 수가 같다면 전선 수가 더 적은 결과가 반환된다.
		static Result max(Result o1, Result o2) {
			if(o1.coreCount > o2.coreCount)
				return o1;
			else if(o1.coreCount == o2.coreCount && o1.wireLen < o2.wireLen)
				return o1;
			
			return o2;
		}
	}
	
	// 코어를 전원과 연결하는 메소드
	// 현재 연결하는 코어 번호와 결과 객체
	private static void coreLineUp(int coreIdx, Result result) {
		if(coreIdx == coreList.size()) {	// 모든 코어를 사용했다면
			answer = Result.max(answer, result);
			return;			
		}
		
		// 현재 모든 코어가 연결되더라도 저장되어있는 최대 코어 수보다 적으면 더 계산할 필요 없다.
		if(coreList.size() - coreIdx + result.coreCount < answer.coreCount)
			return;
		
		// 현재 코어 정보 받아오기
		Core currentCore = coreList.get(coreIdx);
		
		// 상 하 좌 우 방향으로 전선을 연결해본다.
		for(int[] dir: DIR) {
			// 코어 각 방향으로 연결해보기
			// 하면서 모든 방향으로 연결이 되게 되어있다.
			int wireConnectCount = connect(currentCore, dir);	// connectCount는 연결된 전선 수 이다.
			
			// 해당 코어의 전선이 연결되었다면 연결되었다고 카운트해준다.
			Result next = new Result(wireConnectCount == 0 ? result.coreCount : result.coreCount + 1, result.wireLen + wireConnectCount);
			
			// 현재 결과를 가지고 다음 코어 연결
			coreLineUp(coreIdx + 1, next);
			
			// 전선 회수
			disconnect(currentCore, dir, wireConnectCount);
		}
	}
	
	// 전선 연결 메소드
	private static int connect(Core core, int[] dir) {
		int times = 1;
		int[][] copyMap = copy(map);
		int wireCount = 0;
		
		while(true) {
			// 당므 전선이 깔리 위치
			int nextRow = core.row + dir[0] * times;
			int nextCol = core.col + dir[1] * times++;
			
			// 다음 칸이 맵 범위 밖이라면 성공적으로 전선이 설치된 것이다.
			if(nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize) {
				map = copyMap;
				break;
			}
			
			// 중간에 전선을 만나면 만들 수 없는 것이기에 값을 초기화하고 돌아간다.
			if(copyMap[nextRow][nextCol] == 1) {
				wireCount = 0;
				break;
			}
			
			// 아무 이상 없음
			// 마지막 전선을 깐다.
			copyMap[nextRow][nextCol] = 1;
			wireCount++;
		}
		
		return wireCount;
	}
	
	// 전선 끊는 메소드
	private static void disconnect(Core core, int[] dir, int count) {
		int times = 1;
		
		// 전선이 깔린 수 만큼 돌아가며 초기화한다.
		while(times <= count) {
			int nRow = core.row + dir[0] * times;
			int nCol = core.col + dir[1] * times++;	// time + 1로 다음 칸
			
			// 지도에도 연결 해제 표시해준다.
			map[nRow][nCol] = 0;
		}
	}
	
	// 전체 배열 복사 메소드
	private static int[][] copy(int[][] originMap) {
		int[][] copyMap = new int[mapSize][mapSize];
		
		for(int row = 0; row < mapSize; row++) {
			copyMap[row] = Arrays.copyOf(originMap[row], mapSize);		
		}
		
		return copyMap;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= totalCase; tc++) {
			mapSize = Integer.parseInt(br.readLine().trim());
			map = new int[mapSize][mapSize];
			answer = new Result(0, 0);
			
			// 맵 상태 입력
			coreList = new ArrayList<>();
			for(int row = 0; row < mapSize; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < mapSize; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if(map[row][col] == 1 && (row > 0 && row < mapSize - 1 && col > 0 && col < mapSize - 1))
						coreList.add(new Core(row, col));
				}
			}
			
			// 코어에 전선 연결
			coreLineUp(0, answer);
			
			
			
			sb.append("#").append(tc).append(" ").append(answer.wireLen).append("\n");
		}	// tc end
		System.out.println(sb);
	}	// main end
}
