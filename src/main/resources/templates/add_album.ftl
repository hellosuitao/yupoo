<div class="container">
    <form action="#" method="get" class="mt-4">
        <div class="form-group">
            <label class="h4" for="contactcomment">评论一下吧</label>
            <textarea class="form-control border" rows="5" id="contactcomment" required=""></textarea>
        </div>
        <button type="button" id="submit" class="mt-3 btn btn-success btn-block py-3" onclick="addmes()">提交</button>
    </form>
    <h3 class="h4">其他精彩评论</h3>
    <div class="media-body" id="mess">
        <#list Mess.content as m>
        <#--            <#if m_index=2>-->
        <#--                <#break >-->
        <#--            </#if>-->
            <div class="comment">
                <!-- <img src="images/te2.jpg" class="img-circle image-responsive" alt="image"> -->
                <h5 class="h5">${m.name}</h5>
                <p class="lead" id="messMes${m_index}">
                    ${m.mes}
                </p>
            </div>

        </#list>

        <#--        <div class="comment">-->
        <#--            <!-- <img src="images/te1.jpg" class="img-circle image-responsive" alt="image"> &ndash;&gt;-->
        <#--            <h5 class="h5">匿名用户</h5>-->
        <#--            <p class="lead">本来还以为怕到了不新鲜 ， 到了打一看 ，惊了 ，蛮新鲜的 ， 洗了尝尝特别好吃 。看了没有坏的分量也足-->
        <#--                第一次品尝新鲜柠檬，原来一直吃的是柠檬水，感觉味道不错，柠檬都完好无损没有坏的，有几个叶子还是绿的挺新鲜，吃了一个酸酸甜甜的，可能还不够熟透，放多几天再吃挺好看，总之对口感和外观还是比较满意的以后买水果就上你家了，希望你们继续保持质量哦~祝生意越来越好！</p>-->
        <#--        </div>-->
    </div>
    <#--    <input type="button" value="上一页" onclick="flipOver(-1)"><span id="currentPage">第${Mess.number+1}页</span><span id="currentPage">共${Mess.totalPages}页</span><input type="button" value="下一页" onclick="flipOver(1)">-->
    <nav class="numbering">
        <ul class="pagination paging">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <#if 5 <= Mess.totalPages >
                <#if Mess.number<2>
                    <#list  1..5 as y>
                        <li class="active">
                            <a href="javascript:void(0)" onclick="updownpage(${y-1})">${y}<span class="sr-only">(current)</span></a>
                        </li>
                    </#list>
                <#elseif Mess.totalPages <= Mess.number+1>
                    <#list  Mess.number-3..Mess.number+1 as c>
                        <li class="active">
                            <a href="javascript:void(0)" onclick="updownpage(${c-1})">${c}<span class="sr-only">(current)</span></a>
                        </li>
                    </#list>
                <#elseif Mess.totalPages <= Mess.number+2>
                    <#list  Mess.number-2..Mess.number+2 as c>
                        <li class="active">
                            <a href="javascript:void(0)" onclick="updownpage(${c-1})">${c}<span class="sr-only">(current)</span></a>
                        </li>
                    </#list>
                <#else>
                    <#list  Mess.number-1..Mess.number+3 as z>
                        <li class="active">
                            <a href="javascript:void(0)" onclick="updownpage(${z-1})">${z}<span class="sr-only">(current)</span></a>
                        </li>
                    </#list>
                </#if>
            <#else>
                <#if Mess.totalPages=0>
                    <li class="active"><a href="javascript:void(0)">0<span class="sr-only">(current)</span></a></li>
                    <#else>
                <#list  1..Mess.totalPages as y>

                    <li class="active"><a href="javascript:void(0)" onclick="updownpage(${y-1})">${y}<span class="sr-only">(current)</span></a></li>

                </#list>
                </#if>
            </#if>

            <li>
                <a href="" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>