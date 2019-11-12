function submitInformation(){
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
        //window.location.replace('reimbursements.html')
        console.log(data);
    })
    .catch(err => console.log(err));
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