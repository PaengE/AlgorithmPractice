#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main(){
	int T, i, j;
	scanf("%d", &T);
	int n[T];
	for (i = 0; i < T; i++){
		scanf("%d", &n[i]);
	}
	for (i = 0; i < T; i++){
		int a = n[i] / 2, b = n[i] / 2;
		if (a == 2){
			printf("%d %d\n", a, b);
			continue;
		} else {
			for (; a > 2; a--, b++){
				if (a % 2 == 0){
					continue;
				} else {
					int checked = 1; 
					for(j = 3; j < b; j+=2){
						if (a > j && a % j == 0){
							checked = 0;
							break;
						}
						if (b % j == 0){
							checked = 0;
							break;
						}
					}
					if (checked == 1){
						printf("%d %d\n", a, b);
						break;
					}
				}
			}
		}
	}
	return 0;
}
