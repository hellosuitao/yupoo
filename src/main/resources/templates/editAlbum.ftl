<div id="main">
    <input type="hidden" id="albumId" value="${album.id}"/>
    <input type="hidden" id="beforePictures" value="${beforePics}">
    <input type="hidden" id="beforeAudios" value="${beforeAudios}">
    <div class="subfild-content base-info">
        <div class="kv-item ue-clear">
            <label><span class="impInfo">*</span>商品名称</label>
            <div class="kv-item-content">
                <input type="text" id="name" placeholder="商品名称" value="${album.name}"/>
            </div>
            <span class="kv-item-tip">标题字数限制在(1-30)个字符</span>
        </div>

        <#--分类不修改-->
        <div class="kv-item ue-clear">
            <label>所在分类</label>
            <div class="kv-item-content">
                <select id="categoryId">
                    <option value="${album.albumCategories[0].id}">${album.albumCategories[0].name}</option>
                    <#if albumCategories??>
                        <#list albumCategories as albumCategory>
                            <#if album.albumCategories[0].id!=albumCategory.id>
                                <option value="${albumCategory.id}">${albumCategory.name}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
            </div>
<#--            <div id="addCatagory">-->
<#--                <button onclick="addCatagory()"><img src="/img/skin_/ico01.png"></button>-->
<#--            </div>-->
<#--            <span class="kv-item-tip">没有合适的分类？尝试创建一个吧</span>-->
<#--            <img href="/img/skin_/ico01_1.png"/>-->
        </div>

        <div class="kv-item ue-clear">
            <label><span class="impInfo">*</span>商品价格</label>
            <div class="kv-item-content">
                <input type="text" id="price" placeholder="商品价格0.01(分)-9999999.99(元)" value="${album.price}"/>
            </div>
        </div>

        <div class="kv-item ue-clear">
            <label>是否上架</label>
                <#if album.open=="下架">
                                <input type="radio" name="need" value="1"/>
                            <span class="text">是</span>
                                <input type="radio" name="need" value="0" checked/>
                            <span class="text">否</span>
                <#else>
                                <input type="radio" name="need" value="1" checked/>
                    <span class="text">是</span>
                                <input type="radio" name="need" value="0"/>
                            <span class="text">否</span>
                </#if>
        </div>

        <div class="kv-item ue-clear">
            <label><span class="impInfo">*</span>商品图片</label>
            <#list pictures as picture>
                <img style="width: 100px;height: 100px" id="${picture.id}" src="${picture.path}" alt="${picture.name}"/>
                <button type="button" onclick="delPic(${picture.id})" value="删除">删除</button>
                <button type="button" onclick="setCoverPath(${picture.id})" value="设为封面">设为封面</button>
            </#list>
            <br>
            添加图片：<input id="pictures" type="file" onchange="addPic()" multiple="true">
            <#--图片回显div-->
            <div id="reShowPic">
            </div>
        </div>
        <div class="kv-item ue-clear">
            <label><span class="impInfo">*</span>视频</label>
            <#list audios as audio>
                <video id="${audio.id}" src="${audio.path}" width="30%" controls="controls">
                    <source src="${audio.path}" type="video/mp4">
                    <source src="${audio.path}" type="video/ogg">
                    <source src="${audio.path}" type="video/webm">
                    <object data="${audio.path}" width="320" height="240">
                        <embed src="${audio.path}" width="320" height="240">
                    </object>
                </video>
                <button type="button" onclick="delAudio(${audio.id})" value="删除">删除</button>
            </#list>
            <br>
            添加视频：<input id="albumaudio" type="file" onchange="uploadAudio()" multiple="true">
            <div id="reShowAudio">
            </div>
        </div>

    </div>

    <h2 class="subfild">
        <span>商品描述</span>
    </h2>

    <div class="subfild-content remarkes-info">
        <div class="kv-item ue-clear">
            <label><span class="impInfo">*</span>描述内容</label>
            <div class="kv-item-content">
                <textarea id="content" name="content" style="width:800px;height:240px;">${album.description}</textarea>
            </div>
        </div>
    </div>

    <div class="buttons">
        <input class="button" type="button" onclick="updateAlbum()" value="确认修改" id="addAlbum"/>
    </div>
</div>


