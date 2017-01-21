package com.fh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fh.entity.app.AppLoc;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath:/spring/ApplicationContextTest.xml"}) 
public class ArrayTest {

	@Autowired
	private CommonService commonService;

	
	
	@Test
	public void testCollections() throws Exception{
		
		List<AppLoc> appLocs=commonService.selectAdvanced(AppLoc.class, new GeneralQueryParam());
		List<Integer> list=new ArrayList<Integer>();
		list.add(2);
		
		list.add(4);
		list.add(3);
		System.out.println(Collections.max(list));
		Collections.sort(list);
		for(Integer i:list){
			System.out.println(i);
		}
		
		List<Menu> menus=new ArrayList<Menu>();
		menus.add(new Menu(22, "log", 0));
		menus.add(new Menu(44, "log", 0));
		menus.add(new Menu(33, "log", 0));
		Collections.sort(menus);
		for(Menu m:menus){
			System.out.println("方式1:"+m.getId());
			
		}
		for(int i=0;i<menus.size();i++){
			System.out.println("方式2:"+menus.get(i).getId());
			
		}
		Iterator<Menu> it=menus.iterator();
		while(it.hasNext()){
			System.out.println("方式3:"+it.next().getId());
		}
	}
	//@Test
	public void testThread() throws InterruptedException{
			final List<Integer> list=new ArrayList<Integer>();
			//final List<Integer> list=Collections.synchronizedList(new ArrayList<Integer>());
			for(int i=0;i<10;i++){
				list.clear();
				for(int j=0;j<10000;j++){
					new Thread(new Runnable() {
						@Override
						public void run() { 
							list.add(2);
						}
					}).start();
				
				}

				Thread.sleep(10); 
				System.out.println(list.size());
			}
	}

}
