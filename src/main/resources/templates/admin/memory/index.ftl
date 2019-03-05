<#include "/admin/layout/layout.ftl"><#import "/admin/layout/macro.ftl" as macro><#assign css><style></style></#assign><#assign js><script>    function del(id) {        layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {            $.ajax({                type: "GET",                dataType: "text",                url: "${ctx!}/memory/delete/" + id,                success: function (res) {                    layer.msg("删除成功", {time: 2000}, function () {                        location.reload();                    });                },                error:function () {                    layer.msg("删除成功", {time: 2000}, function () {                        location.reload();                    });                }            });        }        );    }</script></#assign><@layout title="购物车管理" active="memory"><!-- Content Header (Page header) --><section class="content-header">    <h1>        购物车列表        <small>一切从这里开始</small>    </h1>    <ol class="breadcrumb">        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>        <li><a href="#"><i class="fa fa-list-ul"></i> 用户管理</a></li>        <li class="active"><i class="fa fa-table"></i> 用户列表</li>    </ol></section><!-- Main content --><section class="content">    <!-- Default box -->    <div class="box box-primary">        <div class="box-header">        </div>        <div class="box-body">            <table class="table table-striped">                <tr>                    <th>ID</th>                    <th>用户</th>                    <th>景点</th>                    <th>票价</th>                    <th>购买日期</th>                    <th>出发日期</th>                    <th>操作</th>                </tr>                <#list PageInfo.data as memoryData>                <tr>                    <td>${(memoryData.memory.id)!}</td>                    <td>${(memoryData.userName)!}</td>                    <td>${(memoryData.ticketName)!}</td>                    <td>${(memoryData.ticketPrice)!}</td>                    <td>${((memoryData.memory.buydate)?string("yyyy-MM-dd"))!}</td>                    <td>${((memoryData.memory.godate)?string("yyyy-MM-dd"))!}</td>                    <td>                    <#--<@shiro.hasPermission name="system:user:edit">-->                        <a class="btn btn-sm btn-primary" href="${ctx!}/memory/edit/${memoryData.memory.id}">编辑</a>                    <#--</@shiro.hasPermission>-->                    <#--<@shiro.hasPermission name="system:user:deleteBatch">-->                        <button class="btn btn-sm btn-danger" onclick="del(${memoryData.memory.id})">删除</button>                    <#--</@shiro.hasPermission>-->                    </td>                </tr>                </#list>            </table>        </div>        <!-- /.box-body -->        <div class="box-footer clearfix">            <a class="btn btn-sm btn-primary" href="${ctx!}/memory/index/1">首页</a>        <#if PageInfo.currentPage == 1>            <a class="btn btn-sm btn-primary" href="${ctx!}/memory/index/1">上一页</a>        <#else>            <a class="btn btn-sm btn-primary" href="${ctx!}/memory/index/${PageInfo.currentPage-1}">上一页</a>        </#if>        <#if PageInfo.currentPage < PageInfo.count/10>            <a class="btn btn-sm btn-primary" href="${ctx!}/memory/index/${PageInfo.currentPage+1}">下一页</a>        <#else>            <a class="btn btn-sm btn-primary" href="${ctx!}/memory/index/${PageInfo.currentPage}">下一页</a>        </#if>            <a class="btn btn-sm btn-primary" href="${ctx!}/memory/index/${(PageInfo.count/10)?ceiling}">尾页</a>        <#--<@macro.list userList=userList url="${ctx!}/admin/user/index?" />-->        </div>    </div>    <!-- /.box --></section><!-- /.content --></@layout>