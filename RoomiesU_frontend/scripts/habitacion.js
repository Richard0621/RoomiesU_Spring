function loadHabitaciones(){
    let request = sendRequest('api/habitaciones/list', 'GET' , '')
    let table = document.getElementById('habitacion-table');
    table.innerHTML = "";
    request.onload = function (){
        let data = request.response;
        console.log(data);
        data.forEach((element, index) => {
            table.innerHTML += `
                <tr>
                    <th>${element.id}</th>
                    <td>${element.precio}</td>
                    <td>${element.descripcion}</td>
                    <td>${element.unidadVivienda ? element.unidadVivienda.nombre : "Sin asignar"}</td>
                    <td>${element.requisitos}</td>
                    <td>${element.administrador.nombre}</td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick='window. location =
                        "form_habitacion.html?idhabitacion=${element.id}"'>Ver</button>
                    </td>
                </tr>
            ` 
        });
    }
    request.onerror = function (){
        table.innerHTML = `
            <tr>
                <td colspan="5">Error al recuperar los datos. </td>
            </tr>
        `;
    }
}

//Función para cargar los datos de la habitación
function loadHabitacion(idhabitacion){
    let request = sendRequest('api/habitaciones/list/'+idhabitacion, 'GET', '')
    let id = document.getElementById('habitacion_id')
    let precio = document.getElementById('precio')
    let descripcion = document.getElementById('descripcion')
    let unidad = document.getElementById('unidad_vivienda')  
    let requisitos = document.getElementById('requisitos')
    let administrador = document.getElementById('administrador')
    request.onload = function(){
        let data = request.response;
        //Se actualiza el valor de las variables según el JSON
        console.log(data);
        id.value = data.id
        precio.value = data.precio
        descripcion.value = data.descripcion        
        unidad.value = data.unidadVivienda.nombre
        requisitos.value = data.requisitos
        administrador.value = data.administrador.nombre
    }
    request.onerror = function(){
        alert("Error al recuperar los datos.");
    }
}

//Función para editar los datos de la habitación en el formulario de edición
function saveHabitacion(){
    let id = document.getElementById('habitacion_id').value
    let precio = document.getElementById('precio').value
    let descripcion = document.getElementById('descripcion').value
    //let unidad = document.getElementById('unidad_vivienda').value
    let requisitos = document.getElementById('requisitos').value
    //let administrador = document.getElementById('administrador').value
    let data = {'id': id, 'precio': precio, 'descripcion': descripcion, 'requisitos': requisitos}
    console.log(data);
    let request = sendRequest('api/habitaciones/update', 'PUT', data)
    request.onload = function(){
        alert('Habitación actualizada exitosamente.')
        window.location = 'home.html';
    }
    request.onerror = function(){
        alert('Error al guardar los cambios.')
    }
}

//Función para crear una habitación en el formulario de edición
function createHabitacion(){
    //let id = document.getElementById('usuario_id').value
    let precio = document.getElementById('precio').value
    let descripcion = document.getElementById('descripcion').value
    //let unidad = document.getElementById('unidad_vivienda').value
    let requisitos = document.getElementById('requisitos').value
    //let administrador = document.getElementById('administrador').value
    let data = {'precio': precio, 'descripcion': descripcion, 
        'unidadVivienda': {'id': 1}, 'requisitos': requisitos, 'administrador': {'id': 1}}
    console.log(data);
    try {
        let request = sendRequest('api/habitaciones/create', 'POST', data)
        request.onload = function(){
            alert('Habitación creada exitosamente.')
            window.location = 'home.html';
        }
        request.onerror = function(){
            alert('Error al guardar los cambios.')
        }
    } catch {
        alert('Error al guardar los cambios. No tienes permiso.')
    }
}

//Función para eliminar la habitación en el formulario de edición
function deleteHabitacion(){
    let id = document.getElementById('habitacion_id').value
    let request = sendRequest('api/habitaciones/delete/'+ id , 'DELETE', '')
    request.onload = function(){
        alert('Registro Eliminado Exitosamente.')
        window.location = 'home.html';
    }
    request.onerror = function(){
        alert('Error al guardar los cambios.')
    }
}