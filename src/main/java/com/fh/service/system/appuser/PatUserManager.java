package com.fh.service.system.appuser;

import java.util.List;

import com.fh.entity.Page;
import com.fh.util.PageData;



public interface PatUserManager {
	

	
	/**病人列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPdPageUser(Page page)throws Exception;

	

	
}

