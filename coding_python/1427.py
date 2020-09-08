import sys

n = sys.stdin.readline().rstrip()
li = []
for i in range(len(n)):
    li.append(int(n[i]))
li.sort(reverse=True)

for i in li:
    sys.stdout.write('%d' % i)
