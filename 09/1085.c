#include <stdio.h>

int main(){
	int x, y, w, h;
	scanf("%d %d %d %d", &x, &y, &w, &h);
	
	if (x <= w/2 && y <= h/2){
		(x <= y) ? printf("%d\n", x) : printf("%d\n", y);
	} else if (x > w/2 && y <= h/2){
		(w-x <= y) ? printf("%d\n", w-x) : printf("%d\n", y);
	} else if (x <= w/2 && y > h/2){
		(x <= h-y) ? printf("%d\n", x) : printf("%d\n", h-y);
	} else {
		(w-x <= h-y) ? printf("%d\n", w-x) : printf("%d\n", h-y);
	}
	return 0;
}
