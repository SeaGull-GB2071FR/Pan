package com.GB2071FR.Pan.entity.query;


import com.GB2071FR.Pan.entity.query.SimplePage;
import lombok.Data;

@Data
public class BaseParam {

	private SimplePage simplePage;

	private Integer pageNo;

	private Integer pageSize;

	private String orderBy;

}
