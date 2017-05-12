var computeQuarterList = function(quarterFrom,yearFrom,quarterTo,yearTo,quarterJSON,quarterNameJSON){
	var quarter_id = (quarterFrom - 1) % 3; //index 0 1 2 from quarterFrom val 1 2 3
	quarterTo = (quarterTo -1) % 3;
	var year = yearFrom
	var quarterAttendList = [];
	
	while (year <= yearTo ){
		var quarterItem = {};
		
		var quarterName = quarterNameJSON[(quarter_id+1).toString()];
		if ( Object.keys(quarterJSON).length > 0 && quarterJSON[quarterName][year] !== undefined){
			quarterItem["id"] = quarterJSON[quarterName][year];
		}
		else {
			quarterItem["quarterName"] = {
					"id" : quarter_id + 1
			};
			quarterItem["year"] = year;
		}
		
		quarterAttendList.push(quarterItem);
		
		if ( year === yearTo && quarter_id === quarterTo) break;
		
		year = quarter_id === 0 ? year + 1 : year;
		quarter_id = (quarter_id + 1) % 3;
	}
	
	return quarterAttendList;
}

var toQuarterJSONMapping = function(quarterList){
	var quarterJSON = {};
	
	for ( var i in quarterList){
		var name = quarterList[i].quarterName.name;
		var id = quarterList[i].id;
		var year = quarterList[i].year.toString();
		
		if (quarterJSON[name] === undefined)
			quarterJSON[name] = {};
		
		quarterJSON[name][year] = id;
	}
	
	return quarterJSON;
}

var toQuarterNameJSONMapping = function(quarterNameList){
	var quarterNameJSON = {};
	
	for ( var i in quarterNameList){
		var name = quarterNameList[i].name;
		var id = quarterNameList[i].id.toString();
		quarterNameJSON[id] = name;
	}
	
	return quarterNameJSON;
}