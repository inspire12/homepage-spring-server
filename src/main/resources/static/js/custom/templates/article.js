
function main() {


    document.getElementById("blog-details-text-id")
    
}

function createCommentForm () {
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
    let commentForm = el(commentFormStr);

    let messageTextarea = commentForm.getElementsByClassName("message-name")[0];

    return {"form-section": commentForm, "message": messageTextarea};
}