
var Timer;
var TotalSeconds;
var VarIdInscription;


function CreateTimer(TimerID, Time, IdInscription) {
	Timer = document.getElementById(TimerID);
	TotalSeconds = Time;
	VarIdInscription = IdInscription;
	if(TotalSeconds > 0)
	{
		UpdateTimer()
		window.setTimeout("Tick()", 1000);
	}
	else
	{
		DisplayNoTimeRemain();
	}
}

function Tick() {
	TotalSeconds -= 1;
	UpdateTimer()
	if(TotalSeconds != 0)
		window.setTimeout("Tick()", 1000);
}

function DisplayNoTimeRemain() {
	var TimeStr = LeadingZero(0) + ":" + LeadingZero(0);
	Timer.innerHTML = TimeStr;
}

function UpdateTimer() {
	var Seconds = TotalSeconds;
		
	if(TotalSeconds != 0)
	{
		var Minutes = Math.floor(Seconds / 60);
		// Each minute
		if(Seconds % 60 == 0)
		{
			// Send a request to the servlet manageremainingtime which will 
			// save remaining time in the database
			$.post("manageremainingtime",{
	            timeRemaining:Minutes,
	            mode:"save",
	            idInscription:VarIdInscription
	        }).done(function( data ) {
	       	 testWrite.innerHTML = parseInt(data);
	        })
		}
		Seconds -= Minutes * (60);
	
		// each 5 secondes
		if(Seconds % 5 == 0)
		{
			// Send a request to the servlet manageremainingtime which will 
			// refresh remaining time in session
			 $.post("manageremainingtime",{
	             timeRemaining:TotalSeconds,
	             mode:"refresh"
	         })
		}
	}
	else
	{
		// Send a request to the servlet manageremainingtime which will 
		// manage the end of the test
		$.post("manageremainingtime",{
            mode:"endTest",
            idInscription:VarIdInscription
        }).done(function( data ) {
       	 testWrite.innerHTML = parseInt(data);
        })
        Minutes = 0;        
	}
	// Transmit remainingTime contraining seconds to hidden input to transmit it à the form's validation
	var hiddenField = document.getElementsByClassName("remainingTime");
	var i;
	for (i = 0; i < hiddenField.length; i++) {
		hiddenField[i].value = TotalSeconds;
	}
//	 $("#remainingTime").attr("value",TotalSeconds);
	
	// Transmit the formatted time remaining to the div Timer for display
	var TimeStr = LeadingZero(Minutes) + ":" + LeadingZero(Seconds);
	Timer.innerHTML = TimeStr;
}

function LeadingZero(Time) {
	return (Time < 10) ? "0" + Time : + Time;
}