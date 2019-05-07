<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <#include "common/head.ftl"/>
    <title>Zkyne Cartoon</title>
</head>
<body>
<div class="container">
    <#include "common/top.ftl"/>
    <div class="panel panel-info" style="margin-top: 51px;">
        <div class="panel-heading text-left">
            <div class="row">
                <div class="col-xs-10">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object" data-src="holder.js/256x160" alt="256x160" src="<#if cartoon.coverPic??>${cartoon.coverPic}</#if>" data-holder-rendered="true" style="width: 256px; height: 160px;">
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><#if cartoon.title??>${cartoon.title}</#if></h4>
                        <p>作者 : <span style="color: red"><#if cartoon.author??>${cartoon.author}</#if></span></p>
                        <p>已上传 <span style="color: red;font-weight: bold"><#if cartoon.chapterCnt??>${cartoon.chapterCnt}</#if></span> 话</p>
                        <p>创建时间 : <#if cartoon.createDate??>${cartoon.createDate?string('yyyy-MM-dd hh:mm:ss')}</#if></p>
                        <p><#if cartoon.brief??>${cartoon.brief}</#if></p>
                    </div>
                </div>
                <div class="col-xs-2 text-right">
                    <button type="button" onclick="cartoon._initAddChapter('<#if cartoon.cartoonId??>${cartoon.cartoonId?c}</#if>')" class="btn btn-danger">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  添加章节
                    </button>
                </div>
            </div>
        </div>
        <div class="panel-body" id="panelBody">
            <table class="table table-hover text-center">
                <thead>
                <tr>
                    <th data-class="expand">章节封面</th>
                    <th data-class="expand" class="text-center">章节标题</th>
                    <th data-class="expand" class="text-center">收费状态</th>
                    <th data-class="expand" class="text-center">创建时间</th>
                    <th data-class="expand" class="text-center">状态</th>
                    <th data-class="expand" class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                    <#if page.hasContent()??>
                        <#list page.content as chapter>
                             <tr ${(chapter.isDelete==0)?string('class="success"','')} id="curTr">
                                 <td>
                                     <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="<#if chapter.coverPic??>${chapter.coverPic}</#if>" data-holder-rendered="true" style="width: 64px; height: 64px;">
                                 </td>
                                 <td><#if chapter.title??>${chapter.title}</#if></td>
                                 <td>
                                     <#if chapter.isFree?? && chapter.isFree == 1>
                                            免费
                                         <#elseif chapter.isFree?? && chapter.isFree == 0>
                                            收费
                                         <#else>
                                     </#if>
                                 </td>
                                 <td><#if chapter.createDate??>${chapter.createDate?string('yyyy-MM-dd hh:mm:ss')}</#if></td>
                                 <td id="isDelete" data-value="<#if chapter.isDelete??>${chapter.isDelete}</#if>">
                                     <#if chapter.isDelete?? && chapter.isDelete == 1>
                                         已隐藏
                                     <#elseif chapter.isFree?? && chapter.isDelete == 0>
                                         显示中
                                     <#else>
                                     </#if>
                                 </td>
                                 <td style="padding: 2px 2px 2px ">
                                     <button type="button" onclick="cartoon._previewChapter('<#if chapter.chapterId??>${chapter.chapterId?c}</#if>')" class="btn btn-success">
                                         <span class="glyphicon glyphicon-search" aria-hidden="true"></span>  预 览
                                     </button>
                                     <button type="button" onclick="cartoon._initModifyChapter('<#if chapter.chapterId??>${chapter.chapterId?c}</#if>')" class="btn btn-warning">
                                         <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>  编 辑
                                     </button>
                                     <button type="button" onclick="cartoon._reverseChapter(this,'<#if chapter.chapterId??>${chapter.chapterId?c}</#if>')" class="btn btn-${(chapter.isDelete==0)?string('danger','info')}">
                                         <span class="glyphicon glyphicon-${(chapter.isDelete==0)?string('eye-close','eye-open')}" aria-hidden="true"></span>  ${(chapter.isDelete==0)?string('隐 藏','显 示')}
                                     </button>
                                 </td>
                             </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </div>
        <div class="panel-footer" style="text-align: center">版权所有: @ www.zkyne.com</div>
    </div>
</div>
<#--章节预览-->
<div class="modal fade" id="chapter_preview" role="dialog" style="background-color: rgba(0,0,0,0.75);margin-top: 0px" data-backdrop="static" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 80%;margin-top: 0px">
        <div class="modal-content" style="background-color: rgba(0,0,0,0.15);border: none">
            <div class="modal-header" style="border: none">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" style="font-size:25px;font-weight: bold;color: #ffffff">X</span>
                </button>
                <h4 class="modal-title" id="chapterTitle" style="color: white"></h4>
            </div>
            <div class="modal-body">
                <div class="row text-center">
                    <div class="col-xs-6 col-xs-offset-3" id="coverPic"></div>
                    <div class="col-xs-6 col-xs-offset-3"><span style="color: white">======================== 正文分割线 ========================</span></div>
                    <div class="col-xs-6 col-xs-offset-3" id="pictures"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "common/footer.ftl"/>
<script type="text/javascript" src="/js/cartoon.js"></script>
</body>
</html>
