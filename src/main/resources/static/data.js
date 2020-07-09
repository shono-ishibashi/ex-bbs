$(function () {
    //articleのvalidation
    $("#article-form").validate({
        rules: {
            "name": {
                required: true,
                maxlength: 50
            },
            "content": {
                required: true,
                maxlength: 140
            }
        },
        messages: {
            "name": {
                required: "<div class='alert alert-danger'>名前を入力してください</div>",
                maxlength: "<div class='alert alert-danger'>50文字以下で入力してください</div>"
            },
            "content": {
                required: "<div class='alert alert-danger'>記事を入力してください</div>",
                maxlength: "<div class='alert alert-danger'>140文字以下で入力してください</div>"
            }
        }
    });
    //commentのvalidation

    $(".insert-comment-button").click(function () {
        $(this).closest(".comment-form").validate({
            rules: {
                "name": {
                    required: true,
                    maxlength: 50
                },
                "content": {
                    required: true,
                    maxlength: 140
                }
            },
            messages: {
                "name": {
                    required: "<div class='alert alert-danger'>名前を入力してください</div>",
                    maxlength: "<div class='alert alert-danger'>50文字以下で入力してください</div>"
                },
                "content": {
                    required: "<div class='alert alert-danger'>コメントを入力してください</div>",
                    maxlength: "<div class='alert alert-danger'>140文字以下で入力してください</div>"
                }
            },
            errorPlacement: function (error, element) {
                error.insertBefore(element);
            },
        }).form();

    });
    // $(".comment-form").validate({
    //     rules: {
    //         "name": {
    //             required: true,
    //             maxlength: 50
    //         },
    //         "content": {
    //             required: true,
    //             maxlength: 140
    //         }
    //     },
    //     messages: {
    //         "name": {
    //             required: "<div class='alert alert-danger'>名前を入力してください</div>",
    //             maxlength: "<div class='alert alert-danger'>50文字以下で入力してください</div>"
    //         },
    //         "content": {
    //             required: "<div class='alert alert-danger'>コメントを入力してください</div>",
    //             maxlength: "<div class='alert alert-danger'>140文字以下で入力してください</div>"
    //         }
    //     },
    //     errorPlacement: function(error, element){
    //         error.insertBefore($(element));
    //     },
    // });
    // $('.insert-comment-button').on('click', function() {
    //     if ($(".comment-form").validate().form()) {
    //         $(this).next().closest(".comment-alert").text('ok');
    //         $(".comment-form").submit();
    //     } else {
    //         $(this).next().closest(".comment-alert").text('ng');
    //     }
});

function ajaxpostArticle(name, content) {
    $.ajax({
        type: "POST",
        url: "/ajax/insert-article",
        data: {
            "name": name,
            "content": content
        },
        dataType: "json"
    }).done(function (result) {
        console.log(result);
        //成功した場合
        console.log("success")
        alert("投稿に成功しました")
        // $('#ajaxResult').append("<div>"+rs+"</div>")

    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
        alert("リクエストに失敗" + textStatus + ":\n" + errorThrown)
    })
};

//comment を　取得する
function ajaxpostComment(name, content, articleId) {
    $.ajax({
        type: "POST",
        url: "/ajax/insert-comment",
        data: {
            "name": name,
            "content": content,
            "article-id": articleId
        },
        dataType: "json"
    }).done(function (result) {
        console.log(result);
        //成功した場合
        for (var rs of result) {
            console.log("succes")
            // $('#ajaxResult').append("<div>"+rs+"</div>")
        }
    }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
        alert("リクエストに失敗" + textStatus + ":\n" + errorThrown)
    })
}