package com.project.linkinassistant.bean;

public class AddressStyleInfo {
	private int color; // 颜色资源id
	private String name; // 颜色名
	private boolean check;// 是否是选中状态

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
