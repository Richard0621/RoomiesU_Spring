//Ingreso de usuario 
async function validarLogin() {
    let correo = document.getElementById("correo").value;
    let contraseña = document.getElementById("contraseña").value;

    if (!correo || !contraseña) {
        alert("Por favor, ingresa correo y contraseña");
        return;
    }

    try {
        let response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email: correo, password: contraseña })
        });

        if (response.ok) {
            let data = await response.json();
            localStorage.setItem("token", data.token); // Guardar el token
            window.location.href = "home.html"; // Redirigir a inicio.html
        } else {
            alert("Credenciales incorrectas");
        }
    } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Hubo un problema al conectar con el servidor.");
    }
}

//Registro de usuario
async function validarRegistro() {
    
}

