import sys

def myFunc(e):
    return e[0]

n = int(input())
l = []

for i in range(n):
    a, b = sys.stdin.readline().split()
    s = (int(a), b)
    l.append(s)
    
l.sort(key=myFunc)

for i in l:
    # print(i[0], i[1], sep=' ')
    sys.stdout.write('%d ' % i[0])
    sys.stdout.write('%s\n' % i[1])