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


function createCommentElement(comment) {

    let commentStr = '<li class="single_comment_area">\n' +
        '                            <div class="comment-content d-flex">\n' +
        '                                <div class="comment-author">\n' +
        '                                    <img src="img/bg-img/32.jpg" alt="author">\n' +
        '                                </div>\n' +
        '                                <div class="comment-meta">\n' +
        '                                    <a href="#" class="post-date">27 Aug 2019</a>\n' +
        '                                    <h5>' + comment.author.name + '</h5>\n' +
        '                                    <p>' + comment.message + '</p>\n' +
        '                                    <a href="#" class="like">Like</a>\n' +
        '                                    <a href="#" class="reply">Reply</a>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </li>';
    return createElementFromStr(commentStr);

}