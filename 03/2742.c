#include <stdio.h>

int main(){
	int N, i;
	scanf("%d", &N);
	if(N > 0 && N <= 100000){
		for(i = N; i > 0; i--){
			printf("%d\n", i);
		}	
	}
	return 0; 
}
