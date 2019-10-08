//1 2 3 4 5 
//1 3 6 10 15
#include <stdio.h>

int main(){
	int X, i, count = 0, index = 0;
	scanf("%d", &X);
	
	for (i = 1; ; i++){
		count += i;
		if (count >= X){
			break;
		}
	}
	index = X - (count -i);
	if (i % 2 == 1)
		printf("%d/%d\n", i+1-index, index);
	else
		printf("%d/%d\n", index, i+1-index);
	return 0;
}
