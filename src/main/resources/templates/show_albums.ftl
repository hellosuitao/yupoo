<!DOCTYPE html>
<html lang="en">
<#--作者：随涛-->
<#--相册首页-->
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
<a href="/page/upload_images">上传图片</a>
<a href="/page/upload_audio">上传视频</a>
<a href="/page/add_album">新建相册</a>
<#--搜索相册：<input type="text" id="name" name="name"><input type="button" value="搜索" onclick="selectAlbumByName()">-->
<#--搜索相册：<input type="text" id="name" name="name"><span><a href="" οnclick="selectAlbumByName(this)">搜索</a></span>-->
<form method="post" action="/album/findByName">
    <select name="opname">
        <option value="album">相册</option>
        <option value="picture">图片</option>
    </select>
    <input type="text" name="name" ><input type="submit" value="搜索">
</form>
<input type="button" onclick="javascript:changestyle()" value="点击修改排序方式">
<form style="display: none" id="sortform" name="sortform" method="post" action="/album/changesort">
    <select name="sortoption">
        <option value="createdate">创建时间</option>
        <option value="albumname">文件名字</option>
        <option value="mycustom">自定义排序</option>
    </select>
    <input type="submit" value="保存">
</form>

<table>
    <tr>
        <td>相册数量</td>
        <td>相册名</td>
        <td>是否公开</td>
        <td>封面</td>
        <td>创建时间</td>
    </tr>
   <#list albums as album>
        <tr>
            <td>数量</td>
            <td><a href="/album/showpictures/${album.id}">查看:${album.name}相册</a></td>
            <td><#if album.open==1>
                    公开
                <#else>
                    不公开
                </#if>
            </td>
            <td>${album.coverpath}</td>
            <td>${album.createDate}</td>
        </tr>
    </#list>

</table>
</body>
</html>