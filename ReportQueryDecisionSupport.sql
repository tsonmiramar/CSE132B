/* ii */
with enrollment_grade as
(select e.grade, count(e.grade) as numGrade from ENROLLMENT e
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null
and c.quarter_id = 48 and s.faculty_id = 4 and c.course_id = 6 
group by e.grade
)
select (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as grade, sum(isnull(eg.numGrade,0)) as gradeCount
from GRADE_CONVERSION gc left outer join enrollment_grade eg
on gc.letter_grade = eg.grade 
group by (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end)

/* iii */
with enrollment_grade as
(select e.grade, count(e.grade) as numGrade from ENROLLMENT e
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null
and s.faculty_id = 4 and c.course_id = 6 
group by e.grade
)
select (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as grade, sum(isnull(eg.numGrade,0)) as gradeCount
from GRADE_CONVERSION gc left outer join enrollment_grade eg
on gc.letter_grade = eg.grade
group by (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end)

/* iV */
with enrollment_grade as
(select e.grade, count(e.grade) as numGrade from ENROLLMENT e
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null
and c.course_id = 6 
group by e.grade
)
select (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as grade, sum(isnull(eg.numGrade,0)) as gradeCount
from GRADE_CONVERSION gc left outer join enrollment_grade eg
on gc.letter_grade = eg.grade
group by (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end)

/* v */
with enrollment_grade as
(select e.grade, sum(gc.number_grade*e.unit)/sum(e.unit) as gpa from ENROLLMENT e
join GRADE_CONVERSION gc on e.grade = gc.letter_grade
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
where e.grade is not null
and s.faculty_id = 4 and c.course_id = 6 
group by e.grade
)
select gc.letter_grade, isnull(eg.gpa,0) as gpa from GRADE_CONVERSION gc left outer join enrollment_grade eg on gc.letter_grade = eg.grade