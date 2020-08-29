var txt = document.getElementById('txt');
var list = document.getElementById('list');
var items = [];

txt.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        let val = txt.value;
        checkTagTxt(val);
    }
});

function checkTagTxt(val) {
    if (val !== '') {
        if (items.indexOf(val) >= 0) {
            alert('Tag name is a duplicate');
            return;
        }

        if (val.includes("/")) {
            let tag = {
                "name": val.split("/")[0],
                "value": val.split("/")[1]
            };
            items.push(tag);
            render();
            txt.value = '';
            txt.focus();
        } else {
            alert("/ 로 value name 를 나눠주세요");
        }

    } else {
        alert('Please type a tag Name');
    }
}

function render() {
    list.innerHTML = '';
    items.map((item, index) => {
        list.innerHTML += `<li><span>${item.name} / ${item.value}</span><a href="javascript: remove(${index})">X</a></li>`;
    });
}

function remove(i) {
    items = items.filter(item => items.indexOf(item) != i);
    render();
}

window.onload = function () {
    render();
    txt.focus();
};
