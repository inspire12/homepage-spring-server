
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
        autoQueue: false, // Make sure the files aren't queued until manually added
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
        successFiles.push(uploadFile)
        let dz_insert_button = '<button type="button" class="btn btn-info btn-xs"><span class="glyphicon"></span> 본문에 넣기 </button>';
        let insert_button = Dropzone.createElement(dz_insert_button);
        let _this = this;
        insert_button.addEventListener('click', function (e) {
            e.preventDefault(); // click이벤트 외의 이벤트 막기위해
            e.stopPropagation(); // 부모태그로의 이벤트 전파를 중지 // 파일 업로드시 장애가 발생한 경우
            if (file['status'] != 'error') { // Send Client Event Delete//
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

function submitWriting(myDropzone) {
    let title = document.getElementById("writingTitle").value;
    if (title === "") {
        swal("제목을 입력하세요", "", "warning");
        return ;
    }
    let content = CKEDITOR.instances['writing'].getData();
    console.dir(title);
    console.dir(content);
    let uploadedFiles = myDropzone.files;

    let body = {
        "type": choice.getValue(true),
        "username": user,
        "title": title,
        "content": content,
        "files": successFiles
    };

    putRequest("/articles", body, (data) => {
        window.location.href = "/board"
    });
}

function main() {
    choice = new Choices(document.querySelector(".js-choice"), {
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
    choice.setChoiceByValue('0');
    CKEDITOR.replace('writing');
    if (article != null)
        CKEDITOR.instances['writing'].insertHtml(article.content);
    let dropzone = createDropzone();

    document.getElementById("submitWriting").addEventListener("click", function() {
        submitWriting(dropzone)
    })

}