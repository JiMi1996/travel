<#include "/admin/layout/layout.ftl"><#import "/admin/layout/macro.ftl" as macro><#assign css></#assign><#assign js><script>    $(".btn-submit").click(function () {        $.ajax({            type: "POST",            url: "${ctx!}/traveller/change",            data: $(".form-edit").serialize(),            dataType: "JSON",            success: function(res){                layer.msg("添加成功", {time: 2000                }, function(){                    location.reload();                });            }        });    });</script></#assign><@layout title="旅客编辑" active="traveller"><!-- Content Header (Page header) --><section class="content-header">    <h1>        旅客编辑        <small>编辑旅客详细信息</small>    </h1>    <ol class="breadcrumb">        <li><a href="#"><i class="fa fa-cog"></i> 系统</a></li>        <li><a href="#"><i class="fa fa-list-ul"></i> 旅客管理</a></li>        <li class="active"><i class="fa fa-edit"></i> 旅客编辑</li>    </ol></section><!-- Main content --><section class="content">    <div class="row">        <div class="col-md-10">            <!-- Default box -->            <div class="box  box-primary">                <form class="form-horizontal form-edit" method="post" action="${ctx!}/traveller/change">                    <div class="box-body">                            <input type="hidden" id="id" name="id" value="${(traveller.id)!}">                        <div class="form-group">                            <label class="col-sm-2 control-label">姓名：</label>                            <div class="col-sm-10">                                    <input id="name" name="name" class="form-control" type="text" value="${(traveller.name)!}">                            </div>                        </div>                        <div class="form-group">                            <label class="col-sm-2 control-label">性别：</label>                            <div class="col-sm-10">                                <select name="sex" class="form-control">                                    <option value="女" <#if (traveller.sex)! == "女">selected="selected"</#if>>女</option>                                    <option value="男" <#if (traveller.sex)! == "男">selected="selected"</#if>>男</option>                                </select>                            </div>                        </div>                        <div class="form-group">                            <label class="col-sm-2 control-label">电话号码：</label>                            <div class="col-sm-10">                                <input id="phone" name="phone" class="form-control" value="${(traveller.phone)!}">                            </div>                        </div>                    </div>                    <div class="box-footer">                        <a type="button" class="btn btn-default btn-back" href="${ctx!}/traveller/index/1">返回</a>                        <button type="button" class="btn btn-info pull-right btn-submit">提交</button>                    </div>                </form>            </div>            <!-- /.box -->        </div>    </div></section><!-- /.content --></@layout>