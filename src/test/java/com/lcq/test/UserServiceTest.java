package com.lcq.test;

import com.lcq.dao.CategoryDao;
import com.lcq.dao.SorderDao;
import com.lcq.domain.*;
import com.lcq.pojo.PagePOJO;
import com.lcq.service.CategoryService;
import com.lcq.service.ForderService;
import com.lcq.service.UserService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UserServiceTest extends TestCase {
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	//private CategoryDao categoryDao;

	@Test
	public void selectUserByIdTest() {
		System.out.println(userService);
		//User user = userService.selectUserById(1);
		//System.out.println(user.getUsername());
	}
	// 测试spring -- > applicationContext.xml --> getAutoWireCapableBeanFactoryBeanFactory
	@Test
	public void testSpringAPI () {
		System.out.println(userService);
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		BeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
		System.out.println("beanFactory = " + beanFactory);     // 可以查看beanFactory中都实例化了哪些类。
		System.out.println(beanFactory.isSingleton("userServiceImpl"));  // TRUE
	}

	// 修改zhangsan为张三
	@Test
	public void testUpdateName() {
		User user = new User();  // 设置张三对象
		user.setId(2);  // 设置要修改的对象id是zhangsan额
		//user.setUsername("张三");
		user.setPassword("123456");
		//userService.updateUserInfo(user); // 调用修改的方法        // 在这里并没有设置事务提交，怎么就可以了修改到数据库了呢？
	}
	// 获取用户的信息
	@Test
	public void testGet() {
		int id = 10;  // 设置要获取的用户对应的id信息
		User closer = userService.get("1");
		System.out.println(closer);
	}

	/* 从maven工程中读取resources下的文件 */
	@Test
	public void testIO () throws IOException {
		URL url = getClass().getClassLoader().getResource("log4j.properties");
		File file = new File(url.getFile());
		System.out.println("=================================" + file.canRead());
		FileReader fr = new FileReader(file);
		int i = 0;
		while ( i != -1) {
			i = fr.read();
			System.out.print((char)i);
		}
	}
	/* 使用apache commons的io包 */
	@Test
	public void testCommonsIO () throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		String result = null;
		result = IOUtils.toString(cl.getResourceAsStream("step.md"));
		System.out.println(result);
	}

	/* 测试查询类别 */
	@Test
	public void testQueryJoingAccount() {
		List<Category> list = categoryService.queryJoinAccount("");
		for (Category c : list) {
			System.out.println(c);
			System.out.println(c.getAccount());
		}
	}

	// 测试级联查询 -- 分页 -- 物理分页
	@Test
	public void test () {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", new Integer(0));
		map.put("pagesize", new Integer(2));
		map.put("type", "手表");

		List<Category> list = categoryService.queryJoinAccountForPage(map);

		System.out.println("list:" + list);
		System.out.println("物理分页查找完成");
	}  // success

	// 测试 分页 pojo
	@Test
	public void testSelectPage () {

		// 常见分页对象
		// {int, totalNumber, int currentPage, int totalPage, int pageSize, int startIndex, int totalSelect}
		PagePOJO page = new PagePOJO(5, 1, 1, 3, 1, 4);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("type", "手表");
		List<Category> list =  categoryService.queryJoinAccountForPage(params);
		System.out.println("list:" + list );
		System.out.println("查询完成");
	}
	@Test
	public void testSekectPageFirstMap () {
		List<Category> categories = categoryService.queryJoinAccountForPage("", "1", "2");
		System.out.println(categories);
	}


	/*
	* 获取category表的总记录数
	* */
	@Test
	public void testGetCount () {
		int result = categoryService.getCount("");
		System.out.println("category表中有"+ result + "条记录");
	}
	/*
	* 根据id集合来删除数据
	* */
	@Test
	public void testDelCategoryByIDs () {
		boolean isDeleteSuccessful = categoryService.deleteCategoryById("38,39");
		String delinfo = "";
		delinfo = isDeleteSuccessful ? "成功" : "失败";
		System.out.println("删除"+delinfo);
	}

	@Test
	public void testSaveCategory () throws Exception {
		Category c = new Category();
		c.setId(1);     // id 是由数据库中生成的。，在这里设置为1也没有用。sql语句是自己写的，插入的字段中没有包含id
		c.setType("蟋蟀服饰");
		c.setHot(0);
		Account account = new Account();
		account.setId(2);
		c.setAccount(account);
		categoryService.save(c);
	}

	/*
	* 测试修改商品类别的信息
	* */
	@Test
	public void testUpdateCategory () throws Exception {
		Category c = new Category();
		c.setId(30);
		c.setType("手表");
		c.setHot(0);
		Account account = new Account();
		account.setId(5);
		c.setAccount(account);
		categoryService.update(c);
	}

	/*
	* 获取所有热点类型的商品类别【热卖】
	* */
	@Test
	public void testGetHotCategories () {
		List<Category> list =categoryService.queryByHot(true);
		System.out.println(list);

		System.out.println("----------------");
		// List<Category> list1 = categoryDao.queryByHot(true);  dao被暂时注释掉了
		//System.out.println(list1);
	}


	/*
	* 测试list集合是否可以存放null值
	* */
	@Test
	public void testListAddNull () {
		List list = new ArrayList();
		list.add(1);
		list.add(null);
		System.out.println(list);
	}

	@Test
	public void testlogin() {
		System.out.println( userService );
		User user = userService.login("lin-cq", "opopop");
		System.out.println( user );
	}

	@Test
	public void testToken () {

	}

	@Test
	public void testModel() {}

	/*
	* 测试保存到购物车
	* */
	@Test
	public void testAddCart () {
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ForderService service = (ForderService)context.getBean("forderService");
		Forder forder = new Forder();
		User user = new User();
		user.setId(2);
		user.setLogin( "Tomcat");
		forder.setUser( user );
		forder.setTotal(0);
		forder.setSorders( null );
		forder.setDate( new Date() );
		Status status = new Status();
		status.setId(1);  // 未发货
		forder.setStatus( status );

		forder.setName( user.getLogin() );
		forder.setPhone("110");
		forder.setAddress("广东珠海");
		forder.setPost("515520");
		forder.setRemark( "老板，要快点发货哦" );

		service.saveOrUpdate( forder );

	}

	@Autowired
	private SorderDao sorderDao;
	@Test
	public void testAddSorders() {
		Map<String, Object> map = new HashMap();
		map.put("forderID", 5);
		Set<Sorder> sorders = new HashSet<Sorder>();

		Sorder s = new Sorder();
		s.setName("手机");
		s.setNumber(2);
		s.setPrice( 16 );
		Product p = new Product();
		p.setId(16);
		p.setName("手机");
		s.setProduct( p );

		Sorder s1 = new Sorder();
		s1.setNumber(5);
		s1.setPrice( 201 );
		s1.setName("领带");
		Product p1 = new Product();
		p1.setId(15);
		p1.setName("领带");
		s1.setProduct( p1 );

		sorders.add(s);
		sorders.add(s1);

		map.put("sorders", sorders);

		sorderDao.addSorderBatch( map );  // forderID, sorders 测试目的： 是否可以批量添加sorder信息，并且指定其所属购物车的位置
	}
}


