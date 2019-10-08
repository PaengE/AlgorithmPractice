#include <stdio.h>
//#define LIMITED_NUMBER 10
//#define AVAILABLE_NUMBER 42

int main(){
	int N, i, M = 0;
	double tsum, avg = 0.0;
	scanf("%d", &N);
	int arr[1000];
	
	for (i = 0; i < N; i++){
		scanf("%d", &arr[i]);
		if (M < arr[i])
			M = arr[i];
	}
	for (i = 0; i < N; i++){
		tsum += (double)arr[i]/(double)M*100;
	}
	avg = tsum / (double)N;
	printf("%.2lf", avg);
	return 0;
}
