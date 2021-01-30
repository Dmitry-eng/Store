var uuid;
$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    window.onload  = function (){
        loadEmployee();
    }
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
        // document.location.href="newEmployee/"
        var div = document.getElementById('edit');
        div.href = "newEmployee/"+uuid;
    });
});

function loadEmployee() {
    event.preventDefault();
    $.ajax({
        type: "POST",
        dataType: 'JSON',
        url: '/employee/list',
    }).done(function (data) {
        $('tbody#employee').empty();
        for (let i = 0; i < data.length; i++) {
            $('tbody#employee').append(
                "  <tr id=\""+ data[i].id +" \"   class=\"clickTable\"> <th> " + data[i].surname + " </th> " +
                "  <td> " + data[i].name + " </td>" +
                "  <td> " + data[i].middleName + "</td> </tr>"
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
function remove(){
    $.ajax({
        type: "DELETE",
        url: '/employee/remove/' +  uuid,
        async: false,
        success: function (data) {
            if (data) {
                alertNoClose.show("Удаление завершено успешно!");
            } else {
                alertNoClose.show("Ошибка при удалении.");
            }
        }
    });
    loadEmployee();
}
function searchContains(){
    event.preventDefault();
    $.ajax({
        type: "POST",
        dataType: 'JSON',
        url: '/employee/list/' + $('#list').val(),
    }).done(function (data) {
        $('tbody#employee').empty();
        for (let i = 0; i < data.length; i++) {
            $('tbody#employee').append(
                "  <tr id=\""+ data[i].id +" \"   class=\"clickTable\"> <th> " + data[i].surname + " </th> " +
                "  <td> " + data[i].name + " </td>" +
                "  <td> " + data[i].middleName + "</td> </tr>"
            );
        }
    })
}