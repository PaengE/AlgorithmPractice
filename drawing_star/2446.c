#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {

	int i, j, k, n;
	scanf("%d", &n);

	for (i = 0; i < 2 * n - 1; i++) {
		if (i < n) {
			for (j = 0; j < i; j++) {
				printf(" ");
			}
			for (k = 0; k < 2 * n - 2 * i - 1; k++) {
				printf("*");
			}
		}
		else {
			for (j = n - 1; j > i - n + 1; j--) {
				printf(" ");
			}
			for (k = 0; k < 2*i - 2*(n-1) + 1; k++) {
				printf("*");
			}
		}


		printf("\n");
	}

	

}