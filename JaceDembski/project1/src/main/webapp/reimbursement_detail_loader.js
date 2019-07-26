{
    
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/project1/servlet/loadReqDetail');
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 ){
            if(xhr.status === 200) {
                
                let reimDetail = JSON.parse(xhr.responseText);
                console.log(reimDetail);
                document.getElementById("Ticket_No").innerHTML = reimDetail.ticketNo;
                document.getElementById("Emp_id").innerHTML = reimDetail.empId;
                document.getElementById("Amount").innerHTML = reimDetail.amount;
                document.getElementById("Status").innerHTML = reimDetail.status;

                dateString = "";
                dateString = dateString.concat(reimDetail.lastUpdate.month);
                dateString = dateString.concat(" ").concat(reimDetail.lastUpdate.dayOfMonth);
                dateString= dateString.concat(" ").concat(reimDetail.lastUpdate.year);

                document.getElementById("Last_Update").innerHTML = dateString;
                if(reimDetail.resolvedBy =='0')
                {
                    document.getElementById("Resolved_By").innerHTML = "";
                }
                else{
                    document.getElementById("Resolved_By").innerHTML = reimDetail.resolvedBy;
                }
                    
                
                document.getElementById("Details").innerHTML = reimDetail.details;
                
                if(reimDetail.status.toUpperCase() === "PENDING")
                {
                    unhideButtonsIfManager();
                    setButtonClicks(reimDetail.ticketNo);
                }
            } else if (xhr.status === 401){
                location.assign('http://localhost:8080/project1/login.html');
            }
            else {
                console.log('Ajax request responded with http status code ' + xhr.status);
            }
        }
    };
    xhr.send();
}


function unhideButtonsIfManager() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/project1/servlet/myData');
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 ){
            if(xhr.status === 200) {
                let empData = JSON.parse(xhr.responseText);
                if(empData.accessLvl.toUpperCase() === "M")
                {
                    document.getElementById("ResolveFeatures").toggleAttribute("hidden");
                }
                else if(empData.accessLvl.toUpperCase() === "B")
                {
                    //do nothing
                }
            } else if (xhr.status === 401){
                location.assign('http://localhost:8080/project1/login.html');
            }
            else {
                console.log('Ajax request responded with http status code ' + xhr.status);
            }
        }
    };
    xhr.send();
}

function setButtonClicks(ticketNumber) {
    document.getElementById("ApproveBtn").onclick = () => {
        let xhr = new XMLHttpRequest();
    
        let params = 'ticketNo=' + ticketNumber;

        xhr.open('POST', 'http://localhost:8080/project1/servlet/approveRequest');

        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.setRequestHeader("Content-length", params.length);
        xhr.setRequestHeader("Connection", "close");

        xhr.onreadystatechange = () => {
            if(xhr.readyState === 4 ){
                if(xhr.status === 200) {
                    // Let servlet handle status update
                    let redirEndpt = xhr.getResponseHeader("returnPoint");
                    location.assign('http://localhost:8080' + redirEndpt);
                } else if (xhr.status === 401){
                    location.assign('http://localhost:8080/project1/login.html');
                }
                else {
                    console.log('Ajax request responded with http status code ' + xhr.status);
                }
            }
        };
        xhr.send(params);
    }

    document.getElementById("RejectBtn").onclick = () => {
        let xhr = new XMLHttpRequest();
    
        let params = 'ticketNo=' + ticketNumber;

        xhr.open('POST', 'http://localhost:8080/project1/servlet/rejectRequest');
        
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.setRequestHeader("Content-length", params.length);
        xhr.setRequestHeader("Connection", "close");

        
        xhr.onreadystatechange = () => {
            if(xhr.readyState === 4 ){
                if(xhr.status === 200) {
                    // Let servlet handle status update
                    let redirEndpt = xhr.getResponseHeader("returnPoint");
                    location.assign('http://localhost:8080' + redirEndpt);
                } else if (xhr.status === 401){
                    location.assign('http://localhost:8080/project1/login.html');
                }
                else {
                    console.log('Ajax request responded with http status code ' + xhr.status);
                }
            }
        };
        xhr.send(params);
    }
}
