<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#import "/spring.ftl" as spring/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/style02.css" />
<link rel="stylesheet" href="/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="/css/skin_/nav.css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<title>底部内容页</title>
</head>

<body >
<div id="container">
	<div id="bd">
    	<div class="sidebar">
        	<div class="sidebar-bg"></div>
            <ul class="nav">
                <li class="nav-li current">
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text"><@spring.message code="nav.Merchandise_management" /></span></a>
                	<ul class="subnav">
<#--                        需要国际化-->
                        <li class="subnav-li" href="" data-id="2" id="add_commodity"><a  href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text"><@spring.message code="nav.add_commodity" /></span></a></li>
                        <li class="subnav-li" href=""  data-id="3" id="Manage_goods"><a  href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text"><@spring.message code="nav.Manage_goods" /></span></a></li>
                        <li class="subnav-li" href="" data-id="4" id="CategoryManage"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text"><@spring.message code="nav.CategoryManage" /></span></a></li>
                    </ul>
                </li>

                <li class="nav-li last-nav-li">
                	<a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text"><@spring.message code="nav.Data_management" /></span></a>
                    <ul class="subnav">
<!--                    	<li class="subnav-li" data-id="12"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">流量总览</span></a></li>-->
                        <li class="subnav-li" href="" data-id="12" id="Video_Recycle_Bin"><a class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text"><@spring.message code="nav.Video_Recycle_Bin" /></span></a></li>
                        <li class="subnav-li" href="" data-id="13" id="Picture_Recycle_Bin"><a class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text"><@spring.message code="nav.Picture_Recycle_Bin" /></span></a></li>
                    </ul>
                </li>
            </ul>
            <div class="tree-list outwindow">
            	<div class="tree ztree"></div>
            </div>
        </div>
        <div class="main">
        	<div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">

                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content">
            </div>
        </div>
    </div>
</div>

<div class="more-bab-list">
	<ul></ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-bl"></div>
</div>
</body>
<script type="text/javascript" src="/js/nav.js"></script>
<script type="text/javascript" src="/js/Menu.js"></script>
<script type="text/javascript" src="/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
    $(window).load(function () {//替换国际化请求
        //拿到全局变量 l
        var l = localStorage.getItem("language");
        //获取iframe,并修改src值
        $("#add_commodity").attr("href","/album/showAdd?l=" + l);
        $("#Manage_goods").attr("href","/album/findAll?l=" + l);
        $("#CategoryManage").attr("href","/albumCategory/findAll?l=" + l);
        $("#Video_Recycle_Bin").attr("href","/delAudio/findAll?l=" + l);
        $("#Picture_Recycle_Bin").attr("href","/delPic/findAll?l=" + l)
        var menu = new Menu({
        	defaultSelect: $('.nav').find('li[data-id="2"]')
        });
    });

	// var menu = new Menu({    //因国际化需要，移动到了上方函数中
	// 	defaultSelect: $('.nav').find('li[data-id="2"]')
	// });
	
	// 左侧树结构加载
	var setting = {};


	// $.fn.zTree.init($(".tree"), setting, zNodes);
	
	
	$('.sidebar h2').click(function(e) {
        $('.tree-list').toggleClass('outwindow');
		$('.nav').toggleClass('outwindow');
    });
	
	$(document).click(function(e) {
		if(!$(e.target).is('.tab-more')){
			 $('.tab-more').removeClass('active');
			 $('.more-bab-list').hide();
		}
    });
</script>
</html>
