#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(){
	char str[1000000];
	scanf("%s", &str);
	int tmp[26] = { 0 };
	
	int i, overlap = 0;
	int length = strlen(str);
	for (i = 0; i < length; i++){
		tmp[toupper(str[i]) - 65] += 1;
	}
	int index = 0, max = tmp[0];
	for (i = 1; i < 26; i++){
		if (max < tmp[i]){
			overlap = 0;
			index = i;
			max = tmp[i];
		}
		else if (max != 0 && max == tmp[i])
			overlap += 1;
	}
	if (overlap > 0)
		printf("?");
	else
		printf("%c", index+65);
	return 0;
}
