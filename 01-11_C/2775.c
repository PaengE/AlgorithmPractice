#include <stdio.h>
#include <string.h>

int main(){
	int T, i, j, k, l;
	scanf("%d", &T);
	int K[T], N[T], room[15][15];
	memset(room, 0, sizeof(room));
	for (i = 1; i < 15; i++){
		room[0][i] = i;
	}
	for (i = 0; i < T; i++){
		scanf("%d", &K[i]);
		scanf("%d", &N[i]);
	}
	for (i = 0; i < T; i++){
		for (j = 1; j <= K[i]; j++){
			for (k = 1; k <= N[i]; k++){
				if (room[j][k] == 0){
					for (l = 1; l <= k; l++){
						room[j][k] += room[j-1][l];
					}
				}
			}
		}
		printf("%d\n", room[K[i]][N[i]]);
	}
	return 0;
}
