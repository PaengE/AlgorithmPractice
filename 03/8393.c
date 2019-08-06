#include <stdio.h>

int main(){
	int n, i, sum = 0;
	
	scanf("%d", &n);
	if(n >= 1 && n <= 10000){
		for(i = 1; i <= n; i++){
			sum = sum + i;
		}	
	}
	printf("%d\n", sum);
}
