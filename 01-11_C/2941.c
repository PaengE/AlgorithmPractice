#include <stdio.h>
#include <string.h>

int main(){
	char str[101];
	scanf("%s", str);
	int i, count, length = strlen(str);
	count = length;
	for (i = 1; i < length; i++){
		if (str[i] == '-'){
			if (str[i-1] == 'c' || str[i-1] == 'd')
				count -= 1;
		} else if (str[i] == '='){
			if (str[i-1] == 'c' || str[i-1] == 's')
				count -= 1;
			else if (str[i-1] == 'z')
				if (str[i-2] == 'd')
					count -= 2;
				else 
					count -= 1;
		} else if (str[i] == 'j'){
			if (str[i-1] == 'l' || str[i-1] == 'n')
				count -= 1;
		}
	}
	printf("%d\n", count);
	return 0;
}
