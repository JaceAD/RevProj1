{
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/project1/servlet/loadMyReq');
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 ){
            if(xhr.status === 200) {
                
                let reimReqs = JSON.parse(xhr.responseText);
                for(let reimReq in reimReqs) {
                    addReqToReqTable(reimReqs[reimReq]);
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

function addReqToReqTable(reimReq) {
    let newRow = document.createElement('tr');
    let ticketCol = document.createElement('td');
    let amountCol = document.createElement('td');
    let statusCol = document.createElement('td');
    let resolverCol = document.createElement('td');
    let dateCol = document.createElement('td');

    ticketCol.innerText = reimReq.ticketNo;
    amountCol.innerText = reimReq.amount;
    statusCol.innerText = reimReq.status;
    resolverCol.innerText = reimReq.resolvedBy;
    console.log(reimReq.lastUpdate);
    dateString = "";
    dateString = dateString.concat(reimReq.lastUpdate.month);
    dateString = dateString.concat(" ").concat(reimReq.lastUpdate.dayOfMonth);
    dateString= dateString.concat(" ").concat(reimReq.lastUpdate.year);
    console.log(dateString);
    dateCol.innerText = dateString;
    



    newRow.appendChild(ticketCol);
    newRow.appendChild(amountCol);
    newRow.appendChild(statusCol);
    newRow.appendChild(resolverCol);
    newRow.appendChild(dateCol);

    newRow.onclick = () => {
        let xhr = new XMLHttpRequest();
        let ticketStr = reimReq.ticketNo;
        console.log("ticket no is " + ticketStr);
        //let params = JSON.stringify({"ticketNo": ticketStr});
        let params = 'ticketNo=' + ticketStr;
        console.log("Params is " + params);
        xhr.open('POST', 'http://localhost:8080/project1/servlet/loadReqPage');
        
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.setRequestHeader("Content-length", params.length);
        xhr.setRequestHeader("Connection", "close");

        xhr.onreadystatechange = () => {
            if(xhr.readyState === 4 ){
                if(xhr.status === 200) {
                    
                    location.assign('http://localhost:8080/project1/reimbursement_detail.html');
                    
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

    if(reimReq.status.toUpperCase() === "PENDING"){
        document.getElementById('PendingTable').appendChild(newRow);
    } else {
        document.getElementById('ResolvedTable').appendChild(newRow);
    }
    
}