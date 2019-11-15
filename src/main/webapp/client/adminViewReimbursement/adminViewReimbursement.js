let currentUser = undefined;

function denied(){
    alert('denied');
}
function approved(){
    alert('approved')
}

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
        readFromServlet();
    })
    .catch(err => {
        window.location.replace('/Project1/client/login/login.html'); 
    })
}

getCurrentUserInfo();

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

        // // create all the td elements and append them to the row
        // const idData = document.createElement('td');
        // idData.innerText = r.id;
        // row.appendChild(idData);

        const amountData = document.createElement('td');
        amountData.innerText = r.amount;
        row.appendChild(amountData);

        const submittedData = document.createElement('td');
        let date=new Date(r.submitted);
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
        authorData.innerText = r.autherName;
        row.appendChild(authorData);

        const resolverData = document.createElement('td');
        resolverData.innerText = r.resolverName;
        row.appendChild(resolverData);

        const statusIdData = document.createElement('td');
        if(r.statusId === 1){
            statusIdData.innerHTML = `<button class="btn btn-lg btn-primary" type="button" onclick="approved()">Approved</button> <button class="btn btn-lg btn-primary" type="button" onclick="denied()">Denied</button>`;
        } else if(r.statusId === 2){
            statusIdData.innerText = 'Approved';
        } else if (r.statusId == 3){
            statusIdData.innerText = 'Denied';
        }
        row.appendChild(statusIdData);

        const typeIdData = document.createElement('td');
        if(r.typeId === 1){
            typeIdData.innerText = 'Lodging';
        } else if (r.typeId === 2){
            typeIdData.innerText = 'Travel';
        } else if (r.typeId === 3){
            typeIdData.innerText = 'Food';
        } else if (r.typeId === 4){
            typeIdData.innerText = 'Other';
        }
        // typeIdData.innerText = r.typeId;
        row.appendChild(typeIdData);

        // append the row into the table
        document.getElementById('reimbursement-table-body').appendChild(row);
    });
}