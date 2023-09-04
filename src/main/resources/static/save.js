var savePost = () => {
    var data = {
        title : $('#title').val(),
        postPass : $('#postPass').val(),
        author : $('#author').val(),
        contents : $('#contents').val()
    };

    $.ajax({
        type: 'POST',
        url: '/posts/save',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function () {
        alert('글이 등록되었습니다.');
        window.location.href = '/posts/?page=1';
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}