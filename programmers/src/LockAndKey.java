import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LockAndKey {
    public boolean solution(int[][] key, int[][] lock) {
        // lock 의 홈의 개수 계산
        int hole = 0;
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) {
                    hole++;
                }
            }
        }

        // lock 에 패딩을 주어 확장
        int[][] extendedLock = extendLock(lock, key.length);

        // 확장된 lock 에서 key 를 흘러가듯 밀면서 비교
        if (check(extendedLock, key, hole)) {
            return true;
        }

        // key 를 90도, 180도, 270도 회전시킨 후, 확장된 lock 에서 key 를 흘러가듯 밀면서 비교
        for (int i = 0; i < 3; i++) {
            key = rotateKey(key);
            if (check(extendedLock, key, hole)) {
                return true;
            }
        }

        return false;
    }

    // 확장된 lock 에서 주어진 key 를 흘러가듯 밀면서 홈과 일치하는 곳이 홈의 개수와 같은지를 검사
    private boolean check(int[][] extendedLock, int[][] key, int hole) {
        int extendedLockSize = extendedLock.length;
        int keySize = key.length;

        for (int i = 0; i < extendedLockSize - keySize + 1; i++) {
            for (int j = 0; j < extendedLockSize - keySize + 1; j++) {
                int count = 0;
                for (int k = 0; k < keySize; k++) {
                    for (int l = 0; l < keySize; l++) {
                        // (i + k, j + l)이 원래 lock 안의 인덱스라면
                        if (rangeInOriginalLock(i + k, j + l, extendedLockSize, keySize)) {
                            // key 의 돌기와 lock 의 홈이 맞으면 count++
                            if (extendedLock[i + k][j + l] == 0 && key[k][l] == 1) {
                                count++;
                            }
                            // key 의 돌기와 lock 의 돌기가 만나면 안되므로
                            // break 를 걸어야 하나 이중포문을 탈출하기가 마땅치 않아 간단히 오답처리를 위해 count--
                            else if (extendedLock[i + k][j + l] == 1 && key[k][l] == 1) {
                                count--;
                            }
                        }
                    }
                }

                if (count == hole) {
                    return true;
                }
            }
        }
        return false;
    }

    // 확장된 lock 에서 (x, y) 가 원래 lock 안의 위치인지를 판별
    private boolean rangeInOriginalLock(int x, int y, int extendedLockSize, int keySize) {
        if (x < keySize - 1 || extendedLockSize - keySize + 1 < x) {
            return false;
        }

        if (y < keySize - 1 || extendedLockSize - keySize + 1 < y) {
            return false;
        }

        return true;
    }

    // lock 에 패딩을 주어 확장시킴
    private int[][] extendLock(int[][] lock, int keySize) {
        int[][] extendLock = new int[lock.length + (keySize - 1) * 2][lock.length + (keySize - 1) * 2];
        for (int[] t : extendLock) {
            Arrays.fill(t, -1);
        }

        for (int i = keySize - 1; i < keySize - 1 + lock.length; i++) {
            for (int j = keySize - 1; j < keySize - 1 + lock.length; j++) {
                extendLock[i][j] = lock[i - keySize + 1][j - keySize + 1];
            }
        }

        return extendLock;
    }

    // 시계방향 90도 회전
    private int[][] rotateKey(int[][] key) {
        // 2차원배열 깊은 복사
        int[][] copyKey = Arrays.stream(key).map(int[]::clone).toArray(int[][]::new);

        int size = key.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                key[j][size - i - 1] = copyKey[i][j];
            }
        }

        return key;
    }

    @Test
    public void test() {
        Assertions.assertEquals(true,
                solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }
}
