<%--
  Created by IntelliJ IDEA.
  User: LCQ
  Date: 2017/11/7
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function(){
        var productGridSelector = '#product-dg';
        $(productGridSelector).datagrid({
            //请求数据的url地址，后面会改成请求我们自己的url
            url: 'productsjoincategory',
            // ------ 下面是本地测试数据
            //url:'/ssm/jquery-easyui-1.3.5/demo/datagrid/products.json',                       //url:'/ssm/jquery-easyui-1.3.5/shop.json',
            loadMsg:'Loading......',
            queryParams: {
                name:''
            },                                                                                                                   //参数      // 设置请求的参数
            //width:300,
            fitColumns:true,                                                                                            //水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
            striped:true,                                                                                                  //显示斑马线

            //当数据多的时候不换行
            nowrap:true,
            singleSelect:false, //如果为真，只允许单行显示，全显功能失效
            //设置分页
            pagination:true,

            rowStyler: function(index,row){
                console.info("index" + index + "," + row)
                if(index % 2 == 0) {
                    return 'background-color:#fff;';
                } else {
                    return 'background-color:#e0edf1;';
                }

            },
            //同列属性，但是这些列将会冻结在左侧,大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
            frozenColumns:[[
                {field:'checkbox',checkbox:true},
                {field:'id',title:'编号',width:200}
            ]],
            //配置datagrid的列字段
            //field：列字段的名称，与json的key捆绑
            //title：列标题，是显示给人看的
            //  singleSelect: true,                                                                                 true: 单选 false 复选
            columns:[[
                {field:'name',title:'商品名称',width:100,
                    //用来格式化当前列的值，返回的是最终的数据
                    formatter: function(value,row,index){
                        return "<span title=" +　value + ">" + value + "</span>";
                    }
                },

                {field: 'pic', title: '图片信息', width: 200,
                    formatter: function (value, row, index) {

                        if (value != '' && value != 'WEB-INF/upload') {
                            return '<img width="100" height="100" src = "upload/' + value + '">';  // 编写一个请求--> 使其支持 webinf文件夹
                        }
                    }
                },

                {field:'price',title:'商品价格',width:100},
                {field:'remark',title:'简单描述',width:100},
                {field:'xremark',title:'详细描述',width:100},
                {field:'date',title:'上架时间',width:100},
                {field:'commend',title:'推荐商品',width:100,
                    formatter: function (value, row, index) {
                        if (value) {
                            return "<input type = 'checkbox' checked = 'checked' disabled = 'true'>";
                        } else {
                            return "<input type = 'checkbox' disabled = 'true'>";
                        }
                    }
                },
                {field: 'category.type', title: '商品所属类别', width: 200,  // category.type 是商品类别
                    formatter: function (value, row, index) {
                        console.log(row);
                        if (row.category != null && row.category.type != null) {
                            return row.category.type;  // 如果商品类别不为空，返回商品类别
                        } else {
                            return "此商品暂时未分类";
                        }
                    }
                }
            ]],
            /* ----------------------------定义工具栏：add,update,delete,select */
            toolbar: [{
                iconCls: 'icon-add',
                text:'添加商品',
                handler: function(){
                    var xhr =$.ajax({url:'product-pre-save',async:false});     // 加载save页面的内容，
                    parent.$("#win").window({  // 因为<div>放在了aindex.jsp中，所以这里创建窗口要先调用parent
                        title: "添加商品",
                        width: 350,
                        height:500,
                        content: xhr.responseText
                    });
                }
            },'-',{
                iconCls: 'icon-edit',
                text:'更新商品',
                handler: function(){
                    var rows = $("#product-dg").datagrid("getSelections");
                    if (rows.length == 0)  {
                        $.messager.show({
                            title: '错误提示',
                            msg: '至少要选择一条记录',
                            timeout: 2000,
                            showType: 'slide',
                        });
                    } else if (rows.length != 1) {
                        $.messager.show({
                            title: '错误提示',
                            msg: '每次只能更新一条记录',
                            timeout: 2000,
                            showType: 'slide',
                        });
                    } else {

                        var xhr =$.ajax({url:'product-pre-update',async:false});     // 加载save页面的内容，

                        parent.$("#win").window({
                            title: '更改类别',
                            width: 350,
                            height:520,
                            content: xhr.responseText,
                        });
                    }
                }
            },'-',{
                iconCls: 'icon-remove',
                text:'删除商品',
                handler: function(row){
                    //判断是否有选中行记录，使用getSelections获取选中的所有行
                    var rows = $(productGridSelector).datagrid("getSelections");
                    //返回被选中的行，如果没有任何行被选中，则返回空数组
                    if(rows.length == 0) {
                        //弹出提示信息
                        $.messager.show({ //语法类似于java中的静态方法，直接对象调用
                            title:'错误提示',
                            msg:'至少要选择一条记录',
                            timeout:2000,
                            showType:'slide',
                        });
                    } else {
                        //提示是否确认删除，如果确认则执行删除的逻辑
                        $.messager.confirm('删除的确认对话框', '您确定要删除此项吗？', function(r){
                            if (r){ // true
                                // 1. 从获取的记录中获取相应的id,拼接id的值，然后发送后台： 如： 1, 2, 3, 4
                               var ids= "";
                               for ( var i=0; i<rows.length; i++ ) {
                                   ids += rows[i].id + ",";
                               }
                               ids = ids.substr(0, ids.lastIndexOf(","));  // 去掉最后一个逗号
                                // 2. 发送ajax请求
                                var url = 'deleteProducts';
                                $.post(url, {ids: ids}, function (result){
                                    if (result == "true") {
                                        $(productGridSelector).datagrid("uncheckAll");        // 将刚刚选中的记录删除，要不然会影响后面更新的操作
                                        $(productGridSelector).datagrid("reload");  // 不带参数默认为上面的queryParams 即： page rows type
                                    } else {
                                        $.messager.show({
                                            title: '删除异常',
                                            msg: '删除失败，请检查操作',
                                            timeout: 2000,
                                            showType: 'slide',
                                        }, "text");
                                    }
                                } );
                            }
                        });
                    }
                }
            },'-',{ //查询按钮不是LinkButton，它有语法，但是也支持解析HTML标签
                text:"<input id='ss' name='serach' />"
            }]
        });

        //把普通的文本框转化为查询搜索文本框
        $('#ss').searchbox({
            //触发查询事件
            searcher:function(value,name){ //value表示输入的值
                //查询操作
                $(productGridSelector).datagrid('load',{          // load 后面还带了参数 --> 一个js对象，该对象的存在会覆盖先前的queryParams
                    name: value,        // name属性是db中表格的字段
                });
            },
            prompt:'请输入搜索关键字' //默认的显示
        });
    });

</script>
<table id="product-dg"></table>


