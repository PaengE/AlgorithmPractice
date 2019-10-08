#include <stdio.h>

int main(){
	int T, i = 0;
	scanf("%d", &T);
	int A[T], B[T];
	
	for(i = 0; i < T; i++){
		scanf("%d %d", &A[i], &B[i]);
		if(A[i] >= 1 && A[i] <= 1000 && B[i] >= 1 && B[i] <= 1000){
			
		}
	}
	for(i = 0; i < T; i++){
		if(A[i] >= 1 && A[i] <= 1000 && B[i] >= 1 && B[i] <= 1000){
			printf("%d\n", A[i] + B[i]);
		} else {
			printf("범위 초과");
		}
	}
	return 0; 
}
