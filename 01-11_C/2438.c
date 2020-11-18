#include <stdio.h>

int main(){
	int N, i, j;
	scanf("%d", &N);
	
	if(N >= 1 && N <= 100){
		for(i = 0; i < N; i++){
			for(j = 0; j <= i; j++){
				printf("*");
			}
		printf("\n");
		}
	}
	return 0;
}
