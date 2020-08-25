#include <stdio.h>

void star(int n);

int main(){
	int N;
	scanf("%d", &N);
	int T = N;
	while (T > 3 && (T /= 3) != 0){
	}
	if (T % 3 != 0)
		printf("N is not 3's multiple.\n");
	else
		star(N);
	return 0;
}

void star(int n){
	int i, j, x, y;
	
	for (i = 0; i < n; i++){
		for (j = 0; j < n; j++){
			x = i;
			y = j;
			while (x){
				if (x % 3 == 1 && y % 3 == 1)
					break;
				x /= 3;
				y /= 3;
			}
			if (x)
				printf(" ");
			else
				printf("*");
		}
		printf("\n");
	}
}