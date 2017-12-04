package com.lcq.dao;

import com.lcq.domain.Category;

import java.util.List;
import java.util.Map;

/*
* 1. 其实这是一个mapper，可以用来对表category记录进行增删改查
* 2. 接口可以继承一个接口
* 3. 在写法上，接口实现接口的写法没有被采取。会引起编译错误
* */
public interface CategoryDao {

	/* 一点东西都没有去写，然而继承了basedao，意味着这个接口就已经拥有了父接口basedao中的属性以及方法 */
	/* 可以通过由于方法中的类型是T泛型，所以这才算做是灵活吧，在继承时，将category传入，basedao在处理到所有的T泛型时候，都会将T型来改为Category类型。 */
	/* 这是我的理解。等下测试吧 */

	/* 所有的有共性的dao接口都不需要写类似的方法 */
	/* 有特殊的抽象方法仍然可以自己来实现 */

	/* 接下来，我去抽取service层的吧 */

	/* 以上的注释，我发现在ssm中暂时不适用 */
	public List<Category> queryJoinAccount(String type);
	public List<Category> queryJoinAccountForPage(Map<String, Object> map);
	public int  getCount(String type);  // 获取 category 表的记录 总数
	public void deleteCategoryByIds(List<Integer> ids) throws Exception;  // 根据ids去删除用户  // 抛出异常则删除失败
	public void save(Category c) throws Exception;
	public void update(Category c) throws Exception;
	public List<Category> getCategories();

	public List<Category> queryByHot(boolean hot);
}
