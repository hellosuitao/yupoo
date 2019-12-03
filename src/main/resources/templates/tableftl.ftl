<table border="1px solid black" cellspacing="5px" cellpadding="3px"
       class="table table-striped table-bordered table-hover">
    <tr>
        <th>
            <input type="checkbox" id="selectall" onclick="selectAll(this)" value=""/>&nbsp;&nbsp;全选
        </th>
        <th>ID</th>
        <th>商品名</th>
        <th>商品价格</th>
        <th>商品分类</th>
        <th>商品描述</th>
        <th>点赞/评论</th>
        <th>上架时间</th>
        <th>上架状态</th>
        <th>操作</th>
    </tr>

    <#if album??>
        <tr>
            <td>
                <input type="checkbox" name="checkid" id="" value="${album.id}"/>
            </td>
            <td>${album.id}</td>
            <td>${album.name}</td>
            <td>${album.price}</td>
            <td>${album.albumCategories[0].name}</td>
            <td>${album.description}</td>
            <td><span id="looknum0">${likes.upnum}</span>/<span id="upnum0">${likes.looknum}</span></td>
            <td>${album.createDate}</td>
            <td id="${album.id}">${album.open}</td>
            <td>
                <button type="button" class="btn-default" onclick="editAlbum(${album.id})" value="${album.id}">编辑
                </button>
                <button type="button" class="btn-danger" value="${album.id}" onclick="btnDelete(${album.id})">删除
                </button>
                <button type="button" data-toggle="modal" data-target="#myModal">修改点赞数</button>


                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">添加分类</h4>
                            </div>
                            <form id="picStatu">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input type="hidden" value="${album.id}" name="pid" >
                                        <label for="edit_customerName" class="col-sm-2 control-label">点赞数</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="categoryName" placeholder="点赞数"
                                                   name="upnum"/>
                                        </div>
                                        <label for="edit_customerName" class="col-sm-2 control-label">浏览数</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="categoryName" placeholder="浏览数"
                                                   name="looknum"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" onclick="updatePicStatus()" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>
                </div>


            </td>
        </tr>
    <#else>
        <#list albumPage.content as album>
            <tr>
                <td>
                    <input type="checkbox" name="checkid" id="" value="${album.id}"/>
                </td>
                <td>${album.id}</td>
                <td>${album.name}</td>
                <td>${album.price}</td>
                <td>${album.albumCategories[0].name}</td>
                <td>${album.description}</td>
                <td><span id="looknum${album_index}">${likes[album_index].upnum}</span>/<span id="upnum${album_index}">${likes[album_index].looknum}</span></td>
                <td>${album.createDate}</td>
                <td id="${album.id}">${album.open}</td>
                <td>
                    <button type="button" class="btn-default" onclick="editAlbum(${album.id})" value="${album.id}">编辑
                    </button>
                    <button type="button" class="btn-danger" value="${album.id}" onclick="btnDelete(${album.id})">删除
                    </button>
                    <button type="button" data-toggle="modal" data-target="#myModal${album_index}">修改点赞数</button>


                    <div class="modal fade" id="myModal${album_index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                                aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">修改</h4>
                                </div>
                                <form id="picStatu${album_index}">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <input type="hidden" value="${album.id}" name="pid" >
                                            <label for="edit_customerName" class="col-sm-2 control-label">点赞数</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="categoryName" placeholder="点赞数" value="${likes[album_index].upnum}"
                                                       name="upnum"/>
                                            </div>
                                            <label for="edit_customerName" class="col-sm-2 control-label">浏览数</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="categoryName" placeholder="浏览数" value="${likes[album_index].looknum}"
                                                       name="looknum"/>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" onclick="updatePicStatus(${album_index})" class="btn btn-primary">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </#list>
    </#if>
</table>
<#--分页-->
<#--如果第一页且不到5页-->
<#if (currentPage<=2)&&(totalPages<=5)>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <#list 1..totalPages as i>
                <li><a onclick="findByPage(${i});">${i}</a></li>
            </#list>
        </ul>
    </nav>
<#--第一页且超过5页-->
<#elseif (currentPage<=2)&&(totalPages>5)>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li><a onclick="findByPage(1);">1</a>
            </li>
            <li><a onclick="findByPage(2);">2</a>
            </li>
            <li><a onclick="findByPage(3);">3</a>
            </li>
            <li><a onclick="findByPage(4);">4</a>
            </li>
            <li><a onclick="findByPage(5);">5</a>
            </li>
            <li><a onclick="findByPage(${currentPage+1});"
                   aria-label="Next">下一页</a></li>
            <li><a onclick="findByPage(${totalPages});"
                   aria-label="Next">尾页</a>
            </li>
        </ul>
    </nav>
<#--最后一页不到5页-->
<#elseif (currentPage>=totalPages-2)&&(totalPages<=5)>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <#list 1..totalPages as i>
                <li><a onclick="findByPage(i);">${i}</a></li>
            </#list>
        </ul>
    </nav>
<#--最后一页超过5页-->
<#elseif (currentPage>=totalPages-2)&&(totalPages>5)>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a onclick="findByPage(1);" aria-label="Previous">
                    首页
                </a>
            </li>
            <li>
                <a onclick="findByPage(${currentPage-1})" ; aria-label="Previous">
                    上一页
                </a>
            </li>
            <li><a onclick="findByPage(${currentPage-4});">${currentPage-4}</a>
            </li>
            <li><a onclick="findByPage(${currentPage-3});">${currentPage-3}</a>
            </li>
            <li><a onclick="findByPage(${currentPage-2});">${currentPage-2}</a>
            </li>
            <li><a onclick="findByPage(${currentPage-1});">${currentPage-1}</a>
            </li>
            <li><a onclick="findByPage(${currentPage});">${currentPage}</a>
            </li>
        </ul>
    </nav>
<#--超过5页-->
<#else>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a onclick="findByPage(1);" aria-label="Previous">
                    首页
                </a>
            </li>
            <li>
                <a onclick="findByPage(${currentPage-1});" aria-label="Previous">
                    上一页
                </a>
            </li>
            <li><a onclick="findByPage(${currentPage-2});">${currentPage-2}</a>
            </li>
            <li><a onclick="findByPage(${currentPage-1});">${currentPage-1}</a>
            </li>
            <li><a onclick="findByPage(${currentPage});">${currentPage}</a></li>
            <li><a onclick="findByPage(${currentPage+1});">${currentPage+1}</a>
            </li>
            <li><a onclick="findByPage(${currentPage+2});">${currentPage+2}</a>
            </li>
            <li>
                <a onclick="findByPage(${currentPage+1});" aria-label="Next">
                    下一页
                </a>
            </li>
            <li>
                <a onclick="findByPage(${totalPages});" aria-label="Next">
                    尾页
                </a>
            </li>
        </ul>
    </nav>
</#if>
