#include <stdio.h>

int main(){
	int N, i, j, score = 0, count = 0;
	scanf("%d", &N);
	char arr[N][80];
	
	for(i = 0; i < N; i++){
		scanf("%s", &arr[i]);
	}

	for (i = 0; i < N; i++){
		j = 0;
		if (arr[i][j] == 'O'){
			count++;
			score = score + count; 
		}
		for (j = 1; arr[i][j] != NULL; j++){
			if(arr[i][j] == 'O'){
				count++;
				score += count;
			} else {
				count = 0;
			}
		}
		printf("%d\n", score);
		count = 0;
		score = 0;
	}
	return 0;
}
