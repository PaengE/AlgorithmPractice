#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

// 별찍기 - 13
int main() {

	int i = 1, j = 1, n;
	scanf("%d", &n);

	for (i = 0; i < 2 * n - 1; i++) {
		if (i < n) {
			for (j = 0; j < i + 1; j++) {
				printf("*");
			}
		}
		else {
			for (j = n - 1; j > i - n; j--) {
				printf("*");
			}
		}

		printf("\n");
	}
	return 0;

}