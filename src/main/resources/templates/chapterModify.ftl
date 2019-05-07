<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <#include "common/head.ftl"/>
    <link rel="stylesheet" href="/css/upload.css"/>
    <title>Zkyne Cartoon</title>
    <style>
        html,body,h1,h2,h3,h4,h5,h6,div,dl,dt,dd,ul,ol,li,p,blockquote,pre,hr,figure,table,caption,th,td,form,fieldset,legend,input,button,textarea,menu{margin:0;padding:0;}
        body{padding:100px;font-size: 14px;}
        h1{font-size: 26px;}
        p{font-size: 14px; margin-top: 10px;}
        pre{background:#eee;border:1px solid #ddd;border-left:4px solid #f60;padding:15px;margin-top: 15px;}
        h2{font-size: 20px;margin-top: 20px;}
        .case{margin-top: 15px;width:800px;}
        td{padding:5px;}
        table{margin-top: 20px;}
    </style>
</head>
<body>
<div class="container">
    <#include "common/top.ftl"/>
    <div class="panel panel-info">
        <div class="panel-body" id="panelBody">
            <form class="form-horizontal" action="/cartoon/add">
                <div class="row">
                    <div class="col-xs-8">
                        <div class="form-group">
                            <label for="cartoonId" class="col-xs-4 control-label">作品名称:</label>
                            <div class="col-xs-8">
                                <span class="form-control"><#if cartoon.title??>${cartoon.title}</#if></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="title" class="col-xs-4 control-label">章节标题:</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" name="title" value="<#if cartoonChapter.title??>${cartoonChapter.title}</#if>" id="title" placeholder="章节标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="isFree" class="col-xs-4 control-label">收费状态:</label>
                            <div class="col-xs-8">
                                <label class="radio-inline">
                                    <input type="radio" value="1" name="isFree" <#if cartoonChapter.title?? && cartoonChapter.isFree == 1>checked="checked"</#if>>免费
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="0" name="isFree" <#if cartoonChapter.title?? && cartoonChapter.isFree == 0>checked="checked"</#if>>收费
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type" class="col-xs-4 control-label">章节封面:</label>
                            <div class="col-xs-8">
                                <img onerror="this.src='/images/cover_default.png'" onclick="cartoon._selectPic();" src="<#if cartoonChapter.coverPic??>${cartoonChapter.coverPic}</#if>" id="coverImg" style="width: 256px;height: 160px;cursor:pointer" alt=""/>
                                <p style="font-size: 12px;color: red">格式为png或jpg, 规格256x160, 大小限制5M</p>
                                <input type="file" style="display: none" class="form-control" name="coverPic" id="coverPic">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="type" class="col-xs-4 control-label">章节内容:</label>
                            <div class="col-xs-8 col-xs-offset-4">
                                <div class="case">
                                    <div class="upload" data-name="pictures" action='/chapter/${cartoon.cartoonId?c}/upload' data-value='<#if cartoonChapter.chapterPicturesStr??>${cartoonChapter.chapterPicturesStr}</#if>' id='case3'></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-xs-3 col-xs-offset-9">
                            <button type="button" onclick="cartoon._modifyChapter('<#if cartoonChapter.chapterId??>${cartoonChapter.chapterId?c}</#if>');" id="btn-modifyChapter" class="btn btn-success">
                                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>  更新章节
                            </button>&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" onclick="history.go(-1);" id="btn-addChapter" class="btn btn-default">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>  返回
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="panel-footer" style="text-align: center">版权所有: @ www.zkyne.com</div>
    </div>
</div>

<div class="modal fade" id="modify_chapter_success" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="margin-top: 200px">
            <div class="modal-body">
                <div class="row text-center">
                    <div class="col-xs-12" style="margin-top: 20px">
                        <span class="glyphicon glyphicon-ok" style="font-size: 28px;font-weight: bold;color: #5cb85c" aria-hidden="true"></span>
                    </div>
                    <div class="col-xs-12" style="margin-top: 20px">
                        <span style="font-size: 22px;font-weight: bold">恭喜您，作品章节更新成功！</span>
                    </div>
                </div>
                <div class="row text-center" style="margin-top: 30px">
                    <div class="col-xs-4">
                        <button type="button" onclick="cartoon._listCartoons();" class="btn btn-success">返回作品列表</button>
                    </div>
                    <div class="col-xs-4">
                        <button type="button" onclick="cartoon._listChapters('<#if cartoon.cartoonId??>${cartoon.cartoonId?c}</#if>');" class="btn btn-success">前往章节列表</button>
                    </div>
                    <div class="col-xs-4">
                        <button type="button" onclick="cartoon._initAddChapter('<#if cartoon.cartoonId??>${cartoon.cartoonId?c}</#if>');" class="btn btn-success">继续添加章节</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "common/footer.ftl"/>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/cover.upload.js"></script>
<script type="text/javascript" src="/js/cartoon.js"></script>
<script type="text/javascript">
    $(function(){
        $("#case3").upload(
            //该函数为点击放大镜的回调函数，如没有该函数，则不显示放大镜
            function(_this,data){
                toastr.success(data);
            }
        )
    })
</script>
</body>
</html>
