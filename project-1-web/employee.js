let currentUser = undefined;

function newReimbursement(){
    fetch('http://localhost:8080/Project1/auth/login', {
        credentials: 'include'
    })
    .then(res => res.json())
    .then(data => {
        if(data.role === 'MANAGER'){
            
        } else {
            window.location.replace('employee.html');
        }
    })
    .catch(err => console.log(err));
}

function viewReimbursement(){
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
        if(data.role === 'MANAGER'){
            
        } else {
            window.location.replace('employee.html');
        }
    })
    .catch(err => console.log(err));
}

function getCurrentUserInfo() {
    fetch('http://localhost:8080/Project1/auth/session-user', {
        credentials: 'include'
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        // document.getElementById('users-name').innerText = data.username
        currentUser = data;
    })
    .catch(err => {
        console.log(err);
        window.location.replace('login.html'); 
    })
}

getCurrentUserInfo();