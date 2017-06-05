drop table if exists CPQG;
with grade_table as
(select (case when gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as gradeBase 
from GRADE_CONVERSION gc where gc.letter_grade in ('A','B','C','D','F')),
CPQG_key as
(select c.id as course_id, f.id as faculty_id, q.id as quarter_id, gt.gradeBase from COURSE c 
cross join FACULTY f cross join QUARTER q cross join grade_table gt
where f.id != 12),
enrollment_grade as
(select c.course_id, s.faculty_id, c.quarter_id, 
	(case when SUBSTRING(e.grade,1,1) like 'F' then 'other' else SUBSTRING(e.grade,1,1) end) as grade, 
	count(grade) as gradeCount from ENROLLMENT e 
	join SECTION s on e.section_id = s.id
	join CLASS c on s.class_id = c.id
	where grade is not null and faculty_id != 12
	group by c.course_id,s.faculty_id,c.quarter_id,grade)
select * into CPQG from
(select ck.*, isnull(eg.gradeCount,0) as gradeCount from
CPQG_key ck left outer join enrollment_grade eg
on ck.course_id = eg.course_id and ck.faculty_id = eg.faculty_id and ck.quarter_id = eg.quarter_id and ck.gradeBase like eg.grade) CPQG_View

drop table if exists CPG;
with grade_table as
(select (case when gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as gradeBase 
from GRADE_CONVERSION gc where gc.letter_grade in ('A','B','C','D','F')),
CPG_key as 
(select c.id as course_id, f.id as faculty_id, gt.gradeBase from COURSE c 
cross join FACULTY f 
cross join grade_table gt where f.id != 12),
enrollment_grade as
(select c.course_id, s.faculty_id, 
	(case when SUBSTRING(e.grade,1,1) like 'F' then 'other' else SUBSTRING(e.grade,1,1) end) as grade, count(grade) as gradeCount 
	from ENROLLMENT e 
	join SECTION s on e.section_id = s.id
	join CLASS c on s.class_id = c.id
	where grade is not null and faculty_id != 12
	group by c.course_id,s.faculty_id,grade)
select * into CPG from
(select ck.*, isnull(eg.gradeCount,0) as gradeCount from
CPG_key ck left outer join enrollment_grade eg
on ck.course_id = eg.course_id and ck.faculty_id = eg.faculty_id and ck.gradeBase like eg.grade) CPQG_View
GO

create or alter trigger CPG_CPQG_Insert on ENROLLMENT
after insert as
begin
update CPQG 
set gradeCount = gradeCount + 1
where course_id in ( select c.course_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and faculty_id in ( select s.faculty_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and quarter_id in ( select c.quarter_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and gradeBase in ( select (case when SUBSTRING(i.grade,1,1) like 'F' then 'other' else SUBSTRING(i.grade,1,1) end) as grade from inserted i );

update CPG
set gradeCount = gradeCount + 1
where course_id in ( select c.course_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and faculty_id in ( select s.faculty_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and gradeBase in ( select (case when SUBSTRING(i.grade,1,1) like 'F' then 'other' else SUBSTRING(i.grade,1,1) end) as grade from inserted i )
end
go 

create or alter trigger CPG_CPQG_Update on ENROLLMENT
after update as
begin
update CPQG 
set gradeCount = gradeCount + 1
where course_id in ( select c.course_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and faculty_id in ( select s.faculty_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and quarter_id in ( select c.quarter_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and gradeBase in ( select (case when SUBSTRING(i.grade,1,1) like 'F' then 'other' else SUBSTRING(i.grade,1,1) end) as grade from inserted i );

update CPG
set gradeCount = gradeCount + 1
where course_id in ( select c.course_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and faculty_id in ( select s.faculty_id from inserted i join SECTION s on i.section_id = s.id join CLASS c on s.class_id = c.id )
and gradeBase in ( select (case when SUBSTRING(i.grade,1,1) like 'F' then 'other' else SUBSTRING(i.grade,1,1) end) as grade from inserted i )

update CPQG 
set gradeCount = gradeCount - 1
where course_id in ( select c.course_id from deleted d join SECTION s on d.section_id = s.id join CLASS c on s.class_id = c.id )
and faculty_id in ( select s.faculty_id from deleted d join SECTION s on d.section_id = s.id join CLASS c on s.class_id = c.id )
and quarter_id in ( select c.quarter_id from deleted d join SECTION s on d.section_id = s.id join CLASS c on s.class_id = c.id )
and gradeBase in ( select (case when SUBSTRING(d.grade,1,1) like 'F' then 'other' else SUBSTRING(d.grade,1,1) end) as grade from deleted d );

update CPG
set gradeCount = gradeCount - 1
where course_id in ( select c.course_id from deleted d join SECTION s on d.section_id = s.id join CLASS c on s.class_id = c.id )
and faculty_id in ( select s.faculty_id from deleted d join SECTION s on d.section_id = s.id join CLASS c on s.class_id = c.id )
and gradeBase in ( select (case when SUBSTRING(d.grade,1,1) like 'F' then 'other' else SUBSTRING(d.grade,1,1) end) as grade from deleted d )
end