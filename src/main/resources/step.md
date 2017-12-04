1. 我们在WebRoot目录下新建一个public文件夹，在里面新建一个head.jspf（jspf表示JSP片段，供其他JSP页面包含的）。
2. 设置方便书写的变量 -- ${pageContext.request.contextPath} --${shop}
3.我们先在WebRoot文件夹下新建一个temp.jsp文件作为临时开发文件，因为在这里写jsp可以直接测出来，等效果可以后，再将内容复制到aindex.jsp中的相应位置。
4.使用left fetch 表名 on 主表字段 = 从表字段   ---> 避免n+1问题 
  ```sql
   select * from category LEFT JOIN account on account_id = account.id where type = ‘手表’;
```
5. 点击菜单中的选项后，就会在右边选项卡中显示WWEB-INF/category/query.jsp页面中些的内容。
    接下来主要使完成这个query.jsp页面，即可完成右边选项卡的显示了

6. 可以在baseController中设置一个modelAttribute获取请求中的page参数和size参数        
7. 通过使用 ModelAttribute 注解来获得页面上的数据，该注解我使用在basecontroller中。
8. 分页查找出商品的步骤 ： 设置 pageMap, 存放 offset 以及 pagesize, 在查询出来数据之后，按照规定的key继续存放 total 和 rows，最后将这个Map实例返回出去 

   <!-- 根据id 删除1条或多条商品类别信息 -->
    <delete id = "deleteCategoryByIds" parameterType="int">
        delete from category where id in (
        <if test = "list != null">
            <foreach  item = "id" collection="#{list}" separator = ",">id</foreach>
        </if>
        )
    </delete>
    
    这样子不小心就可以把数据表中所有的数据都删除掉了