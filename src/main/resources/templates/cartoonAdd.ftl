<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <#include "common/head.ftl"/>
    <title>Zkyne Cartoon</title>
</head>
<body>
<div class="container">
    <#include "common/top.ftl"/>
    <div class="panel panel-info" style="margin-top: 101px;">
        <div class="panel-body" id="panelBody">
            <form class="form-horizontal" action="/cartoon/add">
                <div class="row">
                    <div class="col-xs-8">
                        <div class="form-group">
                            <label for="title" class="col-xs-4 control-label">作品名称:</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" name="title" id="title" placeholder="漫画作品名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="author" class="col-xs-4 control-label">作者笔名:</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" name="author" id="author" placeholder="漫画作者笔名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="catrgoryId" class="col-xs-4 control-label">作品分类:</label>
                            <div class="col-xs-8">
                                <select id="categoryId" name="categoryId" class="form-control">
                                    <option>请选择</option>
                                    <#if categoryList??>
                                        <#list categoryList as category>
                                            <option value="${category.categoryId?c}">${category.title}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type" class="col-xs-4 control-label">漫画类型:</label>
                            <div class="col-xs-8">
                                <label class="radio-inline">
                                    <input type="radio" value="0" name="type" checked="checked">条漫
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="1" name="type">页漫
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="finishStatus" class="col-xs-4 control-label">更新状态:</label>
                            <div class="col-xs-8">
                                <label class="radio-inline">
                                    <input type="radio" value="0" name="finishStatus" checked="checked">连载中
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" value="1" name="finishStatus">已完结
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="priceUnitChapter" class="col-xs-4 control-label">章节价格:</label>
                            <div class="col-xs-8">
                                <select id="priceUnitChapter" name="priceUnitChapter" class="form-control">
                                    <option value="0">全本免费</option>
                                    <option value="5">收费章节5分/话</option>
                                    <option value="10">收费章节10分/话</option>
                                    <option value="15">收费章节15分/话</option>
                                    <option value="20">收费章节20分/话</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="brief" class="col-xs-4 control-label">作品介绍:</label>
                            <div class="col-xs-8">
                                <textarea class="form-control" style="resize:none" id="brief"  name="brief" placeholder="作品介绍" rows="7" cols="75"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <img onerror="this.src='/images/cover_default.png'" onclick="cartoon._selectPic();" src="" id="coverImg" style="width: 256px;height: 160px;cursor:pointer" alt=""/>
                        <p style="font-size: 12px;color: red">格式为png或jpg, 规格256x160, 大小限制5M</p>
                        <input type="file" style="display: none" class="form-control" name="coverPic" id="coverPic">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-xs-3 col-xs-offset-9">
                            <button type="button" onclick="cartoon._addCartoon();" id="btn-addCartoon" class="btn btn-success">
                                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>  创建漫画
                            </button>&nbsp;&nbsp;
                            <button type="button" onclick="history.go(-1);" class="btn btn-default">
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
<#include "common/footer.ftl"/>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/cartoon.js"></script>
</body>
</html>
