function submitNewReimbursement(){
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let typeSelect = document.getElementById('typeSelect').value;

    reimbursement = {
        amount,
        description,
        typeSelect
    }

    console.log(amount);
    console.log(description);
    console.log(typeSelect);

    fetch('http://localhost:8080/Project1/reimbursements', {
        // credentials: 'include',
        method: 'POST'
        // body: JSON.stringify(reimbursement),
        // headers: {
        //     'content-type': 'application/json'
        // }
    })
    .then(res => res.json())
    .then(data => {
        console.log(data);
    })
    .catch(err => console.log(err));
    
    console.log("trying to fetch")
}


function getCurrentUserInfo() {
    console.log("not done checking")
    fetch('http://localhost:8080/Project1/auth/session-user', {
        credentials: 'include'
    })
    .then(resp => resp.json())
    .then(data => {
        currentUser = data;
        console.log(currentUser);
    })
    .catch(err => {
        window.location.replace('/Project1/client/login/login.html'); 
    })
    console.log("done checking")
}

getCurrentUserInfo();