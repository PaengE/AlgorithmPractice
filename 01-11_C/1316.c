#include <stdio.h>
#include <string.h>

int main() {
	int N, i, j, alpha[27];
	scanf("%d", &N);
	int count = N;
	char str[101];
	for (i = 0; i < N; i++) {
		memset(alpha, -1, sizeof(alpha));
		scanf("%s", str);
		int length = strlen(str);
		for(j = 0; j < length; j++) {
			if (alpha[str[j] - 'a'] == -1 || alpha[str[j] - 'a'] == j-1){
				alpha[str[j] - 'a'] = j;
			} else {
				count--;
				break;
			}
		}
	}
	printf("%d\n", count);
	return 0;
}

