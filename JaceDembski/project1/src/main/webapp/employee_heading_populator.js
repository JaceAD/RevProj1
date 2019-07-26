{
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/project1/servlet/myData');
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 ){
            if(xhr.status === 200) {
                
                let empData = JSON.parse(xhr.responseText);
                document.getElementById("empNameH").innerHTML = empData.fName.concat(" ").concat(empData.lName);
                document.getElementById("empIdH").innerHTML = empData.id;
                document.getElementById("empMailH").innerHTML = empData.email;
                if(empData.accessLvl.toUpperCase() === "M")
                {
                    document.getElementById("empAccessH").innerHTML = "Manager";
                }
                else if(empData.accessLvl.toUpperCase() === "B")
                {
                    document.getElementById("empAccessH").innerHTML = "Basic";
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

