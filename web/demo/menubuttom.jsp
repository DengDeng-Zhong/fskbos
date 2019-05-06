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
<a href="#" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-edit'">Edit</a>

<div id="mm" style="width:150px;">
    <div>Cut</div>
    <div>Copy</div>
    <div class="menu-sep"></div>
    <div>Paste</div>
</div>

</body>
</html>
