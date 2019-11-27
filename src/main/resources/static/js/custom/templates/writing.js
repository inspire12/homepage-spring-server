function submitWriting(writing) {
    console.dir(writing.value)
    let body = {
        "user_id":1,
        "content": writing.value
    };
    putRequest("/articles", body, (data)=>{console.dir(data)});
}