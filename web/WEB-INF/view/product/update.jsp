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
<form title="添加商品" id = "ff" method = "post"  enctype = "multipart/form-data">
    <div>
        <label>商品名称：</label><input type="text" name="name">
    </div>
    <div>
        <label>商品价格：</label><input type="text" name = "price">
    </div>
    <div>
        <label>图片上传：</label><input type="file" name="image">
    </div>
    <div>
        <label>所属类别：</label>
        <select name="category.id" id="cc"></select>
    </div>
    <div>
        <label>加入推荐：</label>
        <input type="radio" name="commend" value="true" />推荐
        <input type="radio" name="commend" checked="checked" value="false" />不推荐
    </div>
    <div>
        <label>是否有效：</label>
        <input type="radio" name="open" value="true" />上架
        <input type="radio" name="open" checked="checked" value="false" />下架
    </div>
    <div>
        <label>简单描述：</label>
        <textarea name="remark" cols="40" rows="4"></textarea>
    </div>
    <div>
        <label>详细描述：</label>
        <textarea name="xremark" cols="40" rows="8"></textarea>
    </div>
    <div>
        <a id="submit" href="#" class="easyui-linkbutton">添加</a>
        <a id="reset" href="#" class="easyui-linkbutton">重置</a>
    </div>
    <input type="hidden" name="id">
</form>

<script>

    $(function () {
        var dg = "#product-dg";

        // 对商品类的下拉列表框进行远程架子啊
        $("#cc").combobox({
            // 将请求发送给categoryAction中的query方法处理，这里需要将处理好的数据返回到这边来显示了，所以后台需要将数据dabaochengjson格式发过来
            url: 'category-get-all',
            valueField: 'id',
            textField: 'type',    // 我们下来列表中显示的是商品的类别名
            panelHeight: 'auto',  // 自适应高度
            panelWidth:120,//下拉列表是两个组件组成的
            width:120, //要同时设置两个宽度才行
            editable:false, //下拉框不允许编辑

            required: true,
            missingMessage: '请选择商品所属类别'
        });  // combobox 继承 combo 继承 validatebox， 所以可以直接再这里设置验证

        // 完成数据的回显，更新时，用户可定先选择了要更新的那一行，首先我们得拿到那一行

        var rows = $(dg).datagrid("getSelections");
        console.log(rows);
        var rowObj  = {
            id:rows[0].id,
            name:rows[0].name,
            price:rows[0].price,
            remark:rows[0].remark,
            xremark:rows[0].xremark,
            commend:rows[0].commend,
            open:rows[0].open,
            'category.id':rows[0].category==null ? null : rows[0].category.id, //EasyUI不支持account.id这种点操作，所以要加个引号
        }

        console.log(rowObj);

        // 将拿到的那一行对应的数据字段加载到表单里没实现回显
        $("#ff").form('load',rowObj);

        // 回显完了数据后，设置一下验证功能
        $("input[name=name]").validatebox({
            required: true,
            missingMessage: '请输入类别名称'
        });
        $("input[name=price]").numberbox({
            required:true,
            missingMessage:'请输入商品价格',
            min:0,
            precision:2, //保留两位小数
            prefix:'$'
        });
        $("input[name='image']").validatebox({
            required:true,
            missingMessage:'请上传商品图片',
            //设置自定义方法
            validType:"format['gif,jpg,jpeg,png']"//中括号里面是参数
        });
        $("textarea[name=remark]").validatebox({
            required:true,
            missingMessage:'请输入商品的简单描述'
        });
        $("textarea[name=xremark]").validatebox({
            required:true,
            missingMessage:'请输入商品的详细描述'
        });

        // 窗体弹出默认是禁用验证
        $("#ff").form("disableValidation");
        // 注册button的事件
        $("#submit").click(function () {
            // 开启验证
            $("#ff").form("enableValidation");
            $("#ff").form("submit", {
                url: 'product-update',   // 提交时将请求传给productController的update方法执行
                success: function () {
                    // 如果过成功了，关闭当前窗口，并刷新页面
                    parent.$("#win").window("close");
                    $(dg).datagrid("reload");
                }
            })
        });

        // 注册button重置时间
        $("#reset").click(function () {
            $("#ff").form("disableValidation");  // 重置不需要表单验证
            $("#ff").form("reset");
        });
    });

</script>