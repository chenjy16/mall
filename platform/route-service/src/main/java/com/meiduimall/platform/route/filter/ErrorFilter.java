package com.meiduimall.platform.route.filter;
/*package com.meiduimall.service.route.filter;
import org.apache.http.HttpStatus;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ErrorFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		ZuulException zuulException=(ZuulException)ctx.getThrowable();
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
		ctx.setResponseBody(null);
		return null;
	}

	@Override
	public String filterType() {
		 return "error";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
*/