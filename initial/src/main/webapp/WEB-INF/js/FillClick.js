function setCommand(id1, val, id2){
    var command = document.getElementById(id1);
    var submit = document.getElementById(id2);
    
    command.value = val;
    
    submit.submit();
    window.alert(command.value + ' ' +  submit.HTML);
    return false;
}

function setCommand2(id1, val, id2){
	$('#' + id1).val(val);
	$('#' + id2).click();
	return false;
}

function setCommand1(event){
	$('#command').val('LangOnChange');
	$('#language').val(event.target.id);
	$('#myForm').submit();
	
	return false;
}

function setCommand3(val){
	$('#command').val(val);
	$('#myForm').submit();
	
	return false;
}
function setCommand4(val){
	$('#command').val(val);
	var messText =$('#noItemSelected').val();
	if ($('#notEmptselect').val() == null){
		if (!$('#msg').length){
			$('#alerts').append('<div id ="msg" class="alert alert-danger" role="alert">' +  messText +'</div>');    
		}		
	} else{
		$('#myForm').submit();
	}
	return false;
}


var numroute=[], alpharoute=[];

function deleteLast(){
	alpharoute.splice(-1, 1);
	numroute.splice(-1, 1);
	refreshSelected();
}

function add2route(event){
	var name = $('#misctab #row' + event.target.id).text();
	numroute.push(event.target.id);
	alpharoute.push(name);
	refreshSelected();
};

function refreshSelected(){
	$('#numroute').val(numroute.join(','));
	$('#alpharoute').val(alpharoute.join(','));
	$('#selectedroute').val(alpharoute.join(' - '));
}

function loadRouteArray(val){
	var arr=[];
	var alpha = val.trim();
	if (alpha.length != 0){
		arr = alpha.split(',');
	}
	return arr;
}

function loadRouteJSP(){
	alpharoute = loadRouteArray($('#alpharoute').val());
//	console.log(alpharoute);
	numroute = loadRouteArray($('#numroute').val());
	$('#selectedroute').val(alpharoute.join(' - '));
}

function doUpdate(event, comm){
//	alert(event.target.id);
	console.log(comm);
	$('#Id').val(event.target.id);
	$('#command').val(comm);
	$('#myForm').submit();
}

function upd(){
	alert($(this).id.val());
}



