n = int(input())
constructor = 0
min_constructor = -1

for i in range(n//10, n):
    num = i
    tmp = i
    while(i != 0):
        tmp += i % 10
        if(tmp > n):
            tmp = 0;
            break;
        i = i // 10
    if (tmp != 0 and tmp == n):
        constructor = num
        if (min_constructor > constructor):
            min_constructor = constructor
        elif (min_constructor == -1):
            min_constructor = constructor
    tmp = 0
if (min_constructor == -1):
    print(0)
else:
    print(min_constructor)