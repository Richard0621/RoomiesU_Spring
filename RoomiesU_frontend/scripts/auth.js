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
    let rol = document.getElementById("rolInput").value;
    if (!rol) {
        alert("Por favor, selecciona un rol");
        return;
    }
    let username = document.getElementById("nombre_usuario").value;
    let nombre = document.getElementById("nombre").value;
    let apellido = document.getElementById("apellido").value;
    let identificacion = document.getElementById("identificacion").value;
    let correo = document.getElementById("correo").value;
    let contraseña = document.getElementById("contraseña").value;
    let telefono = document.getElementById("telefono").value;

    if (!correo || !contraseña || !nombre || !apellido || !identificacion || !telefono || !username) {
        alert("Por favor, completa todos los campos obligatorios");
        //alert("Por favor, ingresa todos los datos correctamente");
        return;
    }

    if (contraseña.length < 8) {
        alert("La contraseña debe tener al menos 8 caracteres");
        return;
    }
    if (telefono.length < 10) {
        alert("El teléfono debe tener al menos 10 dígitos");
        return;
    }
    if (identificacion.length < 10) {
        alert("La identificación debe tener al menos 10 dígitos");
        return;
    }
    if (isNaN(telefono) || isNaN(identificacion)) {
        alert("El teléfono y la identificación deben ser números");
        return;
    }

    try {
        let response = await fetch("http://localhost:8080/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username,
                nombre, 
                apellido,  
                password: contraseña, 
                identificacion, 
                email: correo,
                telefono, 
                edad: null,
                descripcion: "",
                "isAvailable": true,
                roles: [rol] })
        });

        if (response.ok) {
            let data = await response.json();
            alert("Usuario registrado correctamente");
            //localStorage.setItem("token", data.token); // Guardar el token
            window.location.href = "index.html"; // Redirigir a inicio.html
        } else {
            alert("Digite los datos correctamente");
        }
    } catch (error) {
        console.error("Error al registrar nuevo usuario: ", error);
        alert("Hubo un problema al conectar con el servidor.");
    }
}

function toggleCollapse() {
    document.getElementById("rolCollapse").classList.toggle("active");
}

function selectRol(rol) {
    document.getElementById("rolInput").value = rol;
    document.getElementById("rolCollapse").classList.remove("active");
}

