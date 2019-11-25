function submitComment(message) {
    let url = "http://localhost:8080/comments";

    console.dir(message);
    console.dir(message.value)
    let data = {
        "user_id": 1,
        "message": message.value
    };

    fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, cors, *same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json',
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrer: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data)
    }).then(data => {
        f(data)
    }).catch(e => {
        console.dir(e);
    })
}

function createCommentForm() {
    let commentFormStr = ' <form action="#" method="post">\n' +
        '                        <div class="row">\n' +
        '                            <div class="col-12">\n' +
        '                                <textarea name="message" class="message form-control mb-30" placeholder="Messages"></textarea>\n' +
        '                            </div>\n' +
        '                            <div class="col-12">\n' +
        '                                <button type="submit" class="btn alime-btn btn-2 mt-15">Send Messages</button>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                    </form>';
    let commentForm = createElementFromStr(commentFormStr);
    let messageTextarea = commentForm.getElementsByClassName("message-name")[0];
    return {"form-section": commentForm, "message": messageTextarea};
}