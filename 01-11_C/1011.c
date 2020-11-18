#include <stdio.h>
#include <math.h>
#define MAXINT 2147483648

int main(){
	long long T, i, j;
	scanf("%lld", &T);
	long long x[T], y[T];
	long long count[T];
	for (i = 0; i < T; i++){
		scanf("%lld %lld", &x[i], &y[i]);
	}
	long long distance = 0, sum = 0;
	for (i = 0; i < T; i++){
		if (y[i] > x[i] && x[i] >= 0 && y[i] >= 0 && x[i] < MAXINT && y[i] < MAXINT){
			distance = y[i] - x[i];
			sum = 0;
			for (j = 1; ; j++){
				sum += 2*j;
				if (sum == distance){
					count[i] = 2*j;
					break;
				} else if (sum > distance){
					if ((sum - j) < distance){
						count[i] = 2*j;
						break;
					} else {
						count[i] = 2*(j-1) + 1;
						break;
					}
				}
			}
		}
		printf("%lld\n", count[i]);
	}
	return 0;
}
