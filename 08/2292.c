//1 6 12 18 24 
//1 7 19 37 61
#include <stdio.h>

int main(){
	int N, count, i;
	scanf("%d", &N);
	count = 1;
	
	for (i = 0; ; i++){
		count += 6*i;
		if (count >= N){
			break;
		}
	}
	printf("%d\n", i+1);
	return 0;
}
