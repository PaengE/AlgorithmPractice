#include <stdio.h>
#include <string.h>

int main(){
	char S[101] = { NULL };
	int alpha[26] = { [0 ... 25] = -1 };
	int i;
	scanf("%s", &S);
	
	for (i = 0; i < strlen(S); i++){
		if (alpha[S[i] - 97] == -1)
			alpha[S[i] - 97] = i;
	}
	
	for (i = 0; i < 26; i++){
		printf("%d ", alpha[i]);
	}
	return 0;
}
