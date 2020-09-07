-- 루시와 엘라 찾기
SELECT animal_id, name, sex_upon_intake 
FROM animal_ins
WHERE name IN('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty');

-- 이름에 el이 들어가는 동물 찾기
SELECT animal_id, name
FROM animal_ins
WHERE name LIKE '%el%' AND animal_type = 'Dog'
ORDER BY name ASC;

-- 중성화 여부 파악하기
SELECT animal_id, name, IF(sex_upon_intake LIKE 'Intact %', 'X', 'O') AS '중성화'
FROM animal_ins
ORDER BY animal_id;

-- 오랜 기간 보호한 동물(2)
SELECT O.animal_id, O.name
FROM animal_ins I INNER JOIN animal_outs O
ON I.animal_id = O.animal_id
ORDER BY O.datetime - I.datetime DESC
LIMIT 2;

-- DATETIME에서 DATE로 형 변환
SELECT animal_id, name, date_format(datetime, '%Y-%m-%d') AS '날짜'
FROM animal_ins
ORDER BY animal_id ASC;