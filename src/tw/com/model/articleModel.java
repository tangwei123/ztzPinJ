package tw.com.model;

public class articleModel {
	/**
	 * 文章ID号
	 */
	private int id;
	/**
	 * 文章所在分类的ID号
	 */
	private int articleCatId;
	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * 文章描述
	 */
	private String des;
	/**
	 * 文章首页图片
	 */
	private String indexImg;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 文章发布时间
	 */
	private String addTime;
	/**
	 * 文章排序
	 */
	private int sort;
	/**
	 * 文章是否被删除
	 */
	private int isDeleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArticleCatId() {
		return articleCatId;
	}
	public void setArticleCatId(int articleCatId) {
		this.articleCatId = articleCatId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getIndexImg() {
		return indexImg;
	}
	public void setIndexImg(String indexImg) {
		this.indexImg = indexImg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public articleModel() {
		super();
	}
	@Override
	public String toString() {
		return "articleModel [id=" + id + ", articleCatId=" + articleCatId + ", title=" + title + ", des=" + des
				+ ", indexImg=" + indexImg + ", content=" + content + ", addTime=" + addTime + ", sort=" + sort
				+ ", isDeleted=" + isDeleted + "]";
	}
	
}
