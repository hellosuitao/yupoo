<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#import "/spring.ftl" as spring/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
		<title>回收站</title>
		<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="/css/skin_/form.css" />
		<script src="/js/jquery-1.11.1.min.js"></script>
		<style type="text/css">	
			#toolbar{
				/* border: #0000FF 0.125rem solid; */
				border-bottom: 0.0625rem solid darkgray;
				clear: both;
				overflow: auto;
				margin-bottom: 1.6125rem;
			}

			#toolbar ul{

				list-style-type: none;
				text-align: center;
				vertical-align: middle;
			}
			
			#toolbar ul li{	
				float: left;
				margin-right: 3.5rem;
				margin-bottom: 0.3125rem;
				vertical-align: middle;
				cursor: pointer;
			}	
			
			#toolbar ul li:hover{
				transform: scale(1.1);
			}
				
			#pics{
				width: 100%;
				overflow: auto;

			}
			
			.pic{
				padding: 0rem;
				width: 20%;
				height: 20%;
				overflow: auto;
				margin: 0.125rem;
				float: left;
				text-align: center;
				border-radius: 15px;
			}
			.pic img{
				width: 70%;
				-moz-box-shadow: 2px 2px 10px #909090;/*firefox*/
				-webkit-box-shadow: 2px 2px 10px #909090;/*safari或chrome*/
				box-shadow:2px 2px 10px #909090;/*opera或ie9*/
			}
			
			.navi ul{
				list-style-type:none;
				text-align: center;
				vertical-align: middle;
			}
			
			.navi ul li{
				float: left;
				margin-right: 3.5rem;
				margin-bottom: 0.3125rem;
				vertical-align: middle;
			}
			
			.refresh{
				margin: 0.125rem;
				border:1px solid #00A039;
				background-color: #00A039;
				border-radius:5px;
				color: white;
			}
			
			.delete{
				margin: 0.125rem;
				border: 1px solid #E84B39;
				background-color: #E84B39;
				border-radius:5px;
				color: white;
			}
			.hide{
				display: none;
			}
			
			
		</style>
		<script type="text/javascript">
			$(function(){
				$(".refresh").click(function(){
					var node = document.getElementById(this.parentElement.id);
					node.parentElement.removeChild(node);
				})
				
				$(".delete").click(function(){
					var node = document.getElementById(this.parentElement.id);
					confirm("您正在进行删除操作","确认删除?操作将无法恢复");
					// document.removeChild(node);
					node.parentElement.removeChild(node);
				})
				
			})
			
			var countSelectTimes = 0;

			// 清空
			function clearall(){
				swal({ 
						title: "确定清空吗？", 
						text: "此操作将无法恢复！", 
						type: "warning",
						showCancelButton: true, 
						confirmButtonColor: "#DD6B55",
						confirmButtonText: "清空！", 
						cancelButtonText: "取消！",
						closeOnConfirm: false, 
						closeOnCancel: false	
						},
						function(isConfirm){ 
						if (isConfirm) { 
							document.getElementById('pics').innerHTML = "";
							document.getElementById('pics').innerHTML = "<h1 style='text-align: center;color: lightgray;'>空空如也</h1>";
							swal("清空！", "回收站空空如也。",
						"success"); 
						} else { 
							swal("取消！", "图片都原封不动:)",
						"error"); 
						} 
				    });
			}
			// 批量还原
			function multirefresh(){
				swal("批量恢复","您正在进行批量恢复操作","success");	
				$('.hide input[type="checkbox"]:checked').each(
				function () {
				var pic = document.getElementById(this.parentElement.parentElement.id);
				pic.parentNode.removeChild(pic)
				}
				);
				
			}
			// 批量删除
			function multidelete(){
				swal({
						title: "确定删除吗？", 
						text: "此操作将无法恢复！", 
						type: "warning",
						showCancelButton: true, 
						confirmButtonColor: "#DD6B55",
						confirmButtonText: "删除！", 
						cancelButtonText: "取消！",
						closeOnConfirm: false, 
						closeOnCancel: false	
						},
						function(isConfirm){ 
						if (isConfirm) { 
							$('.hide input[type="checkbox"]:checked').each(
							function () {
							var pic = document.getElementById(this.parentElement.parentElement.id);
							pic.parentNode.removeChild(pic)
							}
							);
							swal("删除！", "批量操作成功",
						"success"); 
						} else { 
							swal("取消！", "图片都原封不动:)",
						"error"); 
						} 
				    });
			}
			// 选择
			function select(){
				countSelectTimes += 1;
				var state = countSelectTimes%2;
				if(state == 1){
					var nodelist = document.getElementsByClassName("hide");
					for(var i = 0;i<nodelist.length;i++){
						var node = nodelist[i];
						node.style.display = "inherit";
					}
				}else if(state == 0){
					var nodelist = document.getElementsByClassName("hide");
					for(var i = 0;i<nodelist.length;i++){
						var node = nodelist[i];
						node.style.display = "none";
					}
				}
			}
		</script>
	</head>
	<body>
		<div class="container">
			<div class="main">
				<h2 class="subfild">
					<span><@spring.message code="recyclebinAudio.Video_recycling"/></span>
				</h2>
				<div id="toolbar">
					<ul>
						<li id="clear"><img src="/recyclebin/clear.png"><@spring.message code="recyclebinAudio.Clear"/></li>
						<#if dels.totalElements % dels.size=1 && dels.number+1 == dels.totalPages  >
						<li id="multirefresh"  onclick="insIds(${dels.getNumber()})" ><img src="/recyclebin/restore32.png" ><@spring.message code="recyclebinAudio.Bulk_recovery"/></li>
						<li id="multidelete"   onclick="delIds(${dels.getNumber()})" ><img src="/recyclebin/delete32.png" ><@spring.message code="recyclebinAudio.batch_deletion"/></li>
							<#else >
								<li id="multirefresh"  onclick="insIds(${dels.getNumber()+1})" ><img src="/recyclebin/restore32.png" ><@spring.message code="recyclebinAudio.Bulk_recovery"/></li>
								<li id="multidelete"   onclick="delIds(${dels.getNumber()+1})" ><img src="/recyclebin/delete32.png" ><@spring.message code="recyclebinAudio.batch_deletion"/></li>
						</#if>
						<li id="select" onclick="select()"><img src="/recyclebin/select.png" ><@spring.message code="recyclebinAudio.operating"/></li>
					</ul>		
				</div>

				<div id="pics">
                    <#list dels.content as a>
					<div class="pic" id="01">
						<i class="pid">${a.audioId}</i><br/>
						<div class="hide">
							选择
							<input type="checkbox" class="xuanze" id="${a.audioId}"/>
						</div>
						<div style="width:15%;height: 100px">
							<video id="${a.id}" src="${a.path}" controls="controls">
								<source src="${a.path}" type="video/mp4">
								<source src="${a.path}" type="video/ogg">
								<source src="${a.path}" type="video/webm">
								<object data="${a.path}">
									<embed src="${a.path}">
								</object>
							</video>
						</div>


<#--						<input type="video" src="${a.path}" style="width:  140px;-->
<#--                                                                   height: 260px;">-->
						<#if dels.totalElements % dels.size=1 && dels.number+1 == dels.totalPages  >
						<button class="refresh" type="button" onclick="ins(${a.audioId},${dels.getNumber()})"><@spring.message code="recyclebinAudio.restore"/></button>
						<button class="delete"  type="button" onclick="del(${a.audioId},${dels.getNumber()})"><@spring.message code="recyclebinAudio.delete"/></button>
						<#else >
						<button class="refresh" type="button" onclick="ins(${a.audioId},${dels.getNumber()+1})"><@spring.message code="recyclebinAudio.restore"/></button>
						<button class="delete"  type="button" onclick="del(${a.audioId},${dels.getNumber()+1})"><@spring.message code="recyclebinAudio.delete"/></button>
						</#if><br />
					</div>
				</#list>

				</div>
				<nav class="navi">
					<#if dels.getTotalPages()=0 >
					<span style="text-align: center;
                                 width: 100%;"><h2><@spring.message code="recyclebinAudio.Recycle_Bin_is_empty"/></h2></span>
					</#if>
					<ul>
						<li>
							<a href="#" aria-label="Previous">
								<#if 0 < dels.getNumber()>
								<span aria-hidden="true" onclick="search(${dels.getNumber()})">&laquo;</span>
								<#else >
									<span aria-hidden="true">&laquo;</span>
								</#if>
							</a>
						</li>
                        <#if 5 <= dels.getTotalPages() >
                        <#if dels.getNumber()<2>
                            <#list  1..5 as y>
                            <li class="active">
                                <button class="buttons" onclick="search(${y})">${y}</button>
						    </li>
						    </#list>
                            <#elseif dels.totalPages <= dels.number+1>
                                <#list  dels.getNumber()-3..dels.getNumber()+1 as c>
                                    <li class="active">
                                        <button class="buttons" onclick="search(${c})">${c}</button>
                                    </li>
                                </#list>
                        <#elseif dels.totalPages <= dels.number+2>
                            <#list  dels.getNumber()-2..dels.getNumber()+2 as c>
                                <li class="active">
                                    <button class="buttons" onclick="search(${c})">${c}</button>
                                </li>
                            </#list>
                            <#else>
                                <#list  dels.getNumber()-1..dels.getNumber()+3 as z>
                                    <li class="active">
                                        <button class="buttons" onclick="search(${z})">${z}</button>
                                    </li>
                                </#list>
                        </#if>
							<#elseif dels.getTotalPages()=0 >
								<li class="active">
									<button>1</button>
								</li>
                            <#else>
                                <#list  1..dels.getTotalPages() as y>
                                    <li class="active">
                                        <button class="buttons" onclick="search(${y})">${y}</button>
                                    </li>
                                </#list>
                        </#if>

						<li>
							<a href="#" aria-label="Next">
								<#if dels.getNumber()+1 < dels.getTotalPages()>
								<span aria-hidden="true" onclick="search(${dels.getNumber()+2})">&raquo;</span>
								<#else>
									<span aria-hidden="true" >&raquo;</span>
								</#if>
							</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>

	</body>
	<script>
		function findDel() {
			search(1);
		}
		window.onload=(findDel);
		function del(id,page) {
			var  id1=new Array(1);
			id1[0] = id;
			$.post("/delAudio/delIds",{
				"ids":id1
			},function (data) {
				if(data.status == "success"){
					search(page);
				}else {
					alert("Fail delete");
				}
			})
		}
		function ins(id,page) {
			var  id1 =new Array(1);
			id1[0] = id;
			$.post("/delAudio/insertAudios",{
				"ids":id1
			},function (data) {
				if(data.status == "success"){
					search(page);
				}else {
					alert("Fail Recover");
				}
			})
		}
		function del(id,page) {
			var  id1=new Array(1);
			id1[0] = id;
			$.post("/delAudio/delIds",{
				"ids":id1
			},function (data) {
				if(data.status == "success"){
					search(page);
				}else {
					alert("Fail delete");
				}
			})
		}
		function delIds(page) {
			var c = new Array($(".xuanze").is('checked').length);
			var d=0;
			$(".xuanze").each(function () {
				if ($(this).is(':checked')){
					c[d]=$(this).attr('id');
					d++;
				}
			})
			$.post("/delAudio/delIds",{
				"ids":c
			},function (data) {
				if(data.status == "success"){
				    search(page);
                }else {
				    alert("Fail delete");
                }
			})
		}
		function insIds(page) {
			var c = new Array($(".xuanze").is('checked').length);
			var d=0;
			$(".xuanze").each(function () {
				if ($(this).is(':checked')){
					c[d]=$(this).attr('id');
					d++;
				}
			})
			$.post("/delAudio/insertAudios",{
				"ids":c
			},function (data) {
				if(data.status == "success"){
					search(page);
				}else {
					alert("Please click operation");
				}
			})
		}
		function search(page) {
			var l = localStorage.getItem("language");
			$.post("/delAudio/findAll?l="+l,{
			    "page":page-1,
			},function (data) {
				$("html").empty();
				$("html").html(data)
			})
		}

		$("#clear").click(function(){
			$.ajax({
				type: "post",
				url: "/delAudio/delAll",
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				success: function (data) {
					if (data.status == "success") {
						search(1);
					} else {
						alert("Fail delete");
					}
				}
			})
		});

	</script>
</html>
