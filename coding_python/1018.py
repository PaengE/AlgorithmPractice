n, m = map(int, input().split())

a = []
for i in range(n):
    b = input()
    a.append(str(b))
# str1 = 'qwewqeqw'
# str2 = 'qweqweweqwrqw'
# a.append(str1)
# a.append(str2)
# for w in range(2, 10, 2):
#     print(w)

print(a)
print('n = ', n, 'm = ', m)

# a = ['a', 'b']
# if a[0] != a[1]:
#     print(1)

min_count = -1;
for i in range(n-7):
    # print('i = ', i)
    for j in range(m-7):
        # print('j = ', j)
        count = 0
        for k in range(i, i+7):
            # print('k = ', k)
            for l in range(j, j+7, 2):
                # print('l = ',l)
                if k < n and l < m:
                    # print('qwer')
                    if a[k][l] == a[k][l+1] or a[k][l] == a[k+1][l]:
                        # print('count++')
                        count += 1
        print('count = ', count)
        if min_count == -1:
            min_count = count
        elif min_count > count:
            min_count = count
print(min_count)
