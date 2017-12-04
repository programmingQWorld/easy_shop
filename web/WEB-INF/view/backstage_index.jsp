<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/2
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../public/header.jspf" %>
    <style type="text/css">
        #menu {
            width:200px;
            /*border:1px solid red;*/
        }
        #menu ul {
            list-style: none;
            padding: 0px;
            margin: 0px;
        }
        #menu ul li {
            border-bottom: 1px solid #fff;

        }
        #menu ul li a {
            /*先将a标签转换为块级元素，才能设置宽和内间距*/
            display: block;
            background-color: #00a6ac;
            color: #fff;
            padding: 5px;
            text-decoration: none;
        }
        #menu ul li a:hover {
            background-color: #008792;
        }


    </style>
</head>

<body class="easyui-layout">
<div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
<div data-options="region:'west',title:'West',split:true" style="width:200px;">
    <!-- 此处显示的是系统菜单 -->
    <div id="menu" class="easyui-accordion" style="width:300px;height:200px;">
        <div title="基本管理" data-options="iconCls:'icon-save'" style="overflow:auto;">
            <ul>
                <li><a href="#" title = "category-query">类别管理</a>
                <li><a href="#" title = "product-query" >商品管理</a>
            </ul>
        </div>
        <div title="其他管理" data-options="iconCls:'icon-reload',selected:true" style="">
            <ul>
                <li><a href="#">类别管理</a>
                <li><a href="#">商品管理</a>
            </ul>
        </div>
    </div>
</div>
<div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;" id = "content">
    <div id="tt" class = "easyui-tabs" data-options="fit:true">
        <div title="系统缺省页面" style = "padding: 10px">
            此处以后显示相应的系统信息(当前操作系统的类型，当前项目的域名，硬件的相关配置或者显示报表)
        </div>
    </div>
</div>

<%--  写一个盒子，基于这个盒子创建窗口 --%>
<div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>

</body>

<script type = "text/javascript">
    $(function (){
        /*
        * $("a[title]") --- 获取带有title属性的a标签
        * 为获取到的a标签绑定点击事件
         * */
        $(function(){
            $("a[title]").click(function(){
                var text = $(this).text();
                var href = $(this).attr("title");
                //判断当前右边是否已有相应的tab
                if($("#tt").tabs("exists", text)) {
                    $("#tt").tabs("select", text);
                } else {
                    //如果没有则创建一个新的tab，否则切换到当前tag
                    result =$.ajax({url:href,async:false});
                    $("#tt").tabs("add",{
                        title:text,
                        closable:true,
                        //content:'<iframe title= "' + text + '" src= "' + href + '" frameborder="0" width="100%" height="100%" />'
                        content: result.responseText

                        // ajax 请求到的 responseText 赋值给 content
                        // 而responseText的来源就是 springmvc 的controller
                        // href:默认通过url地址加载远程的页面，但是仅仅是body部分
                    });
                }
            });
        });
    });

</script>



</html>
<%--<body>
&lt;%&ndash;<frameset border="5" rows="150, *">
    <frame src = "top.jsp">     超级麻烦，而且企业中也不会用frameset了。使用easyui吧
    <frameset border = "5" cols="150,  *">
        <frame src = "left.jsp"/>
        <frame src = "right.jsp"/>
    </frameset>
</frameset>&ndash;%&gt;
</body>--%>