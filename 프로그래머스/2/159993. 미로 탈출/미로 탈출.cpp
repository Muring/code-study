#include <string>
#include <vector>
#include <queue>

using namespace std;

// 상 하 좌 우
int D_ROW[] = { -1, 1, 0, 0 };
const int D_COL[] = { 0, 0, -1, 1 };

int row_size;
int col_size;

class Point {
public:
	int row;
	int col;
	int count;
	bool operator==(const Point& rhs) const;

	Point() {}
	Point(int row, int col, int count);
};

Point::Point(int row, int col, int count) {
	this->row = row;
	this->col = col;
	this->count = count;
}

bool Point::operator==(const Point& rhs) const {
	return row == rhs.row and col == rhs.col;
}

bool isAvailable(int row, int col) {
	if (0 <= row && 0 <= col && row < row_size && col < col_size) {
		return true;
	}
	return false;
}

// BFS 방식의 완전탐색으로 최소 거리를 구한다.
int getMinDistance(Point from, Point to, const vector<string> maps) {
	// 먼저 제자리를 돌지 않도록 방문한 곳을 체크할 방문 배열을 생성한다.
	vector<vector<bool>> visited(row_size, vector<bool>(col_size, false));
	// 이후 BFS에서 사용할 queue 생성
	queue<Point> que;
	// queue에 시작지점 넣기
	que.push(from);
	// 시작지점 방문처리
	visited[from.row][from.col] = true;

	// BFS 시작
	while (!que.empty()) {
		Point current_point = que.front();
		que.pop();

		// BFS 기저조건
		if (current_point == to) {
			return current_point.count;
		}

		// 탐색
		for (int delta_idx = 0; delta_idx < 4; delta_idx++) {
			// 다음 칸
			int next_row = current_point.row + D_ROW[delta_idx];
			int next_col = current_point.col + D_COL[delta_idx];

			// 맵 밖이거나 이미 방문된 곳이거나 벽인지 확인한다.
			if (!isAvailable(next_row, next_col) || visited[next_row][next_col] || maps[next_row][next_col] == 'X') {
				continue;
			}

			// 조건을 통과했으면 방문처리
			visited[next_row][next_col] = true;

			// 큐에 해당 장소 추가
			que.push(Point(next_row, next_col, current_point.count + 1));
		}
	}	

	return -1;
}


int solution(vector<string> maps) {
	int answer = 0;
	row_size = maps.size();
	col_size = maps[0].size();

	Point start, lever, exit;
	for (int row = 0; row < row_size; row++) {
		for (int col = 0; col < col_size; col++) {
			if (maps[row][col] == 'S') {
				start.row = row;
				start.col = col;
				start.count = 0;
			}
			else if (maps[row][col] == 'L') {
				lever.row = row;
				lever.col = col;
				lever.count = 0;
			}
			else if (maps[row][col] == 'E') {
				exit.row = row;
				exit.col = col;
				exit.count = 0;
			}
		}
	}
	
	int dist_to_lever = getMinDistance(start, lever, maps);
	int dist_to_exit = getMinDistance(lever, exit, maps);
	answer = (dist_to_exit == -1 || dist_to_lever == -1) ? -1 : dist_to_exit + dist_to_lever;

	return answer;
}