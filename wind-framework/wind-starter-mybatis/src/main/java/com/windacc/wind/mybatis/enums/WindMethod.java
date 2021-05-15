package com.windacc.wind.mybatis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/14 22:27
 */
@Getter
@AllArgsConstructor
public enum WindMethod {

	/**
	 * 如果插入已经存在相同的记录,则忽略当前新数据
	 */
	INSERT_IGNORE_ONE("insertIgnore", "如果插入已经存在相同的记录,则忽略当前新数据（选择字段插入）",
			"<script>\nINSERT IGNORE INTO %s %s VALUES %s\n</script>"),

	/**
	 * 插入更新
	 */
	UPSERT_ONE("upsert", "插入或更新一条数据（选择字段插入）",
			"<script>\nINSERT INTO %s %s VALUES %s on duplicate key update %s\n</script>");

	private final String method;
	private final String desc;
	private final String sql;
}
