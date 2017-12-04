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
</form>


<script type="text/javascript">
    /*  应以对上传图片的验证*/
    $.extend($.fn.validatebox.defaults.rules, {
        // 函数的名称：{ 函数的实现体（又是一个json对象，里面包括函数的实现，和错误消息的设置） }
        format: {  // format是自定义方法名
            // 函数实现： 如果返回false，即验证失败
            validator:function (value, param) {  // value是用户输入的值，param是我们自己设置的参数，当匹配成功蔡返回真
                // 获取当前文件的后缀名
                var ext = value.substring(value.lastIndexOf('.') + 1);
                // 获取当前文件的后缀名，然后比较即可
                var arr=param[0].split(",");
                for (var i=0; i<arr.length; i++) {
                    if(ext == arr[i]) {
                        return true;
                    }
                }
                return false;
            },
            // 错误消息
            messasge: '文件后缀必须为：{0}'  // 这个验证主要是针对上传的图片，要求后缀名是.jpg， .gif， .png等后缀名才行，这些后缀名我们会放到param中，到时候用户输入的跟param比较即可
        }
    });

    $(function () {

        $("input[name=type]").validatebox({      // 这里是"类别名称"的验证功能，如果用户没填好就提交的话，就会有提示
            required: true,
            missingMessage: '请输入类别名称'  // 提示的内容
        });
        // 对管理员的下来列表框进行远程加载
        $("#cc").combobox({
            // 将请求发送给accountController中的query方法处理，这里需要将处理好的数据返回到这边来显示了，所以后台需要将数据打包成json格式发过来
            url: 'category-get-all',     // 请求所有商品类别的信息
            valueField: 'id',    // 下来列表中显示的是商品类别的id
            textField: 'type',  //下拉列表中显示的是商品类别
            panelHeight: 'auto',  // 自适应高度
            panelWidth: 120,  // 下拉列表是两个组件组成的  【 我估计 是 外框和 内框 】
            width: 120,         // 要同时设置两个宽度才行
            editable: false  // 下拉框不允许编辑
        });

        $("input[name=name]").validatebox({
            required:true,
            missingMessage:'请输入商品名称'
        });
        $("input[name=price]").validatebox({
            required: true,
            missingMessage: '请输入商品价格',
            min:0,
            precision: 2, // 保留两位小数
            prefix: '$',
        });

        $("input[name='fileImage.upload']").validatebox({
            required: true,
            missingMessage: '请上传商品图片',
            // 设置自定义方法
            validType: "format['gif,jpg,jpeg,png']"
        });

        $("textarea[name=remark]").validatebox({
            required: true,
            missingMessage: '请输入商品的简单描述',
        });
        $("textarea[name=xremark]").validatebox({
            required: true,
            missingMessage: '请输入商品的详细描述'
        });

        // 窗体弹出默认是禁用验证
        $("#ff").form("disableValidation");

        // 提交按钮的事件
        $("#submit").click(function () {
           // 开启验证
           $("#ff").form("enableValidation");
           if ($("#ff").form("validate")) {
               $("#ff").form("submit", {
                   url: 'product-save',  // 保存商品的url
                   contentType: 'text/json',
                   success: function () {   // post请求发送成功之后就可以执行success回调函数了
                       parent.$("#win").window("close");
                       parent.$("#product-dg").datagrid("reload");  // 刷新商品信息
                   }
               });
           }
        });
        // 重置button的事件
        $("#reset").click(function () {
            $("#ff").form("disableValidation");  // 重置不需要表单验证
            $("#ff").form("reset");
        });

    });
</script>
