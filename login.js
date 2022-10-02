document.getElementById("register-btn").addEventListener("click", () => {
    const data = {
        id: Math.random().toString(16).slice(2),
        name: document.getElementById("register-name").value,
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
    fetch("http://localhost:8080/go-cheeta-backend/customer", options);
    window.open(
        'https://static.wixstatic.com/media/4a1d5b_5361da33eab146479729473ed7a5d8ce~mv2.gif',
        '_blank' 
      );
})
