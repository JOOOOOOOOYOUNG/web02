package com.member.dispatcher;

import lombok.Setter;

@Setter
public class ViewResolver {
	

	public String prefix;
	public String suffix;
	
	public String getView(String viewName) {
		// index => prefix + 뷰파일이름 + suffix
		//		  "/member/" + "test/index" + ".jsp"
		return prefix+viewName+suffix;
	}
	
	
}
