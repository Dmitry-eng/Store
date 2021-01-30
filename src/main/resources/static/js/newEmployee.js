var uuid;
var employee;
$(document).ready(function () {
        $(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });

        window.onload = function () {
            initEmployee();
            initForm();
            initBranch()
            initRole();
        }

        $('#password').blur(function () {
            validPasswordForm();
        });

        $('#login').blur(function () {
            validLoginForm();
        });

        $('#inputEmail').blur(function () {
            validEmailForm();
        });
        $('#lastName').blur(function () {
            validLastName();
        });
        $('#name').blur(function () {
            validName();
        });
        $('#middleName').blur(function () {
            middleNameValid();
        });


        $('#send').click(function () {
            event.preventDefault();
            if (!validPasswordForm() || !validLoginForm() || !validEmailForm() || !validLastName() || !validName()
                || !middleNameValid()) {
                alertNoClose.show('Проверьте правильность заполнения формы');
                return;
            }
            $.ajax({
                type: "POST",
                dataType: 'json',
                contentType: "application/json",
                url: '/employee/save/',
                data: buildJsonObject(),
            }).then(function (data) {

                if (data.result = 'true') {
                    alertNoClose.show('Пользователь сохранен');
                    uuid = data.uuid;
                } else {
                    alertNoClose.show('Ошибка сохранения');
                }
            })
        });
    }
);

function validName() {
    if ($('#name').val().replace(" ", "").length == 0) {
        $('#messageValidName').text("Поле не может быть пустым");
        $('#validName ').html('&#10060;');

        return false;
    } else {
        $('#messageValidName').text("");
        $('#validName ').html('&#10004;');
        return true;
    }
}

function validLastName() {
    if ($('#lastName').val().replace(" ", "").length == 0) {
        $('#messageValidLastName').text("Поле не может быть пустым");
        $('#validPasswordLastName ').html('&#10060;');

        return false;

    } else {
        $('#messageValidLastName').text("");
        $('#validPasswordLastName ').html('&#10004;');
        return true;
    }
}

function validPasswordForm() {
    if ($('#password').val().replace(" ", "").length == 0 && getUUID().length == 0) {
        $('#messageValidPassword').text("Поле не может быть пустым");
        $('#validPassword ').html('&#10060;');
        return false;
    } else {
        $('#messageValidPassword').text("");
        $('#validPassword ').html('&#10004;');
    }
    return true;
}

function middleNameValid() {
    if ($('#middleName').val().replace(" ", "").length == 0) {
        $('#middleNameValid').text("Поле не может быть пустым");
        $('#pinMiddleName ').html('&#10060;');
        return false;
    } else $('#middleNameValid').text("");
    $('#pinMiddleName ').html('&#10004;');
    return true;
}

function validLoginForm() {
var bool;
    if ($('#login').val().replace(" ", "").length == 0) {
        $('#messageValidLogin').text("Поле не может быть пустым");
        $('#pinValidLogin').html('&#10060;');
        return false;
    } else
        $.ajax({
            type: "PUT",
            async: false,
            url: '/employee/login/' + $('#login').val() + getUUID(),
            success: function (data) {
                if (data) {
                    $('#messageValidLogin').text("Логин свободный.");
                    $('#pinValidLogin').html('&#10004;');
                    bool = true;
                } else {
                    $('#messageValidLogin').text("Логин занят.");
                    $('#pinValidLogin').html('&#10060;');
                    bool = false;
                }
            }
        });
    return bool;
}


function validEmailForm() {
    var bool;
    if ($('#inputEmail').val().replace(" ", "").length == 0) {
        $('#messageValidEmail').text("Поле не может быть пустым");
        $('#validEmail ').html('&#10060;');
        return false;
    }
    var pattern = /^[a-z0-9_-]+@[a-z0-9-]+\.[a-z]{2,6}$/i;
    var mail = $('#inputEmail');
    if (mail.val().search(pattern) != 0) {
        $('#messageValidEmail').text("Проверьте правильность заполнения адреса электронной почты");
        $('#validEmail').html('&#10060;');
        return false;
    }

    $.ajax({
        type: "PUT",
        url: '/employee/email/' + $('#inputEmail').val() + getUUID(),
        async: false,
        success: function (data) {
            if (data) {
                $('#messageValidEmail').text("Почта свободна");
                $('#validEmail').html('&#10004;');
                bool = true;
            } else {
                $('#messageValidEmail').text("Почта занята.");
                $('#validEmail ').html('&#10060;');
                bool = false;
            }
        }
    });
    return bool;
}

var AlertBox = function (id, option) {
    this.show = function (msg) {
        if (msg === '' || typeof msg === 'undefined' || msg === null) {
            throw '"msg parameter is empty"';
        } else {
            var alertArea = document.querySelector(id);
            var alertBox = document.createElement('DIV');
            var alertContent = document.createElement('DIV');
            var alertClass = this;
            alertContent.classList.add('alert-content');
            alertContent.innerText = msg;

            alertBox.classList.add('alert-box');
            alertBox.appendChild(alertContent);

            alertArea.appendChild(alertBox);

            if (!option.persistent) {
                var alertTimeout = setTimeout(function () {
                    alertClass.hide(alertBox);
                    clearTimeout(alertTimeout);
                }, option.closeTime);
            }
        }
    };

    this.hide = function (alertBox) {
        alertBox.classList.add('hide');
        var disperseTimeout = setTimeout(function () {
            alertBox.parentNode.removeChild(alertBox);
            clearTimeout(disperseTimeout);
        }, 500);
    };
};

var alertNoClose = new AlertBox('#alert-area', {
    closeTime: 5000,
    persistent: false,
    hideCloseButton: true
});

function buildJsonObject() {
    var formData = new Object();
    if (uuid != null) {
        formData.id = uuid;
    }
    formData.login = $('#login').val();
    if ($('#password').val().replace(" ", "").length != 0) {
        formData.password = $('#password').val();
    }
    formData.email = $('#inputEmail').val();
    formData.phoneNumber = $('#phoneNumber').val();
    formData.name = $('#name').val();
    formData.surname = $('#lastName').val();
    formData.middleName = $('#middleName').val();
    formData.birthDate = $('#birthDate').val();

    let arr = []
    $('tbody#branch td input').each(function (key) {
        if ($(this).prop('checked')) {
            var obj = new Object();
            obj.id = $(this).attr('id')
            arr.push(obj);
        }
    });
    formData.branch = arr;
    let arrPosition = []
    $('tbody#position td input').each(function (key) {
        if ($(this).prop('checked')) {
            var obj = new Object();
            obj.id = $(this).attr('id')
            arrPosition.push(obj);
        }
    });
    formData.roles = arrPosition;
    return JSON.stringify(formData);
}

function initBranch() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/branch/load',
    }).done(function (data) {

        $('tbody#branch').empty();


        for (let i = 0; i < data.length; i++) {
            $('tbody#branch').append(
                " <tr>  <td colspan=\"10\">" + data[i].address + "</td>\n" +
                "  <td><input id=\"" + data[i].id + "\" " + isBranch(employee, data[i].id) + " type=\"checkbox\"></td> </tr>"
            );
        }
    })
}

function isBranch(employee, element) {
    if (employee != null && employee.branch != null) {
        return isChecked(employee.branch, element);
    }
}

function isRole(employee, element) {
    if (employee != null && employee.roles != null) {
        return isChecked(employee.roles, element);
    }
}

function initRole() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/role/load',
    }).done(function (data) {
        $('tbody#position').empty();


        for (let i = 0; i < data.length; i++) {
            $('tbody#position').append(
                " <tr>  <td colspan=\"10\"> " + data[i].description + " </td>\n" +
                "  <td><input id=\"" + data[i].id + " \" " + isRole(employee, data[i].id) + " type=\"checkbox\"></td> </tr>"
            );

        }
    })
}

// function initUUID() {
//     if ($("meta[name='uuid']").attr("content") != null) {
//         uuid = $("meta[name='uuid']");
//     }
// }

function initForm() {
    if ($("meta[name='employee']").attr("content") != null) {
        var element = $("meta[name='employee']").attr("content");
        let employee = JSON.parse(element);
        $('#login').val(employee.login);
        // $('#password').val(employee.password);
        $('#inputEmail').val(employee.email);
        $('#phoneNumber').val(employee.phoneNumber);
        $('#name').val(employee.name);
        $('#lastName').val(employee.surname);
        $('#middleName').val(employee.middleName);
        $('#birthDate').val(employee.birthDate);
        uuid = employee.id;

    }
}

function isChecked(values, uuid) {

    for (let i = 0; i < values.length; i++) {
        if (values[i].id == uuid) {
            return "checked"
        }
    }
}

function initEmployee() {
    if ($("meta[name='employee']").attr("content") != null) {
        var element = $("meta[name='employee']").attr("content");
        employee = JSON.parse(element);
    }
}

function getUUID() {
    if (uuid == null) {
        return "";
    }
    {
        return "/" + uuid;
    }
}