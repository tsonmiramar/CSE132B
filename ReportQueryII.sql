/* a */
with student_enroll_meeting as
(select m.*, c.id as class_id, wm.weekday from ENROLLMENT e 
join SECTION s on e.section_id = s.id
join CLASS c on s.class_id = c.id
join MEETING m on e.section_id = m.section_id
join WEEKLY_MEETING wm on m.id = wm.id
where e.student_id = 12 and e.grade is null),
conflict_class as
(select c.id as class_conflict_id, sem.class_id as class_enrolled_id from WEEKLY_MEETING wm
join MEETING m on wm.id = m.id
join SECTION s on m.section_id = s.id
join CLASS c on s.class_id = c.id
join QUARTER q on c.quarter_id = q.id
join QUARTER_NAME qn on q.name_id = qn.id
join student_enroll_meeting sem on wm.weekday like sem.weekday 
	and m.start_time < sem.end_time 
	and m.end_time > sem.start_time
where qn.name = 'SPRING' and q.year = 2017 
and m.id not in
(select id from student_enroll_meeting) 
group by c.id, sem.class_id)

select cs_conflict.symbol as conflict_symbol, cu_conflict.currNum as conflict_num, c_conflict.title as conflict_title, 
	   cs_enrolled.symbol as enrolled_symbol, cu_enrolled.currNum as enrolled_num, c_enrolled.title as enrolled_title 
from conflict_class cc
left join CLASS c_conflict on cc.class_conflict_id = c_conflict.id
left join COURSE cu_conflict on c_conflict.course_id = cu_conflict.id
left join COURSE_SUBJECT cs_conflict on cu_conflict.subject_id = cs_conflict.subject_id
left join CLASS c_enrolled on cc.class_enrolled_id = c_enrolled.id
left join COURSE cu_enrolled on c_enrolled.course_id = cu_enrolled.id
left join COURSE_SUBJECT cs_enrolled on cu_enrolled.subject_id = cs_enrolled.subject_id

/* b */
with student_from_section as 
(select student_id from ENROLLMENT where grade is null and section_id = 9),
current_enroll_meeting as
(select count(sfs.student_id) as num_student_enroll, m.id as meeting_id, m.start_time, m.end_time,  cast(wm.weekday as varchar) as weekday from ENROLLMENT e
join MEETING m on m.section_id = e.section_id
join WEEKLY_MEETING wm on m.id = wm.id
join student_from_section sfs on e.student_id = sfs.student_id
group by m.id, m.start_time, m.end_time, cast(wm.weekday as varchar)),
day_available_time as
(select m.id, wm.weekday, rsat.start_time, rsat.end_time from current_enroll_meeting cem
join MEETING m on cem.meeting_id = m.id
join WEEKLY_MEETING wm on m.id = wm.id
join REVIEW_SESSION_AVAILABLE_TIME rsat on m.start_time >= rsat.end_time or m.end_time <= rsat.start_time),
final_available_time_with_null as
(select wd.day, 
	case when isnull(cem.num_student_enroll,0) = 0 then rsat.start_time else dat.start_time end as start_time, 
	case when isnull(cem.num_student_enroll,0) = 0 then rsat.end_time else dat.end_time end as end_time
from WEEKDAY wd cross join REVIEW_SESSION_AVAILABLE_TIME rsat
left outer join current_enroll_meeting cem on rsat.start_time = cem.start_time and rsat.end_time = cem.end_time and wd.day = cem.weekday
left outer join day_available_time dat on dat.start_time = rsat.start_time and dat.end_time = rsat.end_time and wd.day = cast(dat.weekday as varchar))
select * from final_available_time_with_null where start_time is not null and end_time is not null
