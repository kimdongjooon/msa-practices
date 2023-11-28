package com.poscodx.emaillist.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DirectionVo {
	private String coil_no;
	private String dir_dt;
	private String size_db;
	private float dir_thk;
	private float dir_wth;
	private float dir_len;
	private float target_rdt;
	private float target_fdt;
	private float target_ct;
	private String thk_aim_id;
	private String wth_aim_id;
	private String fdt_aim_id;
	private String ct_aim_id;
	private String content;
	private String created_date;
	private String last_updated_date;
}
