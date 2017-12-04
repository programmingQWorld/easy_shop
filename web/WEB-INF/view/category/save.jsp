<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/10
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css">
    form div {
        margin:5px;
    }
</style>

<form id = "ff" method = "post">
    <div>
        <%--@declare id="name"--%><label for="name">商品类别名称：</label><input type="text" name="type">
    </div>
    <div>
        <lable>所属管理员：</lable>&nbsp;&nbsp;
        <select id = "cc" name = "account.id">
        </select>
    </div>
    <div>
        <label>热点:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" value="1" name="hot"  />是
        <input type="radio" value="0" name="hot" checked />否
    </div>
    <div>
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
    </div>
</form>
<script type="text/javascript">
    $(function () {
        // 窗体弹出默认是禁用验证，因为刚弹出的窗口，用户还么天就显示的话，太丑
        $("#ff").form("disableValidation");  // str 是可选的单词口令

        $("input[name=type]").validatebox({      // 这里是"类别名称"的验证功能，如果用户没填好就提交的话，就会有提示
            required: true,
            missingMessage: '请输入类别名称'  // 提示的内容
        });
        // 对管理员的下来列表框进行远程加载
        $("#cc").combobox({
            // 将请求发送给accountAction中的query方法处理，这里需要将处理好的数据返回到这边来显示了，所以后台需要将数据打包成json格式发过来
            url: 'account-get-all',     // 请求所有管理员的信息
            valueField: 'id',    // 下来列表中显示的是管理员的id
            textField: 'login',  //下拉列表中显示的是管理员的登录名
            panelHeight: 'auto',  // 自适应高度
            panelWidth: 120,  // 下拉列表是两个组件组成的  【 我估计 是 外框和 内框 】
            width: 120,         // 要同时设置两个宽度才行
            editable: false  // 下拉框不允许编辑
        });

        // 注册button的时间。即当用户点击“添加”的时候做的事
        $("#btn").click(function () {
            // 开启验证
            $("#ff").form("enableValidation");
            // 如果验证成功，则提交数据
            if($("#ff").form("validate")) {
                // 调用submit方法提交数据
                $("#ff").form('submit', {
                    url: 'category-save',
                    success: function () { // 成功后
                        parent.$("#win").window("close");                   //  如果成功了，关闭当前窗口
                        parent.$("#category-dg").datagrid("reload");    // 刷新页面，刚刚添加的就显示出来了。
                    }
                });
            }
        });
    });
</script>