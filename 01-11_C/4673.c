#include <stdio.h>
#define MAXSIZE 10001

void d(int n);

int main(){
	d(MAXSIZE);
	return 0;
}

void d(int n){
	int tarr[MAXSIZE] = { 0 };
	int i, selfNum;
	
	for (i = 1; i < n; i++){
		int t = i;
		selfNum = t;
		do {
			selfNum += t % 10;
		} while ((t /= 10)!= 0);
		
		if (selfNum < MAXSIZE)
			tarr[selfNum] = 1;
		if (tarr[i] == 0){
			printf("%d\n", i);
		}
		selfNum = 0;
	}
}
