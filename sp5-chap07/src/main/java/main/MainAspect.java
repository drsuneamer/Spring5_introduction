package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		Calculator cal = ctx.getBean("calculator", Calculator.class);
		// RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
		// -> 오류 발생 ($Proxy17은 calculator을 상속받는다.
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		ctx.close();

		/*
		 * RecCalculator.factorial([5]) 실행 시간 : 41300 ns 	// ExeTimeAspect의 measure()이 출력
		 * cal.factorial(5) = 120
		 * com.sun.proxy.$Proxy17	// 스프링이 생성한 프록시 타입
		 */
	}

}
