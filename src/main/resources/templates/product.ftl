<!DOCTYPE html>
<html>
<head>
    <title>商品详情</title>
    <!-- 页面作者：YangYuxuan -->
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript">
        addEventListener("load", function () {
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
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- 自定义css -->
    <style type="text/css">
        .comment {
            border-top: 1px solid gray;
            padding-top: 0.625rem;
            /* 向下平移20像素单位长度 */
            margin-top: 0.3125rem;
        }

        .piclist {
            width: 23%;
            margin-top: 0.3125rem;
            opacity: 0.3;
        }

        .piclist:hover {
            border: 0.0625rem solid orange;
            opacity: 1;
        }

        .like {
            font-size: 20px;
            color: #ccc;
            cursor: pointer;
        }

        .cs {
            color: #f00;
        }

        .paging > .active > a,
        .paging > .active > a:hover {
            background-color: #5CB85C;
            border-color: forestgreen;
        }
    </style>
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
    <link href="js/sharejs/share.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/sharejs/jquery.share.min.js"></script>

    <#--share.js-->
    <script type="text/javascript">
        var $config = {
            url                 : window.location.href,// 网址，默认使用 window.location.href
            source              : '', // 来源（QQ空间会用到）, 默认读取head标签：<meta name="site" content="http://overtrue" />
            title               : '你好分享', // 标题，默认读取 document.title 或者 <meta name="title" content="share.js" />
            description         : '', // 描述, 默认读取head标签：<meta name="description" content="PHP弱类型的实现原理分析" />
            image               : '你好图片', // 图片, 默认取网页中第一个img标签
            sites               : ['qzone', 'qq', 'weibo','wechat'], // 启用的站点
            disabled            : ['google', 'facebook', 'twitter'], // 禁用的站点
            wechatQrcodeTitle   : '微信扫一扫：分享', // 微信二维码提示文字
            wechatQrcodeHelper  : '<p>微信里点“发现”，扫一下</p><p>二维码便可将本文分享至朋友圈。</p>',
            target : '_blank' //打开方式
        };
        $('.social-share').share($config);
    </script>

    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 1000);
            });
        });
    </script>
    <!-- start-smoth-scrolling -->
    <!--适配手机-->
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
</head>
<body>
<div class="social-share">分享</div>
<!-- header -->
<div class="agileits_header">
    <div class="container">
        <div class="agile-login">
            <ul>
                <li id="username">
                    <a href="registered.html">Create Account</a>
                </li>
                <li id="toplogin">
                    <a href="login.html">Login</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="logo_products">
    <div class="container">
        <div class="w3ls_logo_products_left1">
            <ul class="phone_email">
                <li><i class="fa fa-phone" aria-hidden="true"></i>电话订单：028-6666666</li>
            </ul>
        </div>
        <div class="w3ls_logo_products_left">
            <h1><a href="/index">睿讯商城</a></h1>
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

<!-- breadcrumbs -->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="/index"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a></li>
            <li class="active"><#list album.albumCategories as a>${a.name}<#if (a_has_next=true)>/</#if></#list></li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<div class="products">
    <div class="container">
        <div class="agileinfo_single">

            <div class="col-md-4 agileinfo_single_left">
                <div class="wrapper" style="width: 100%;height: 0;padding-bottom: 100%;overflow: hidden">
                    <img id="showpic" src="${album.coverpath}" alt=" " class="img-responsive"
                         style="width: 100%;">
                </div>
                <div style="width: auto;margin-top: auto;">
                    <#list "${album.pictures}"?split(",") as img>
                        <#if img!="">
                        <img src="${img}" alt=" " class="piclist"></#if>
                    </#list>
                </div>
            </div>


            <div class="col-md-8 agileinfo_single_right">
                <h2>${album.name}</h2>
                <!-- 点赞位置 -->
                <div id="dianzan" style="font-size: 12px;">
                    <span class="like" id="iszan" onclick="likes(${picStatu.pid},1)">&#10084;</span><span
                            id="num">${picStatu.upnum}</span>
                    <span style="padding-left: 30px;">浏览量：</span><span id="liulan">${picStatu.looknum}</span>
                </div>
                <div class="w3agile_description">
                    <h4>商品描述 :</h4>
                    <p>
                        ${album.description}
                    </p>
                </div>
                <div class="snipcart-item block">
                    <div class="snipcart-thumb agileinfo_single_right_snipcart">
                        <h1 class="h1" style="color: orangered;">￥${album.price}</h1>
                    </div>
                    <div class="snipcart-details agileinfo_single_right_details">
                        <!-- <input type="button" name="viewComment" value="查看评论" class="button"> -->
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="container" id="container" style="display:none;">
    <#--    <form action="#" method="get" class="mt-4">-->
    <#--        <div class="form-group">-->
    <#--            <label class="h4" for="contactcomment">评论一下吧</label>-->
    <#--            <textarea class="form-control border" rows="5" id="contactcomment" required=""></textarea>-->
    <#--        </div>-->
    <#--    </form>-->
    <#--    <button type="button" class="mt-3 btn btn-success btn-block py-3" onclick="addmes()">提交</button>-->

    <#--    <h3 class="h4">其他精彩评论</h3>-->
    <#--    <div class="media-body" id="mess">-->
    <#--        <#list Mess.content as m>-->
    <#--        &lt;#&ndash;            <#if m_index=2>&ndash;&gt;-->
    <#--        &lt;#&ndash;                <#break >&ndash;&gt;-->
    <#--        &lt;#&ndash;            </#if>&ndash;&gt;-->
    <#--            <div class="comment">-->
    <#--                <!-- <img src="images/te2.jpg" class="img-circle image-responsive" alt="image"> &ndash;&gt;-->
    <#--                <h5 class="h5">${m.name}</h5>-->
    <#--                <p class="lead" id="messMes${m_index}">-->
    <#--                    ${m.mes}-->
    <#--                </p>-->
    <#--            </div>-->

    <#--        </#list>-->

    <#--        &lt;#&ndash;        <div class="comment">&ndash;&gt;-->
    <#--        &lt;#&ndash;            <!-- <img src="images/te1.jpg" class="img-circle image-responsive" alt="image"> &ndash;&gt;&ndash;&gt;-->
    <#--        &lt;#&ndash;            <h5 class="h5">匿名用户</h5>&ndash;&gt;-->
    <#--        &lt;#&ndash;            <p class="lead">本来还以为怕到了不新鲜 ， 到了打一看 ，惊了 ，蛮新鲜的 ， 洗了尝尝特别好吃 。看了没有坏的分量也足&ndash;&gt;-->
    <#--        &lt;#&ndash;                第一次品尝新鲜柠檬，原来一直吃的是柠檬水，感觉味道不错，柠檬都完好无损没有坏的，有几个叶子还是绿的挺新鲜，吃了一个酸酸甜甜的，可能还不够熟透，放多几天再吃挺好看，总之对口感和外观还是比较满意的以后买水果就上你家了，希望你们继续保持质量哦~祝生意越来越好！</p>&ndash;&gt;-->
    <#--        &lt;#&ndash;        </div>&ndash;&gt;-->
    <#--    </div>-->
    <#--    &lt;#&ndash;    <input type="button" value="上一页" onclick="flipOver(-1)"><span id="currentPage">第${Mess.number+1}页</span><span id="currentPage">共${Mess.totalPages}页</span><input type="button" value="下一页" onclick="flipOver(1)">&ndash;&gt;-->
    <#--    <nav class="numbering">-->
    <#--        <ul class="pagination paging">-->
    <#--            <li>-->
    <#--                <a href="javascript:void(0)" aria-label="Previous" onclick="updownpage(0)">-->
    <#--                    <span aria-hidden="true">&laquo;</span>-->
    <#--                </a>-->
    <#--            </li>-->
    <#--            <#if 5 <= Mess.totalPages >-->
    <#--                <#if Mess.number<2>-->
    <#--                    <#list  1..5 as y>-->
    <#--                        <li class="active">-->
    <#--                            <a href="javascript:void(0)" onclick="updownpage(${y-1})">${y}<span class="sr-only">(current)</span></a>-->
    <#--                        </li>-->
    <#--                    </#list>-->
    <#--                <#elseif Mess.totalPages <= Mess.number+1>-->
    <#--                    <#list  Mess.number-3..Mess.number+1 as c>-->
    <#--                        <li class="active">-->
    <#--                            <a href="javascript:void(0)" onclick="updownpage(${c-1})">${c}<span class="sr-only">(current)</span></a>-->
    <#--                        </li>-->
    <#--                    </#list>-->
    <#--                <#elseif Mess.totalPages <= Mess.number+2>-->
    <#--                    <#list  Mess.number-2..Mess.number+2 as c>-->
    <#--                        <li class="active">-->
    <#--                            <a href="javascript:void(0)" onclick="updownpage(${c-1})">${c}<span class="sr-only">(current)</span></a>-->
    <#--                        </li>-->
    <#--                    </#list>-->
    <#--                <#else>-->
    <#--                    <#list  Mess.number-1..Mess.number+3 as z>-->
    <#--                        <li class="active">-->
    <#--                            <a href="javascript:void(0)" onclick="updownpage(${y-1})">${y}<span class="sr-only">(current)</span></a>-->
    <#--                        </li>-->
    <#--                    </#list>-->
    <#--                </#if>-->
    <#--            <#else>-->
    <#--                <#if Mess.totalPages=0>-->
    <#--                    <li class="active"><a href="javascript:void(0)" onclick="updownpage(0)">0 <span class="sr-only">(current)</span></a>-->
    <#--                    </li>-->

    <#--                <#else >-->
    <#--                    <#list  1..Mess.totalPages as y>-->

    <#--                        <li class="active"><a href="javascript:void(0)" onclick="updownpage(${y-1})">${y}<span-->
    <#--                                        class="sr-only">(current)</span></a></li>-->

    <#--                    </#list>-->
    <#--                </#if>-->

    <#--            </#if>-->

    <#--            &lt;#&ndash;            <#list 0..Mess.totalPages-1 as m>&ndash;&gt;-->
    <#--            &lt;#&ndash;                <#if m_index=5>&ndash;&gt;-->
    <#--            &lt;#&ndash;                    <#break>&ndash;&gt;-->
    <#--            &lt;#&ndash;                </#if>&ndash;&gt;-->
    <#--            &lt;#&ndash;                <li class="active"><a href="javascript:void(0)" onclick="flipOver(${m})">${m+1}<span class="sr-only">(current)</span></a></li>&ndash;&gt;-->

    <#--            &lt;#&ndash;            </#list>&ndash;&gt;-->
    <#--            &lt;#&ndash;            <li><a href="#">2</a></li>&ndash;&gt;-->
    <#--            &lt;#&ndash;            <li><a href="#">3</a></li>&ndash;&gt;-->
    <#--            &lt;#&ndash;            <li><a href="#">4</a></li>&ndash;&gt;-->
    <#--            &lt;#&ndash;            <li><a href="#">5</a></li>&ndash;&gt;-->
    <#--            <li>-->
    <#--                <a href="javascript:void(0)" aria-label="Next" onclick="updownpage(1)">-->
    <#--                    <span aria-hidden="true">&raquo;</span>-->
    <#--                </a>-->
    <#--            </li>-->
    <#--        </ul>-->
    <#--    </nav>-->
</div>

</div>
</div>
<!-- new -->
<#--<div class="newproducts-w3agile">-->
<#--    <div class="container">-->
<#--        <h3>更多新品</h3>-->
<#--        <div class="agile_top_brands_grids">-->
<#--            <div class="col-md-3 top_brand_left-1">-->
<#--                <div class="hover14 column">-->
<#--                    <div class="agile_top_brand_left_grid">-->
<#--                        <div class="agile_top_brand_left_grid_pos">-->
<#--                            <img src="images/offer.png" alt=" " class="img-responsive">-->
<#--                        </div>-->
<#--                        <div class="agile_top_brand_left_grid1">-->
<#--                            <figure>-->
<#--                                <div class="snipcart-item block">-->
<#--                                    <div class="snipcart-thumb">-->
<#--                                        <a href="product.html"><img title=" " alt=" " src="images/14.png"></a>-->
<#--                                        <p>阿克苏红心苹果</p>-->
<#--                                        <div class="stars">-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>-->
<#--                                        </div>-->
<#--                                        <h4>￥58</h4>-->
<#--                                    </div>-->
<#--                                    <div class="snipcart-details top_brand_home_details">-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </figure>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="col-md-3 top_brand_left-1">-->
<#--                <div class="hover14 column">-->
<#--                    <div class="agile_top_brand_left_grid">-->
<#--                        <div class="agile_top_brand_left_grid_pos">-->
<#--                            <img src="images/offer.png" alt=" " class="img-responsive">-->
<#--                        </div>-->
<#--                        <div class="agile_top_brand_left_grid1">-->
<#--                            <figure>-->
<#--                                <div class="snipcart-item block">-->
<#--                                    <div class="snipcart-thumb">-->
<#--                                        <a href="product.html"><img title=" " alt=" " src="images/15.png"></a>-->
<#--                                        <p>库尔勒香梨</p>-->
<#--                                        <div class="stars">-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>-->
<#--                                        </div>-->
<#--                                        <h4>￥30.99</h4>-->
<#--                                    </div>-->
<#--                                    <div class="snipcart-details top_brand_home_details">-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </figure>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="col-md-3 top_brand_left-1">-->
<#--                <div class="hover14 column">-->
<#--                    <div class="agile_top_brand_left_grid">-->
<#--                        <div class="agile_top_brand_left_grid_pos">-->
<#--                            <img src="images/offer.png" alt=" " class="img-responsive">-->
<#--                        </div>-->
<#--                        <div class="agile_top_brand_left_grid1">-->
<#--                            <figure>-->
<#--                                <div class="snipcart-item block">-->
<#--                                    <div class="snipcart-thumb">-->
<#--                                        <a href="product.html"><img title=" " alt=" " src="images/15.png"></a>-->
<#--                                        <p>库尔勒香梨</p>-->
<#--                                        <div class="stars">-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>-->
<#--                                        </div>-->
<#--                                        <h4>￥30.99</h4>-->
<#--                                    </div>-->
<#--                                    <div class="snipcart-details top_brand_home_details">-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </figure>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="col-md-3 top_brand_left-1">-->
<#--                <div class="hover14 column">-->
<#--                    <div class="agile_top_brand_left_grid">-->
<#--                        <div class="agile_top_brand_left_grid_pos">-->
<#--                            <img src="images/offer.png" alt=" " class="img-responsive">-->
<#--                        </div>-->
<#--                        <div class="agile_top_brand_left_grid1">-->
<#--                            <figure>-->
<#--                                <div class="snipcart-item block">-->
<#--                                    <div class="snipcart-thumb">-->
<#--                                        <a href="product.html"><img title=" " alt=" " src="images/15.png"></a>-->
<#--                                        <p>库尔勒香梨</p>-->
<#--                                        <div class="stars">-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star blue-star" aria-hidden="true"></i>-->
<#--                                            <i class="fa fa-star gray-star" aria-hidden="true"></i>-->
<#--                                        </div>-->
<#--                                        <h4>￥30.99</h4>-->
<#--                                    </div>-->
<#--                                    <div class="snipcart-details top_brand_home_details">-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </figure>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="clearfix"></div>-->
<#--        </div>-->
<#--    </div>-->
<#--</div>-->
<!-- //new -->
<!-- //footer -->
<div class="footer">
    <div class="container">
        <div class="w3_footer_grids">
            <div class="col-md-3 w3_footer_grid">
                <h3>联系</h3>

                <ul class="address">
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>此处商家联系地址</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                                href="mailto:info@example.com">此处商家邮箱可为
                            超链接</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>028 66666666</li>
                </ul>
            </div>
            <div class="col-md-3 w3_footer_grid">
                <h3>信息</h3>
                <ul class="info">

                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="contact.html">联系我们</a></li>
                </ul>
            </div>

            <div class="col-md-3 w3_footer_grid">
                <h3>返回</h3>
                <ul class="info">
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="login.html">登录</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="registered.html">注册</a></li>
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

    // 点击图片进行切换显示
    $(".piclist").hover(function () {
        var image = this.getAttribute("src");
        var showpic = document.getElementById("showpic");
        showpic.src = image;

    });
</script>
<!-- 点赞 -->
<script>
    $(function () {
        $(".like").click(function () {
            $(this).toggleClass('cs');
        })
    });

    function addmes() {
        var a = $("#contactcomment").val()
        $("#mess").prepend(' <div class="comment" id="mes">\n' +
            '                        <h5 class="h5">匿名用户</h5>\n' +
            '                        <p class="lead">\n' +
            '                       ' + a + ' \n' +
            '                        </p>\n' +
            '                        </div>');
    }

    function addmes() {
        var mes = $("#contactcomment").val();
        $.ajax({
            type: "post",
            url: "/insmes",
            data: JSON.stringify({"pid":${album.id}, "name": "匿名用户", "mes": mes}),
            contentType: "application/json;charset=UTF-8",
            dataType: "json",

            success: function (data) {
                if (data.code == "20000") {
                    alert("留言添加成功");
                    updownpage(0);
                    $("#contactcomment").val("");
                }
                if (data.code == "50000") {
                    alert("留言添加失败");
                    alert(data.message);
                }
            },
            error: function () {
                alert("留言添加失败")
            }
        })


    }

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
                "isup": $("#iszan").hasClass('cs'),
            }, function (data) {
                $("#num").text(data)
            });
    }

    function flipOver(id) {
        var currentPage = id;
        // alert(currentPage);
        $.ajax({
            type: "post",
            url: "/message/mesList",
            data: JSON.stringify({"page": currentPage, "pid":${album.id}}),
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            success: function (data) {

                $("#messMes0").text(data.data.content[0].mes);

                if (data.data.content[1] != null) {
                    $("#messMes1").text(data.data.content[1].mes);
                }
                if ($("#mes").html() != "") {
                    $("#mes").remove();
                }

            }

        })
    }

    $(function () {
        updownpage(0);
    });

    function updownpage(page) {
        $.post("/mesOverPage", {
            "pid":${album.id},
            "page": page,
        }, function (data) {

            $("#container").empty();
            $("#container").html(data);

        })
    }

</script>

</body>
</html>
