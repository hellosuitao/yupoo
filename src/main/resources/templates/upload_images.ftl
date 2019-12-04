<!DOCTYPE html>
<html lang="en">
<#--作者：suitao-->
<#--上传图片-->
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.0.0.min.js"></script>
</head>
<body>
<a href="/album/1">返回首页</a><br>
<form method="post" action="/picture/upload" enctype="multipart/form-data">
    选择相册
    <select name="id">
        <option value="1">相册1</option>
        <option value="2">相册2</option>
        <option value="3">相册3</option>
    </select>
    <input type="file" name = "file" multiple="multiple" accept="image/png,image/gif,image/jpg"/>
    <input type="submit" value="上传图片"/>
    <button class="btn btn-default" onclick="javascript:history.back(-1);">返回上一页</button>
</form>


</body>


</html>