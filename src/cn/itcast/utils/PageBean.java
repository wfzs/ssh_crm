package cn.itcast.utils;

import java.util.List;

public class PageBean {

	//��ǰҳ
	private Integer currentPage;
	//�ܼ�¼��
	private Integer totalCount;
	//ÿҳ��ʾ����
	private Integer pageSize;
	//��ҳ��
	private Integer totalPage;
	//��ҳ�б�����
	private List list;
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if(this.pageSize==null){
			this.pageSize = 3;
		}
		this.totalPage=(this.totalCount+this.pageSize-1)/this.pageSize;
		if(this.currentPage<1){
			this.currentPage = 1;
		}
		if(this.currentPage>this.totalPage){
			this.currentPage = this.totalPage;
		}
	}
	//������ʼλ��
	public int getStart(){
		return (this.currentPage-1)*pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
