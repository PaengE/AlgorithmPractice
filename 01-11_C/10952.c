#include <stdio.h>

int main(){
	int A[100] = {1, };
	int B[100] = {1, };
	int i, j = 0;
	
	do{
		scanf("%d %d", &A[i], &B[i]);
		i++;
	}while(A[i-1] != 0 && B[i-1] != 0);	// checking inserted A, B
										// aren't 0
	
	for(j = 0; j < i-1; j++){
		printf("%d\n", A[j]+B[j]);
	} 
	return 0;
}
