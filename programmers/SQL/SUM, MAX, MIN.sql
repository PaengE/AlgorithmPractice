-- 최댓값 구하기
SELECT max(datetime) AS 시간 FROM animal_ins;

-- 최솟값 구하기
SELECT min(datetime) AS 시간 FROM animal_ins;

-- 동물 수 구하기
SELECT count(*) FROM animal_ins;

-- 중복 제거하기
SELECT count(distinct name) FROM animal_ins;