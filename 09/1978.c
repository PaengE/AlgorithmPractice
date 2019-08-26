#include <stdio.h>

int main(){
	int T, i, j, division = 0, count = 0;
	scanf("%d", &T);
	int N[T];
	
	for (i = 0; i < T; i++){
		scanf("%d", &N[i]);
		if (N[i] == 2){
			count++;
		} else if (N[i] % 2 == 1){
			for(j = 1; j < N[i]; j+=2){
				if (N[i] % j == 0){
					division++;
				}
				if (division == 2){
					break;
				}
			}
			if (N[i] != 1 && division < 2){
				count++;
			}
		}
		division = 0;
	}
	printf("%d\n", count);
	return 0;
}
