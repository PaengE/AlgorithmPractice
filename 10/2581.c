#include <stdio.h>

int main(){
	int M, N, i, j;
	int division = 0, min = 0, sum = 0;
	scanf("%d", &M);
	scanf("%d", &N);
	
	for (i = M; i <= N; i++){
		if (i == 2){
			min = 2;
			sum += i;
		} else if (i % 2 == 1 && i != 1){
			for (j = 3; j < i; j+=2){
				if (i % j == 0){
					division++;
					break;
				}
			}
			if (min == 0 && division == 0){
				min = i;
			}
			if (division < 1){
				sum += i;
			}
		}
		division = 0;
	}
	if (min == 0){
		printf("-1\n");
	} else {
		printf("%d\n%d\n", sum, min);
	}
	return 0;
}