<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#import "/spring.ftl" as spring/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style02.css"/>
    <link rel="stylesheet" type="text/css" href="/css/WdatePicker.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skin_/category_manager.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.grid.css"/>
    <link href="https://cdn.bootcss.com/jquery-treetable/3.2.0/css/jquery.treetable.min.css"/>
    <script src="https://cdn.bootcss.com/jquery-treetable/3.2.0/jquery.treetable.min.js"/>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"/>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"/>

    <script type="text/javascript">

        $("#treeTable").treeTable({
            "column": 1,
            "expandLevel": 2
        });

    </script>

    <title>表格</title>
    <#--一级二级-->
    <style type="text/css">
        #home span {
            display: block;
            height: 30px;
            width: 100%;
        }

        #home {
            transition: 300ms;
            height: 30px;
            width: 160px;
            overflow: hidden;
            background-color: #0094FFFF;
        }

        #sun {
            width: 140px;
            min-height: 100%;
            float: right;
            background-color: #FF7F7FFF
        }
    </style>
    <style type="text/css">
        #selectinfo {
            margin-left: 0.625rem;
            margin-right: 0.625rem;
        }
    </style>
    <!--适配手机-->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
</head>

<body>
<div id="container">
    <div id="hd"></div>
    <div id="bd">
        <div id="main">
            <div class="table">
                <button type="button" onclick="addCatagory(0);">New</button>
                <div></div>
                <#--表格-->
                <div id="categoryftl">
                    <table id="treeTable" border="1px solid black" cellspacing="5px" cellpadding="3px"
                           class="table table-striped table-bordered table-hover">
                        <#--导航栏-->
                        <thead>
                        <tr role="row">
<#--                            <th>-->
<#--                                <input type="checkbox" id="selectall" onclick="selectAll(this)" value=""/>&nbsp;&nbsp;Check-->
<#--                                All-->
<#--                            </th>-->
<#--                            <th>CategoryId</th>-->
<#--                            <th>CategoryName</th>-->
<#--                            <th>Operation</th>-->
                            <th><@spring.message code="category_manager.CategoryId" /></th>
                            <th><@spring.message code="category_manager.CategoryName" /></th>
                            <th><@spring.message code="category_manager.Operation" /></th>
                        </tr>
                        </thead>
                        <#--数据表-->
                        <tbody>
                        <#--显示一级分类-->
                        <#list albumCategories as aparent>
                        <#--判断是否是一级分类-->
                            <#if aparent.parentId==0>
                                <tr id="${aparent.id}" onclick="showChild(${aparent.id})">
<#--                                    <td>-->
<#--                                        <input type="checkbox" name="checkid" id="" value="${aparent.id}"/>-->
<#--                                    </td>-->
                                    <td>${aparent.id}</td>
                                    <td class="name${aparent.id}">${aparent.name}</td>
                                    <td>
                                        <button type="button" class="btn-default"
                                                onclick="editCategory(${aparent.id})"
                                                value="${aparent.id}">Edit
                                        </button>
                                        <button type="button" class="btn-default"
                                                onclick="addCatagory(${aparent.id})"
                                                value="${aparent.id}">New
                                        </button>
                                        <button type="button" class="btn-danger" value="${aparent.id}"
                                                onclick="btnDelete(${aparent.id})">Delete
                                        </button>
                                    </td>
                                    <div></div>
                                </tr>
                            </#if>
                        <#--显示二级分类-->
                            <#list albumCategories as child>
                            <#--判断是否是子类-->
                                <#if child.parentId=aparent.id>
                                    <tr class="child${aparent.id}" id="${child.id}"
                                        style="background-color: #00e58b;display: none;">
<#--                                        <td>-->
<#--                                            <input type="checkbox" name="checkid" id="" value="${child.id}"/>-->
<#--                                        </td>-->
                                        <td>${child.id}</td>
                                        <td class="name${child.id}">${child.name}</td>
                                        <td>
                                            <button type="button" class="btn-default"
                                                    onclick="editCategory(${child.id})"
                                                    value="${child.id}">Edit
                                            </button>
                                            <button type="button" class="btn-danger" value="${child.id}"
                                                    onclick="btnDelete(${child.id})">Delete
                                            </button>
                                        </td>
                                    </tr>
                                </#if>
                            </#list>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
    /*显示隐藏二级分类*/
    function showChild(parentId) {
        if ($(".child" + parentId).css("display") == "none") {
            $(".child" + parentId).show();
        } else {
            $(".child" + parentId).hide();
        }
    }
    /*添加分类*/
    function addCatagory(id) {
        var name = prompt("CategoryName", "Please input name");
        if (name) {
            if (confirm("CategoryName：" + name)) {
                var albumCategory = JSON.stringify({"name": name, "parentId": id});
                $.ajax({
                    url: "/albumCategory/addAlbumCategory",
                    type: "POST",
                    data: albumCategory,
                    contentType: 'application/json;charset=utf-8',
                    type: "json",
                    success: function (data) {
                        if (data.status == "success") {
                            window.location.reload();
                        } else if(data.message){
                            alert(data.message);
                        }else{
                            alert("Fail append");
                        }
                    }
                });
            }
        }
    };

    /*编辑分类*/
    function editCategory(id) {
        var name = prompt("分类名称", "请输入新的分类名");
        if (name) {
            if (confirm("分类名称：" + name)) {
                var albumCategory = JSON.stringify({"name": name, "id": id});
                $.ajax({
                    url: "/albumCategory/updateName",
                    type: "POST",
                    data: albumCategory,
                    contentType: 'application/json;charset=utf-8',
                    type: "json",
                    success: function (data) {
                        if (data.status == "success") {
                            $(".name" + id).html(name);
                        }
                    }
                });
            }
        } else {
            alert("分类信息不完整，请重新创建");
        }
    }


    /*删除分类*/
    function btnDelete(id) {
        if (confirm("Confirm operation???")) {
            $.post("/albumCategory/deleteById", {"categoryId": id}, function f(data) {
                if (data.status = "success") {
                    alert("Delete successfully");
                    window.location.reload();
                } else {
                    alert("Fail delete");
                }
            })
        }

    }
</script>


</html>
