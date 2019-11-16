let currentUser = undefined;

function newReimbursement(){
    window.location = ('/Project1/client/newReimbursement/newReimbursement.html');
}

function viewReimbursement(){
    window.location = ('/Project1/client/viewReimbursement/viewReimbursement.html');
}

function logout(){
    fetch('http://localhost:8080/Project1/auth/logout', {
        credentials: 'include'
    })
    .then(res => {
        window.location = ('/Project1/client/login/login.html'); 
    })
    .catch(err => {
    })
}

function getCurrentUserInfo() {
    fetch('http://localhost:8080/Project1/auth/session-user', {
        credentials: 'include'
    })
    .then(resp => resp.json())
    .then(data => {
        currentUser = data;
    })
    .catch(err => {
        window.location = ('/Project1/client/login/login.html'); 
    })
}

getCurrentUserInfo();