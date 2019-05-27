package tw.com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import tw.com.model.articleModel;
import tw.com.service.articleService;
import tw.com.service.factoryService;
import tw.com.utils.uploadFile;

@WebServlet(urlPatterns = {"/article"})
public class articleController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
//		System.out.println("get方式");
		Enumeration<String> parametersEnumeration = req.getParameterNames();
		while (parametersEnumeration.hasMoreElements()) {
			String string = (String) parametersEnumeration.nextElement();
			if(string.equals("action")) {
				String doFunString = req.getParameter(string);
				try {
					Method method = this.getClass().getDeclaredMethod(doFunString, HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(this, req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
//		System.out.println("post方式");
		Enumeration<String> parametersEnumeration = req.getParameterNames();
		while (parametersEnumeration.hasMoreElements()) {
			String string = (String) parametersEnumeration.nextElement();
			if(string.equals("action")) {
				String doFunString = req.getParameter(string);
				try {
					Method method = this.getClass().getDeclaredMethod(doFunString, HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(this, req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		String queryString = req.getQueryString();//先获取到action=editOne&id=4 然后用&分隔数据，然后在用=分隔数据 再去反射操作
//		String[] queryArray = queryString.split("&");
//		for(String parameterString:queryArray) {
//			String[] actionStr = parameterString.split("=");
//			if(actionStr[0].equals("action")) {
//				String functionNameString = actionStr[1];//拿到action的方法名，接下来通过反射的方式去执行方法
//				try {
//					Method method = this.getClass().getDeclaredMethod(functionNameString, HttpServletRequest.class, HttpServletResponse.class);
//					method.invoke(this, req, resp);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}
	
	private void editOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
//		String queryString = req.getQueryString();//这块注释掉的代码，有点舍近求远的获取url参数，并判断参数是否存在
//		String[] queryArray = queryString.split("&");
//		Map<String, String> queryData = new HashMap<String, String>();
//		for(String parameterString:queryArray) {
//			String[] actionStr = parameterString.split("=");
//			if(!actionStr[0].equals("action")) {
//				queryData.put(actionStr[0], actionStr[1]);
//			}
//		}
//		if(!queryData.containsKey("id")) {
//			resp.getWriter().write("关键参数错误！");
//		}
		String idValue = req.getParameter("id");
		if(idValue == null) {
//			req.setAttribute("errMessage", "缺少ID相关参数，无法继续操作");
//			this.getServletContext().setAttribute();//设置错误提示信息
			resp.sendRedirect("/ztzPin/err.jsp");//跳转到错误页面去
//			req.getRequestDispatcher("err.jsp").forward(req, resp);
		}else {
			articleService asticleservice = factoryService.getArticleService();
			articleModel articlemodel = asticleservice.getOneById(new Integer(idValue));
//			System.out.println(articlemodel.toString());
//			System.out.println(this.getServletContext().getContextPath());
			req.setAttribute("articleInfo", articlemodel);
			req.getRequestDispatcher("articleDetail2.jsp").forward(req, resp);;
		}
	}
	
	private void doEditArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idValue = req.getParameter("id");
		if(idValue == null) {
			resp.sendRedirect("/ztzPin/err.jsp");//跳转到错误页面去
		}else {
			articleService asticleservice = factoryService.getArticleService();
			articleModel articlemodel = asticleservice.getOneById(new Integer(idValue));
			if(articlemodel.getIsDeleted() == 1) {
				req.setAttribute("infoMessage", "该条数据已被删除不能继续编辑");
				req.getRequestDispatcher("/info.jsp").forward(req, resp);
			}else {
//				System.out.println(articlemodel.toString());
				try {
					System.out.println(req.getParameter("title"));
					System.out.println(req.getParameter("des"));					
					Map<String, String[]> xxxMap = req.getParameterMap();
					for (String key : xxxMap.keySet()) {
						String[] value = xxxMap.get(key); 
						System.out.println("Key = " + key + ", Value = " + value);
					}
					BeanUtils.populate(articlemodel, req.getParameterMap());
					int res = asticleservice.editOne(articlemodel);
					if(res > 0) {
						req.setAttribute("infoMessage", "编辑数据成功");
						req.getRequestDispatcher("/info.jsp").forward(req, resp);
					}else {
						req.setAttribute("infoMessage", "编辑数据失败");
						req.getRequestDispatcher("/info.jsp").forward(req, resp);
					}
//					System.out.println(articlemodel.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
	
	private void doEditArticle2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idValue = req.getParameter("id");
		if(idValue == null) {
			resp.sendRedirect("/ztzPin/err.jsp");//跳转到错误页面去
		}else {
			articleService asticleservice = factoryService.getArticleService();
			articleModel articlemodel = asticleservice.getOneById(new Integer(idValue));
			if(articlemodel.getIsDeleted() == 1) {
				req.setAttribute("infoMessage", "该条数据已被删除不能继续编辑");
				req.getRequestDispatcher("/info.jsp").forward(req, resp);
			}else {
				uploadFile fileUpload = new uploadFile();
				fileUpload.sizeThreshold = 300 * 1024;
				fileUpload.fileSize = 3 * 1024 * 1024;
				fileUpload.totalSize = 20 * 1024 * 1024;
				fileUpload.tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
				fileUpload.savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
				Map<String, String> mapData = fileUpload.doUpload(req);
//				for(String item:mapData.keySet()) {
//					System.out.println(mapData.get(item));
//				}
				try {
					BeanUtils.populate(articlemodel, mapData);
					asticleservice.editOne(articlemodel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
	}
}
