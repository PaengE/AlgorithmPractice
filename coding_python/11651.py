import sys

n = int(sys.stdin.readline())
l = []
for i in range(n):
    a, b = map(int, sys.stdin.readline().split())
    l.append((b, a))
l.sort()

for i in l:
    print(i[1], i[0], sep=' ')