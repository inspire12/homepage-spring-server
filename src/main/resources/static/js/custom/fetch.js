

function getRequest(url, f) {
    // let request = {
    //     "url":url,
    // };

    fetch(url).then(response => {
        __swalStatusMessage(status);
        let data = JSON.stringify(response);
        f(data)
    }).catch(e => {
        console.dir (e);
    })
}


function putRequest(url, body, func) {

    return fetch(url, {
        method: 'PUT', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body)
    }).then(response => {
        console.dir(response);
        let status = response.status;
        __swalStatusMessage(status);
        let data = JSON.stringify(response);
        func(data)
    }).catch(e => {
        console.dir(e);
    })
}

function __swalStatusMessage(status) {
    if (status !== 200) {
        swal("문제가 발생했습니다.","임원진들에게 알려주세요." ,"warning")
    }
}