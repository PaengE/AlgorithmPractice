#include <stdio.h>

int main(){
	int C, N, i, j, sum = 0, count = 0;
	double avg = 0.0;
	scanf("%d", &C);
	int carr[C][1000];
	int narr[C];
	
	for (i = 0; i < C; i++){
		scanf("%d", &narr[i]);
		for (j = 0; j < narr[i]; j++){ 
			scanf("%d", &carr[i][j]);
		}
	}
	for (i = 0; i < C; i++){
		for (j = 0; j < narr[i]; j++){
			sum += carr[i][j];
		}
		avg = (double)(sum / narr[i]);
		for (j = 0; j < narr[i]; j++){
			if (avg < (double)carr[i][j])
				count++;
		}
		printf("%.3lf%%\n", (double)count*100/narr[i]);
		count = 0;
		avg = 0.0;
		sum = 0;
	}
	return 0;
}
