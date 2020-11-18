#include <stdio.h>
#include <math.h>
int main() {
	int T, i;
	scanf("%d", &T);
	int x1[T], y1[T], r1[T], x2[T], y2[T], r2[T];
	for (i = 0; i < T; i++){
		scanf("%d %d %d %d %d %d", &x1[i], &y1[i], &r1[i], &x2[i], &y2[i], &r2[i]);
	}
	for (i = 0; i < T; i++) {
		double distance = sqrt(pow(x1[i]-x2[i], 2) + pow(y1[i]-y2[i], 2));
		if (x1[i] == x2[i] && y1[i] == y2[i]){
			if (r1[i] == r2[i])
				printf("-1\n");
			else
				printf("0\n");
		}
		else {
			if ((r1[i] + r2[i]) > distance && abs(r1[i] - r2[i]) < distance)
				printf("2\n");
			else if ((r1[i] + r2[i]) == distance || abs(r1[i] - r2[i]) == distance)
				printf("1\n");
			else
				printf("0\n");
		}
	}
	return 0;
}