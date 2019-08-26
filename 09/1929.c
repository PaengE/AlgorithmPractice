#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h> 

int main(){				// 에라토스테네스의 체
	int M, N, i, j;
	scanf("%d %d", &M, &N);
	int *arr = (int *)malloc(sizeof(int) * (N+1));
	memset(arr, 0x0, sizeof(int) * (N+1));

	arr[1] = 1;
	for (i = 2; i < N+1; i++){
		if(arr[i] == 0){
			if((long long)pow(i, 2) > 1000001){
				break;
			} else {
				for (j = (int)pow(i, 2); j < N+1; j = j+i){
					arr[j] = 1;
				}
			}
		}
	}
	for (i = M; i < N+1; i++){
		if (arr[i] == 0){
			printf("%d\n", i);
		}
	}
	free(arr);
	return 0;
}
