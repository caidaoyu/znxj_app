package com.niule.znxj.web.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.niule.znxj.core.common.Resources;

/**
 * 过滤器
 * 功能：对访问者IP进行限制访问
 */
public class IpFilter implements Filter{

	//用来存放允许访问的ip
	private List<String> allowList = new ArrayList<String>();
	
	//app端接口
	private String appForward = "/app";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		try {
			System.out.println("过滤器IpFilter开始初始化，功能：IP访问限制");
			initConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		//获取访问的IP地址
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String servlerPath = httpServletRequest.getServletPath();
		String remoteAddr = getIpAddr(httpServletRequest);
		System.out.println("===============" + servlerPath+"  ip:"+remoteAddr);
		//如果allowList为空,则认为没做限制,不为空则检查是否限制
		if (servlerPath.indexOf(appForward)!=-1) {
			filterChain.doFilter(request, response);
		}else {
			if(allowList.size() == 0 || allowList == null) {
				filterChain.doFilter(request, response);
			} else {
				Boolean flag = false;  //访问标志，默认为false，限制访问
				for(String regex : allowList){
					if(remoteAddr.matches(regex)){
						//ip没被限制，正常访问
						filterChain.doFilter(request, response);
						flag = true;  //置为true，表示不限制访问
						break;
					}
				}
				if(!flag) {
					//ip被限制，跳到指定页面
					request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
				}
			}
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("过滤器IpFilter结束。");
	}

	/**
	 * 对配置文件进行初始化并校验
	 * @author 欧阳
	 * @serialData 20180728
	 * @throws IOException
	 */
	public void initConfig() throws IOException {
		//获取允许的ip值
		String allowIPWildcard = Resources.ApplicationResources.getString("allowIPWildcard");
		if(allowIPWildcard != null && !"".equals(allowIPWildcard)) {
			//获取每个含通配符的ip地址
			String[] allowIPWildcards = allowIPWildcard.split(",|;");
			
			if(allowIPWildcards.length > 0) {
				for(String ip : allowIPWildcards) {
					if(ip.indexOf("*") != -1) {
						//对*进行替换
						ip = ip.replaceAll("\\*", "(25[0-5]|2[0-4]\\\\d|[0-1]\\\\d{2}|[1-9]?\\\\d)");
						allowList.add(ip);
					} else {
						throw new RuntimeException("配置文件有错，请检查！");
					}
				}
			}
		}
		//打印输出allowList
		for(String str : allowList) {
			System.out.println(str);
		}
	}
	
	/**
	 * 对配置文件进行校验
	 * @param allowIPWildcard
	 * @return
	 */
	public Boolean validate(String allowIPWildcard) {
		Boolean result = false;
		//IP地址每一段的正则
		String regx = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		//整个ip的正则
		//String ipRegx = regx + "\\." + regx + "\\."+ regx + "\\." + regx;
		Pattern pattern = Pattern.compile("("+regx+"\\."+ regx+"\\."+regx+"\\."+ "\\*)|" + 
						"("+regx+"\\."+regx+"\\."+regx+"\\."+ "\\*(,|;))*");
		if(this.isNullorMatches(allowIPWildcard, pattern)){
			result = true;  //匹配成功
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 进行正则匹配
	 * @param allow
	 * @return
	 */
	public Boolean isNullorMatches(String allow, Pattern pattern) {
		//如果为空，说明用户没添加该项，不做处理
		if(allow == null || "".equals(allow.trim())) {
			return true;
		} else {
			//在最后面没有,或;的给添上
			if(!allow.endsWith(";") && !allow.endsWith(",")) {
				allow += ";";
			}
			//如果匹配，则返回true
			if(pattern.matcher(allow).matches()) {
				return true;
			}
		}
		
		return false;
	}
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
    public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
       return ipAddress;   
    }
}

