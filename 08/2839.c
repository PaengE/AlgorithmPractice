#include <stdio.h>

int main(){
	int N, kg3 = 0, kg5 = 0;
	scanf("%d", &N);
	
	if (N >= 3 && N <= 5000){
		if (N % 5 == 0) {
			kg5 += N / 5;
			N %= 5;
		} else if (N % 3 == 0){
			while ((N -= 3) >= 0){
				kg3++;
				if (N % 5 == 0){
					kg5 += N / 5;
					break;
				}
			}
		} else {
			while ((N -= 3) >= 0){
				kg3++;
				if (N % 5 == 0){
					kg5 += N / 5;
					N %= 5;
					break;
				}
			}
			if (N != 0){
				printf("-1\n");
				return 0;
			}
		}
		printf("%d\n", kg5 + kg3);
	} else {
		printf("-1\n");
	}
	return 0;
}
