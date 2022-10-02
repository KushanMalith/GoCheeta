document.getElementById("login-btn").addEventListener("click", () => {
    console.log(document.getElementById("register-email").value)
    console.log(document.getElementById("register-password").value)
    
    const data = {
        id: 128192,
        email: document.getElementById("register-email").value,
        mobile: document.getElementById("register-mobile").value,
        password: document.getElementById("register-password").value
    }

    const options = {
        method : "POST", 
        headers : {
            "content-type" : "application/json"
        },
        body : JSON.stringify(data)
    };
    fetch("http://localhost:8080/go-cheeta-service/customer", options);
})

