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