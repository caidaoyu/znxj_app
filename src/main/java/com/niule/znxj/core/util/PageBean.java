package com.niule.znxj.core.util;
import java.util.List;

public class PageBean {

	private List list;
	private long allRow;
	private int totalPage;
	private int currentPage;
	private int pageSize = 15;


	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public long getAllRow() {
		return allRow;
	}

	public void setAllRow(long allRow) {
		this.allRow = allRow;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

	

	/***************************************************************************
	 * 
	 * ������ҳ������̬���������ⲿֱ��ͨ���������� pageSize ÿҳ��¼�� allRow �ܼ�¼��
	 * 
	 * @return ��ҳ��
	 */
	public static long counTotalPage(
			final int pageSize, final long allRow) 
	{
		long totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
				/ pageSize + 1;
		return totalPage;
	}

	/***************************************************************************
	 * 
	 * ���㵱ǰҳ��ʼ��¼ pageSize ÿҳ��¼�� currentPage ��ǰ�ڼ�ҳ
	 * 
	 * @return ��ǰҳ��ʼ��¼��
	 */
	public static int countOffset(final int pageSize, 
			final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/***************************************************************************
	 * 
	 * ���㵱ǰҳ����Ϊ0���������URl��û��"?page=",����1���� page����Ĳ���������Ϊ�գ������򷵻�1��
	 * 
	 * @return ��ǰҳ
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
}


