const deletePost = () => {
    const id = $('#id').val();
    const page = $('#page').val();

    $.ajax({
        type: 'DELETE',
        url: '/posts/delete/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
    }).done(function () {
        alert('글이 삭제되었습니다.');
        window.location.href = '/posts/?page=' + page;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}