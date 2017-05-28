/* ii */
select (case when e.grade like '%+' or e.grade like '%-' or e.grade like 'F' then 'other' else e.grade end) as grade,count(*) as numGrade
from ENROLLMENT e 
join SECTION s on e.section_id = s.id
where e.grade is not null and e.grade != 'IN' and e.letter_option = 1
and s.faculty_id= 1 and s.class_id = 14
group by (case when e.grade like '%+' or e.grade like '%-' or e.grade like 'F' then 'other' else e.grade end)

/* iii */
select (case when e.grade like '%+' or e.grade like '%-' or e.grade like 'F' then 'other' else e.grade end) as grade,count(*) as numGrade from ENROLLMENT e
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null and e.grade != 'IN' and e.letter_option = 1
and s.faculty_id = 1 and c.course_id = 1
group by (case when e.grade like '%+' or e.grade like '%-' or e.grade like 'F' then 'other' else e.grade end)

/* iV */
select (case when e.grade like '%+' or e.grade like '%-'  or e.grade like 'F' then 'other' else e.grade end) as grade,count(*) as numGrade from ENROLLMENT e
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null and e.grade != 'IN' and e.letter_option = 1
and e.student_id = 1 and c.course_id = 1
group by (case when e.grade like '%+' or e.grade like '%-' or e.grade like 'F' then 'other' else e.grade end)

/* v */
select sum(gc.number_grade * e.unit)/sum(e.unit) as GPA from ENROLLMENT e
join GRADE_CONVERSION gc on gc.letter_grade = e.grade
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null and e.grade != 'IN' and e.letter_option = 1
and s.faculty_id = 1 and c.course_id = 1