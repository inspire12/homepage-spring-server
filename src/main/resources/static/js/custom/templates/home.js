console.log("hello home");
const urlParams = new URLSearchParams(window.location.search);
const isSignup = urlParams.get("signup")
if (isSignup === 'true') {
    swal("회원가입에 성공했습니다.", "로그인 해주세요", "success");
}

let code = ""
document.onkeypress = function (event) {
    code +=event.key
    if (code === "inspire123"){
        window.location.reload();
    }
}
