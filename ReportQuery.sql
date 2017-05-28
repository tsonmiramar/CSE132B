/* Report I */
/* c_i */
select cu.symbol, cs.currNum, e.grade, e.unit, qn.name, q.year from ENROLLMENT e
join SECTION s on s.id = e.section_id	
join CLASS c on c.id = s.class_id
join COURSE cs on cs.id = c.course_id
join COURSE_SUBJECT cu on cu.subject_id = cs.subject_id
join QUARTER q on q.id = c.quarter_id
join QUARTER_NAME qn on qn.id = q.name_id
where e.grade is not null and e.letter_option = 1 and e.student_id = 1
order by qn.name, q.year asc

/* c_ii */
select sum(gc.number_grade*e.unit)/sum(e.unit) as GPA, qn.name, q.year from ENROLLMENT e
join GRADE_CONVERSION gc on e.grade = gc.letter_grade
join SECTION s on s.id = e.section_id	
join CLASS c on c.id = s.class_id
join COURSE cs on cs.id = c.course_id
join COURSE_SUBJECT cu on cu.subject_id = cs.subject_id
join QUARTER q on q.id = c.quarter_id
join QUARTER_NAME qn on qn.id = q.name_id
where e.grade is not null and e.letter_option = 1 and e.student_id = 1
group by e.student_id, qn.name, q.year
order by qn.name,q.year asc

/* c_iii */
select sum(gc.number_grade*e.unit)/sum(e.unit) as Cumulative_GPA from ENROLLMENT e
join GRADE_CONVERSION gc on e.grade = gc.letter_grade
join SECTION s on s.id = e.section_id	
join CLASS c on c.id = s.class_id
join COURSE cs on cs.id = c.course_id
join COURSE_SUBJECT cu on cu.subject_id = cs.subject_id
join QUARTER q on q.id = c.quarter_id
join QUARTER_NAME qn on qn.id = q.name_id
where e.grade is not null and e.letter_option = 1 and e.student_id = 1
group by e.student_id

/* d */
select degree_unit.type_id, degree_unit.name, 
	case when degree_unit.min_unit-ISNULL(student_unit.unit_per_category,0) < 0 then 
		0 
	else 
		degree_unit.min_unit-ISNULL(student_unit.unit_per_category,0) end as remaining_unit 
from 
(select ccr.type_id, cy.name, ccr.min_unit from DEGREE d 
join UNIT_REQUIREMENT ur on ur.degree_id = d.id
join UNIT_CATEGORY uc on ur.totalunit_id = uc.id
join UNIT_COURSE_CATEGORY ucc on uc.id = ucc.unit_category_id
join COURSE_CATEGORY_REQUIREMENT ccr on ucc.course_category_id = ccr.id
join COURSE_TYPE cy on ccr.type_id = cy.id
where d.id = 5) degree_unit

left outer join 

(select ct.id, ct.name, sum(e.unit) as unit_per_category from ENROLLMENT e 
join STUDENT s on e.student_id = s.id
join SECTION st on e.section_id = st.id
join CLASS c on st.class_id = c.id
join COURSE cr on c.course_id = cr.id
join QUARTER q on c.quarter_id = q.id
join QUARTER_NAME qn on q.name_id = qn.id
join COURSE_TYPE_CATEGORY ctc on ctc.course_id = cr.id
join COURSE_TYPE ct on ctc.type_id = ct.id
where s.id = 13
group by ct.id, ct.name
) student_unit

on degree_unit.type_id = student_unit.id

/* e */
/* ii */
select student_unitSum.concentration_id, student_unitSum.name from
(select degree_unit.concentration_id, degree_unit.name, sum(student_unit.unitTaken) as studentUnitSum from 
(select mc.id as concentration_id, mc.name, c.id as course_id, ctr.min_gpa, ctr.min_unit from MS_CONCENTRATION mc 
join MS_CONCENTRATION_COURSE mcc on mc.id = mcc.concentration_id
join COURSE c on mcc.course_id = c.id
join COURSE_SUBJECT cs on c.subject_id = cs.subject_id
join MS_CONCENTRATION_REQUIREMENT mcr on mc.id = mcr.concentration_id
join COURSE_CATEGORY_REQUIREMENT ctr on mcr.course_category_id = ctr.id
where mc.degree_id = 6 ) degree_unit
left outer join 
(select  mcc.concentration_id ,sum(gc.number_grade*e.unit)/sum(e.unit) as gpa, sum(e.unit) as unitTaken, cr.id as course_id from ENROLLMENT e
join STUDENT s on e.student_id = s.id
join MASTER m on s.id = m.id
join SECTION st on e.section_id = st.id
join CLASS cs on st.class_id = cs.id
join COURSE cr on cs.course_id = cr.id
join MS_CONCENTRATION_COURSE mcc on mcc.course_id = cr.id
join COURSE_SUBJECT cb on cr.subject_id = cb.subject_id
join GRADE_CONVERSION gc on gc.letter_grade = e.grade
where e.grade is not null and s.pid = 20
group by s.pid, cr.id, mcc.concentration_id) student_unit

on degree_unit.concentration_id = student_unit.concentration_id and degree_unit.course_id = student_unit.course_id
where isnull(student_unit.gpa,0) >= degree_unit.min_gpa
group by degree_unit.concentration_id, degree_unit.name ) student_unitSum

join MS_CONCENTRATION_REQUIREMENT mcc on student_unitSum.concentration_id = mcc.concentration_id
join COURSE_CATEGORY_REQUIREMENT ccr on mcc.course_category_id = ccr.id
where student_unitSum.studentUnitSum >= ccr.min_unit;

/* iii */
with student_unit as 
(select  mcc.concentration_id ,sum(gc.number_grade*e.unit)/sum(e.unit) as gpa, sum(e.unit) as unitTaken, cr.id as course_id from ENROLLMENT e
join STUDENT s on e.student_id = s.id
join MASTER m on s.id = m.id
join SECTION st on e.section_id = st.id
join CLASS cs on st.class_id = cs.id
join COURSE cr on cs.course_id = cr.id
join MS_CONCENTRATION_COURSE mcc on mcc.course_id = cr.id
join GRADE_CONVERSION gc on gc.letter_grade = e.grade
where e.grade is not null and s.pid = 20
group by s.pid, cr.id, mcc.concentration_id),

degree_unit as 
(select mc.id as degree_concentration_id, mc.name as concentration_name, mcc.course_id as degree_course_id, ctr.min_gpa, c.unitTo as min_unit from MS_CONCENTRATION mc 
join MS_CONCENTRATION_COURSE mcc on mc.id = mcc.concentration_id
join COURSE c on mcc.course_id = c.id
join MS_CONCENTRATION_REQUIREMENT mcr on mc.id = mcr.concentration_id
join COURSE_CATEGORY_REQUIREMENT ctr on mcr.course_category_id = ctr.id
where mc.degree_id = 6 ), 
student_degree_unit as
(select degree_unit.degree_concentration_id, degree_unit.degree_course_id from
degree_unit
left outer join 
student_unit

on degree_unit.degree_concentration_id = student_unit.concentration_id and degree_unit.degree_course_id = student_unit.course_id
where student_unit.concentration_id is NULL)

select mcc.concentration_id, mc.name as concentration_name, sdu.degree_course_id from MS_CONCENTRATION_COURSE mcc 
join MS_CONCENTRATION mc on mcc.concentration_id = mc.id
left outer join student_degree_unit sdu on mcc.concentration_id = sdu.degree_concentration_id and mcc.course_id = sdu.degree_course_id;