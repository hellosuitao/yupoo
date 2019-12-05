<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/css/style02.css"/>
    <link rel="stylesheet" type="text/css" href="/css/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skin_/form.css"/>
    <link href="/umeditor/themes/default/_css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/global.js"></script>
    <script type="text/javascript" src="/js/jquery.select.js"></script>
    <script type="text/javascript" src="/js/WdatePicker.js"></script>
    <script type="text/javascript">
        window.UMEDITOR_HOME_URL = 'umeditor/';  // 请换成绝对路径
    </script>
    <script type="text/javascript" src="/js/umeditor.config.js"></script>
    <script type="text/javascript" src="/js/editor_api.js"></script>
    <script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="/js/dropzone.js"></script>
    <style type="text/css">
        #addCatagory {
            display: inline;
            float: left;
            margin-left: 0.625rem;
            /* border: 0.0625rem solid black; */
            text-align: center;
        }

        /*图片上传样式*/
        #my-awesome-dropzone {
            border: 3px dotted deepskyblue;
            background-color: lightgray;
            /* background-image: url(images/10.png); */
            border-radius: 0.9375rem;
            width: 31.25rem;
            overflow: auto;
            height: 14.75rem;
            color: gray;
            text-align: center;
            font-size: 1.5rem;
        }

    </style>
    <title>商品信息</title>


</head>

<body>
<div id="container">
    <div id="hd">
    </div>
    <div id="bd">
        <div id="main">
            <h2 class="subfild">
                <span>商品信息</span>
            </h2>
            <div class="subfild-content base-info">
                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>商品名称</label>
                    <div class="kv-item-content">
                        <input type="text" id="name" placeholder="商品名称"/>
                    </div>
                    <span class="kv-item-tip">标题字数限制在(1-30)个字符</span>
                </div>

                <div class="kv-item ue-clear">
                    <label>所在分类</label>
                    <div class="kv-item-content">
                        <select id="categoryId">
                            <option value="0">所有</option>
                            <#if albumCategories??>
                                <#list albumCategories as albumCategory>
                                    <option value="${albumCategory.id}">${albumCategory.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
<#--                    <div id="addCatagory">-->
<#--                        <button onclick="addCatagory()"><img src="/img/skin_/ico01.png"></button>-->
<#--                    </div>-->
<#--                    <span class="kv-item-tip">没有合适的分类？尝试创建一个吧</span>-->
<#--                    <img href="/img/skin_/ico01_1.png"/>-->
                </div>

                <div class="kv-item ue-clear">
                    <label>商品价格</label>
                    <div class="kv-item-content">
                        <input type="text" id="price" placeholder="商品价格0.01(分)-9999999.99(元)"/>
                    </div>
                </div>

                <div class="kv-item ue-clear">
                    <label>是否上架</label>
                    <div class="kv-item-content">
                    	<span class="choose">
                            <span class="checkboxouter">
                                <input type="radio" name="need" value="1" checked/>
                                <span class="radio"></span>
                            </span>
                            <span class="text">是</span>
                        </span>
                        <span class="choose">
                            <span class="checkboxouter">
                                <input type="radio" name="need" value="0"/>
                                <span class="radio"></span>
                            </span>
                            <span class="text">否</span>
                        </span>
                    </div>
                </div>

                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>商品图片</label>
                    <input id="pictures" type="file" onchange="uploadPic()" multiple="true">
                </div>
                <#--图片回显div-->
                <div id="reShowPic">
                </div>

                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>商品视频</label>
                    <input id="albumaudio" type="file" onchange="uploadAudio()" mutiple="true">
                </div>
                <#--图片回显div-->
                <div id="reShowAudio">
                </div>
            </div>

            <h2 class="subfild">
                <span>商品描述</span>
            </h2>

            <div class="subfild-content remarkes-info">
                <div class="kv-item ue-clear">
                    <label>描述内容</label>
                    <div class="kv-item-content">
                        <textarea placeholder="文章内容" id="content" name="content"
                                  style="width:800px;height:240px;"></textarea>
                    </div>
                </div>
            </div>

            <div class="buttons">
                <#--                <input class="button" type="button" value="效果预览"/>-->
                <input class="button" type="button" onclick="addAlbum()" value="确认上传" id="addAlbum"/>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('select').select();
    showRemind('input[type=text],textarea', 'color5');
    UM.getEditor('content');

    var audiopath = "";
    var haudiopath = "";
    var tempType = "";
    /*上传视频*/
    function uploadAudio() {
        var formData = new FormData();
        var audios = document.getElementById("albumaudio");
        var fileObj = audios.files[0];
        if (fileObj.type != "video/mp4") {
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
                    alert("视频上传成功！！！");
                    if (!haudiopath) {
                        haudiopath = audiopath + ",";
                    } else {
                        haudiopath = haudiopath + audiopath + ",";
                    }
                    tempType = fileObj.type;
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

    /*以下是商品添加*/
    /*添加分类*/
    function addCatagory() {
        var name = prompt("分类名称", "请输入分类名");
        if (name) {
            if (confirm("分类名称：" + name)) {
                var albumCategory = JSON.stringify({"name": name});
                $.ajax({
                    url: "/albumCategory/addAlbumCategory",
                    type: "POST",
                    data: albumCategory,
                    contentType: 'application/json;charset=utf-8',
                    type: "json",
                    success: function (data) {
                        if (data.status == "success") {
                            alert("成功添加" + name);
                            $("#categoryId").append('<option value=' + data.data + '>' + name + '</option>');
                        } else {
                            alert("分类添加失败！！！");
                        }
                    }
                });
            }

        } else {
            alert("分类信息不完整，请重新创建");
        }
    };
    var pictures = "";
    var hpictures = "";

    /*图片上传方法*/
    function uploadPic() {
        var formData = new FormData();
        var img_file = document.getElementById("pictures");
        var fileObj;
        for (var i = 0; i < 10; i++) {
            fileObj = img_file.files[i];
            if (fileObj == null) {
                break;
            }
            if ((fileObj.type != "image/jpeg") && (fileObj.type != "image/jpg") && (fileObj.type != "image/png")) {
                alert("图片应该为jpeg，jpg，png");
                return;
            }
            formData.append("file", fileObj);
        }
        if (img_file.files[10] != null) {
            alert("最多同时上传十张图片");
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
                    alert("图片上传成功！！！");
                    reshow();
                } else if (data.message != null) {
                    alert(data.message);
                } else {
                    alert("图片上传失败！！！")
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

    /*商品添加*/
    function addAlbum() {
        var userId = localStorage.getItem("userId");
        var name = $("#name").val();
        var price = $("#price").val();
        var open = $(":input[name='need']:checked").val();
        var description = $('textarea[name="content"]').val();
        var album = JSON.stringify({
            'name': name, 'albumCategories': [{'id': $("#categoryId").val()}],
            'price': price * 100, 'open': open, 'description': description,
            'pictures': hpictures, 'audios': haudiopath, 'uid': userId
        })
        $.ajax({
            url: "/album/addAlbum",
            type: "POST",
            data: album,
            contentType: 'application/json;charset=utf-8',
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    alert("商品添加成功!");
                    window.location.href = "";
                } else if (data.message == "请先登录") {
                    if (confirm("是否登录？")) {
                        localStorage.setItem("username", null);
                        window.parent.parent.location.reload();
                        // window.location.href = "/adminLogin.html";
                    } else {
                        window.location.href = "";
                    }
                } else if (data.message != null) {
                    alert(data.message);
                } else {
                    album("商品添加失败");
                }
            }
        });
    };

</script>
</html>
