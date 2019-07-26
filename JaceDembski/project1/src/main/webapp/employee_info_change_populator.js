{
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/project1/servlet/myData');
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 ){
            if(xhr.status === 200) {
                
                let empData = JSON.parse(xhr.responseText);
                //console.log(empData);
                document.getElementById("FirstName").setAttribute("value", empData.fName);
                document.getElementById("LastName").setAttribute("value", empData.lName);
                document.getElementById("Email").setAttribute("value", empData.email);

                if(empData.accessLvl.toUpperCase() === "M")
                {
                    document.getElementById("AccessManager").toggleAttribute("selected");
                }
                else if(empData.accessLvl.toUpperCase() === "B")
                {
                    document.getElementById("AccessBasic").toggleAttribute("selected")
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

document.getElementById("PasswordChangeLink").onclick = () => {
    location.href = "http://localhost:8080/project1/servlet/redirPassChange";
};