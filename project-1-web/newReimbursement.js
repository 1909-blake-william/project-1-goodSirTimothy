function submitNewReimbursement(){
    console.log("BLAAAAAAHHHHHHHHH")
    /*
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let typeSelect = document.getElementById('typeSelect').selected[0].value;

    console.log(amount);
    console.log(description);
    console.log(typeSelect);
    */
}
function login(event){
    event.preventDefault();
    console.log("BLAAAAAAHHHHHHHHH")
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