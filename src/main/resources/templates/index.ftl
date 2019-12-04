<!DOCTYPE html>
<html>

<head>
    <title>主页</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <style>
        .nav li {
            padding-left: 25px;
            padding-right: 25px;
        }

        .like {
            font-size: 20px;
            color: #ccc;
            cursor: pointer;
        }

        tr {
            margin: 0.3125rem;
            /*border-color: #FE9126;*/
            border-color: #138F3B;
        }

        td {
            border-color: #138F3B;
            background-color: white;
            border: 15px solid #138F3B;
            height: 100%;
            padding-bottom: 0;
        }

        .cs {
            color: #f00;
        }

        .stars {
            display: none;
        }

    </style>
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic'
          rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <#assign isup = 0 >
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 1000);
            });
        });
        window.onload = function () {
            <#if (user??)>
            localStorage.setItem("username",${user.username})
            localStorage.setItem("userId",${user.id})
            $("#username").html("<a id='useraa'>Welcome：" + "</a>");
            $("#toplogin").html("<a href='javascript:;' onclick='logout()'>SignOut</a>");
            $("#useraa").text("Welcome!${user.username}")
            </#if>
        }
    </script>
    <!-- start-smoth-scrolling -->
    <!--适配手机-->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
</head>

<body>
<!-- header -->
<div class="agileits_header">
    <div class="container">
        <div class="w3l_offers">
        </div>
        <div class="agile-login">
            <ul>
                <li id="username">
                    <a href="registered.html">Create Account</a>
                </li>
                <li id="toplogin">
                    <a href="login.html">Login</a>
                </li>
                <li id="myAdmin">
                    <a href="admin.html">Admin</a>
                </li>
            </ul>
        </div>
        <div class="product_list_header">
            <form action="#" method="post" class="last">
                <input type="hidden" name="cmd" value="_cart">
                <input type="hidden" name="display" value="1">
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<div class="logo_products">
    <div class="container">
        <div class="w3ls_logo_products_left1">
            <ul class="phone_email">
                <li><i class="fa fa-phone" aria-hidden="true"></i>TEL:028 66666666</li>

            </ul>
        </div>
        <div class="w3ls_logo_products_left">
            <h1><a href="/index">测试页面</a></h1>
        </div>
        <div class="w3l_search">
            <form action="#" method="post">
                <input type="search" name="Search" placeholder="search...	" required="">
                <button type="submit" class="btn btn-default search" aria-label="Left Align">
                    <i class="fa fa-search" aria-hidden="true"> </i>
                </button>
                <div class="clearfix"></div>
            </form>
        </div>

        <div class="clearfix"></div>
    </div>
</div>
<!-- //header -->
<!-- navigation -->
<div class="navigation-agileits">
    <div class="container">
        <nav class="navbar navbar-default">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header nav_2">
                <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                        data-target="#bs-megadropdown-tabs">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="/index" class="act">Home</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categories<b class="caret"></b></a>
                        <ul class="dropdown-menu multi-column columns-3">
                            <div class="row">
                                <div class="multi-gd-img">
                                    <ul class="multi-column-dropdown">
                                        <h6 style="color: #138F3B">All Categories</h6>
                                        <#list albumCategory as category>
                                            <li>
                                                <a href="findAllByCategoryId?albumCategory=${category.id}">${category.name}</a>
                                            </li>

                                        </#list>

                                    </ul>
                                </div>

                            </div>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<!-- //navigation -->
<!-- main-slider -->
<ul id="demo1">
    <li>
        <img src="images/11.jpg" alt=""/>
        <!--Slider Description example-->
        <div class="slide-desc">
            <h3>睿讯项目测试页面</h3>
        </div>
    </li>
    <li>
        <img src="images/22.jpg" alt=""/>
        <div class="slide-desc">
            <h3>睿讯项目测试页面</h3>
        </div>
    </li>

    <li>
        <img src="images/44.jpg" alt=""/>
        <div class="slide-desc">
            <h3>睿讯项目测试页面</h3>
        </div>
    </li>
</ul>
<!-- //main-slider -->
<!-- //top-header and slider -->
<!-- top-brands -->
<div class="tlinks">Collect from
    <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a>
</div>
<div class="top-brands">
    <div class="container"
         style="background-color: white;margin-top: 4.625rem;margin-bottom: 4.625rem;padding:0.3125rem;">
<#--         style="background-color: #FE9126;margin-top: 4.625rem;margin-bottom: 4.625rem;padding:0.3125rem;">-->
        <table  style="width: 100%;border: 1px solid #138F3B;table-layout: fixed;" cellspacing="5px">
<#--        <table  style="width: 100%;border: 1px solid #FE9126;table-layout: fixed;" cellspacing="5px">-->
        <tr>
                <#list albumPage.content as a >
                    <#if (a_index==3)>
                <p>&nbsp</p>
                <p>&nbsp</p>
            </tr>
            <tr>
                    </#if>
                <td>
                <#--<div class="yyx" style="width: 100%;height: 100%;border: 2px solid black;">-->
                <#--<div class="agile_top_brand_left_grid" >-->

                <#--<div class="agile_top_brand_left_grid1">-->
                <#--<figure>-->
                <#--<div class="snipcart-item block">-->
                <#--<div class="yyx" style="height: 100%;width: 100%;border:2px solid black;margin: auto;">-->

                    <div class="wrapper" style="width: 100%;height: auto;overflow: hidden;text-align: center;margin-bottom: 30px;margin-top: 30px">
                        <a href="findAlbumById?id=${a.id}"><img title=" " alt=" " src="${a.coverpath}"
                         style="width: 100%;overflow: hidden"/></a>
                    </div>

                <#--</div>-->
                    <div class="wrapper"
                         style="width: 100%;text-align: center;bottom: 10px;height: auto;">

                        <p>${a.name}</p>
                        <div class="stars">
                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                            <i class="fa fa-star blue-star" aria-hidden="true"></i>
                            <i class="fa fa-star gray-star" aria-hidden="true"></i>
                        </div>
                        <h4 style="color: orangered">￥${a.price}</h4>
                        <div id="dianzan"
                             style="font-size: 12px;padding-top: 20px;text-align: center;">
                                                                <span class="like" onclick="likes(${a.id},${a_index})"
                                                                      id="iszan${a_index}">&#10084;</span><span
                                id="num${a_index}">${picStatuByPidS[a_index].upnum}</span>
                            <span style="padding-left: 20px;">浏览量：</span><span
                                id="liulan">${picStatuByPidS[a_index].looknum}</span>
                        </div>

                    </div>

                <#--</div>-->
                <#--</figure>-->
                <#--</div>-->
                <#--</div>-->
                <#--</div>-->
                </td>
                </#list>
        </tr>
        </table>
        <div class="clearfix"></div>


        <nav class="numbering">
            <ul class="pagination paging">

                <li>
                    <a href="/index?page=${albumPage.number}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>

                </li>
                <#if 5 <= albumPage.totalPages >
                    <#if albumPage.number<2>
                        <#list  1..5 as y>
                            <li class="active">
                                <a href="/index?page=${y-1}">${y}<span
                                        class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    <#elseif albumPage.totalPages <= albumPage.number+1>
                        <#list  albumPage.number-3..albumPage.number+1 as c>
                            <li class="active">
                                <a href="/index?page=${c-1}">${c}<span
                                        class="sr-only">(current)</span></a></li>
                        </#list>
                    <#elseif albumPage.totalPages <= albumPage.number+2>
                        <#list  albumPage.number-2..albumPage.number+2 as c>
                            <li class="active">
                                <a href="/index?page=${c-1}">${c}<span
                                        class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    <#else>
                        <#list  albumPage.number-1..albumPage.number+3 as z>
                            <li class="active">
                                <a href="/index?page=${z-1}">${z}<span
                                        class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    </#if>
                <#else>
                    <#list  1..albumPage.totalPages as y>
                        <li class="active">
                            <a href="/index?page=${y-1}">${y}<span class="sr-only">(current)</span></a>
                        </li>
                    </#list>
                </#if>
                <li>
                    <a href="/index?page=${albumPage.number+1}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</div>
</div>
</div>
<!-- //top-brands -->
<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <a href="beverages.html"> <img class="first-slide" src="images/b1.jpg" alt="First slide"></a>

        </div>
        <div class="item">
            <a href="personalcare.html"> <img class="second-slide " src="images/b3.jpg" alt="Second slide"></a>

        </div>
        <div class="item">
            <a href="household.html"><img class="third-slide " src="images/b1.jpg" alt="Third slide"></a>

        </div>
    </div>

</div>
<!-- /.carousel -->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="w3_footer_grids">
            <#--<div class="col-md-3 w3_footer_grid">
                <h3>联系</h3>

                <ul class="address">
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>此处商家联系地址</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>
                        <a href="mailto:info@example.com">此处商家邮箱可为 超链接</a>
                    </li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>028 66666666</li>
                </ul>
            </div>-->
           <#-- <div class="col-md-3 w3_footer_grid">
                <h3>信息</h3>
                <ul class="info">

                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i>
                        <a href="contact.html">联系我们</a>
                    </li>
                </ul>
            </div>-->

            <div class="col-md-3 w3_footer_grid">
                <h3>RETURN</h3>
                <ul class="info">

                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i>
                        <a href="login.html">Login</a>
                    </li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i>
                        <a href="registered.html">Create Account</a>
                    </li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

    <div class="footer-copy">

        <div class="container">
            <p>Copyright &copy; 2019睿讯商城All rights reserved.</p>
        </div>
    </div>

</div>
<script src="js/bootstrap.min.js"></script>

<!-- top-header and slider -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function () {
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
<script src="js/minicart.min.js"></script>
<script>
    // Mini Cart
    paypal.minicart.render({
        action: '#'
    });

    if (~window.location.search.indexOf('reset=true')) {
        paypal.minicart.reset();
    }
</script>
<!-- main slider-banner -->
<script src="js/skdslider.min.js"></script>
<link href="css/skdslider.css" rel="stylesheet">
<script type="text/javascript">
    jQuery(document).ready(function () {
        jQuery('#demo1').skdslider({
            'delay': 5000,
            'animationSpeed': 2000,
            'showNextPrev': true,
            'showPlayButton': true,
            'autoSlide': true,
            'animationType': 'fading'
        });

        jQuery('#responsive').change(function () {
            $('#responsive_wrapper').width(jQuery(this).val());
        });

    });
</script>
<!-- //main slider-banner -->
<!--点赞-->
<script>
    $(function () {
        $(".like").click(function () {
            $(this).toggleClass('cs');
        })
    })

    function likes(id, pid) {


        // $.ajax({
        // 	type: "post",
        // 	url: "http://localhost:8080/likeup",
        // 	data:JSON.stringify({"pid":id}),
        // 	contentType: "application/json;charset=UTF-8",
        // 	dataType: "json",
        $.post("/likeup",
                {
                    "pid": id,
                    "isup": $("#iszan" + pid).hasClass('cs'),
                }, function (data) {

                    $(("#num" + pid)).text(data)
                });
    }

    function logout() {
        $.ajax({
            url: '/login/logout',
            type: 'get',
            data: null,
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    localStorage.setItem("usernaem",null);
                    localStorage.setItem("userId",null);
                    window.location.href = "/index";
                }
            },
            error: function (data) {
                window.location.href = "/index"
            }
        });
    }

    // }
    //
    // 		alert("客户信息更新成功！");
    // 		window.location.href="onlineBookstore.jsp";
    //
    // 	} else {
    // 		alert("客户信息更新失败！");
    //
    // 		searchCustomer(1);
    // 	}
    // });

</script>
</body>

</html>