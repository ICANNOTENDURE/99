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

	
	/**保存用户
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除用户
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改用户
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	

	/**批量删除用户
	 * @param USER_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] USER_IDS)throws Exception;
	
}

