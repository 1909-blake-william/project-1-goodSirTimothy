function submitNewReimbursement(event) {
    event.preventDefault();
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let typeSelect = document.getElementById('typeSelect').value;
    let typeId;
    if (typeSelect === 'Lodging') {
        typeId = 1;
    } else if (typeSelect === 'Travel') {
        typeId = 2;
    } else if (typeSelect === 'Food') {
        typeId = 3;
    } else if (typeSelect === 'Other') {
        typeId = 4;
    }

    reimbursement = {
        amount,
        description,
        author: currentUser.userId,
        statusId: 1,
        typeId
    }

    console.log(reimbursement);
        fetch('http://localhost:8080/Project1/reimbursements', {
            credentials: 'include',
            method: 'POST',
            body: JSON.stringify(reimbursement),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(res => {
            if (res.status === 201) {
                window.location.replace('/Project1/client/employee/employee.html');
            } else {
                document.getElementById('error-id').InnerText = 'Something went wrong with input...';
            }
        })
}

function back() {
    window.location.replace('/Project1/client/employee/employee.html');
}


function getCurrentUserInfo() {
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
}

getCurrentUserInfo();