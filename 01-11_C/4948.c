#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

int main(){
	int i, j, k, n[123458], count;
	memset(n, -1, sizeof(int)*123458);
	
	for (i = 1; n[i-1] != 0; i++){
		scanf("%d", &n[i]);
	}
	for (i = 1; n[i] != 0; i++){
		count = 2*n[i] - n[i];
		int *arr = (int *)malloc(sizeof(int)*(2*n[i]+1));
		memset(arr, 0x0, sizeof(int)*(2*n[i]+1));
	
		arr[1] = 1;
		for (k = 2; k < 2*n[i]+1; k++){
			if(arr[k] == 0){
				if((long long)pow(k, 2) > 2*123456){
					break;
				} else {
					for (j = (int)pow(k, 2); j < 2*n[i]+1; j = j+k){
						if (j > n[i] && arr[j] == 0){
							arr[j] = 1;
							count--;
						}
					}
				}
			}
		}
		printf("%d\n", count);
		free(arr);

	}
	return 0;
}