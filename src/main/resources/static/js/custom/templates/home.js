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

function appendGallaryImgs() {
    let imgs = ["3", "4", "5", "6", "7", "8", "9", "10", "boyoung_test"];

    for (let i = 0; i < imgs.length; i++) {
        let singleGallayItem;
        if(i%2 === 0)
            singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i] + ".jpg", "human",500);
        else if(i%3 === 0)
            singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i] + ".jpg", "country",500);
        else if(i%5 === 0)
            singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i] + ".jpg", "nature",500);
        else
            singleGallayItem = createGallaryImg("/img/bg-img/" + imgs[i] + ".jpg", "video",500);
        document.getElementById("alime-portfolio").append(singleGallayItem);
    }

}

