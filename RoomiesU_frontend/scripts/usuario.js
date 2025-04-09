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
                        "form_usuario.html?idusuario=${element.id}"'>Ver</button>
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
                        <button type="button" class="btn btn-primary" onclick='window.location =
                        "form_usuario.html?idusuario=${element.id}"'>Ver</button>
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

//Función para cargar los datos del usuario 
function loadUsuario(idusuario){
    let request = sendRequest('api/user/list/'+idusuario, 'GET', '')
    let id = document.getElementById('usuario_id')
    let rol = document.getElementById('rol')
    let username = document.getElementById('username')
    let nombre = document.getElementById('nombre')
    let apellido = document.getElementById('apellido')
    let identificacion = document.getElementById('identificacion')
    let email = document.getElementById('email')
    let telefono = document.getElementById('telefono')
    let edad = document.getElementById('edad')
    let descripcion = document.getElementById('descripcion')
    request.onload = function(){
        let data = request.response;
        //Se actualiza el valor de las variables según el JSON
        console.log(data);
        id.value = data.id
        rol.value = data.roles.map(r => r.name).join(", ")
        username.value = data.username
        nombre.value = data.nombre
        apellido.value = data.apellido
        identificacion.value = data.identificacion 
        email.value = data.email
        telefono.value = data.telefono
        edad.value = data.edad
        descripcion.value = data.descripcion
    }
    request.onerror = function(){
        alert("Error al recuperar los datos.");
    }
}

//Función para editar los datos del usuario en el formulario de edición
function saveUsuario(){
    let id = document.getElementById('usuario_id').value
    //let rol = document.getElementById('rol').value
    let username = document.getElementById('username').value
    let nombre = document.getElementById('nombre').value
    let apellido = document.getElementById('apellido').value
    let identificacion = document.getElementById('identificacion').value
    let email = document.getElementById('email').value
    let password = document.getElementById('password').value
    let telefono = document.getElementById('telefono').value
    let edad = document.getElementById('edad').value
    let descripcion = document.getElementById('descripcion').value
    let data = {'id': id, 'username':username,'nombre': nombre, 
        'apellido': apellido, 'identificacion': identificacion, 'email': email,
        'password': password, 'telefono': telefono, 'edad': edad, 'descripcion': descripcion}
    console.log(data);
    let request = sendRequest('api/user/update', 'PUT', data)
    request.onload = function(){
        alert('Usuario actualizado exitosamente.')
        window.location = 'home.html';
    }
    request.onerror = function(){
        alert('Error al guardar los cambios.')
    }
}

//Función para crear los datos del usuario en el formulario de edición
function createUsuario(){
    //let id = document.getElementById('usuario_id').value
    let rol = document.getElementById('rol').value
    let username = document.getElementById('username').value
    let nombre = document.getElementById('nombre').value
    let apellido = document.getElementById('apellido').value
    let identificacion = document.getElementById('identificacion').value
    let email = document.getElementById('email').value
    let password = document.getElementById('password').value
    let telefono = document.getElementById('telefono').value
    let edad = document.getElementById('edad').value
    let descripcion = document.getElementById('descripcion').value
    let data = {'roles': [rol], 'username':username,'nombre': nombre, 
        'apellido': apellido, 'identificacion': identificacion, 'email': email,
        'password': password, 'telefono': telefono, 'edad': edad, 'descripcion': descripcion}
    console.log(data);
    let request = sendRequest('api/user/create', 'POST', data)
    request.onload = function(){
        alert('Usuario creado exitosamente.')
        window.location = 'home.html';
    }
    request.onerror = function(){
        alert('Error al guardar los cambios.')
    }
}

//Función para eliminar el usuario en el formulario de edición
function deleteUsuario(){
    let id = document.getElementById('usuario_id').value
    let request = sendRequest('api/user/delete/'+ id , 'DELETE', '')
    request.onload = function(){
        alert('Registro Eliminado Exitosamente.')
        window.location = 'home.html';
    }
    request.onerror = function(){
        alert('Error al guardar los cambios.')
    }
}