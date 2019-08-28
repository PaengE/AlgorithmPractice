#include <stdio.h>
#include <math.h>
#include <string.h>

#define TESTCASE 10000

int main(){
	int i = 0, j;
	int a[TESTCASE], b[TESTCASE], c[TESTCASE];
	memset(a, -1, sizeof(int) * TESTCASE);
	memset(b, -1, sizeof(int) * TESTCASE);
	memset(c, -1, sizeof(int) * TESTCASE);
	
	while (1){
		scanf("%d %d %d", &a[i], &b[i], &c[i]);
		if (a[i] == 0 && b[i] == 0 && c[i] == 0){
			break;
		} else {
			i++;
		}
	}
	for (j = 0; j < i; j++){
		if (a[j] >= b[j]){
			if (a[j] >= c[j]){
				(pow(a[j], 2) == pow(b[j], 2) + pow(c[j], 2)) ? printf("right\n") : printf("wrong\n");
			} else {
				(pow(c[j], 2) == pow(b[j], 2) + pow(a[j], 2)) ? printf("right\n") : printf("wrong\n");
			}
		} else {
			if (b[j] >= c[j]){
				(pow(b[j], 2) == pow(a[j], 2) + pow(c[j], 2)) ? printf("right\n") : printf("wrong\n");
			} else {
				(pow(c[j], 2) == pow(a[j], 2) + pow(b[j], 2)) ? printf("right\n") : printf("wrong\n");
			}
		}
	}
	return 0;
}
