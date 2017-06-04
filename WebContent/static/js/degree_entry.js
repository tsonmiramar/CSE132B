$('document').ready(function(){
	/******** INITIALIZATION *********/
	$("#courseConcentration").hide();
	
	/******** EVENT HANDLER *********/
	$("#degreeType").on('change',function(){
		if ($("#degreeType option:selected").text() === 'Master'){
			$("#courseConcentration").show();
		}
		else {
			$("#courseConcentration").hide();
		}
	});
	
	$("#courseConcentration").on('click','#AddCourseBtn', function(){
		
		var courseName = $("#course option:selected").text();
		var courseId = $("#course option:selected").val();
		
		if (courseId === undefined) return;
		
		var courseBtn = "<button class=\"btn btn-default\" value=\""+courseId+"\" type=\"button\">"+ courseName+
		" <span class=\"glyphicon glyphicon-remove\"></span></button>"
		$("#concentrationList").append(courseBtn);
	});
	
	$("#courseConcentration").on('click','#concentrationList button',function(){
		$(this).remove();
	});
	
	$("#unitCategoryRequirement").on('click','#removeBtn',function(){
		$(this).closest("#categoryRequirement").remove();
	});
	
	$("#AddUnitCategoryBtn").on('click',function(){
		var categoryRequirement = $("<div id=\"categoryRequirement\"></div>").append($("#categoryRequirement").html());
		$("#unitCategoryRequirement").append(categoryRequirement);
		var removeBtn = "<div class=\"row\" > "
						+"	<button class=\"pull-right\" type=\"button\" class=\"btn btn-primary\" id=\"removeBtn\"> "
						+"			<span class=\"glyphicon glyphicon-remove\"></span> "
						+"	</button>" 
						+"</div>"
						
		$("#unitCategoryRequirement #categoryRequirement").last().prepend(removeBtn);
	});
	
	
	$("#department").on('change',function(){
		var id = $("#department option:selected").val();
	
		$.ajax({
			url:contextPath+"/course/department/"+id+"/list",
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("course list retrieved");
				console.log(data);
				$("#course").empty();
				for ( var idx in data){
					var text = data[idx].courseSubject.symbol + " " + data[idx].courseUnitNumber.currNum;
					var value = data[idx].id;
					var option = new Option(text,value);
					$("#course").append($(option));
				}
			},
			error: function(data, textStatus){
				alert("Failed to retrieve course list. Please refresh page");
			}
		});
	});
	
	$("#degree_entryForm").submit(function(e){
		e.preventDefault();
		
		var degreeRequire = {
				'degreeDepartment': {
					'id': $("#department option:selected").val()
				},
				'degree': {
					'degreeType':{
						'id' : $("#degreeType option:selected").val()
					},
					'name': $("#name").val(),
					'institution': $("#institution").val(),
					'concentrationCourseList': []
				},
				'totalUnit':{
					'unitNumber': $("#totalUnitNumber").val(),
					'unitCourseCategoryRequirement': []
					
				}
		}
		
		if ( $("#degreeType option:selected").text() === "Master"){
			$("#concentrationList button").each(function(){
				degreeRequire.degree.concentrationCourseList.push({
					'courseConcentration':{
						'id' : $(this).val()
					}
				});
			})
		}
		
		$("#unitCategoryRequirement #categoryRequirement").each(function(){
			degreeRequire.totalUnit.unitCourseCategoryRequirement.push({
				"courseCategoryRequirement" :{
					"type" : {
						'id': $(this).find("#type option:selected").val()
					},
					"minGPA" : $(this).find("#GPA").val(),
					"minUnit" : $(this).find("#minimumUnit").val()
				}
			});
		});
		
		$.ajax({
			url:$("#degree_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(degreeRequire),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new degree requirement");
			},
			error: function(data, textStatus){
				alert("Failed to insert degree requirement. Please retry");
			}
		});
	});
});