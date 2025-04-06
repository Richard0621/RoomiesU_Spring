const url = "http://localhost:8080/"

function sendRequest(endPoint, method, data){
    let request = new XMLHttpRequest();
    request.open(method, url+endPoint);
    request.responseType = 'json' ;
    request. setRequestHeader('Content-Type' , 'application/json');
    const token = localStorage.getItem('token');
    if (token) {
        request.setRequestHeader('Authorization', 'Bearer ' + token);
    }
    request.send(data ? JSON.stringify(data): data);
    return request
}