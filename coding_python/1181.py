import sys

n = int(input())

input_str = set()
b = set()

for i in range(n):
    a = sys.stdin.readline().rstrip()
    input_str.add(a)
    b.add(len(a))

input_str_len = sorted([i for i in b])

ll = []
for length in input_str_len:
    lll = []
    for s in input_str:
        if len(s) == length:
            lll.append(s)
    lll.sort()
    ll.extend(lll)

for s in ll:
    sys.stdout.write('%s\n' % s)