#include <stdio.h>
#include <string.h>

int main(){
	char str[8];
	scanf("%[^\n]s", str);
	if (strlen(str) < 8){
		char sep[] = " ";
		char* token = strtok(str, sep);
		char* cnum1 = token;
		token = strtok(NULL, " ");
		char* cnum2 = token;
		
		int length = strlen(cnum1);
		int i; char tmp1[4], tmp2[4];
		for(i = 0; i < length ; i++){
			tmp1[i] = cnum1[length-1-i];
			tmp2[i] = cnum2[length-1-i];
		}
		if (atoi(tmp1) < atoi(tmp2))
			printf("%s\n", tmp2);
		else		
			printf("%s\n", tmp1);	
	} else {
		printf("Value out of range for str\n");
	}
	return 0;
}
