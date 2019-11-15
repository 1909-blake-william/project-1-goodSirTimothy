let currentUser = undefined;



function logout(){
    fetch('http://localhost:8080/Project1/auth/logout', {
        credentials: 'include'
    })
    .then(res => {
        window.location.replace('/Project1/client/login/login.html'); 
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
        window.location.replace('/Project1/client/login/login.html'); 
    })
}

getCurrentUserInfo();