#include <stdio.h>

int main(){
	int a;
	scanf("%d", &a);
	
	if(a >= 0 && a <= 100){
		if(a < 60)
			printf("F");
		else if(a >= 60 && a < 70)
			printf("D");
		else if(a >= 70 && a < 80)
			printf("C");
		else if(a >= 80 && a < 90)
			printf("B");
		else if(a >= 90 && a <= 100)
			printf("A");
	}
}
