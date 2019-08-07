#include <stdio.h>
#define LIMITED_NUMBER 10

int main(){
	int a, b, c, i, mul;
	int nArr[LIMITED_NUMBER] = { 0, };
	scanf("%d %d %d", &a, &b, &c);
	
	if (a >= 100 && a < 1000 && b >= 100 && b < 1000 && c >= 100 && c < 1000){
		mul = a * b * c;
		while (mul / 10 != 0){	// 최상위 자릿수 제외 계산 
			nArr[mul%10]++;
			mul = mul / 10;
		}
		nArr[mul]++;			// 최상위 자릿수 계산 
		for (i = 0; i < LIMITED_NUMBER; i++){
			printf("%d\n", nArr[i]);
		}
	} else {
		printf("Inserted Number Scope Error.\n");
	}
	return 0;
}
