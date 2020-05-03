package bj_17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_주석테스트 {
	
	private static int sx, sy, N, map[][], min = Integer.MAX_VALUE;
	private static int[] dx = {1, 1, -1, -1}, dy = {-1, 1, 1, -1};
	private static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		findStart();
		System.out.println(min);
	}

	private static void findStart() {
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 1; j <= N - 2; j++) {
				visit[i][j] = true;
				sx = i;
				sy = j;
				backTracking(i, j, 0, 0, 0, 0, 0);
				visit[i][j] = false;
				
			}
		}
	}

	private static void backTracking(int x, int y, int d, int d1, int d2, int nd1, int nd2) {
		if (d1 < nd1 || d2 < nd2) return;
//		if (d == 1 && d1 < nd1) return;
		
		if (d > 3) return;
		if (d == 3) {
			if (sx < x && sy > y) return;
			if (d1 == nd1 && d2 == nd2 && sx == x && sy == y) {
				System.out.println("x " + x + " y " + y + " d1 " + d1 + " d2 " + d2+ " nd1 " + nd1 + " nd2 " + nd2);
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						System.out.print((visit[i][j] ? 1 : 0) + " ");
					}
					System.out.println();
				}
				System.out.println();
				countCheck(x, y, d1, d2);
				return;
			}
		}
		
		
//		if (d > 0 && sx == x && sy == y) return;
		
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) return;

//		System.out.println("nx " + nx + " ny " + ny + " d " + d);
		if (d == 0) d1++;
		else if (d == 1) d2++;
		else if (d == 2) nd1++;
		else if (d == 3) nd2++;
		
		
//		if (nx == 1 && ny == 1) {
//			System.out.println("<<<<<<<<<<<<");
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print((visit[i][j] ? 1 : 0) + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		boolean flag = false;
		if (visit[nx][ny]) flag = true;
		//다음칸으로 전진 
		visit[nx][ny] = true;
		backTracking(nx, ny, d, d1, d2, nd1, nd2);
//		visit[nx][ny] = false;
		
//		visit[nx][ny] = true;
//		if (d < 3)
		backTracking(nx, ny, d + 1, d1, d2, nd1, nd2);
		if (!flag)
			visit[nx][ny] = false;
		
//		if (nx == sx && ny == sy) visit[nx][ny] = true;
	}

	
	
	private static void countCheck(int x, int y, int d1, int d2) {
		int[] sum = new int[5];
		for (int i = 0; i < x + d1; i++) {	//1
			for (int j = 0; j <= y; j++) {
				if (visit[i][j]) break;
				sum[0] += map[i][j];
			}
		}
		
		for (int i = 0; i <= x + d2; i++) {	//2
			for (int j = N - 1; j > y; j--) {
				if (visit[i][j]) break;
				sum[1] += map[i][j];
			}
		}
		
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y + d2 - d1; j++) {
				if (visit[i][j]) break;
				sum[2] += map[i][j];
			}
		}
		
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = N - 1; j >= y + d2 - d1; j--) {
				if (visit[i][j]) break;
				sum[3] += map[i][j];
			}
		}
		
		sum[4] = map[x][y] + map[x + d1 + d2][y + d2 - d1];
		System.out.println("sum " + sum[4]);
		for (int i = x + 1; i < x + d1 + d2; i++) {
			boolean flag = false;
			for (int j = 0; j < N; j++) {
				
				if (!flag) {
					if (visit[i][j]) {
						flag = true;
						System.out.println("5번맵 x" + i + " y " + j + " map[i][j] " + map[i][j]);
						sum[4] += map[i][j];
					} 
				} else {
					if (visit[i][j]) {
						System.out.println("5번맵 x" + i + " y " + j + " map[i][j] " + map[i][j]);
						sum[4] += map[i][j];
						break;
					} else {
						System.out.println("5번맵 x" + i + " y " + j + " map[i][j] " + map[i][j]);
						sum[4] += map[i][j];
					
					} 
				}
				System.out.println("sum누적 " + sum[4]);
			}
		}
		
		System.out.println(Arrays.toString(sum));
		Arrays.sort(sum);
		int rslt = sum[4] - sum[0];
		if (min > rslt) min = rslt;
	}
}