#include <stdio.h>

int main(){
	int a, b; 
	double result;
	scanf("%d %d", &a, &b);
	if (a > 0 && b < 10){
		result = (double)a / (double)b;
		printf("%0.9f", result);
	}
}
