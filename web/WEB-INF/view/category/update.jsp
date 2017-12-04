<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/10
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    form div {
        margin:5px;
    }
</style>
<form id="ff" method="post">
    <div>
        <label>类别名称:</label> <input type="text" name="type" />
    </div>
    <div>
        <label>热点:</label>
        是<input type="radio" name="hot" value="1" />
        否<input type="radio" name="hot" value="0" />
    </div>
    <div>
        <label>所属管理员:</label>
        <!-- 下拉列表我们采用远程加载的方法加载管理员数据 -->
        <select name="account.id" id="cc"></select>
    </div>
    <div>
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新</a>
        <input type="hidden" name="id" />
    </div>
</form>


<script>
    $(function () {
        var dg = $("#category-dg");
        $("#cc").combobox({
            // 将请求发送给accountController 中的query方法处理，这里需要将处理好的数据返回到这边来显示了，所以后台需要将数据打包枨json格式发过来
            url: 'account-get-all',
            valueField: 'id',       // 与json绑定的key
            textField: 'login',  // 显示的是管理缘分的登录名
            panelHeight:'auto', //自适应高度
            panelWidth:150,//下拉列表是两个组件组成的
            width:150, //要同时设置两个宽度才行
            editable:false //下拉框不允许编辑
        });


        // 完成数据的回显，更新时，用户肯定先选择了要更新的哪一行，首先我们拿到那一行
        var rows = dg.datagrid("getSelections");
        // 将拿到的哪一行对应的数据字段加载到表单里，实现回显
        console.log(rows);
        $("#ff").form('load', {
            id: rows[0].id,
            type: rows[0].type,
            hot: rows[0].hot,
            'account.id': rows[0].account.id   //Easy_ui不支持account.id这种操作，所以要加个引号
        });

        // 会先玩了数据后，设置一下验证功能呢
        $("input[name = type]").validatebox({
            required: true,
            missingMessage: '请输入类别名称',
        });
        // 窗体弹出默认时禁用验证
        $("#ff").form("disableValidation");
        // 注册button的时间
        $("#btn").click(function () {
            // 开启验证
            $("#ff").form("enableValidation");
            if ($("#ff").form("validate")) {
                // 调用submit方法提交数据
                $("#ff").form("submit", {
                    url: 'category-update',
                    success: function (){
                        // 如果过成功了，关闭当前窗口，并刷新页面
                        parent.$("#win").window("close");
                        dg.datagrid("reload");
                    }
                })
            }
        });

    });
</script>