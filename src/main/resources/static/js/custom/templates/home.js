console.log("hello home");
let code = ""
document.onkeypress = function (event) {
    code +=event.key
    if (code === "inspire123"){
        window.location.reload();
    }
}
class img {
    constructor(name, tag, delay){
        this.name = name;
        this.tag = tag;
        this.delay = delay;
    }
}


