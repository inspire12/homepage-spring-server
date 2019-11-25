

function getData(url, f) {
    // let request = {
    //     "url":url,
    // };

    fetch(url).then(data => {
        f(data)
    }).catch(e => {
        console.dir (e);
    })
}