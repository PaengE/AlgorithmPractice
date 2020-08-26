n, m = map(int, input().split())
li = list(map(int, input().split()))

max = 0
for i in range(0, n):
    for j in range(i+1, n):
        for k in range(j+1, n):
            tmp = li[i] + li[j] + li[k]
            if(tmp <= m and max < tmp):
                max = tmp

print(max)