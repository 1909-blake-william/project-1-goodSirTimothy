
function readFromServlet() {
    fetch('http://localhost:8080/Project1/reimbursements')
        .then(res => res.json())
        .then(data => {
            reimbursementToTable(data);
            console.log(data);
        })
        .catch(err => console.log(err));
}

function reimbursementToTable(reimbursement) {

    reimbursement.forEach(r => {
        // create the row element
        const row = document.createElement('tr');

        // create all the td elements and append them to the row
        const idData = document.createElement('td');
        idData.innerText = r.id;
        row.appendChild(idData);

        const amountData = document.createElement('td');
        amountData.innerText = r.amount;
        row.appendChild(amountData);

        const submittedData = document.createElement('td');
        let date=new Date(r.submitted);
        console.log(date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear());
        submittedData.innerText = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        row.appendChild(submittedData);

        const resolvedData = document.createElement('td');
        date=new Date(r.resolved);
        if(r.resolved !== null){
            resolvedData.innerText = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        } else {
            resolvedData.innerText = r.resolved;
        }
        row.appendChild(resolvedData);

        const descriptionData = document.createElement('td');
        descriptionData.innerText = r.description;
        row.appendChild(descriptionData);

        const authorData = document.createElement('td');
        authorData.innerText = r.author;
        row.appendChild(authorData);

        const resolverData = document.createElement('td');
        resolverData.innerText = r.resolver;
        row.appendChild(resolverData);

        const statusIdData = document.createElement('td');
        statusIdData.innerText = r.statusId;
        row.appendChild(statusIdData);

        const typeIdData = document.createElement('td');
        typeIdData.innerText = r.typeId;
        row.appendChild(typeIdData);

        // append the row into the table
        document.getElementById('reimbursement-table-body').appendChild(row);

    });
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
            window.location.replace('/Project1/client/login/login.html');
        })
}

getCurrentUserInfo();