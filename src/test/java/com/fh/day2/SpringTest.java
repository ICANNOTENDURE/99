package com.fh.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/ApplicationContextTest.xml")
public class SpringTest {

	@Autowired
	private CommonService commonService;
	
	
	@Test
	public void test() throws Exception{
		
		long start=System.currentTimeMillis();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("parent", "0");
		List<Menu>  menus=commonService.selectByEqCon(Menu.class, map);
		for(Menu menu:menus){
			menu.getNodes().addAll(getNodes(menu.getId()));
		}
		System.out.println(JSON.toJSONString(menus));
		System.out.println(" test1:"+(System.currentTimeMillis()-start)+"ms");
		
	}
	
	public List<Menu> getNodes(String parent) throws Exception{
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("parent", parent);
		List<Menu>  menus=commonService.selectByEqCon(Menu.class, map);
		for(Menu menu:menus){
			menu.getNodes().addAll(getNodes(menu.getId()));
		}
		return menus;
	} 
	
	//1117ms
	//1415ms
	//@Test
	public void test2() throws Exception{
		long start=System.currentTimeMillis();
		List<Menu>  menus=commonService.selectAdvanced(Menu.class,new GeneralQueryParam());
		List<Menu> root=new ArrayList<Menu>();
		Map<String,Menu> map=new HashMap<String, Menu>();
		for(Menu menu:menus){
			if("0".equals(menu.getParent())){
				root.add(menu);
			}
			map.put(menu.getId(), menu);
		}
		Iterator<String>  keys=map.keySet().iterator();
		while(keys.hasNext()){
			String key =keys.next();
			Menu menu=map.get(key);
			if(!"0".equals(menu.getParent())){
				Menu parentMenu=map.get(menu.getParent());
				parentMenu.getNodes().add(menu);
			}
		}
		System.out.println(JSON.toJSONString(root));
		System.out.println(" test1:"+(System.currentTimeMillis()-start)+"ms");
	}
	
	
}
