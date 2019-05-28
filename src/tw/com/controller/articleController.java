package tw.com.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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
		Enumeration<String> parametersEnumeration = req.getParameterNames();
		while (parametersEnumeration.hasMoreElements()) {
			String string = (String) parametersEnumeration.nextElement();
			if(string.equals("action")) {
				String doFunString = req.getParameter(string);
				try {
					Method method = this.getClass().getDeclaredMethod(doFunString, HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(this, req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		Enumeration<String> parametersEnumeration = req.getParameterNames();
		while (parametersEnumeration.hasMoreElements()) {
			String string = (String) parametersEnumeration.nextElement();
			if(string.equals("action")) {
				String doFunString = req.getParameter(string);
				try {
					Method method = this.getClass().getDeclaredMethod(doFunString, HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(this, req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void addOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/addArticle.jsp").forward(req, resp);
	}
	
	private void doAddArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		articleService asticleservice = factoryService.getArticleService();
		articleModel articlemodel = new articleModel();
		uploadFile fileUpload = new uploadFile();
		fileUpload.sizeThreshold = 300 * 1024;
		fileUpload.fileSize = 3 * 1024 * 1024;
		fileUpload.totalSize = 20 * 1024 * 1024;
		fileUpload.tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
		fileUpload.savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		Map<String, String> mapData = fileUpload.doUpload(req);
		try {
			BeanUtils.populate(articlemodel, mapData);
			Date nowDate = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = dateformat.format(nowDate);
			articlemodel.setAddTime(dateString);
			asticleservice.addOne(articlemodel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void editOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idValue = req.getParameter("id");
		if(idValue == null) {
			req.setAttribute("errMessage", "缺少ID相关参数，无法继续操作");
			req.getRequestDispatcher("err.jsp").forward(req, resp);
		}else {
			articleService asticleservice = factoryService.getArticleService();
			articleModel articlemodel = asticleservice.getOneById(new Integer(idValue));
			req.setAttribute("articleInfo", articlemodel);
			req.getRequestDispatcher("articleDetail2.jsp").forward(req, resp);;
		}
	}
	
	/**
	 * 没有 enctype="multipart/form-data"
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
	}
	
	/**
	 * 有 enctype="multipart/form-data"
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
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
				try {
					BeanUtils.populate(articlemodel, mapData);
					asticleservice.editOne(articlemodel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void listArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageInfo = req.getParameter("page");
		if(pageInfo == null) {
			pageInfo = "1";
		}
		List<articleModel> articlelist = null;
		articleService articleservice = factoryService.getArticleService();
		articlelist = articleservice.getList(pageInfo);
		for(articleModel articleInfo:articlelist) {
			System.out.println(articleInfo.toString());
		}
	}
}
