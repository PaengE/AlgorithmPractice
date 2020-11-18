-- 모든 레코드 조회하기
SELECT * FROM ANIMAL_INS ORDER BY ANIMAL_ID ASC;

-- 역순 정렬하기
SELECT name, datetime FROM animal_ins ORDER BY animal_id DESC;

-- 아픈 동물 찾기
SELECT animal_id, name FROM animal_ins WHERE intake_condition = 'Sick';

-- 어린 동물 찾기
SELECT animal_id, name FROM animal_ins WHERE intake_condition != 'Aged';

-- 동물의 아이디와 이름
SELECT animal_id, name FROM animal_ins ORDER BY animal_id ASC;

-- 여러 기준으로 정렬하기
SELECT ANIMAL_ID, name, datetime FROM animal_ins ORDER BY name ASC, datetime DESC;

-- 상위 n개 레코드
SELECT name FROM animal_ins ORDER BY datetime LIMIT 1;