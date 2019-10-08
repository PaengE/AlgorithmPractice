#include <stdio.h>

int main(void) {
	int a1, a2, a3, b1, b2, b3;
	scanf("%d %d %d %d %d %d", &a1, &b1, &a2, &b2, &a3, &b3);
	if (a1 == a2){
		printf("%d ", a3);
	} else {
		(a1 == a3) ? printf("%d ", a2) : printf("%d ", a1);
	}
	if (b1 == b2){
		printf("%d ", b3);
	} else {
		(b1 == b3) ? printf("%d ", b2) : printf("%d ", b1);
	}
	return 0;
}
