/*
 *  @项目名称: ${project_name}
 *
 *  @文件名称: ${file_name}
 *  @Date: ${date}
 *  @Copyright: ${year} www.meiduimall.com Inc. All rights reserved.
 *
 *  注意：本内容仅限于美兑壹购物公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.meiduimall.platform.route.hanler.impl;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import com.meiduimall.platform.route.hanler.Handler;
import com.netflix.zuul.context.RequestContext;

public class HandlerChain implements Handler{
	
	private List<Handler> handlers = new ArrayList<>();
	
	public HandlerChain addProcesser(Handler handler){
	    this.handlers.add(handler);
	    return this;
	}
	
	/**
	 * 功能描述: <br>
	 * Author: 陈建宇
	 * Date:   2017年4月26日 上午10:53:13
	 */
	@Override
	public Boolean process(RequestContext ctx) {
		Iterator<Handler> it=handlers.iterator();
		while(it.hasNext()){
			Handler handler=it.next();
			Boolean res=handler.process(ctx);
			if(!res){
				break;
			}
		}
		handlers.clear();
		return true;
	}
	
}
