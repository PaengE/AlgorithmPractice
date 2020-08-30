import sys
n = int(sys.stdin.readline())

li = [0] * 10001

for i in range(n):
    li[int(sys.stdin.readline())] += 1

for j in range(10001):
    sys.stdout.write('%s\n' % j * li[j])