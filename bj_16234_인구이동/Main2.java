package bj_16234_인구이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int n, l, r, moveCnt;
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {-1, 0, 1, 0};
	private static int[][] map, checkMap, memo;
	private static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		checkMap = new int[n][n];
		memo = new int[2000][4];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			System.out.println("\n\nwhile도는중 ");
			int cnt = findStart();
			System.out.println("cnt " + cnt);
			System.out.println("moveCnt " + moveCnt);
			if (cnt == 0) break;
			else move(cnt);
			moveCnt++;
		}
		System.out.println(moveCnt);
	}


	private static int findStart() {
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (checkMap[i][j] == 0) {
					q.add(new Node(i, j));
					if (bfs(cnt)) cnt++;
				}
			}
		}
		return cnt - 1;
	}

	private static boolean bfs(int cnt) {
		Node temp, first = q.peek();
		int nx, ny, num = 0, sum = 0;
		boolean isChange = false;
		
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if (checkMap[nx][ny] == 0 && Math.abs(map[temp.x][temp.y] - map[nx][ny]) >= l && Math.abs(map[temp.x][temp.y] - map[nx][ny]) <= r) {
					checkMap[temp.x][temp.y] = checkMap[nx][ny] = cnt;
					num++;
					sum += map[nx][ny];
					q.add(new Node(nx, ny));
				}
			}
		}
		if (num != 0) {
			print();
			System.out.println("num " + num);
			memo[cnt][0] = first.x;						//시작 x, y좌
			memo[cnt][1] = first.y;
			memo[cnt][2] = map[first.x][first.y] + sum;	//연합의 원소 총합 
			memo[cnt][3] = num + 1;						//연합의 원소 총 개수
			isChange = true;
		}
		return isChange;
	}


	private static void print() {
		System.out.println(">>>>>>>>>copyMap");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(checkMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(">>>>>>>>>>map");
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}


	private static void move(int cnt) {
		for (int i = 1; i <= cnt; i++) {
			System.out.println("합 " + memo[i][2]);
			System.out.println("개수  " + memo[i][3]);
			System.out.println("이걸로채울거" + memo[i][2] / memo[i][3]);
			map[memo[i][0]][memo[i][1]] = memo[i][2] / memo[i][3];
			checkMap[memo[i][0]][memo[i][1]] = 0;
			q.add(new Node(memo[i][0], memo[i][1]));
			fill(i, memo[i][0], memo[i][1], memo[i][2] / memo[i][3]);
		}
	}


	private static void fill(int cnt, int i, int j, int val) {
		Node temp;
		int nx, ny;
		
		while (!q.isEmpty()) {
			temp = q.poll();
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if (checkMap[nx][ny] == cnt) {
					map[nx][ny] = val;
					checkMap[nx][ny] = 0;
					q.add(new Node(nx, ny));
				}
			}
		}
	}
}













