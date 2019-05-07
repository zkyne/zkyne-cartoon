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
                <div class="col-xs-4 col-xs-offset-8 text-right">
                    <button type="button" onclick="cartoon._initAddCartoon()" class="btn btn-info"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  新建漫画</button>
                </div>
            </div>
        </div>
        <div class="panel-body" id="panelBody">
            <div class="row">
                <#if page.hasContent()??>
                    <#list page.content as cartoon>
                     <div class="col-xs-3">
                         <div class="thumbnail">
                             <img data-src="holder.js/100%x200" alt="100%x200" src="${cartoon.coverPic}" data-holder-rendered="true" style="height: 160px; width: 100%; display: block;">
                             <div class="caption">
                                 <h3>${cartoon.title}</h3>
                                 <p>${cartoon.author}</p>
                                 <p>${cartoon.brief}</p>
                                 <p>
                                     <button type="button" onclick="cartoon._initAddChapter('<#if cartoon.cartoonId??>${cartoon.cartoonId?c}</#if>');" class="btn btn-success">
                                         <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  新增章节
                                     </button>
                                     <button type="button" onclick="cartoon._listChapters('<#if cartoon.cartoonId??>${cartoon.cartoonId?c}</#if>');" class="btn btn-info">
                                         <span class="glyphicon glyphicon-list" aria-hidden="true"></span>  管理章节
                                     </button>
                                 </p>
                             </div>
                         </div>
                     </div>
                    </#list>
                <#else>
                <#--没有作品-->
                    <span>尚未创建漫画作品</span>
                </#if>
            </div>
        </div>
        <div class="panel-footer" style="text-align: center">版权所有: @ www.zkyne.com</div>
    </div>
</div>
<#include "common/footer.ftl"/>
<script type="text/javascript" src="/js/cartoon.js"></script>
</body>
</html>
