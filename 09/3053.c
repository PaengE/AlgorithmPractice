#include <stdio.h>
#include <math.h>

#define PI 3.14159265358979

int main(){
	double r;
	scanf("%lf", &r);
	
	printf("%.6lf\n", PI * pow(r, 2));
	printf("%.6lf\n", 2.0 * pow(r, 2));
	return 0;
}
