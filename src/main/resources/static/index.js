$( document ).ready(function() {


	$( "#viewClient" ).click(function() {
 		var customerIdInfo = $("#customerIdInfo").val();
		$.ajax({
			type: "GET",
			url: "./clientInfo/"+ customerIdInfo,
			cache: false,
			success: function(data){
				$("#textareaGet").text(JSON.stringify(data));
			},
			error: function(error){
				$("#textareaGet").text(JSON.stringify(error));
			}
		});
	});



	$( "#createAccount" ).click(function() {
 		var customerIdAdd = $("#customerIdAdd").val();
 		var initialCredit = $("#initialCredit").val();
 		
 		var data = {
 			"customerId": parseInt(customerIdAdd),
			"initialCredit": parseFloat(initialCredit)
 		};

		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "./addAccount",
			cache: false,
			data: JSON.stringify(data),
			dataType: "json",
			success: function(resp){
				$("#textareaPost").text(JSON.stringify(resp));
			},
			error: function(error){
				$("#textareaPost").text(JSON.stringify(error));
			}
		});
	});


});

