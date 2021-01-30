var uuid;
$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    window.onload = function () {
        loadProduct();

    }
    $("#dataPhoto").change(function(){
        readURL(this);
    });
    $('#search').click(function (){
        searchContains();
    })
    $('.delete').on('click', function (e) {
        $('.buttonDisabled').addClass('disabled');
        $('.buttonDisabled').addClass('disable');
        $('.clickTable').removeClass('marked');
        remove();
    });
    $('.edit').on('click', function (e) {
        $('.buttonDisabled').addClass('disabled');
        $('.buttonDisabled').addClass('disable');
        $('.clickTable').removeClass('marked');
        var div = document.getElementById('edit');
        div.href = "newEmployee/" + uuid;
    });
});

function loadProduct() {
    event.preventDefault();
    $.ajax({
        type: "PUT",
        dataType: 'JSON',
        url: '/product/list',
    }).done(function (data) {
        $('tbody#product').empty();
        for (let i = 0; i < data.length; i++) {
            $('tbody#product').append(
                "  <tr id=\"" + data[i].id + " \" onclick=clicka(\"" + data[i].id + "\")  class=\"clickTable\"> " +
                "  <td> " + data[i].name + " </td>"
            );
        }
    })
}

$(document).on('click', '.clickTable', function () {
    $('.buttonDisabled').removeClass('disabled')
    $('.buttonDisabled').removeClass('disable')
    $('.clickTable').removeClass('marked');
    $(this).addClass('marked');
    uuid = $(this).attr('id');
});

function remove() {
    $.ajax({
        type: "DELETE",
        url: '/product/remove/' + uuid,
        success: function (data) {
            if (data) {
                alertNoClose.show("Удаление завершено успешно!");
            } else {
                alertNoClose.show("Ошибка при удалении.");
            }
        }
    });
    loadProduct();
}

function emptyForm() {
    $('#sendForm')[0].reset();
    $('#filePrev').attr('src', "");
}

function editForm() {
    emptyForm();
    $.ajax({
        type: "GET",
        url: '/product/get/' + uuid,
        async: false,
        success: function (data) {
            uuid = data.id;
            $('#description').val(data.description);
            $('#name').val(data.name);
            $('#date').val(data.data);
            $('#author').val(data.author);
            $('#filePrev').attr('src', data.photo);
        }
    });
}

function newProduct() {

    var formData = new FormData($('#sendForm')[0]);
    if(uuid != null){
        formData.append("id", uuid);
    }



    $.ajax({
        // dataType: 'json',
        processData: false,
        contentType: false,
        enctype: 'multipart/form-data',
        type: "POST",
        url: '/product/add/',
        async: false,
        data: formData,
        success: function (data) {
            if (data) {
                alertNoClose.show("Добавление завершено успешно!");
            } else {
                alertNoClose.show("Ошибка при добавлении.");
            }
        }
    });
    loadProduct();
}

function productBuild() {

    var formData = new Object();
    if (uuid != null) {
        formData.id = uuid;
    }
    var dw = new FormData();
    dw.append('dwa', $('#dataPhoto').prop("files")[0]);
    formData.dataPhoto = $('#dataPhoto').prop("files")[0];
    formData.name = $('#name').val();
    formData.description = $('#description').val();
    formData.date = $('#date').val();
    formData.author = $('#author').val();
    return JSON.stringify(formData);
}
function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#filePrev').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function searchContains(){
        event.preventDefault();
        $.ajax({
            type: "PUT",
            dataType: 'JSON',
            url: '/product/list/' + $('#list').val(),
        }).done(function (data) {
            $('tbody#product').empty();
            for (let i = 0; i < data.length; i++) {
                $('tbody#product').append(
                    "  <tr id=\"" + data[i].id + " \" onclick=clicka(\"" + data[i].id + "\")  class=\"clickTable\"> " +
                    "  <td> " + data[i].name + " </td>"
                );
            }
        })

}