function submitNewReimbursement(){
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let typeSelect = document.getElementById('typeSelect').value;

    console.log(amount);
    console.log(description);
    console.log(typeSelect);
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