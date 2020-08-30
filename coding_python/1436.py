n = int(input())

cnt = 0
i = 666
while(cnt < n):
    if str(i).count('666') >= 1:
        cnt += 1
    i += 1
print(i-1)
