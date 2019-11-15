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
            window.location.replace('/Project1/client/admin/admin.html');
        } else {
            window.location.replace('/Project1/client/employee/employee.html');
        }
    })
    .catch(document.getElementById('error-id').innerText = 'An input field was left empty');
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