#include <stdio.h>

int main(){
	int T, i;
	scanf("%d", &T);
	int A[T], B[T];
	
	for(i = 0; i < T; i++){
		scanf("%d %d", &A[i], &B[i]);
	}
	for(i = 0; i < T; i++){
		if(A[i] > 0 && A[i] < 10 && B[i] > 0 && B[i] < 10){
			printf("Case #%d: %d\n", i+1, A[i]+B[i]);			
		}
	}
	return 0;
}
