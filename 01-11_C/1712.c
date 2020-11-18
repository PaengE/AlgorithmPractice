#include <stdio.h>
#include <math.h>
#define LIMITS 2100000000

int main() {
	int A, B, C, x = 0;
	int budget = 0, turnover = 0;
	scanf("%d %d %d", &A, &B, &C);
	
	if (A <= LIMITS && B <= LIMITS && C <= LIMITS){
		if (B >= C)
			printf("-1\n");
		else {
			x = floor(A/(C-B)) + 1;
			printf("%d\n", x);
		}
	} else {
		printf("-1");
	}
	return 0;
}
