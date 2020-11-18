#include <stdio.h>

int main(){
	int N, a, b, c, cycle = 0;
	scanf("%d", &N);
	if (N >= 0 && N < 10){
		a = 0;
		b = N;
		while (N != b*10 + (a+b)%10){
			cycle++;
			c = b*10 + (a+b)%10;
			a = c / 10;
			b = c % 10;
		}
		printf("%d\n", cycle+1);
	} else if (N >= 10 && N <= 99){
		a = N / 10;
		b = N % 10;
		while (N != b*10 + (a+b)%10){
			cycle++;
			c = b*10 + (a+b)%10;
			a = c / 10;
			b = c % 10;
		}
		printf("%d\n", cycle+1);
	} else {
		printf("N의 범위 벗어남.");
	}
}
