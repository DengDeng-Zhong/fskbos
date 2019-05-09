<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <title>Title</title>
</head>

<body class="easyui-layout">
<div style="margin-bottom:20px">
    <input id="language" class="easyui-combobox" name="language" style="width:100%;" data-options="
					valueField: 'id',
					textField: 'text',
					label: 'Language:',
					labelPosition: 'top',
					url:'${pageContext.request.contextPath}/json/combobox.json'">
</div>

</body>
</html>
