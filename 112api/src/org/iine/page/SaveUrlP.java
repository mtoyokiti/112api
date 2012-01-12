package org.iine.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iine.component.UrlManagerC;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * 
 * ＊refererからURLを類推する ＊URLは小文字で登録する ＊
 * 
 * @author kuro
 * 
 */
public class SaveUrlP {

	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	
	private PrintWriter w;
	private String referer = "";
	private String pass = "";
	private String title;

	/**
	 * URL一覧のメモリキャッシュ
	 */
	private static HashMap<String, Long> urls = new HashMap<String, Long>();

	/**
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void http(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		referer = req.getHeader("REFERER");

		// 無視する
		if ((referer == null) || ("".equals(referer))) {
			return;
		}

		this.w = resp.getWriter();
		referer = referer.toLowerCase();

		// 登録済みかどうかを確認
		Long id = urls.get(referer);

		// 登録されていない場合は新規登録
		if (id == null) {
			id = save(req, resp);
			
			if (isEmpty(title)) {
				SetTitlePage page = new SetTitlePage(id, pass, referer);
				page.http(req, resp);
				page.writeHtml();
			}
		}
		
		this.countUp(id);
		w.print("サンキュー　いいね！ Thank you. IINE!");
	}
	
	
	
	/**
	 * DBのカウンタテーブルにデータを追加する。
	 * 奇数分、偶数分
	 * ミリ秒の１桁目
	 * 
	 * @param id
	 */
	private void countUp(long id){
		long now = System.currentTimeMillis();
		long ms1 = now % 10;
		Calendar calendar = Calendar.getInstance();
		long m = calendar.get(Calendar.MINUTE) % 2;
		Entity entity = new Entity("COUNTER" + m + ms1);
		entity.setProperty("u", id);
		ds.put(entity);
	}
	
	
	


	/**
	 * 
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private long save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UrlManagerC url = new UrlManagerC(referer);
		// 既にDBにあるかどうかを再度確認する
		// あったらDBから取ってきたIDを登録して返す
		if (url.isFound()) {
			long keyId = url.getId();
			if (!isEmpty(url.getTitle()))
				urls.put(referer, new Long(keyId));
			title = url.getTitle();
			pass = url.getPassword();
			return keyId;
		}
		
		url.write();
		this.pass = url.getPassword();
		
		long id = url.getId();
		title = "";
		

		
		return id;
	}

	
	
	
	// DBからデータをあらかじめ読み込んでおく
	static {
		Query query = new Query("SiteList");
		PreparedQuery pq =  ds.prepare(query);
		Iterable<Entity> ents =  pq.asIterable();
		for (Entity entity : ents) {
			String title = (String)entity.getProperty("title");
			if ((title == null) || (title.equals(""))) {
				// 登録しない
			} else {
				urls.put((String)entity.getProperty("url"), entity.getKey().getId());
			}
		}
	}
	
	private boolean isEmpty(String val) {
		return ((val == null) || (val.equals("")));
	}


}
