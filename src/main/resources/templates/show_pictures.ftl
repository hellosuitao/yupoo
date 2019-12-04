<!DOCTYPE html>
<html lang="en">
<#--作者：suitao-->
<#--显示图片-->
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.0.0.min.js"></script>
    <script type="text/javascript">
        function changestyle() {
            document.getElementById("sortform").style.display="";
        }
    </script>
</head>
<body>
<a href="/album/1">返回首页</a><br>
相册名：${album.name}||共有${album_size}项<br>
<a href="/page/upload_images">上传图片</a>
<a href="/page/upload_audio">上传视频</a>
<a href="/album/album_message">相册信息</a>
<a href="/album/delete/${album.id}">删除相册</a><br>
<input type="button" onclick="javascript:changestyle()" value="点击修改排序方式">
<form style="display: none" id="sortform" name="sortform" method="post" action="/picture/changesort">
    <input style="display: none" name="albumid" value="${album.id}">
    <select name="sortoption">
        <option value="createdate">上传时间</option>
        <option value="albumname">文件名称</option>
        <option value="picsize">文件大小</option>
        <option value="mycustom">自定义类型</option>
    </select>
    <input type="submit" value="保存">
</form>
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
            <td><a href="/picture/setcover/${picture.id}">设为封面</a></td>
            <td><a>移动到</a></td>
            <td><a href="/picture/delete/${picture.id}">删除</a></td>
        </tr>
    </#list>

</table>
</body>
</html>