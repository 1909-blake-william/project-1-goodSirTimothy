let reimbursement = undefined;
let typeCheck = 'Date';

function back() {
    window.location = '/Project1/client/employee/employee.html';
}

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

function readFromServlet() {
    fetch('http://localhost:8080/Project1/reimbursements')
        .then(res => res.json())
        .then(data => {
            reimbursement = data;

            reimbursement.forEach(r => {
                reimbursementToTable(r);
            });
            console.log(data);
        })
        .catch(err => console.log(err));
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

function limitTableDisplay(i) {
    document.getElementById('reimbursement-table-body').innerText = "";
    console.log('test: ' + i)
    if (i === 0) {
        reimbursement.forEach(r => {
            reimbursementToTable(r);
        });
    } else {
        console.log('test')
        reimbursement.forEach(r => {
            if (r.statusId === i) {
                reimbursementToTable(r);
            }
        });
    }
}

function reimbursementToTable(r) {
    // create the row element
    const row = document.createElement('tr');

    // // create all the td elements and append them to the row
    // const idData = document.createElement('td');
    // idData.innerText = r.id;
    // row.appendChild(idData);

    const amountData = document.createElement('td');
    amountData.innerText = r.amount;
    row.appendChild(amountData);

    const submittedData = document.createElement('td');
    let date = new Date(r.submitted);
    console.log(date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear());
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
        statusIdData.innerText = 'Pending';
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