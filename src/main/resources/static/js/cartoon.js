$(function () {
    $("#coverPic").change(function (e) {
        $('#coverImg').attr("src",URL.createObjectURL($(this)[0].files[0]));
    })
});

var cartoon = {
    URL:{
        _listCartoons:function () {
            return "/cartoons";
        },
        _listChapters:function (cartoonId) {
            return "/cartoons/"+ Number(cartoonId) + "/chapters";
        },
        _initAddCartoon:function () {
            return "/cartoons/init";
        },
        _addCartoon:function () {
            return  "/cartoons";
        },
        _initAddChapter:function (cartoonId) {
            return "/cartoons/" + Number(cartoonId) + "/chapters/init";
        },
        _addChapter:function (cartoonId) {
            return "/cartoons/" + Number(cartoonId) + "/chapters";
        },
        _reverseChapter:function (cartoonId,chapterId,isDelete) {
            return "/cartoons/" + Number(cartoonId) + "/chapters/"+ Number(chapterId) +"/"+ Number(isDelete);
        },
        _previewChapter:function (cartoonId,chapterId) {
            return "/cartoons/" + Number(cartoonId) + "/chapters/"+Number(chapterId);
        },
        _initModifyChapter:function (cartoonId,chapterId) {
            return "/cartoons/" + Number(cartoonId) + "/chapters/" + Number(chapterId) + "/init";
        },
        _modifyChapter:function (cartoonId, chapterId) {
            return "/cartoons/" + Number(cartoonId) + "/chapters/"+ Number(chapterId);
        }
    },
    _reload:function (uri) {
        window.location = uri;
    },
    _listCartoons: function(){
        cartoon._reload(cartoon.URL._listCartoons());
    },
    _listChapters: function(cartoonId){
        cartoon._reload(cartoon.URL._listChapters(cartoonId));
    },
    _initAddCartoon:function () {
        cartoon._reload(cartoon.URL._initAddCartoon());
    },
    _selectPic:function () {
        $('#coverPic').click();
    },
    _addCartoon:function(){
        var dataJ = {
            title:$('#title').val(),
            author:$('#author').val(),
            categoryId:$('#categoryId').val(),
            type:$('input[name="type"]:checked').val(),
            finishStatus:$('input[name="finishStatus"]:checked').val(),
            priceUnitChapter:$('#priceUnitChapter').val(),
            brief:$('#brief').val()
        };
        $('#btn-addCartoon').html("创建中").prop("disabled","disabled");
        $.ajaxFileUpload({
            url:cartoon.URL._addCartoon(),
            data:dataJ,
            type:"post",
            secureuri: false,
            fileElementId: 'coverPic',
            cache: false,
            dataType:"json",
            async:false,
            success : function(result){
                $('#btn-addCartoon').html("创建漫画").prop("disabled",false);
                if(result.code === 200){
                    cartoon._initAddChapter(result.data.cartoonId);
                }else{
                    toastr.error(result.message);
                }
            },
            error: function(result) {
                $('#btn-addCartoon').html("创建漫画").prop("disabled",false);
                toastr.error(result.message);
            }
        })
    },
    _initAddChapter:function(cartoonId){
        cartoon._reload(cartoon.URL._initAddChapter(cartoonId));
    },
    _addChapter:function(cartoonId){
        var pictures = $('#pictures').val();
        var pictureArray = pictures.split(',');
        if(pictureArray.length < ($('.case li').length-1)){
            toastr.warning("有图片正在上传中,请稍后...");
            return;
        }
        var dataJ = {
            title:$('#title').val(),
            isFree:$('input[name="isFree"]:checked').val(),
            pictures:pictures
        };
        $('#btn-addChapter').html("添加中").prop("disabled","disabled");
        $.ajaxFileUpload({
            url:cartoon.URL._addChapter(cartoonId),
            data:dataJ,
            type:"post",
            secureuri: false,
            fileElementId: 'coverPic',
            cache: false,
            dataType:"json",
            async:false,
            success : function(result){
                $('#btn-addChapter').html("添加章节").prop("disabled",false);
                if(result.code === 200){
                    $("#add_chapter_success").modal({
                        show:true,
                        backdrop:'static',
                        keyboard:false
                    });
                }else{
                    toastr.error(result.message);
                }
            },
            error: function(result) {
                $('#btn-addChapter').html("添加章节").prop("disabled",false);
                toastr.error(result.message);
            }
        })
    },
    _reverseChapter:function (obj,cartoonId,chapterId) {
        var str = "显示";
        var $curBtn = $(obj);
        var status = $curBtn.parents('tr#curTr').find('td#isDelete').attr('data-value');
        var isDelete = 0;
        if(Number(status) === 0){
            str = "隐藏";
            isDelete = 1;
        }
        Ewin.confirm({message: "您确认要" + str + "该章节吗？"}).on(function (e) {
            if (e) {
                $.ajax({
                    url:cartoon.URL._reverseChapter(cartoonId,chapterId,isDelete),
                    type:'patch',
                    data:{},
                    dataType:'json',
                    success:function (result) {
                        if(result.code === 200){
                            // 成功
                            if(isDelete === 1){
                                $curBtn.html('显 示');
                                $curBtn.html('<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>  显 示');
                                $curBtn.removeClass('btn-danger').addClass('btn-info');
                                $curBtn.parents('tr#curTr').removeClass('success');
                                $curBtn.parents('tr#curTr').find('td#isDelete').html('已隐藏');
                                $curBtn.parents('tr#curTr').find('td#isDelete').attr('data-value',isDelete);
                            }else{
                                $curBtn.html('<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>  隐 藏');
                                $curBtn.removeClass('btn-info').addClass('btn-danger');
                                $curBtn.parents('tr#curTr').addClass('success');
                                $curBtn.parents('tr#curTr').find('td#isDelete').html('显示中');
                                $curBtn.parents('tr#curTr').find('td#isDelete').attr('data-value',isDelete);
                            }
                            toastr.success("操作成功");
                        }else{
                            toastr.error(result.message);
                        }
                    },
                    error:function () {
                        toastr.error("网络错误");
                    }
                });
            }
        });
    },
    _previewChapter:function (cartoonId, chapterId) {
        $.ajax({
            url:cartoon.URL._previewChapter(cartoonId,chapterId),
            type:'get',
            data:{},
            dataType:'json',
            success:function (result) {
                if(result.code === 200){
                    // 成功
                    $('#chapterTitle').html(result.data.title);
                    $('#coverPic').html('<img class="media-object"  alt="" src="' + result.data.coverPic + '" data-holder-rendered="true" style="width: 100%"/>');
                    $('#pictures').html('');
                    if(result.data.chapterPictures != null){
                        $(result.data.chapterPictures).each(function () {
                            $('#pictures').append('<img class="media-object"  alt="" src="' + this.picture + '" data-holder-rendered="true" style="width: 100%"/>');
                        });
                    }
                    $("#chapter_preview").modal({
                        show:true,
                        backdrop:'static',
                        keyboard:false
                    });
                }else{
                    toastr.error(result.message);
                }
            },
            error:function () {
                toastr.error("网络错误");
            }
        });
    },
    _initModifyChapter:function (cartoonId,chapterId) {
        cartoon._reload(cartoon.URL._initModifyChapter(cartoonId,chapterId));
    },
    _modifyChapter:function (cartoonId,chapterId) {
        var pictures = $('#pictures').val();
        var pictureArray = pictures.split(',');
        if(pictureArray.length < ($('.case li').length-1)){
            toastr.warning("有图片正在上传中,请稍后...");
            return;
        }
        var dataJ = {
            title:$('#title').val(),
            isFree:$('input[name="isFree"]:checked').val(),
            pictures:pictures
        };
        $('#btn-modifyChapter').html("更新中").prop("disabled","disabled");
        $.ajaxFileUpload({
            url:cartoon.URL._modifyChapter(cartoonId,chapterId),
            data:dataJ,
            type:"put",
            secureuri: false,
            fileElementId: 'coverPic',
            cache: false,
            dataType:"json",
            async:false,
            success : function(result){
                $('#btn-modifyChapter').html("更新章节").prop("disabled",false);
                if(result.code === 200){
                    $("#modify_chapter_success").modal({
                        show:true,
                        backdrop:'static',
                        keyboard:false
                    });
                }else{
                    toastr.error(result.message);
                }
            },
            error: function(result) {
                $('#btn-modifyChapter').html("更新章节").prop("disabled",false);
                toastr.error(result.message);
            }
        })
    }
};