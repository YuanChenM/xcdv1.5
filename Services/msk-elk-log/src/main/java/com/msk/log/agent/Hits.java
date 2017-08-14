package com.msk.log.agent;

import java.util.List;

public class Hits {
	private Integer total;
	private String max_score;
	private List<LogMessage> hits;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getMax_score() {
		return max_score;
	}

	public void setMax_score(String max_score) {
		this.max_score = max_score;
	}

	public List<LogMessage> getHits() {
		return hits;
	}

	public void setHits(List<LogMessage> hits) {
		this.hits = hits;
	}

}
