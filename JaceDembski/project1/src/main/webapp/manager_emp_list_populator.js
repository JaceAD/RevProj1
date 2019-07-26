{
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/project1/servlet/loadAllEmps');
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 ){
            if(xhr.status === 200) {
                
                let emps = JSON.parse(xhr.responseText);
                for(let emp in emps) {
                    addEmpToEmpTable(emps[emp]);
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

function addEmpToEmpTable(emp) {
    let newRow = document.createElement('tr');
    let empIdCol = document.createElement('td');
    let firstNameCol = document.createElement('td');
    let lastNameCol = document.createElement('td');
    


    empIdCol.innerText = emp.id;
    firstNameCol.innerText = emp.fName;
    lastNameCol.innerText = emp.lName;

    newRow.appendChild(empIdCol);
    newRow.appendChild(firstNameCol);
    newRow.appendChild(lastNameCol);
    

    newRow.onclick = () => {
        let xhr = new XMLHttpRequest();
        
        
        let empIdStr = emp.id;
        let params = 'empId=' + empIdStr;

        xhr.open('POST', 'http://localhost:8080/project1/servlet/loadEmpReqs');
        
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.setRequestHeader("Content-length", params.length);
        xhr.setRequestHeader("Connection", "close");

        xhr.onreadystatechange = () => {
            if(xhr.readyState === 4 ){
                if(xhr.status === 200) {
                    
                    //Servlet should return all reimReqs for the user clicked here
                    //Remove all non-head entries
                    let tableRoot = document.getElementById('EmpsReqTable');
                    for(var i = tableRoot.rows.length - 1; i > 0; i--)
                    {
                        tableRoot.deleteRow(i);
                    }

                    let reimReqs = JSON.parse(xhr.responseText);
                    for(let reimReq in reimReqs) {
                        addReqToEmpsReqTable(reimReqs[reimReq]);
                    }

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

    
    document.getElementById('EmployeeTable').appendChild(newRow);
}

function addReqToEmpsReqTable(reimReq) {

    

    /*
    while(tableRoot.hasChildNodes)
    {
        tableRoot.removeChild(tableRoot.firstChild)
    }

    let tableHeader = document.createElement('thead');
    let ticketHead = document.createElement('th');
    let employeeHead = document.createElement('th');
    let amountHead = document.createElement('th');
    let statusHead = document.createElement('th');
    let resolverHead = document.createElement('th');
    let dateHead = document.createElement('th');

    ticketHead.innerText("Ticket");
    employeeHead.innerText("Employee");
    amountHead.innerText("Amount");
    statusHead.innerText("Status");
    resolverHead.innerText("Resolved By");
    dateHead.innerText("Last Update");

    tableHeader.appendChild(ticketHead);
    tableHeader.appendChild(employeeHead);
    tableHeader.appendChild(amountHead);
    tableHeader.appendChild(statusHead);
    tableHeader.appendChild(resolverHead);
    tableHeader.appendChild(dateHead);

    tableRoot.appendChild(tableHeader);
    */
   
    let newRow = document.createElement('tr');
    let ticketCol = document.createElement('td');
    let employeeCol = document.createElement('td');
    let amountCol = document.createElement('td');
    let statusCol = document.createElement('td');
    let resolverCol = document.createElement('td');
    let dateCol = document.createElement('td');

    ticketCol.innerText = reimReq.ticketNo;
    employeeCol.innerText = reimReq.empId;
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
    newRow.appendChild(employeeCol);
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

    document.getElementById('EmpsReqTable').appendChild(newRow);
}