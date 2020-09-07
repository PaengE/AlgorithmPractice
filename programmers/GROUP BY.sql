-- 고양이와 개는 몇 마리 있을까
SELECT animal_type, count(animal_type) AS 'count' 
FROM animal_ins 
WHERE animal_type IN ('Cat', 'Dog') 
GROUP BY animal_type ORDER BY animal_type ASC;

-- 동명 동물 수 찾기
SELECT name, count(name) AS count
FROM animal_ins 
GROUP BY name HAVING count >= 2 
ORDER BY name;

-- 입양 시각 구하기(1)
SELECT hour(datetime) AS hour, count(datetime) AS count
FROM animal_outs
GROUP BY hour HAVING hour >= 9 and hour <= 19
ORDER BY hour;

-- 입양 시각 구하기(2)
WITH RECURSIVE HOUR AS(
	SELECT 0 AS h
	UNION ALL
	SELECT h+1 FROM HOUR WHERE h<23
)
SELECT h AS HOUR, COALESCE(COUNT(ANIMAL_ID),0) AS COUNT
FROM HOUR LEFT JOIN ANIMAL_OUTS ON HOUR.h = DATE_FORMAT(DATETIME, '%k')
GROUP BY HOUR.h;