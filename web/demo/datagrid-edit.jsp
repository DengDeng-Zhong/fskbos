<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <title>Title</title>
</head>

<body >
<%-- datagrid的使用:js来实现排版--%>
<script>
    $(function () {

        $("#grid").datagrid({
            url:'${pageContext.request.contextPath}/json/staff.json',
            columns:[[
                {field:'id',title:'编号',width:100,checkbox:true},
                {field:'name',title:'姓名',width:100},
                {field:'telephone',title:'电话',width:100}
            ]],
            toolbar:[
                {
                    iconCls: 'icon-edit',
                    text:'Edit',
                    handler: function(){
                        alert('编辑按钮')}
                },
                {
                    text:'删除',
                    handler: function(){
                        alert('编辑按钮')}
                }
            ]
        });

    });
</script>
<table id="grid"  class="easyui-datagrid"></table>
</table>


</body>
</html>
