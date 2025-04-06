function loadAdmins(){
    let request = sendRequest('api/user/list', 'GET' , '')
    let table = document.getElementById('administrador-table');
    table.innerHTML = "";
    request.onload = function (){
        let data = request.response;
        console.log(data);
        data.forEach((element, index) => {
            if(element.roles.map(r => r.name).join(", ") == "ADMIN_PENSION"){
            table.innerHTML += `
                <tr>
                    <th>${element.id}</th>
                    <td>${element.roles.map(r => r.name).join(", ")}</td>
                    <td>${element.username}</td>
                    <td>${element.nombre}</td>
                    <td>${element.apellido}</td>
                    <td>${element.identificacion}</td>
                    <td>${element.email}</td>
                    <td>${element.telefono}</td>
                    <td>${element.edad}</td>
                    <td>${element.descripcion}</td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick='window. location =
                        "form_proveedores.html?idproveedor=${element.id}"'>Ver</button>
                    </td>
                </tr>
            ` 
        }});
    }
    request. onerror = function (){
        table.innerHTML = `
            <tr>
                <td colspan="5">Error al recuperar los datos. </td>
            </tr>
        `;
    }
}

function loadEstudiantes(){
    let request = sendRequest('api/user/list', 'GET' , '')
    let table = document.getElementById('administrador-table');
    table.innerHTML = "";
    request.onload = function (){
        let data = request.response;
        console.log(data);
        data.forEach((element, index) => {
            if(element.roles.map(r => r.name).join(", ") == "ESTUDIANTE"){
            table.innerHTML += `
                <tr>
                    <th>${element.id}</th>
                    <td>${element.roles.map(r => r.name).join(", ")}</td>
                    <td>${element.username}</td>
                    <td>${element.nombre}</td>
                    <td>${element.apellido}</td>
                    <td>${element.identificacion}</td>
                    <td>${element.email}</td>
                    <td>${element.telefono}</td>
                    <td>${element.edad}</td>
                    <td>${element.descripcion}</td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick='window. location =
                        "form_proveedores.html?idproveedor=${element.id}"'>Ver</button>
                    </td>
                </tr>
            ` 
        }});
    }
    request. onerror = function (){
        table.innerHTML = `
            <tr>
                <td colspan="5">Error al recuperar los datos. </td>
            </tr>
        `;
    }
}