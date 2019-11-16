let currentUser = undefined;
let reimbursements = undefined;
let typeCheck = 'Date';

function spinnerChange() {
    if (typeCheck !== document.getElementById('typeSelect').value) {
        typeCheck = document.getElementById('typeSelect').value;
        if (typeCheck === 'Date') {
            limitTableDisplay(0);
        } else if (typeCheck === 'Pending') {
            limitTableDisplay(1);
        } else if (typeCheck === 'Approved') {
            limitTableDisplay(2);
        } else if (typeCheck === 'Denied') {
            limitTableDisplay(3);
        }
    }
}

function approved(n) {
    fetch(`http://localhost:8080/Project1/reimbursements/update?reimbId=${n}&status=2`, {
        credentials: 'include',
        method: 'POST'
    })
        .then(res => {
            if (res.status === 201) {
                console.log(res.status)
                reloadTables(n, 2);
            } else {
                alert('Something Went Wrong')
            }
        })
        .catch(err => console.log(err));
}

function denied(n) {
    fetch(`http://localhost:8080/Project1/reimbursements/update?reimbId=${n}&status=3`, {
        credentials: 'include',
        method: 'POST'
    })
        .then(res => {
            if (res.status === 201) {
                reloadTables(n, 3);
            } else {
                alert('Something Went Wrong')
            }
        })
        .catch(err => console.log(err));
}

function logout() {
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
            readFromServlet();
        })
        .catch(err => {
            window.location = ('/Project1/client/login/login.html');
        })
}

getCurrentUserInfo();

function readFromServlet() {
    fetch('http://localhost:8080/Project1/reimbursements')
        .then(res => res.json())
        .then(data => {
            reimbursements = data;
            reimbursements.forEach(r => {
                reimbursementToTable(r);
            });
        })
        .catch(err => console.log(err));
}

function reloadTables(n, x) {
    document.getElementById('reimbursement-table-body').innerText = "";
    updateTableRow(reimbursements, n, x)
    reimbursements.forEach(r => {
        reimbursementToTable(r);
    });
}

function updateTableRow(reimbursements, n, x) {
    reimbursements.forEach(r => {
        console.log(r)
        if (r.id === n) {
            r.resolverName = currentUser.firstName + ', ' + currentUser.lastName;
            r.statusId = x;
            console.log(r)
        }
    });
}

function limitTableDisplay(i) {
    document.getElementById('reimbursement-table-body').innerText = "";
    console.log('test: ' + i)
    if (i === 0) {
        reimbursements.forEach(r => {
            reimbursementToTable(r);
        });
    } else {
        console.log('test')
        reimbursements.forEach(r => {
            if (r.statusId === i) {
                reimbursementToTable(r);
            }
        });
    }
}

function reimbursementToTable(r) {
    // create the row element
    const row = document.createElement('tr');

    const amountData = document.createElement('td');
    amountData.innerText = r.amount;
    row.appendChild(amountData);

    const submittedData = document.createElement('td');
    let date = new Date(r.submitted);
    submittedData.innerText = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    row.appendChild(submittedData);

    const resolvedData = document.createElement('td');
    date = new Date(r.resolved);
    if (r.resolved !== null) {
        resolvedData.innerText = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    } else {
        resolvedData.innerText = r.resolved;
    }
    row.appendChild(resolvedData);

    const descriptionData = document.createElement('td');
    descriptionData.innerText = r.description;
    row.appendChild(descriptionData);

    const authorData = document.createElement('td');
    authorData.innerText = r.autherName;
    row.appendChild(authorData);

    const resolverData = document.createElement('td');
    resolverData.innerText = r.resolverName;
    row.appendChild(resolverData);

    const statusIdData = document.createElement('td');
    if (r.statusId === 1) {
        statusIdData.innerHTML = `<button class="btn btn-lg btn-primary" type="button" onclick="approved(${r.id})">Approved</button> 
            <button class="btn btn-lg btn-primary" type="button" onclick="denied(${r.id})">Denied</button>`;
    } else if (r.statusId === 2) {
        statusIdData.innerText = 'Approved';
    } else if (r.statusId == 3) {
        statusIdData.innerText = 'Denied';
    }
    row.appendChild(statusIdData);

    const typeIdData = document.createElement('td');
    if (r.typeId === 1) {
        typeIdData.innerText = 'Lodging';
    } else if (r.typeId === 2) {
        typeIdData.innerText = 'Travel';
    } else if (r.typeId === 3) {
        typeIdData.innerText = 'Food';
    } else if (r.typeId === 4) {
        typeIdData.innerText = 'Other';
    }
    // typeIdData.innerText = r.typeId;
    row.appendChild(typeIdData);

    // append the row into the table
    document.getElementById('reimbursement-table-body').appendChild(row);
}