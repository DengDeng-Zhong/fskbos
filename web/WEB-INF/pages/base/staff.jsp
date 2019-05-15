<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        function doAdd() {
            //alert("增加...");
            $('#addStaffWindow').window("open");
        }

        function doView() {
            alert("查看...");
        }

        function doDelete() {

            var rows = $("#grid").datagrid("getSelections");
            if (rows.length != 0) {
                var arr = new Array();
                for (var i = 0; i < rows.length; i++) {
                    //alert(JSON.stringify(rows[i]));//每一行的数据的JSON对象
                    arr.push(rows[i].id);
                }

                //将id通过,拼接
                var ids = arr.join(',');
                // alert(ids);//后面执行POST请求
            } else {
                $.messager.alert('提示', '未勾选任何选项');
            }

            //访问staffAction_delete.action
            $.post(
                '${pageContext.request.contextPath }/staffAction_delete.action',
                {ids: ids},
                function (data) {
                    if (data == 'success') {
                        $.messager.alert('提示', '作废成功', 'info');
                        $('#grid').datagrid('reload');
                    } else {
                        $.messager.alert('提示', '作废失败', 'info');
                    }
                }
            );
        }

        function doRestore() {
            alert("将取派员还原...");
        }

        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'name',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'telephone',
            title: '手机号',
            width: 120,
            align: 'center'
        }, {
            field: 'haspda',
            title: '是否有PDA',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "1") {
                    return "有";
                } else {
                    return "无";
                }
            }
        }, {
            field: 'deltag',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "0") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'standard',
            title: '取派标准',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '所谓单位',
            width: 200,
            align: 'center'
        }]];

        $(function () {
            //添加一个表单的手机校验规则
            $.extend($.fn.validatebox.defaults.rules, {
                phoneNumber: {
                    validator: function (value, param) {
                        return /^1[3|5|7|8|9][0-9]{9}$/.test(value);
                    },
                    message: '请输入正确的手机号码!'
                }
            });


            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                pageList: [5, 10, 15],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath }/staffAction_pageQuery.action",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            // 添加取派员窗口
            $('#editStaffWindow').window({
                title: '修改取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            //监听保存按钮
            $('#save').click(function () {
                //表单校验
                var v = $("#addStaffForm").form('validate');
                if (v) {


                    //提交表单
                    $("#addStaffForm").submit();
                    $('#grid').datagrid('reload');
                } else {
                    $.messager.alert('提示', '表单数据格式不正确', 'error');
                }


            });

            //监听更新按钮
            $('#update').click(function () {
                //表单校验
                var v = $("#editStaffForm").form('validate');
                if (v) {
                    //提交表单
                    $("#editStaffForm").submit();
                    $('#grid').datagrid('reload');
                } else {
                    $.messager.alert('提示', '表单数据格式不正确', 'error');
                }


            });
        });

        function doDblClickRow(rowIndex, rowData) {
            //alert("双击表格数据..."+rowData.name+";"+rowIndex);
            //显示一个修改的窗口
            $("#editStaffWindow").window("open");

            //显示数据
            $("#editStaffWindow").form("load",rowData);

        }
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<!-- 添加一个取派员 -->
<div class="easyui-window" title="对收派员进行添加" id="addStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <shiro:hasPermission name="staff">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
            </shiro:hasPermission>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="addStaffForm" action="${pageContext.request.contextPath }/staffAction_save.action"
            method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <!-- TODO 这里完善收派员添加 table -->
                <tr>
                    <td>取派员编号</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true" /></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               data-options="validType:'phoneNumber'"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 修改一个取派员 -->
<div class="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="update" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">更新</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="editStaffForm" action="${pageContext.request.contextPath }/staffAction_update.action"
        method="post">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <!-- TODO 这里完善收派员添加 table -->
                <tr>
                    <td>取派员编号</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true" readonly="true"/></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"
                               data-options="validType:'phoneNumber'"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>	