let successFiles = [];

function createDropzone() {
    let previewNode = document.querySelector("#template");
    previewNode.id = "";
    let previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);
    let myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
        url: "/files", // Set the url
        thumbnailWidth: 80,
        thumbnailHeight: 80,
        parallelUploads: 20,
        previewTemplate: previewTemplate,
        autoQueue: false, // Make sure the files aren't quexued until manually added
        previewsContainer: "#previews", // Define the container to display the previews
        clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
    });

    myDropzone.on("addedfile", function (file) {
        // Hookup the start button
        file.previewElement.querySelector(".start").onclick = function () {
            myDropzone.enqueueFile(file);
        };
        let path = "upload-dir/" + file.name
        console.dir(path)
    });
    myDropzone.on("success", function (file, response) {
        console.dir(response);
        let uploadFile = response['upload-file'];

        let successFile = {
            "file-url": "files/" + uploadFile,
            "filename": uploadFile,
            "type": file.type
        };
        successFiles.push(successFile);
        let dz_insert_button = '<button type="button" class="btn btn-info btn-xs"><span class="glyphicon"></span> 본문에 넣기 </button>';
        let insert_button = Dropzone.createElement(dz_insert_button);
        let _this = this;
        insert_button.addEventListener('click', function (e) {
            e.preventDefault(); // click이벤트 외의 이벤트 막기위해
            e.stopPropagation(); // 부모태그로의 이벤트 전파를 중지 // 파일 업로드시 장애가 발생한 경우
            if (file['status'] !== 'error') { // Send Client Event Delete//
            }
            insertUploadedImg(uploadFile)
        });
        file.previewElement.appendChild(insert_button);

    });
// Update the total progress bar
    myDropzone.on("totaluploadprogress", function (progress) {
        document.querySelector("#total-progress .progress-bar").style.width = progress + "%";
    });

    myDropzone.on("sending", function (file) {
        // Show the total progress bar when upload starts
        document.querySelector("#total-progress").style.opacity = "1";
        // And disable the start button
        file.previewElement.querySelector(".start").setAttribute("disabled", "disabled");
    });

// Hide the total progress bar when nothing's uploading anymore
    myDropzone.on("queuecomplete", function (progress) {
        document.querySelector("#total-progress").style.opacity = "0";
    });
// Setup the buttons for all transfers
// The "add files" button doesn't need to be setup because the config
// `clickable` has already been specified.
    document.querySelector("#actions .start").onclick = function () {
        myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
    };
    document.querySelector("#actions .cancel").onclick = function () {
        myDropzone.removeAllFiles(true);
        successFiles = []
    };
    return myDropzone;
}

function insertUploadedImg(filename) {
    CKEDITOR.instances['writing'].insertHtml('<img src="/images/' + filename + '">')
}

function submitWriting(choice, myDropzone, id) {
    let title = document.getElementById("writingTitle").value;
    if (title === "") {
        swal("제목을 입력하세요", "", "warning");
        return;
    }
    let content = CKEDITOR.instances['writing'].getData();
    console.dir(title);
    console.dir(content);
    let uploadedFiles = myDropzone.files;
    if (window.location.href) {

    }
    let body = {
        "type": choice.getValue(true),
        "username": user,
        "title": title,
        "content": content,
        "files": successFiles
    };

    if (id!== undefined && id !== 0){
        // 글 수정인 경우
        body["id"] = id;
        postRequest("/articles", body, (data) => {
            window.location.href = "/board"
        });
    } else {
        // 글 쓰기인 경우
        putRequest("/articles", body, (data) => {
            window.location.href = "/board"
        });
    }


}

function appendFilesWithDelete(article) {
    let files = article['files'];
    for (let i = 0; i < files.length; i++) {
        let file = createElementFromStr("<li id='"+ files[i].id + "'><a href='"+ files[i].file_url + "'> <i class=\"fa fa-save mr-2\"></i>" + files[i].filename + "</a> </li>")
        let btnDelete = createElementFromStr("<button class='btn btn-danger' onclick='deleteFile( \""+ files[i].id +"\" )'>X</button>")
        file.appendChild(btnDelete);
        document.getElementById("file-list").firstElementChild.append(file)
    }
}
function deleteFile(id) {
    let url = "/files?id=" + id;
    deleteRequest(url, (response) => {
        console.dir(response);
        document.getElementById(response).remove();
    });
}
function main(article) {
    let choice = new Choices(document.querySelector(".js-choice"), {
        shouldSort: false
    });
    choice.setValue([
        {value: '0', label: '잡담'},
        {value: '1', label: '정보'},
        {value: '2', label: '스터디'},
        {value: '3', label: '알고리즘'},
        {value: '4', label: 'AI'},
        {value: '5', label: '개발지식'}
    ]);
    let boardId = 0;
    let title = "";
    let content = "";
    let id;
    if (article != null) {
        id = article.id;
        boardId = article.board_id;
        title = article.subject;
        content = article.content;
        appendFilesWithDelete(article);
    }
    choice.setChoiceByValue(boardId.toString());

    document.getElementById("writingTitle").value = title;
    document.getElementById("writing").value = content;


    CKEDITOR.replace('writing');


    let dropzone = createDropzone();

    document.getElementById("submitWriting").addEventListener("click", function () {
        submitWriting(choice, dropzone, id)
    });
    // if (article != null) {
    //     window.onload = function () {
    //         CKEDITOR.instances['writing'].insertHtml(article.content);
    //     };
    // }
}