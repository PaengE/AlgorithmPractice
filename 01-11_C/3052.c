#include <stdio.h>
#define LIMITED_NUMBER 10
#define AVAILABLE_NUMBER 42

int main(){
	int remainder, i, counter= 0;
	int nArr[LIMITED_NUMBER] = { 0 };
	int rArr[AVAILABLE_NUMBER] = { 0 };
	
	for (i = 0; i < LIMITED_NUMBER; i++){
		scanf("%d", &nArr[i]);
		remainder = nArr[i] % 42;
		rArr[remainder] = 1;
	}
	for (i = 0; i < AVAILABLE_NUMBER; i++){
		if (rArr[i] == 1){
			counter++;
		}
	}
	printf("%d", counter);
	return 0;
}
