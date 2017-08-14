package com.msk.common.service;

import com.alibaba.fastjson.JSON;
import com.hoperun.plug.spring.utils.SpringContextUtil;
import com.msk.common.bean.AsyncReportTaskInfo;
import com.msk.common.dao.RedisExtendsUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 提供 异步任务实时查询的 WebSocket。
 * Created by ye_wenchen on 2016/7/18.
 */
@ServerEndpoint("/websocket")
public class TaskWebSocket
{
	//静态变量，用来记录当前在线连接数。应为线程安全。
	private static int onlineCount = 0;

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<TaskWebSocket> webSocketSet = new CopyOnWriteArraySet<TaskWebSocket>();
	private static HashMap<Session, TaskWebSocket> webSocketMap = new HashMap<>();

	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session)
	{
		this.session = session;
		webSocketSet.add(this);     //加入set中

		addOnlineCount();           //在线数加1
		System.out.println("新连接加入，当前连接数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose()
	{
//		webSocketMap.remove(session);

		webSocketSet.remove(this);  //从set中删除
		subOnlineCount();           //在线数减1
		System.out.println("连接关闭，当前连接数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session)
	{
		System.out.println("来自客户端的消息:" + message);

		try
		{
			String answerMessage = "";
			try
			{
				answerMessage = getTaskStatus(message);
			}
			catch(Exception e1)
			{
				answerMessage = e1.getMessage();
			}

			this.sendMessage(answerMessage);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("连接错误。");
		error.printStackTrace();
	}

	/**
	 * 处理实际需要发送的信息。
	 * @param message
	 * @throws java.io.IOException
	 */
	public void sendMessage(String message) throws IOException
	{
		this.session.getBasicRemote().sendText(message);
//		this.session.getAsyncRemote().sendText(message);
	}

	/**
	 * 生成任务状态信息，并转化为JSON格式。
	 * @param p_taskID 任务唯一标识。
	 * @return
	 * @throws Exception
	 */
	private String getTaskStatus(String p_taskID) throws Exception
	{
		AsyncPrintTaskService asyncPrintTaskService = SpringContextUtil.getBean("AsyncPrintTaskService", AsyncPrintTaskService.class);
		AsyncReportTaskInfo taskInfo =  asyncPrintTaskService.checkTaskStatus(p_taskID);

		return JSON.toJSONString(taskInfo);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		TaskWebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		TaskWebSocket.onlineCount--;
	}
}