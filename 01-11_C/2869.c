#include <stdio.h>
#include <math.h>

int main(){
	int A, B, V, day = 0;
	scanf("%d %d %d", &A, &B, &V);
	
	day = (V-B-1) / (A-B) + 1;
	printf("%d\n", day);
	return 0;
}
