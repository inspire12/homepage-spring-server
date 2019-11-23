console.log("hello home");

class img {
    constructor(name, tag, delay){
        this.name = name;
        this.tag = tag;
        this.delay = delay;
    }
}

function appendFollowAreaImgs(){
    let imgs = ['boyoung_test', "3","18","4","5","6","7"];
    for (let i=0; i  < imgs.length; i++){
        document.getElementById("instagram-feed-area-id").append(createFollowAreaImg(imgs[i]));
    }
}
function createGallaryImgModel (name, tag, delay) {
    let model = {};
    model.name = name;
    model.tag = tag;
    model.delay = delay;
    return model;
}
function appendGallaryImgs() {

    let imgs = [createGallaryImgModel("3", "human", 500), createGallaryImgModel("4", "country", 200)
        , createGallaryImgModel("boyoung_test", "human", 700)];

    for (let i = 0; i < imgs.length; i++) {
        let singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i].name + ".jpg", imgs[i].tag,imgs[i].delay);
        document.getElementById("alime-portfolio").append(singleGallayItem);
    }

}

