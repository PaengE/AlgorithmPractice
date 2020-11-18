#include <stdio.h>

long long sum(int *a, int n);

int main(){
	return 0;
}

long long sum(int *a, int n){
	int i;
	long long totalSum = 0;
	for (i = 0; i < n; i++){
		totalSum += a[i];
	}
	return totalSum;
}
