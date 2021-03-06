<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style02.css"/>
    <link rel="stylesheet" type="text/css" href="/css/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skin_/table.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.grid.css"/>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <title>表格</title>
    <!-- 页面作者：YangYuxuan -->
    <style type="text/css">
        #selectinfo {
            margin-left: 0.625rem;
            margin-right: 0.625rem;
        }
    </style>
</head>

<body>
<div id="container">
<#--    <div id="hd"></div>-->
    <div id="bd">
        <div id="main">
            <div class="search-box ue-clear">
                <div class="search-area">
                    <div class="kv-item ue-clear">
                        <div>
                            <label>选择时间：</label>
                            <span class="choose">
                                    <input type="radio" name="time" value="0" checked/>
                                    &nbsp;&nbsp;&nbsp;  全部
                            </span>
                            <span class="choose">
                                    <input type="radio" name="time" value="3"/>
                                     &nbsp;&nbsp;&nbsp; 近3天
                            </span>
                            <span class="choose">
                                    <input type="radio" name="time" value="7"/>
                                     &nbsp;&nbsp;&nbsp; 近一周
                            </span>
                            <span class="choose">
                                    <input type="radio" name="time" value="30"/>
                                     &nbsp;&nbsp;&nbsp; 近一月
                            </span>
                        </div>
                    </div>
                    <div class="kv-item ue-clear">
                        <label>选择类型:</label>
                        <div class="kv-item-content">
                            <select id="categoryId">
                                <option value="0">所有</option>
                                <#list albumCategories as albumCategory>
                                    <option value="${albumCategory.id}">${albumCategory.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="kv-item ue-clear">
                        <label>精准查询:</label>
                        <div class="kv-item-content">
                            <select id="selectBy" >
                                <option value="0">所有</option>
                                <option value="id">ID</option>
                                <option value="albumName">商品名</option>
                            </select>
                        </div>
                        <div class="kv-item-content">
                            <input type="text" id="selectinfo" placeholder="请输入id或商品名或商品分类"/>
                        </div>
                        <div class="kv-item-content">
                            <input class="button" type="button" value="搜索"/>
                        </div>
                    </div>
                </div>

            </div>

            <div class="table">
                <#--操作-->
                <div class="opt ue-clear">
                	<span class="sortarea">
                    	<span class="sort">
                        	<label>排序：</label>
                            <span class="name">
                            	<i class="icon"></i>
                                <span class="text">名称</span>
                            </span>
                        </span>

                        <i class="list"></i>
                        <i class="card"></i>
                    </span>
                    <span href="javascript:;" class="optarea">
                        <a class="delete">
                            <i class="icon"></i>
                            <span class="text" onclick="deleteMany()">批量删除</span>
                        </a>

                        <a href="javascript:;" class="add">
                            <i class="icon"></i>
                            <span class="text" onclick="upshelf()">批量上架</span>
                        </a>

                        <a href="javascript:;" class="close">
                            <i class="icon"></i>
                            <span class="text" onclick="downshelf()">批量下架</span>
                        </a>
                    </span>
                </div>

                <#--表格-->
                <div id="tableftl">
                    <table border="1px solid black" cellspacing="5px" cellpadding="3px"
                           class="table table-striped table-bordered table-hover">
                        <#--导航栏-->
                        <tr>
                            <th>
                                <input type="checkbox" id="selectall" onclick="selectAll(this)" value=""/>&nbsp;&nbsp;全选
                            </th>

                            <th>ID</th>
                            <th>商品名</th>
                            <th>商品价格</th>
                            <th>商品分类</th>
                            <th>商品描述</th>
                            <th>点赞/评论/分享</th>
                            <th>上架时间</th>
                            <th>上架状态</th>
                            <th>操作</th>
                        </tr>
                        <#--数据表-->
                        <#list albumPage.content as album>
                            <tr>
                                <td>
                                    <input type="checkbox" name="checkid" id="" value="${album.id}"/>
                                </td>
                                <td>${album.id}</td>
                                <td>${album.name}</td>
                                <td>${album.price}</td>
                                <td>${album.albumCategories[0].name}</td>
                                <td>${album.description}</td>
                                <td><span id="looknum${album_index}">${likes[album_index].upnum}</span>/<span id="upnum${album_index}">${likes[album_index].looknum}</span>/<span id="share${album_index}">${likes[album_index].share}</span></td>
                                <td>${album.createDate}</td>
                                <td id="${album.id}">${album.open}</td>
                                <td>
                                    <button type="button" class="btn-default" onclick="editAlbum(${album.id})"
                                            value="${album.id}">编辑
                                    </button>
                                    <button type="button" class="btn-danger" value="${album.id}"
                                            onclick="btnDelete(${album.id})">删除
                                    </button>
                                    <button type="button" data-toggle="modal" data-target="#myModal${album_index}">修改点赞数</button>

                                    <div class="modal fade" id="myModal${album_index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                                                aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title" id="myModalLabel">修改</h4>
                                                </div>
                                                <form id="picStatu${album_index}">
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <input type="hidden" value="${album.id}" id="pid${album_index}" >
                                                            <label for="edit_customerName" class="col-sm-2 control-label">点赞数</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" class="form-control" id="luo${album_index}" placeholder="点赞数" value="${likes[album_index].upnum}"
                                                                       name="upnum"/>
                                                            </div>
                                                            <label for="edit_customerName" class="col-sm-2 control-label">浏览数</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" class="form-control" id="yu${album_index}" placeholder="浏览数" value="${likes[album_index].looknum}"
                                                                       name="looknum"/>
                                                            </div>
                                                            <label for="edit_customerName" class="col-sm-2 control-label">分享数</label>
                                                            <div class="col-sm-10">
                                                                <input type="text" class="form-control" id="qiu${album_index}" placeholder="分享数" value="${likes[album_index].share}"
                                                                       name="share"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <button type="button" onclick="updatePicStatus(${album_index})" class="btn btn-primary">Save</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <#--分页-->
                    <#--如果第一页且不到5页-->
                    <#if (currentPage<=2)&&(totalPages<=5)>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <#list 1..totalPages as i>
                                    <li><a onclick="findByPage(${i});">${i}</a></li>
                                </#list>
                            </ul>
                        </nav>
                    <#--第一页且超过5页-->
                    <#elseif (currentPage<=2)&&(totalPages>5)>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <#list 1..5 as i>
                                    <li><a onclick="findByPage(${i})">${i}</a></li>
                                </#list>
                                <li><a onclick="findByPage(${currentPage+2})">下一页</a></li>
                                <li><a onclick="findByPage(${totalPages})">尾页</a>
                                </li>
                            </ul>
                        </nav>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>

<script>
    $(window).load(function(){
        var userId = localStorage.getItem("userId");
    });

    function updatePicStatus(id) {
        var upnum=$("#luo"+id).val().replace(new RegExp(",","g"),"");
        var looknum=$("#yu"+id).val().replace(new RegExp(",","g"),"");
        var share=$("#qiu"+id).val().replace(new RegExp(",","g"),"");
        var pid=$("#pid"+id).val().replace(new RegExp(",","g"),"");
        $.post("/updateLuo",{"pid":pid,"upnum" :upnum,"looknum":looknum,"share":share},function (data) {
            if(data!=null){
                alert("修改成功");
                window.location.reload();
            }else {
                alert("修改失败");
                window.location.href = "/album/findAll"
            }
        })
    }

    /*编辑商品*/
    function editAlbum(albumId) {
        $.post("/album/editAlbum", {id: albumId}, function (data) {
            $("#bd").html(data);
        })
    }

    function updateAlbum() {
        var name = $("#name").val();
        var price = $("#price").val().replace(new RegExp(",","g"),"");
        var open = $(":input[name='need']:checked").val();
        var description = $('textarea[name="content"]').val();
        var albumId = $("#albumId").val().replace(new RegExp(",","g"),"");
        var album = JSON.stringify({
            'id': albumId, 'albumCategories': [{'id': $("#categoryId").val()}],
            'name': name, 'price': price * 100, 'open': open, 'description': description,
            'pictures': hpictures,'audios':haudiopath
        })
        $.ajax({
            url: "/album/updateAlbum",
            type: "POST",
            data: album,
            contentType: 'application/json;charset=utf-8',
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    alert("商品修改成功!");
                    document.location.reload();//重新加载当前页面
                    // window.parent.main.document.location.reload();//上级页面
                } else {
                    alert("商品修改失败!");
                }
            }
        });
    }

    /*图片删除*/
    function delPic(picId) {
        $("#" + picId).next().next().remove();
        $("#" + picId).next().remove();
        $("#" + picId).remove();
        $.post("/picture/delete", {id: picId}, function () {
        });
    }

    /*后台返回的视频路径*/
    var audiopath = "";
    /*传递给后台的视频路径*/
    var haudiopath ="";
    /*临时视频类型*/
    var tempType="";
    /*上传视频*/
    function uploadAudio() {
        var formData = new FormData();
        var audios = document.getElementById("albumaudio");
        var fileObj = audios.files[0];
        if(fileObj.type!="video/mp4"){
            alert("视频格式应该为mp4");
            return;
        }
        if (fileObj.size > 1024 * 1024 * 100) {//100MB
            alert(fileObj.name + "超过100MB,请重新上传");
            return;
        }
        formData.append("file", fileObj);/*添加至表单*/
        $.ajax({
            url: "/audio/uploadaudio",
            type: "POST",
            data: formData,
            async: false,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.status == "success") {
                    audiopath = data.data;
                    if (!haudiopath) {
                        haudiopath = audiopath+",";
                    } else {
                        haudiopath = haudiopath + audiopath+",";
                    }
                    tempType=fileObj.type;
                    reshowAudio();
                } else if (data.message != null) {
                    alert(data.message);
                } else {
                    alert("商品上传失败！！！")
                }
            },
        });
    }

    /*视频回显*/
    function reshowAudio() {
        if (audiopath != "") {
            //获取div
            var div = document.getElementById("reShowAudio");
            var div2 = document.createElement("video");
            div2.setAttribute('width', '30%');
            div2.setAttribute('controls', 'controls');
            var video = document.createElement("source");
            video.setAttribute('src', audiopath);
            video.setAttribute('type', tempType);
            div2.appendChild(video);
            div.appendChild(div2);
        }
    }

    /*删除视频*/
    function delAudio(audioId){
        $("#" + audioId).next().remove();
        $("#" + audioId).remove();
        $.post("/audio/delete", {id: audioId}, function () {
        });
    }

    /*后台返回的图片路径*/
    var pictures = "";
    /*传递给后台的路径*/
    var hpictures = "";
    /*编辑里面的添加图片方法*/
    function addPic() {
        var formData = new FormData();
        var img_file = document.getElementById("pictures");
        var fileObj;
        for (var i = 0; i < 10; i++) {
            fileObj = img_file.files[i];
            if (fileObj == null) {
                break;
            }
            formData.append("file", fileObj);
        }
        if (img_file.files[10] != null) {
            alert("最多上传十张图片");
            $("#pictures").val("");
            return;
        }
        $.ajax({
            url: "/picture/upload",
            type: "POST",
            data: formData,
            async: false,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.status == "success") {
                    pictures = data.data;
                    if (!hpictures) {
                        hpictures = pictures;
                    } else {
                        hpictures = hpictures + pictures;
                    }
                    reshow();
                } else if (data.message != null) {
                    alert(data.message);
                } else {
                    alert("图片添加失败！！！");
                }
            },
        });
    };

    /*图片回显*/
    function reshow() {
        var paths = pictures.split(",");
        var newImg = "";
        for (var i = 0; i < paths.length; i++) {
            if (paths[i] != "") {
                newImg += '<img style="width: 100px; height: 100px"' + 'src=' + paths[i] + '>';
            }
        }
        $("#reShowPic").append(newImg);
    }

    /*修改封面*/
    function setCoverPath(picId) {
        $.post("/picture/setCoverPath", {id:picId , aid: $("#albumId").val().replace(new RegExp(",","g"),"")}, function () {
            alert("设置成功！！！");
        });
    }

    /*分页查询*/
    function findByPage(aPage) {
        var time = $(":input[name='time']:checked").val();
        var categoryId = $("#categoryId").val().toString();
        var selectBy = $("#selectBy").val();
        var inputValue = $("#selectinfo").val();
        var page = aPage - 1;
        var size = 6;
        $.ajax({
            url: "/album/findByCondition",
            type: "POST",
            async: false,
            data: {
                "time": time,
                "categoryId": categoryId,
                "selectBy": selectBy,
                "inputValue": inputValue,
                "page": page,
                "size": size
            },
            success: function (data) {
                $("#tableftl").html(data);
            }
        });
    }

    /*全选*/
    function selectAll(hello) {
        if (hello.checked) {
            $("input[name='checkid']").attr('checked', true);
        } else {
            $("input[name='checkid']").attr('checked', false);
        }
    }

    /*批量上架*/
    function upshelf() {
        var albumIds = new Array();
        $(":input[name='checkid']:checked").each(function () {
            albumIds.push($(this).val().replace(new RegExp(",","g"),""));
        });
        if (window.confirm("确认操作？")) {
            $.ajax({
                type: "POST",
                url: "/album/upshelf",
                data: {
                    "albumIds": albumIds
                },
                traditional: true,
                success: function (data) {
                    if (data.status == "success") {
                        alert("批量上架成功！");
                        albumIds.forEach(function (i) {
                            document.getElementById(i).innerText = "上架";
                        });
                    } else {
                        alert("操作失败！");
                    }
                }
            });
        }

    }

    /*批量下架*/
    function downshelf() {
        var albumIds = new Array();
        $(":input[name='checkid']:checked").each(function () {
            albumIds.push($(this).val().replace(new RegExp(",","g"),""));
        });
        if (window.confirm("确认操作？")) {
            $.ajax({
                type: "POST",
                url: "/album/downshelf",
                data: {
                    "albumIds": albumIds
                },
                traditional: true,
                success: function (data) {
                    if (data.status == "success") {
                        alert("批量下架成功！");
                        albumIds.forEach(function (i) {
                            document.getElementById(i).innerText = "下架";
                        });
                    } else {
                        alert("操作失败！");
                    }
                }
            });
        }
    }

    /*批量删除*/
    function deleteMany() {
        var albumIds = new Array();
        $(":input[name='checkid']:checked").each(function () {
            albumIds.push($(this).val().replace(new RegExp(",","g"),""));
        });
        var time = $(":input[name='time']:checked").val();
        var categoryId = $("#categoryId").val().replace(new RegExp(",","g"),"");
        var selectBy = $("#selectBy").val().replace(new RegExp(",","g"),"");
        var inputValue = $("#selectinfo").val();
        var page = 0;
        var size = 6;
        if (window.confirm("确认操作？")) {
            if (albumIds.length >= 1) {
                $.ajax({
                    type: "POST",
                    url: "/album/deleteMany",
                    data: {
                        "time": time,
                        "categoryId": categoryId,
                        "selectBy": selectBy,
                        "inputValue": inputValue,
                        "page": page,
                        "size": size,
                        "albumIds": albumIds
                    },
                    traditional: true,
                    success: function (data) {
                        $("#tableftl").html(data);
                    }
                });
            } else {
                alert("至少选择一项！！！");
            }

        }

    }
    //修改点赞数和浏览数



    /*删除商品*/
    function btnDelete(id) {
        var time = $(":input[name='time']:checked").val();
        var categoryId = $("#categoryId").val().replace(new RegExp(",","g"),"");
        var selectBy = $("#selectBy").val().replace(new RegExp(",","g"),"");
        var inputValue = $("#selectinfo").val();
        var page = 0;
        var size = 6;
        if (window.confirm("确认操作？")) {
            $.ajax({
                url: "/album/deleteById",
                type: "POST",
                data: {
                    "id": id,
                    "time": time,
                    "categoryId": categoryId,
                    "selectBy": selectBy,
                    "inputValue": inputValue,
                    "page": page,
                    "size": size
                },
                success: function (data) {
                    alert("商品删除成功!")
                    $("#tableftl").html(data);
                }
            })
        }

    };
    /*搜索*/
    $(".button").click(function () {
        var time = $(":input[name='time']:checked").val();
        var categoryId = $("#categoryId").val().replace(new RegExp(",","g"),"");
        var selectBy = $("#selectBy").val().replace(new RegExp(",","g"),"");
        var inputValue = $("#selectinfo").val();
        var page = 0;
        var size = 6;
        $.ajax({
            url: "/album/findByCondition",
            type: "POST",
            data: {
                "time": time,
                "categoryId": categoryId,
                "selectBy": selectBy,
                "inputValue": inputValue,
                "page": page,
                "size": size
            },
            success: function (data) {
                $("#tableftl").html(data);
            }
        });
    });
</script>

</html>
