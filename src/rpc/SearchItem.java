package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import external.TicketMasterAPI;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		String term = request.getParameter("term");
		
		DBConnection connection = DBConnectionFactory.getDBConnection();
		List<Item> items = connection.searchItems(lat, lon, term);
		//use TicketMasterAPI
//		TicketMasterAPI tmAPI = new TicketMasterAPI();
//		List<Item> items = tmAPI.search(lat, lon, term); 
		Set<String> favorite = connection.getFavoriteItemIds(userId);
		JSONArray array = new JSONArray();
		try {
			for(Item item : items) {
				JSONObject obj = item.toJSONObject();
				// Check if this is a favorite one.
				// This field is required by frontend to correctly display favorite items.
				obj.put("favorite", favorite.contains(item.getItemId()));
				array.put(obj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, array);
		
		
		
		
		
		// TODO Auto-generated method stub
		//response.addHeader("Access-Control-Allow-Origin", "*");
		//response.getWriter().append("Served at: ").append(request.getContextPath()); //这句话只是打印出Served at /Jupiter
		
		//Create a PrintWriter from response such that we can add data to response.
//		PrintWriter out = response.getWriter();
//		if(request.getParameter("username") != null) {
//			//Get the username sent from the client
//			String username = request.getParameter("username");
//			//In the output stream, add something to response body
//			out.print("Hello " + username);
//		}
//		//Send response back to client
//		out.flush();
//		out.close();
		
		//return html 
//		response.setContentType("text/html");
//		//这句话意思是允许任何资源访问
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		PrintWriter out = response.getWriter();
//		out.println("<html><body>");
//		out.println("<h1>This is my homepage</h1>");
//		out.println("<ul><li>fuck you!</li></ul>");
//		out.println("</body></html>");
//		out.close();
		
		//return json object
//		response.setContentType("application/json");
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		
//		String username = "";
//		if(request.getParameter("username")!=null) {
//			username = request.getParameter("username");
//		}
//		
//		JSONObject obj = new JSONObject();
//		try {
//			obj.put("username", username);
//		}catch(JSONException e) {
//			e.printStackTrace();
//		}
//		PrintWriter out = response.getWriter();
//		out.print(obj);
//		out.close();
		
		//return json array
//		response.setContentType("application/json");
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		
//		JSONArray array = new JSONArray();
//		try {
//			array.put(new JSONObject().put("username", "nanshao"));
//			array.put(new JSONObject().put("username", "xuanwang"));
//		}catch(JSONException e) {
//			e.printStackTrace();
//		}
//		PrintWriter out = response.getWriter();
//		out.print(array);
//		out.close();
		
		//use the RpcHelper class
//		JSONArray array = new JSONArray();
//		try{
//			array.put(new JSONObject().put("username", "nanshao").put("username", "xuanwang"));
//			array.put(new JSONObject().put("username", "nanshao").put("username", "nanshao"));
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		RpcHelper.writeJsonArray(response, array);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
