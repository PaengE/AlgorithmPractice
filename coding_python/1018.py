n, m = map(int, input().split())

a = []
for i in range(n):
    a.append(input())

count = []
for i in range(n-7):
    for j in range(m-7):
        cnt = 0
        for k in range(i, i+8):
            for l in range(j, j+8):
                loc = (k+l) % 2 
                if(a[k][l] == 'W' and loc == 0):
                    cnt += 1
                elif(a[k][l] == 'B' and loc == 1):
                    cnt += 1

        count.append(min(cnt, 64 - cnt))
count.sort()
print(count[0])
