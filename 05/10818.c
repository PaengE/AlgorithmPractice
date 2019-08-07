#include <stdio.h>

int main(){
	int N, i, min, max;
	
	scanf("%d", &N);
	if (N >= 1 && N <= 1000000){
		int arr[N];
		min = 1000000;
		max = -1000000;
		for (i = 0; i < N; i++){
			scanf("%d", &arr[i]);
			if (arr[i] >= -1000000 && arr[i] <= 1000000){
				if (min > arr[i])
					min = arr[i];
				if (max < arr[i])
					max = arr[i];
			}
		}
		printf("%d %d\n", min, max);
	}
	return 0;
}
