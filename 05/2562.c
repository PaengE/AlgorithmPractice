#include <stdio.h>
#define LIMITED_NUMBER 9

int main(){
	int nArr[LIMITED_NUMBER], i, max, index;
	max = 0;
	for (i = 0; i < LIMITED_NUMBER; i++){
		scanf("%d", &nArr[i]);
		if (nArr[i] > 0 && nArr[i] < 100){
			if(max < nArr[i]){
				max = nArr[i];
				index = i + 1;
			}
		} else {
			printf("Inserted Number Scope Error.\n");
		}
	}
	printf("%d\n%d", max, index);
	return 0;
}
