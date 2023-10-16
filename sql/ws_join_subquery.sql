use ssafydb;
select * from city;
select * from country;
select * from countrylanguage;

-- 1. 도시명 kabul이 속한 국가의 이름은?
select country.Code as code, country.Name as name
from country inner join city
on country.Code = city.CountryCode and city.Name = 'Kabul';

-- 2. 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가의 이름으로 오름차순 정렬한다.(8건)
select cnt.Name, cl.Language, cl.Percentage
from country as cnt inner join countrylanguage as cl
on cl.Percentage = 100 and cl.IsOfficial = "T" and cl.CountryCode = cnt.Code
order by cnt.Name;

-- 3. 도시명 amsterdam에서 사용되는 주요 언어와 amsterdam이 속한 국가는?
select city.Name, cl.Language, ct.Name
from city
inner join countrylanguage as cl on cl.CountryCode = city.CountryCode and city.Name = "Amsterdam" and cl.IsOfficial = "T"
inner join country as ct on ct.Code = city.CountryCode;

-- 4.     국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 출력하지 않는다. (3건)
select ct.Name, ct.Capital, city.Name as "수도", city.Population as "수도인구"
from country as ct
inner join city on ct.Name like "united%" and ct.Capital = city.ID;

-- 5.     국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 수도 없음이라고 출력한다. (4건)
select ct.Name, ct.Capital, city.Name as "수도", city.Population as "수도인구"
from country as ct
left join city on  ct.capital = city.id
where ct.Name like "united%";

-- 6.     국가 코드 che의 공식 언어 중 가장 사용률이 높은 언어보다 사용율이 높은 공식언어를 사용하는 국가는 몇 곳인가?


-- 7.     국가명 south korea의 공식 언어는?
select language from countrylanguage as cl
left join country on cl.CountryCode = country.Code
where country.Name = "South Korea" and cl.IsOfficial = "T";

-- 8.     국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. (3건)
select ctry.name, code, count(ct.countryCode) as "도시개수"
from country as ctry
inner join city as ct on ctry.Code = ct.CountryCode
where ctry.Name = "bo%"
group by ctry.Name;


select * from city;
select * from country;
select * from countrylanguage;
-- 9.     국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. 도시가 없을 경우는 단독 이라고 표시한다.(4건)

-- 10.  인구가 가장 많은 도시는 어디인가?

-- 11.  가장 인구가 적은 도시의 이름, 인구수, 국가를 출력하시오.

-- 12.  KOR의 seoul보다 인구가 많은 도시들을 출력하시오.

-- 13.  San Miguel 이라는 도시에 사는 사람들이 사용하는 공식 언어는?

-- 14.  국가 코드와 해당 국가의 최대 인구수를 출력하시오. 국가 코드로 정렬한다.(232건)


-- 15.  국가별로 가장 인구가 많은 도시의 정보를 출력하시오. 국가 코드로 정렬한다.(232건)


-- 16.  국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오.(239건)


-- 17.  위 쿼리의 내용이 자주 사용된다. 재사용을 위해 위 쿼리의 내용을 summary라는 이름의 view로 생성하시오.


-- 18. summary에서 code가 KOR인 국가의 대표도시를 조회하시오.
