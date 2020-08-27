n = int(input())
x = []
y = []

for i in range(n):
    a, b = map(int, input().split())
    x.append(a)
    y.append(b)

res = []
for i in range(n):
    k = 1
    for j in range(n):
        if (x[i] < x[j] and y[i] < y[j]):
            k += 1
    print(k, end=' ')