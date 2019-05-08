<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
    <title>Title</title>
    <script>
        $(function () {
            $('#upload_btn').upload({
               action:'abc.action',
               name:'excelname'
            });
        });

    </script>
</head>

<body class="easyui-layout">
普通上传<br>
<form action="abc.action" method="post" enctype="multipart/form-data" target="myframe">
    <input type="file" name="excelFile">
    <input type="button" value="上传">
</form>

<iframe name="myframe" width="0px" height="0px"></iframe><br>
一见上传<br>

<input type="button" id="excel_upload_btn" value="上传文件"/>
<script type="text/javascript">
    $(function () {
        $("#excel_upload_btn").upload({
            action:"abc",
            name:"excelFile"
        });
    });
</script>

</body>
</html>
