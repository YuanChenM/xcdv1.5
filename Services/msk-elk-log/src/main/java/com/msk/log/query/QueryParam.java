package com.msk.log.query;

/**
 * 查询search参数分装类
 * Created by mao_yejun on 2016/6/6.
 */
public class QueryParam {
private QueryMatch query;
private int size;
private Filter filter;
public QueryMatch getQuery() {
	return query;
}
public void setQuery(QueryMatch query) {
	this.query = query;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public Filter getFilter() {
	return filter;
}
public void setFilter(Filter filter) {
	this.filter = filter;
}



}
