-- DDL--
-- db 생성
create database `mybase`;

-- 모든 db 출력
show databases;

-- mybase db 사용, `mybase`로 써도 됨
use mybase;

-- 데이터베이스 삭제
drop database if exists mybase;

-- 테이블 생성
create table employees (
	employee_id int primary key not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(25) not null,
    phone_number varchar(20),
    hire_date date not null,
    job_id varchar(10) not null,
    salary decimal(8, 2),
    commission_pct decimal(2, 2),
    manager_id int,
    department_id int
);

-- insert column into existing table
alter table employees add test_column int;

-- confirm table employees information
desc employees;

-- change test_column type to varchar(20) in employees table
alter table employees modify test_column varchar(20);

-- delete test_column in employees table
alter table employees drop test_column;

-- delete employees table
drop table employees;


-- DML --

-- INSERT --
-- insert data into employees table

# When all values are given
INSERT INTO employees VALUES (
	100,
    "Steven",
    "King",
    "SKING",
    "515.123.4567",
    19871017,
    "AD_PRES",
    24000,
    NULL,
    NULL,
    90
);
select * from employees;

# When some values are missing
INSERT INTO `employees` (
	employee_id,
	first_name,
	last_name,
	email,
	phone_number,
	hire_date,
	job_id,
	salary,
	manager_id,
	department_idemployees
) VALUES (
	101,
	'Neena',
	'Kochhar',
	'NKOCHHAR',
	'515.123.4568',
	DATE_FORMAT('1989-11-21', '%Y-%m-%d'),
	'AD_VP',
	17000,
	100,
	90
);

-- Change all data which commission_pct is NULL to 0.2
UPDATE employees SET commission_pct = 0.2 WHERE commission_pct IS NULL;

-- Change commission_pct to 0.5 where employee_id is 100
UPDATE employees SET commission_pct = 0.5 WHERE employee_id = 100;


-- DELETE --
-- Delete data which employee_id is 100
DELETE FROM employees WHERE employee_id = 100;

-- SELECT --
-- View all data in employees table
SELECT * from employees;

-- View only employee_id in employees table
SELECT employee_id FROM employees;

-- View first_name and last_name in employees table
SELECT first_name, last_name FROM employees;

-- View data which department_id is 90 in employees table
SELECT * from employees WHERE department_id=90;

-- View data which department_id is 90 or 60 in employees table
use ssafydb;
SELECT * from employees WHERE department_id=90 OR department_id = 60;

-- employees 테이블에서 department_id를 조회하는 SQL을 작성하시오.
select department_id from employees;

-- employees 테이블에서 department_id를 중복 없이 조회하는 SQL을 작성하시오.
select DISTINCT department_id from employees;

-- employees 테이블에서 manager_id가 NULL인 데이터를 조회하는 SQL을 작성하시오.
select * from employees where manager_id is NULL;

-- employees 테이블에서 manager_id가 NULL이 아닌 데이터를 조회하는 SQL을 작성하시오.
select * from employees where manager_id is not NULL;

-- employees 테이블에서 department_id를 부서번호로 변경하고, 중복 없이 조회하는 SQL을 작성하시오.
select distinct department_id AS 부서번호 from employees;

-- employees 테이블에서 department_id를 "부 서 번 호"로 변경하고, 중복 없이 조회하는 SQL을 작성하시오.
select distinct department_id AS "부 서 번 호" from employees;

-- employees 테이블에서 중복 없이 department_id를 내림차순으로 조회하는 SQL을 작성하시오.
select distinct department_id from employees order by department_id desc;

-- employees 테이블에서 중복 없이 department_id를 오름차순으로 조회하는 SQL을 작성하시오.
select distinct department_id from employees order by department_id asc;

-- employees 테이블에서 last_name과 first_name을 합쳐 "이름"으로 표현하고, 연봉을 계산하는 SQL을 작성하시오.
select concat(last_name, " ", first_name) as 이름, salary as 연봉 from employees;

-- employees 테이블에서 last_name과 first_name을 합쳐 "이름"으로 표현하고, 입사년도를 출력하는 SQL을 작성하시오.
select concat(last_name, " ", first_name) as 이름, hire_date as 입사년도 from employees;

-- employees 테이블에서 last_name과 first_name을 합쳐 "이름"으로 표현하고, salary가 5000이상 8000이하인 데이터를 출력하는 SQL을 작성하시오.
select concat(last_name, " ", first_name) as 이름, salary as 급여
from employees
where salary between 5000 and 8000;

-- employees 테이블에서 급여 총합과 급여 평균을 구하는 SQL을 작성하시오.
select sum(salary) , avg(salary) from employees;

-- employees 테이블에서 부서 별 급여 총합과 급여 평균을 구하는 SQL을 작성하시오.
select department_id, sum(salary), avg(salary) 
from employees
group by department_id;

-- employees 테이블에서 department_id가 null인 것을 제외하고, 부서 별 급여 총합과 급여 평균을 구하는 SQL을 작성하시오.
select department_id, sum(salary), avg(salary)
from employees
where department_id is not NULL
group by department_id;

-- employees 테이블에서 salary가 15000 초과이면 "고액 연봉" 8000 초과이면 "평균 연봉" 그렇지 않으면 "저액 연봉"을 출력하는 SQL구문을 작성하시오.
select concat(last_name, " ", first_name) as 이름, salary as 급여, 
case when salary > 8000 then "고액연봉"
else "저액연봉" end "연봉등급"
from employees;

-- employees 테이블에서 manager_id와 employee_id를 중복 없이 id 컬럼으로 출력하는 SQL을 작성하시오.
select manager_id as id from employees
UNION
select employee_id as id from employees;

-- employees 테이블에서 manager_id와 employee_id를 중복을 포함하여 id 컬럼으로 출력하는 SQL을 작성하시오.
select manager_id as id from employees
union all
select employee_id as id from employees;

-- employees 테이블에서 first_name이 5글자이면서 마지막에 s로 끝나는 데이터를 찾는 SQL 구문을 작성하시오.
select first_name from employees
where first_name like "____s";

-- employees 테이블에서 first_name에 t가 포함된 데이터를 찾는 SQL 구문을 작성하시오.
select first_name from employees
where first_name like "%t%";
