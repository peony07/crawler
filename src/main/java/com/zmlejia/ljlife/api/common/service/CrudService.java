package com.zmlejia.ljlife.api.common.service;

import com.zmlejia.ljlife.api.common.persistence.CrudDao;
import com.zmlejia.ljlife.api.common.persistence.DataEntity;
import com.zmlejia.ljlife.api.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service基类
 * @author zmwy
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	public T getByMobile(String mobile) {
			return dao.getByMobile(mobile);
	}
	public T getAdCheck(String adId) {
		return dao.getAdCheck(adId);
	}
	/**
	 * 根据complaintId获取回访信息
	 * @param complaintId
	 * @return
	 */
	public T getVisit(String complaintId) {
		return dao.getVisit(complaintId);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}
	
	public List<T> findAreaList(T entity) {
		return dao.findAreaList(entity);
	}
	@Transactional(readOnly = false)
	public void batchupdateCategoryStatus(List<T> list) {
		dao.batchupdateCategoryStatus(list);
	}
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	@Transactional(readOnly = false)
	public String saveGoods(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
		return entity.getId();
	}
	
	/**
	 * 保存小区到office（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void communitySaveOffice(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.communitySaveOffice(entity);
		}else{
			entity.preUpdate();
			dao.communityUpdateOffice(entity);
		}
	}

	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	public String getCategorySnMax(T entity) {
		return dao.getCategorySnMax(entity);
	}
	
	
	public  List<T> getAll(String id){
		return dao.getAll(id);
	}
}
