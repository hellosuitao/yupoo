<!DOCTYPE html>
<html lang="en">
<#--作者：suitao-->
<#--根据图片名模糊查询返回-->
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.0.0.min.js"></script>

</head>
<body>
<a href="/album/1">返回首页</a><br>
<a href="/page/upload_images">上传图片</a>
<a href="/page/upload_audio">上传视频</a>
<table>
    <tr>
        <td>图片名</td>
        <td>图片</td>
        <td>操作</td>
    </tr>
   <#list pictures as picture>
        <tr>
            <td>${picture.name}</td>
            <td>${picture.path}</td>
            <td><a>移动到</a></td>
            <td><a href="/picture/delete/${picture.id}">删除</a></td>
        </tr>
    </#list>

</table>
</body>
</html>