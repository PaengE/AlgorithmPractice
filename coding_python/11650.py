import sys

n = int(sys.stdin.readline())
l = []
for i in range(n):
    a, b = map(int, sys.stdin.readline().split())
    l.append((a, b))
l.sort()

for i in l:
    print(i[0], i[1], sep=' ')