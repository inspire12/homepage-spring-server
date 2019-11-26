

function getRequest(url, f) {
    // let request = {
    //     "url":url,
    // };

    fetch(url).then(data => {
        f(data)
    }).catch(e => {
        console.dir (e);
    })
}


function putRequest(url, body, func) {

    fetch(url, {
        method: 'PUT', // *GET, POST, PUT, DELETE, etc.
        mode: 'same-origin', // no-cors, cors, *same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json',
        },
        redirect: 'follow', // manual, *follow, error
        referrer: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(body)
    }).then(data => function () {
        func(data)
    }).catch(e => {
        console.dir(e);
    })
}
