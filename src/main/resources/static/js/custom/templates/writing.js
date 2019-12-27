var previewNode = document.querySelector("#template");
previewNode.id = "";
var previewTemplate = previewNode.parentNode.innerHTML;
previewNode.parentNode.removeChild(previewNode);

var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
    url: "/files", // Set the url
    thumbnailWidth: 80,
    thumbnailHeight: 80,
    parallelUploads: 20,
    previewTemplate: previewTemplate,
    autoQueue: false, // Make sure the files aren't queued until manually added
    previewsContainer: "#previews", // Define the container to display the previews
    clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
});

myDropzone.on("addedfile", function(file) {
    // Hookup the start button
    file.previewElement.querySelector(".start").onclick = function() { myDropzone.enqueueFile(file); };
    let path = "upload-dir/" + file.name
    console.dir(path)
});

// Update the total progress bar
myDropzone.on("totaluploadprogress", function(progress) {
    document.querySelector("#total-progress .progress-bar").style.width = progress + "%";
});

myDropzone.on("sending", function(file) {
    // Show the total progress bar when upload starts
    document.querySelector("#total-progress").style.opacity = "1";
    // And disable the start button
    file.previewElement.querySelector(".start").setAttribute("disabled", "disabled");
});

// Hide the total progress bar when nothing's uploading anymore
myDropzone.on("queuecomplete", function(progress) {
    document.querySelector("#total-progress").style.opacity = "0";
});
// Setup the buttons for all transfers
// The "add files" button doesn't need to be setup because the config
// `clickable` has already been specified.
document.querySelector("#actions .start").onclick = function() {
    myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
};
document.querySelector("#actions .cancel").onclick = function() {
    myDropzone.removeAllFiles(true);
};

function submitWriting(writingTitle) {
    let title = writingTitle.value;
    let content = CKEDITOR.instances['writing'].getData();
    console.dir(title);
    console.dir(content);
    let body = {
        "type": choice.getValue(true),
        "username": user,
        "title": title,
        "content": content
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
    let container = document.getElementById("fileupload");
}