let constraints = {
    username: {
        presence: true,
        format: {
            pattern: "[a-z0-9]+",
            flags: "i",
            message: "can only contain a-z and 0-9"
        },
        exclusion: {
            within: ["admin", "anonymousUser"],
            message: "'%{value}' is not allowed"
        }
    },
    password: {
        presence: true,
        length: {
            minimum: 6,
            message: "must be at least 6 characters"
        }
    },
    repassword: {
        presence: true,
        equality: "password"
    },
    studentId: {
        presence: true,
        length: {
            is: 9,
            message: "학번을 정확히 입력해주세요"
        }
    },
    email: {
        presence: true,
        email: true
    }
};

let poppers = []

function validateFromForm(form) {

    let username = document.getElementById("username");
    let password = document.getElementById("password");
    let repassword = document.getElementById("repassword");
    let studentId = document.getElementById("studentId");
    let email = document.getElementById("email");
    let agreeTerm = document.getElementById("agreeTerm");

    let requestBody = {
        username: username.value,
        password: password.value, repassword: repassword.value,
        studentId: studentId.value, email: email.value
    };
    let validatedMap = validate(requestBody, constraints);

    console.dir(validatedMap);
    if (validatedMap === undefined) {
        postRequest("/signup", requestBody, function (response) {
            console.dir(response)
        })
    } else {
        let prevPoppersSize = poppers.length
        for (let i = 0; i < prevPoppersSize; i++) {
            poppers[i].destroy();
        }
        poppers = []
        for (let key in validatedMap) {
            let message = "";
            for (let msg in validatedMap[key]) {
                message += validatedMap[key][msg];
            }
            let popperId = "popup" + key

            let domStr = "<div id='" + popperId + "' class='popper'> " + message + "</div>";
            document.body.append(createElementFromStr(domStr))
            poppers.push(new Popper(document.getElementById(key), document.getElementById(popperId), {
                placement: 'right'
            }))
        }
    }

}
