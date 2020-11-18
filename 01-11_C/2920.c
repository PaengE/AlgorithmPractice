#include <stdio.h>
#define LIMITED_NUMBER 8

int main(){
	int nArr[LIMITED_NUMBER], i, standard;
	standard = 0;
	for (i = 0; i < LIMITED_NUMBER; i++){
		scanf("%d", &nArr[i]);
		if (i >= 1 && nArr[i-1] < nArr[i])
			standard++;
		if (i >= 1 && nArr[i-1] > nArr[i])
			standard--;
	}
	if (standard == 7)
		printf("ascending");
	else if (standard == -7)
		printf("descending");
	else
		printf("mixed");
	return 0;
}
