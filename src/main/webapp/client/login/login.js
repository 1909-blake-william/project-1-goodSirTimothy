function login(event){
    event.preventDefault();
    const user = getUserFromInputs();
    console.log(user);
    fetch('http://localhost:8080/Project1/auth/login', {
        credentials: 'include',
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(res => res.json())
    .then(data => {
        console.log(data);
        if(data.role === 'MANAGER'){
            window.location = ('/Project1/client/adminViewReimbursement/adminViewReimbursement.html');
        } else {
            window.location = ('/Project1/client/employee/employee.html');
        }
        
    })
    .catch(err => document.getElementById('error-id').innerText = 'Wrong username or password');
}

function getUserFromInputs(){
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    const user = {
        username: username,
        password: password
    }

    return user
}