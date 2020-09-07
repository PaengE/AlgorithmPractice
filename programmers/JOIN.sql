-- 없어진 기록 찾기
SELECT outs.animal_id, outs.name
FROM animal_outs AS outs LEFT OUTER JOIN animal_ins AS ins 
ON outs.animal_id = ins.animal_id
WHERE ins.animal_id IS NULL;

-- 있었는데요 없었습니다
SELECT O.animal_id, O.name
FROM animal_ins I JOIN animal_outs O 
ON I.animal_id = O.animal_id
WHERE I.datetime > O.datetime
ORDER BY I.datetime ASC;

-- 오랜 기간 보호한 동물(1)
SELECT I.name, I.datetime
FROM animal_ins I LEFT OUTER JOIN animal_outs O 
ON I.animal_id = O.animal_id
WHERE O.animal_id is null
ORDER BY I.datetime ASC
LIMIT 3;

-- 보호소에서 중성화한 동물
SELECT O.animal_id, O.animal_type, O.name
FROM animal_ins I INNER JOIN animal_outs O
ON I.animal_id = O.animal_id
WHERE I.sex_upon_intake IN('Intact Female', 'Intact Male') 
	AND O.sex_upon_outcome IN('Spayed Female', 'Neutered Male')
ORDER BY O.animal_id ASC;