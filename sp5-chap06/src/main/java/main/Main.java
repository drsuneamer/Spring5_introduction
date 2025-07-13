package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client;
import spring.Client2;

public class Main {

	public static void main(String[] args) throws IOException {
		// 1. 컨테이너 초기화 -> 빈 객체의 생성, 의존 주입, 초기화
		// 컨테이너가 초기화되면 getBean() 등을 이용해 컨테이너에 보관된
		// 아래 코드 없이 이것만 실행해도 Client의 afterPropertiesSet과 Client2의 connect는 실행된다!
		// 각각 다른 방법으로 구현된 초기화 메서드이므로
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		// 2. 컨테이너에서 빈 객체 구해서 사용
		// Client client = ctx.getBean(Client.class);
		Client2 client = ctx.getBean(Client2.class);
		client.send();

		// 3. 컨테이너 종료 -> 빈 객체의 소멸
		ctx.close();

		/*
		 * [실행 결과]
		 * 
		 * 7월 11, 2025 6:55:34 오후
		 * org.springframework.context.support.AbstractApplicationContext prepareRefresh
		 * 정보: Refreshing
		 * org.springframework.context.annotation.AnnotationConfigApplicationContext@
		 * 255316f2: startup date [Fri Jul 11 18:55:34 KST 2025]; root of context
		 * hierarchy Client.afterPropertiesSet() 실행 Client2.connect() 실행 Client.send()
		 * to host 7월 11, 2025 6:55:34 오후
		 * org.springframework.context.support.AbstractApplicationContext doClose 정보:
		 * Closing
		 * org.springframework.context.annotation.AnnotationConfigApplicationContext@
		 * 255316f2: startup date [Fri Jul 11 18:55:34 KST 2025]; root of context
		 * hierarchy Client2.close() 실행 Client.destroy() 실행
		 * 
		 * 
		 * [Client2 실행 결과]
		 * 
		 * 7월 11, 2025 7:01:15 오후
		 * org.springframework.context.support.AbstractApplicationContext prepareRefresh
		 * 정보: Refreshing
		 * org.springframework.context.annotation.AnnotationConfigApplicationContext@
		 * 255316f2: startup date [Fri Jul 11 19:01:15 KST 2025]; root of context
		 * hierarchy Client.afterPropertiesSet() 실행 Client2.connect() 실행 Client2.send()
		 * to host 7월 11, 2025 7:01:15 오후
		 * org.springframework.context.support.AbstractApplicationContext doClose 정보:
		 * Closing
		 * org.springframework.context.annotation.AnnotationConfigApplicationContext@
		 * 255316f2: startup date [Fri Jul 11 19:01:15 KST 2025]; root of context
		 * hierarchy Client2.close() 실행 Client.destroy() 실행
		 * 
		 */
	}

}