create or alter trigger meeting_constrain on WEEKLY_MEETING
instead of insert as 
begin
	declare @class_id int, @faculty_id int, @section_id int
	select top 1 @class_id = s.class_id, 
				 @section_id = m.section_id, @faculty_id = s.faculty_id from inserted i join MEETING m on i.id = m.id join SECTION s on m.section_id = s.id 
	
	/* check if exists any meeting that conflict with at least one of the inserted meetings */
	if exists ( select m_inserted_outer.*, i_outer.weekday from inserted i_outer join MEETING m_inserted_outer on i_outer.id = m_inserted_outer.id 
				where exists 
				( select m_inserted_inner.*, i_inner.weekday from inserted i_inner join MEETING m_inserted_inner on i_inner.id = m_inserted_inner.id
				  where i_outer.id != i_inner.id 
				  and cast(i_outer.weekday as varchar) = cast(i_inner.weekday as varchar) 
				  and m_inserted_outer.start_time < m_inserted_inner.end_time 
				  and m_inserted_outer.end_time > m_inserted_inner.start_time )
			  )
		begin
			rollback transaction;
			throw 50000, 'There is at least 1 weekly meeting conflicts with other meetings of this new section',1;
		end
	else if exists ( select m_inserted.*, i.weekday from inserted i join MEETING m_inserted on i.id = m_inserted.id
					 where exists 
					 (  select * from MEETING m_current join WEEKLY_MEETING wm_current on m_current.id = wm_current.id join SECTION s on m_current.section_id = s.id
						where s.id != @section_id and s.class_id = @class_id
						and cast(i.weekday as varchar) = cast(wm_current.weekday as varchar)
						and m_inserted.start_time < m_current.end_time 
						and m_inserted.end_time > m_current.start_time )
					)
		begin
			rollback transaction;
			throw 50000, 'There is at least 1 weekly meeting of this section conflicts with other weekly meetings of other section of this class',1;
		end
	/* Check if exists any meeting from other section taught by the assigned faculty that conflict with at least one of the inserted meeting */ 
	else if exists (select m_inserted.*, i.weekday from inserted i join MEETING m_inserted on i.id = m_inserted.id  
					where exists 
					( select m_current.*,wm_current.weekday from MEETING m_current 
					  join WEEKLY_MEETING wm_current on m_current.id = wm_current.id 
					  join SECTION s on m_current.section_id = s.id 
					  join CLASS c on s.class_id = c.id
					  join QUARTER q on c.quarter_id = q.id
					  join QUARTER_NAME qn on q.name_id = qn.id
					  where s.faculty_id = @faculty_id and qn.name = 'SPRING' and q.year = 2017 and c.id != @class_id 
					  and cast(i.weekday as varchar) = cast(wm_current.weekday as varchar)
					  and m_inserted.start_time < m_current.end_time 
					  and m_inserted.end_time > m_current.start_time )
					)
		begin
			rollback transaction;
			throw 50000, 'There is at least 1 weekly meeting from this class overlaps with one of the current meeting from other class taught by the assigned professor',1
			return;
		end
	else
		begin
			print 'Insert new weekly meeting sucessfully'
			insert into WEEKLY_MEETING select * from inserted
		end
end
