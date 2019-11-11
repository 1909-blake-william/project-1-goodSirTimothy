function submitInformation(){
    let username = document.getElementById('username');
    let password = document.getElementById('password');
    console.log(username.value + ', ' + password.value);
    // fetch('http://api.icndb.com/jokes/random?limitTo=[nerdy]')
    //     .then(resp => resp.json())
    //     .then(data => {
    //         document.getElementById('chuck-norris-joke').innerText = data.value.joke;
    //         console.log(data);
    //     })
    //     .catch(console.log);
}