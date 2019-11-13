

function getData(url, f) {
    let request = {
        "url":url,
    };

    fetch(request).then(data => {
        f(data)
    }).catch(e => {
        console.dir (e);
    })
}