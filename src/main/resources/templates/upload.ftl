
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script src="/js/cover.upload.js"></script>
    <link rel="stylesheet" href="/css/upload.css"/>
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
<h2>案例三：查看图片信息</h2>
<div class="case">
    <div class="upload" action='/upload/add' data-value='' id='case3'></div>
</div>

<h2>参数配置：</h2>
<table border='1'  cellspacing="0" bordercolor="#d7d7d7" style="border-collapse:collapse;">
    <tr>
        <td width='120'>标签</td>
        <td width="300">默认值</td>
        <td>说明</td>
    </tr>
    <tr>
        <td>data-height</td>
        <td>0</td>
        <td>图片上传压缩最大高度，0则根据宽度等比例压缩</td>
    </tr>
    <tr>
        <td>data-width</td>
        <td>1920</td>
        <td>图片上传压缩最大宽度，0则根据高度等比例压缩</td>
    </tr>
    <tr>
        <td>data-type</td>
        <td>png,jpg,jpeg,gif</td>
        <td>允许上传文件的扩展名，多个扩展名用逗号分割，支持非图片格式的文件上传</td>
    </tr>
    <tr>
        <td>data-file</td>
        <td>file</td>
        <td>上传提交服务器的表单名</td>
    </tr>
    <tr>
        <td>data-name</td>
        <td>uoload</td>
        <td>最终表单提交图片路径的表单名</td>
    </tr>
    <tr>
        <td>action</td>
        <td>/upload.php</td>
        <td>服务器接收上传文件的地址，服务器需返回{"code":1,"msg":"/upload/1.jpg"}的JSON字符串，code为上传状态，1为成功，0为失败，msg为成功的文件路径或失败原因提示！</td>
    </tr>
    <tr>
        <td>data-num</td>
        <td>10</td>
        <td>最多可以上传多少个文件，如为1，上传插件为单个文件上传样式</td>
    </tr>
    <tr>
        <td>data-size</td>
        <td>20480</td>
        <td>文件上传单个文件最大容量，图片不传不受该属性限制</td>
    </tr>
    <tr>
        <td>data-value</td>
        <td>null</td>
        <td>已经上传成功的文件名，多个文件用英文逗号分割</td>
    </tr>
</table>
</body>
<script>
    $(function(){
        $("#case3").upload(
            //该函数为点击放大镜的回调函数，如没有该函数，则不显示放大镜
            function(_this,data){
                alert(data)
            }
        )
    })
</script>
</html>