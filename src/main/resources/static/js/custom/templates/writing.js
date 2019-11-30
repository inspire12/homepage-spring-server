function submitWriting(writingTitle) {
    let title = writingTitle.value;
    let content = CKEDITOR.instances['writing'].getData();
    console.dir(title);
    console.dir(content);
    let body = {
        "user_id":user,
        "title": title,
        "content": content
    };
    putRequest("/articles", body, (data)=>{window.location.href="/board"});
}