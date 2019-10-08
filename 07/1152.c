#include <stdio.h>
#include <string.h>
int main(){
	char str[1000002];
	scanf("%[^\n]s", str);
	
	if (strlen(str) < 1000002){
		char sep[] = " ";
		char* token = strtok(str, sep);
		int count = 0;
		while (token != NULL){
			token = strtok(NULL, " ");
			count++;
		}
		printf("%d\n", count);
	} else {
		printf("Value out of range for str\n");
		return ;
	}
	return 0;
}
