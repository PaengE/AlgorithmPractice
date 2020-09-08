import sys

n = int(sys.stdin.readline())
li = []
dic = {}
for i in range(n):
    a = int(sys.stdin.readline())
    li.append(a)
    dic.setdefault(a, 0)

li.sort()
a = sum(li)

f_avg = str(a / n)
dot_idx = f_avg.index('.')
if f_avg.find('-') == -1 and f_avg[dot_idx+1] >= '5':
    avg = int(float(f_avg)) + 1
elif f_avg.find('-') != -1 and f_avg[dot_idx+1] >= '5':
    avg = int(float(f_avg)) - 1
else:
    avg = int(float(f_avg))
sys.stdout.write('%d\n' % avg)

sys.stdout.write('%d\n' % li[n//2])

for i in li:
    dic[i] += 1

d = []
s = set(li)
for i in s:
    if dic[i] == max(dic.values()):
        d.append(i)
d.sort()

if len(d) == 1:
    sys.stdout.write('%d\n' % d[0])
elif len(d) == 2:
    if d[1] <= 0:
        sys.stdout.write('%d\n' % d[1])
    else:
        sys.stdout.write('%d\n' % d[0])
else:
    sys.stdout.write('%d\n' % d[1])

sys.stdout.write('%d\n' % (max(li) - min(li)))