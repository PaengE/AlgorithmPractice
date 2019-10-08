#include <stdio.h>
#include <string.h>

int main(){
	int T, i, j, k;
	scanf("%d", &T);
	if (T < 1 || T > 1000){
		printf("Value out of range for T\n"); return ;
	}
	
	int R[T];
	char S[T][21];
	for (i = 0; i < T; i++){
		scanf("%d %s", &R[i], &S[i]);
		if (R[i] < 1 || R[i] > 8){
			printf("Value out of range for R[%d]\n", i);
			return ;
		}
		if (strlen(S[i]) < 1 || strlen(S[i]) > 20){
			printf("Value out of range for S[%d]\n", i);
			return ;
		}
	}
		
	for (i = 0; i < T; i++){
		for (j = 0; j < strlen(S[i]); j++){
			for (k = 0; k < R[i]; k++){
				printf("%c", S[i][j]);
			}
		}
		printf("\n");
	}
	
	return 0;
}
