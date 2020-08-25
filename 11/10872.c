#include <stdio.h>

int factorial(int n);

int main(){
	int N, result = 0;
	scanf("%d", &N);
	
	if (N >= 0 && N <= 12)
		result = factorial(N);
		
	printf("%d", result);
	return 0;
}

int factorial(int n){
	if (n == 0 || n == 1){
		return 1;
	} else {
		return (n * factorial(n-1));
	}
}