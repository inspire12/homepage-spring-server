console.log("hello home");

class img {
    constructor(name, tag, delay){
        this.name = name;
        this.tag = tag;
        this.delay = delay;
    }
}

function appendGallaryImgs() {
    let imgs = ["3", "4", "5", "6", "7", "8", "9", "10", "boyoung_test"];

    for (let i = 0; i < imgs.length; i++) {
        let singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i] + ".jpg", "",500);
        document.getElementById("alime-portfolio").append(singleGallayItem);
    }

}

