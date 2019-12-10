<!DOCTYPE html>
<#import "/spring.ftl" as spring/>
<html>
	<head>
		<title>登陆</title>
		<!-- for-mobile-apps -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="" />
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<!-- //for-mobile-apps -->
		<link href="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- font-awesome icons -->
		<link href="/css/font-awesome.css" rel="stylesheet">
		<!-- //font-awesome icons -->
		<!-- js -->
		<script src="/js/jquery-1.11.1.min.js"></script>
		<!-- //js -->
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
		<!-- start-smoth-scrolling -->
		<script type="text/javascript" src="/js/move-top.js"></script>
		<script type="text/javascript" src="/js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event) {
					event.preventDefault();
					$('html,body').animate({
						scrollTop: $(this.hash).offset().top
					}, 1000);
				});
			});
		</script>
		<!-- start-smoth-scrolling -->
		<!--
	验证码样式
-->
		<style>

			.main_bar {
				
			}
			
			#vcode {
				height: 35px;
				width: 40%;
				font-size: 15pt;
				
				border-radius: 5px;
				border: 1px;
				padding-left: 8px;
				margin-top: 8px;
				display: inline;
				
			}
			
			#code {
				color: #ffffff;
				/*字体颜色白色*/
				background-color:#3399CC;
				/*margin-top:8px ;
				font-size: 20pt;
				background-size: 20px ;
				font-family: "华康娃娃体W5";
				padding: 5px 35px 10px 35px;*/
				height: 35px;
				width: 40%;
				font-size: 15pt;
				
				border-radius: 5px;
				border: 1px;
				padding-left: 8px;
				margin-top: 8px;
				display: inline;
				float: right;
				display: inline;
				cursor: pointer;
				
			}

			
			#search_pass_link {
				width: 70%;
				text-align: right;
				margin: 0 auto;
				padding: 5px;
			}
			
			.btns {
				width: 30%;
				margin-left: 13%;
				height: 40px;
				border: 0;
				font-size: 14pt;
				/*font-family;*/
				"微软雅黑";
				background-color: #138F3B;
				color: #ffffff;
				cursor: pointer;
				/*设置指针鼠标的样式*/
				border-radius: 20px;
				/*设置圆角样式*/
				border: 0;
			}
			#login1{
				transition: .5s ease-in;
				-webkit-transition: .5s ease-in;
				-moz-transition: .5s ease-in;
				-o-transition: .5s ease-in;
				-ms-transition: .5s ease-in;
				-webkit-appearance: button;
				cursor: pointer;
				width: 100%;
				border-radius: 8px;
				border: 0;
				background-color: #3399CC;
				margin-top: 10px;
				color: white;
				padding: 8px;
			}
			#login1:hover{
				transition: .5s ease-in;
				-webkit-transition: .5s ease-in;
				-moz-transition: .5s ease-in;
				-o-transition: .5s ease-in;
				-ms-transition: .5s ease-in;
				-webkit-appearance: button;
				cursor: pointer;
				width: 100%;
				border-radius: 8px;
				background-color: #138F3B;
				margin-top: 10px;
				padding: 8px;
			}
		</style>
		<!--适配手机-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	</head>

	<body leftmargin="0" onload="changeImg()">

		<div class="breadcrumbs">
			<div class="container">
				<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
					<li>
						<a href="../static/admin.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a>
					</li>
					<li class="active">登录界面</li>
				</ol>
			</div>
		</div>
		<!-- //breadcrumbs -->
		<!-- login -->
		<div class="login">
			<div class="container">
				<h2><@spring.message code="login.top"/></h2>

				<div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">

						<input type="email" id="email" placeholder='<@spring.message code="login.email"/>' required=" ">
						<input type="password" id="password4" placeholder='<@spring.message code="login.password"/>' required=" ">
						<div class="main_bar">
							<input type="text" id="vcode" placeholder='<@spring.message code="login.verify"/>'  onfocus="this.value=''" onblur="if(this.value=='')this.value='Verify code'" />
							<p id="code" title="change verification code">click to get verification code</p>
						</div>
						<div class="forgot">
							<a href="../static/updatepassword.html"><@spring.message code="login.forget" /></a>
						</div>
						<input type="button" onclick="login()" id="login1" value='<@spring.message code="login.login"/>'>

				<p>
<!--					<a href="../static/registered.html" style="color: #138F3B">Create an Account</a>-->
					<a href="../static/registered.html" style="color: #138F3B"><@spring.message code="login.create"/></a>
				</p>
				</div>
			</div>
		</div>

		<a onclick="lgotoGlobal(this)" href="/adminLoginGo?l=zh_CN">中文,</a>
		<a onclick="lgotoGlobal(this)" href="/adminLoginGo?l=en_US">English,</a>
		<a onclick="lgotoGlobal(this)" href="/adminLoginGo?l=fr_FR">Le français,</a>
		<a onclick="lgotoGlobal(this)" href="/adminLoginGo?l=es_LA">Espanol,</a>
		<a onclick="lgotoGlobal(this)" href="/adminLoginGo?l=it_IT">lingua italiana,</a>
		<a onclick="lgotoGlobal(this)" href="/adminLoginGo?l=de_DE">Deutsch</a>

		<script type="text/javascript">
			function lgotoGlobal(obj) {
				// console.log("one 语言选择是" + "de_DE");
				var ahref = obj.href;
				localStorage.setItem("language",ahref.split("=")[1]);//（模拟获取到了？后的值）放入全局变量
				// alert("this.name" + this.name);
				// window.location.href=this.name;
			}

		$(document).ready(function() {
			localStorage.setItem("language","zh_CN");
				/*
					var defaults = {
					containerID: 'toTop', // fading element id
					containerHoverID: 'toTopHover', // fading element hover id
					scrollSpeed: 1200,
					easingType: 'linear'
					};
				*/

				$().UItoTop({
					easingType: 'easeOutQuart'
				});

			});
		</script>

		<!-- //here ends scrolling icon -->
		<script src="../static/js/minicart.min.js"></script>
		<script>
			// Mini Cart
			paypal.minicart.render({
				action: '#'
			});

			if(~window.location.search.indexOf('reset=true')) {
				paypal.minicart.reset();
			}
		</script>
		<!-- main slider-banner -->
		<script src="../static/js/skdslider.min.js"></script>
		<link href="../static/css/skdslider.css" rel="stylesheet">
		<script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery('#demo1').skdslider({
					'delay': 5000,
					'animationSpeed': 2000,
					'showNextPrev': true,
					'showPlayButton': true,
					'autoSlide': true,
					'animationType': 'fading'
				});

				jQuery('#responsive').change(function() {
					$('#responsive_wrapper').width(jQuery(this).val());
				});

			});
		</script>
		<!-- //main slider-banner -->
		<!--
	作者：hbuilder_8.8.0
	时间：2019-11-11
	描述：验证码
-->
		<script type="text/javascript">
			var code; //声明一个变量用于存储生成的验证码
			document.getElementById("code").onclick = changeImg;

			function changeImg() {
				var arrays = new Array(
					'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
					'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z',
					'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
					'U', 'V', 'W', 'X', 'Y', 'Z'
				);
				code = ''; //重新初始化验证码
				//alert(arrays.length);
				//随机从数组中获取四个元素组成验证码
				for(var i = 0; i < 4; i++) {
					//随机获取一个数组的下标
					var r = parseInt(Math.random() * arrays.length);
					code += arrays[r];
				}
				document.getElementById('code').innerHTML = code; //将验证码写入指定区域
			}

			//验证码前端校验登陆
			$(document).ready(function () {
                $("#login1").click(function () {
                    //获取用户输入的验证码
                    var input_code =$("#vcode").val();
                    if(input_code.toLowerCase() == code.toLowerCase()) {
                        // todo 验证码正确(表单提交的方法还没有写哈，强行跳转了先)
                        // alert("前端校验完成，准备跳转到首页")
                        var email=$("#email").val();
                        var password=$("#password4").val();
                        var data="email="+email+"&password="+password;
                        $.ajax({
                            url:'/login/log',
                            type:'post',
                            data: data,
                            dataType:"json",
                            success:function (data) {
                                if("success"==data.status){
                                    var username = data.data.username;
                                    var userId=data.data.id;
                                    localStorage.setItem("username",username);
                                    localStorage.setItem("userId",userId);
									window.location.href="admin.html";
								}else{
                                    alert(data.message);
								}
                            }

                        });

                    }else{
                        alert("Verification code not correct!");
                        //验证码不正确,表单不允许提交
                        return false;
                    }
                    });
            });

		</script>

	</body>

</html>