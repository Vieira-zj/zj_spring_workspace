package com.zhengjin.springmvc4.domain;

public class DemoObj {

	private Long id;
	private String name;

	public DemoObj() {
		super();
	}

	public DemoObj(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String ret;
		if (this.id == null) {
			ret = String.format("id=null, name=%s", this.name);
		} else {
			ret = String.format("id=%s, name=%s", this.id, this.name);
		}
		return ret;
	}

}
