#include <stdio.h>

int main(){
	int T, i, j, k, year, tmpx, tmpy;
	scanf("%d", &T);
	int M[T], N[T], x[T], y[T];
	for (i = 0; i < T; i++){
		scanf("%d %d %d %d", &M[i], &N[i], &x[i], &y[i]);
	}
	for (i = 0; i < T; i++){
		year = 0; tmpx = x[i], tmpy = y[i];
		for (j = 0; tmpx <= M[i]*N[i] || tmpy <= M[i]*N[i]; j++){
			if (tmpx == tmpy){
				printf("%d\n", tmpx);
				break;
			} else if (tmpy > tmpx){
				tmpx += M[i];
			} else {
				tmpy += N[i];
			}
		}
		if (tmpx > M[i]*N[i] || tmpy > M[i]*N[i])
			printf("-1\n");
	}
	return 0;
}

// 시간 초과 
//int main(){
//	int T, i, year, tmpx, tmpy;
//	scanf("%d", &T);
//	int M[T], N[T], x[T], y[T];
//	for (i = 0; i < T; i++){
//		scanf("%d %d %d %d", &M[i], &N[i], &x[i], &y[i]);
//	}
//	for (i = 0; i < T; i++){
//		year = 0; tmpx = 1, tmpy = 1;
//		while (year <= M[i]*N[i]){
//			year++;
//			if (tmpx == x[i] && tmpy == y[i]){
//				printf("%d\n", year);
//				break;
//			}
//			tmpx++; tmpy++;
//			if (tmpx > M[i])
//				tmpx %= M[i];
//			if (tmpy > N[i])
//				tmpy %= N[i];
//		}
//		if (year > M[i]*N[i]){
//			printf("-1\n");
//		}
//	}
//	return 0;
//}
