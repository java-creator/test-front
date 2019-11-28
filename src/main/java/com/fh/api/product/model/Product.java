package com.fh.api.product.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("zzz_pro")
public class Product {

	private Integer id;
	private Integer price;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdate;
	private Integer typeid;
	private String namePro;
	private String filePath;
	private Integer category1;//商品三级联动
	private Integer category2;
	private Integer category3;
	@TableField(exist = false)
	private String brandName;//品牌名称
	private Integer isUp;//上下架
	private Integer stuck;//库存
	private Integer sales;//销量


	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getStuck() {
		return stuck;
	}

	public void setStuck(Integer stuck) {
		this.stuck = stuck;
	}

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getCategory1() {
		return category1;
	}

	public void setCategory1(Integer category1) {
		this.category1 = category1;
	}

	public Integer getCategory2() {
		return category2;
	}

	public void setCategory2(Integer category2) {
		this.category2 = category2;
	}

	public Integer getCategory3() {
		return category3;
	}

	public void setCategory3(Integer category3) {
		this.category3 = category3;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getNamePro() {
		return namePro;
	}

	public void setNamePro(String namePro) {
		this.namePro = namePro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
}
