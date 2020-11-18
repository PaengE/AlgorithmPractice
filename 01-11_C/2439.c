#include <stdio.h>

int main(){
	int N, i, j, k;
	scanf("%d", &N);
	
	if(N >= 1 && N <= 100){
		for(i = 0; i < N; i++){
			for(j = N; j > i+1; j--){
				printf(" ");
			}
			for(k = 0; k <= i; k++){
				printf("*");
			}
		printf("\n");
		}
	}
	return 0;
}
