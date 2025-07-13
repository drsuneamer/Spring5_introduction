package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {

	private String host;

	public void setHost(String host) {
		this.host = host;
	}
	
	// 컨테이너 실행 시점에 Client 빈 초기화하면서 호출됨
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Client.afterPropertiesSet() 실행");
	}

	public void send() {
		System.out.println("Client.send() to " + host);
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Client.destroy() 실행");
	}

}
