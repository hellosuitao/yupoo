<!DOCTYPE html>
<html>
<head>
    <title>产品分类</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    window.onload = function () {

            <#if (user??)>

            $("#username").html("<a id='useraa'>Welcome：" + "</a>");
            $("#toplogin").html("<a href='script:void(0)' onclick='logout()'>Logout</a>");
            $("#useraa").text("Welcome：${user.username}")
            </#if>

    }

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
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

        .cs {
            color: #f00;
        }

        tr {
            margin: 0.3125rem;
            border-color: #138F3B;
        }

        td {
            border-color: #138F3B;
            background-color: white;
            border: 15px solid #138F3B;
            height: 100%;
        }
    </style>
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
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
        window.onload = function () {

            <#if (user??)>

            $("#username").html("<a id='useraa'>Welcome：" + "</a>");
            $("#toplogin").html("<a href='script:void(0)' onclick='logout()'>Logout</a>");
            $("#useraa").text("Welcome：${user.username}")
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
        <div class="agile-login">
            <ul>
                <li id="username">
                    <a href="registered.html">Create Account</a>
                </li>
                <li id="toplogin">
                    <a href="../static/login.html">Login</a>
                </li>
            </ul>
        </div>

        <div class="clearfix"></div>
    </div>
</div>

<div class="logo_products">
    <div class="container">
        <div class="w3ls_logo_products_left1">
            <ul class="phone_email">
                <li><i class="fa fa-phone" aria-hidden="true"></i>电话订单:028 66666666</li>

            </ul>
        </div>
        <div class="w3ls_logo_products_left">
            <h1><a href="/index">返回首页</a></h1>
        </div>
        <div class="w3l_search">
            <form action="/findByName" method="post">
                <input type="search" name="name" placeholder="搜索" required="">
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
                    <#list albumCategory as a>
                        <li class="active">
                            <a href="/findAllByCategoryId?albumCategory=${a.id}">${a.name}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </nav>
    </div>
</div>

<!-- //navigation -->

<!--- pakagedfoods --->

<div class="tab-content">

    <div class="container" style="background-color: #138F3B;margin-top: 4.625rem;margin-bottom: 4.625rem;padding: 5px">
        <div>
            <table style="width: 100%;border: 1px solid #138F3B;table-layout: fixed;" cellspacing="5px">
            <tr>
                    <#list albumPage.content as a>
                        <#if a_index=3>
                    <p>&nbsp</p>
                    <p>&nbsp</p>
                </tr>
                <tr>
                        </#if>
                    <td>

                        <div class="wrapper" style="width: 100%;height: auto;overflow: hidden;text-align: center;margin-bottom: 30px;margin-top: 30px">

                            <a href="findAlbumById?id=${a.id}"><img title=" " alt=" " src="${a.coverpath} " style="width: 100%;overflow: hidden"/></a>

                        </div>

                        <div class="wrapper"
                             style="width: 100%;
                         text-align: center;bottom: 10px;">

                            <p>${a.name}</p>

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
                    <#--</div>-->
                    <#--</figure>-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</div>-->
                    </td>
                    </#list>
            </tr>
            </table>
        </div>
        <nav class="numbering">
            <ul class="pagination paging">
                <li>
                    <a href="/findAllByCategoryId?page=${albumPage.number}&albumCategory=${albumCategoryId}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>

                </li>
                <#if 5 <= albumPage.totalPages >
                    <#if albumPage.number<2>
                        <#list  1..5 as y>
                            <li class="active">
                                <a href="/findAllByCategoryId?page=${y-1}&albumCategory=${albumCategoryId}">${y}
                                    <span class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    <#elseif albumPage.totalPages <= albumPage.number+1>
                        <#list  albumPage.number-3..albumPage.number+1 as c>
                            <li class="active">
                                <a href="/findAllByCategoryId?page=${c-1}&albumCategory=${albumCategoryId}">${c}
                                    <span class="sr-only">(current)</span></a></li>
                        </#list>
                    <#elseif albumPage.totalPages <= albumPage.number+2>
                        <#list  albumPage.number-2..albumPage.number+2 as c>
                            <li class="active">
                                <a href="/findAllByCategoryId?page=${c-1}&albumCategory=${albumCategoryId}">${c}
                                    <span class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    <#else>
                        <#list  albumPage.number-1..albumPage.number+3 as z>
                            <li class="active">
                                <a href="/findAllByCategoryId?page=${z-1}&albumCategory=${albumCategoryId}">${z}
                                    <span class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    </#if>
                <#else>
                    <#if albumPage.totalPages=0>
                        <li class="active">
                            <a href="/findAllByCategoryId?page=0&albumCategory=${albumCategoryId}">0<span
                                    class="sr-only">(current)</span></a>
                        </li>
                    <#else >
                        <#list  1..albumPage.totalPages as y>
                            <li class="active">
                                <a href="/findAllByCategoryId?page=${y-1}&albumCategory=${albumCategoryId}">${y}
                                    <span class="sr-only">(current)</span></a>
                            </li>
                        </#list>
                    </#if>


                </#if>
            <#--                <#list 0..albumPage.totalPages-1 as i>-->
            <#--                <li class="active">-->
            <#--&lt;#&ndash;					&albumCategory=${albumPage.content[0].uid}&ndash;&gt;-->
            <#--                    <a href="/findAllByCategoryId?page=${i}&albumCategory=${albumCategoryId}">${i+1}-->
            <#--                        <span class="sr-only">(current)</span></a>-->
            <#--                </li>-->
            <#--                </#list>-->
            <#--						<li class="active"><a href="#">1<span class="sr-only">(current)</span></a></li>-->
            <#--						<li><a href="#">2</a></li>-->
            <#--						<li><a href="#">3</a></li>-->
            <#--						<li><a href="#">4</a></li>-->
            <#--						<li><a href="#">5</a></li>-->
                <li>
                    <a href="/findAllByCategoryId?page=${albumPage.number}&albumCategory=${albumCategoryId}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <div class="clearfix"></div>

</div>
<!--- pakagedfoods --->
<!-- //footer -->
<div class="footer">
    <div class="container">
        <div class="w3_footer_grids">
            <div class="col-md-3 w3_footer_grid">
                <h3>联系</h3>

                <ul class="address">
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>此处商家联系地址</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="mailto:info@example.com">此处商家邮箱可为 超链接</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>028 66666666</li>
                </ul>
            </div>
            <div class="col-md-3 w3_footer_grid">
                <h3>信息</h3>
                <ul class="info">

                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="contact.html">联系我们</a></li>
                </ul>
            </div>
        <#--            <div class="col-md-3 w3_footer_grid">-->
        <#--                <h3>类别</h3>-->
        <#--                <ul class="info">-->
        <#--                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="#">A类</a></li>-->
        <#--                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="#">B类</a></li>-->
        <#--                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="#">C类</a></li>-->
        <#--                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="#">D类</a></li>-->
        <#--                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="#">E类</a></li>-->
        <#--                </ul>-->
        <#--            </div>-->
            <div class="col-md-3 w3_footer_grid">
                <h3>返回</h3>
                <ul class="info">

                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="../static/login.html">登录</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="registered.html">注册</a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

    <div class="footer-copy">

        <div class="container">
            <p>Copyright &copy; 2019睿讯商城 All rights reserved.</p>
        </div>
    </div>

</div>

<!-- //footer -->
<!-- Bootstrap Core JavaScript -->
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

        $().UItoTop({easingType: 'easeOutQuart'});

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
        // 	url: "/likeup",
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
                    window.location.href = "/index"

                } else {

                    // alert(data.message);
                    window.location.href = "/index"
                }
            },
            error: function (data) {
                window.location.href = "/index"
            }
        });
    }

</script>

</body>
</html>