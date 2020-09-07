-- 이름이 없는 동물의 아이디
SELECT animal_id FROM animal_ins WHERE name IS NULL;

-- 이름이 있는 동물의 아이디
SELECT animal_id FROM animal_ins WHERE name IS NOT NULL;

-- NULL 처리하기
SELECT animal_type, coalesce(name, 'No name') AS name, sex_upon_intake 
FROM animal_ins;