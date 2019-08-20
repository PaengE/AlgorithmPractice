#include <stdio.h>

int main(){
	int T, i;
	scanf("%d", &T);
	int height[T], width[T], nth[T];
	for (i = 0; i < T; i++){
		scanf("%d %d %d", &height[i], &width[i], &nth[i]);
	}
	for (i = 0; i < T; i++){
		if (nth[i]%height[i] == 0){
			printf("%d%02d\n", height[i], nth[i]/height[i]);
		} else {
			printf("%d%02d\n", (nth[i]%height[i]), (nth[i]/height[i])+1);
		}
	}
	return 0;
}
