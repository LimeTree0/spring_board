const updatePost = () => {
    const data = {
        title : $('#title').val(),
        contents : $('#contents').val()
    };

    const id = $('#id').val();

    $.ajax({
        type: 'PUT',
        url: '/posts/update/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function () {
        alert('글이 수정되었습니다.');
        window.location.href = '/posts/update/' + id;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}