#include <stdio.h>

void hanoi(int n, int from, int b, int c);
int count(int n);
 
int main(){
	int N;
    scanf("%d", &N);
    printf("%d\n", count(N));
    hanoi(N,1,2,3);
}

int count(int n){
    int i, d = 1;
    for(i = 2; i <= n; i++)
		d = d*2 + 1;
    return d;
}
void hanoi(int n, int from, int temp, int to){
    if (n == 1){
    	printf("%d %d\n", from, to);
		return;
	}
    hanoi(n - 1, from, to, temp);
    printf("%d %d\n", from, to);
    hanoi(n - 1, temp, from, to);
}