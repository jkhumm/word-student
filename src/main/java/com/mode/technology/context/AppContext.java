package com.mode.technology.context;

/**
 * 应用上下文
 * @since	JDK 1.8
 * @author	heian
 */
public class AppContext {

	private static final InheritableThreadLocal<RequestContext> TL = new InheritableThreadLocal<>();

	public static RequestContext getRequestContext() {
		return TL.get();
	}

	public static void setRequestContext(RequestContext requestContext) {
		TL.set(requestContext);
	}

	public static void removeRequestContext() {
		TL.remove();
	}
}
