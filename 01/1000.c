#include <stdio.h>

int main(){
	int a, b, result;
	scanf("%d %d", &a, &b);
	if (a > 0 && b < 10){
		result = a + b;
		printf("%d", result);
	}
}
