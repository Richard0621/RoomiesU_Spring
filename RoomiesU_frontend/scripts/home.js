document.addEventListener("DOMContentLoaded", () => {
    let token = localStorage.getItem("token");

    if (!token) {
        alert("No tienes sesión iniciada. Redirigiendo al login...");
        window.location.href = "index.html"; // Redirige al login si no hay sesión
    }
});

function cerrarSesion() {
    localStorage.removeItem("token");
    localStorage.removeItem("rol");
    window.location.href = "index.html";
}

