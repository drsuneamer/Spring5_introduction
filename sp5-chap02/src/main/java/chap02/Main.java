package chap02;

// 자바 설정에서 정보 읽어와 빈 객체 생성하고 관리
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// Appcontext에 정의한 @Bean 설정 정보를 읽어와 Greeter 객체 생성하고 초기화
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		System.out.println("(g1 == g2) = " + (g1 == g2));
		// (g1 == g2) = true
		
		ctx.close();
	}
}
