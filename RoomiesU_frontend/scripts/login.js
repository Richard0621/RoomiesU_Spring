async function validarLogin() {
    let usuario = document.getElementById("usuario").value;
    let contraseña = document.getElementById("contraseña").value;

    if (!usuario || !contraseña) {
        alert("Por favor, ingresa usuario y contraseña");
        return;
    }

    try {
        let response = await fetch("http://localhost:80080/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username: usuario, password: contraseña })
        });

        if (response.ok) {
            let data = await response.json();
            localStorage.setItem("token", data.token); // Guardar el token
            window.location.href = "inicio.html"; // Redirigir a inicio.html
        } else {
            alert("Credenciales incorrectas");
        }
    } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Hubo un problema al conectar con el servidor.");
    }
}
