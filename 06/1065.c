#include <stdio.h>

void func(int j, int *tmp, int diff);

int count = 0, idx = 0;

int main(){
	int N;
	scanf("%d", &N);
	
	for (idx = 1; idx <= N; idx++){
		if (idx <= 99){
			count++;
		} else {
			int diff = 0, j = idx;
			int tmp[2] = { 0 };
			tmp[0] = j % 10;
			j /= 10;
			tmp[1] = j % 10;
			j /= 10;
			diff = tmp[0] - tmp[1];
		
			func(j, tmp, diff);
		}
	}
	printf("%d", count);
	return 0;
}
void func(int j, int *tmp, int diff){
	tmp[0] = tmp[1];
	tmp[1] = j % 10;
	if (diff == (tmp[0] - tmp[1]) && (j / 10 == 0)){
		count++;
	} else if (diff == (tmp[0] - tmp[1]) && (j / 10 != 0)){
		func(j / 10, tmp, diff);
	}
}
