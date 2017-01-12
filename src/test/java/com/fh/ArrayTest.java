package com.fh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class ArrayTest {

	
	@Test
	public void testCollections(){
		
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
			System.out.println(m.getId());
		}
	}
}
