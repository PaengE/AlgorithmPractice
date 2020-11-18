#include <stdio.h>
#include <stdlib.h>

int main(){
	int N, sum = 0, i;
	scanf("%d", &N);
	char *num = malloc(sizeof(char) * N);
	scanf("%s", num);
	
	for (i = 0; i < N; i++){
		sum += (int)num[i] - 48;
	}
	printf("%d", sum);
	return 0;
}
