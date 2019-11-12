let currentUser = undefined;

function newReimbursement(){
    window.location.replace('newReimbursement.html');
}

function viewReimbursement(){
    window.location.replace('viewReimbursement.html');
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