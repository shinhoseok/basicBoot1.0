package com.basic.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement // JPA 트랜잭션 관리 활성화
@EnableJpaRepositories(basePackages = "com.basic.repository") // JPA 레포지토리 활성화
public class JpaConfigTransaction {

	@Bean(name = "txManager")
	public JpaTransactionManager txManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public TransactionInterceptor txAdvice(JpaTransactionManager txManager) {
		RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
		txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));

		HashMap<String, TransactionAttribute> txMethods = new HashMap<>();
		txMethods.put("*", txAttribute);

		NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
		txAttributeSource.setNameMap(txMethods);

		TransactionInterceptor txAdvice = new TransactionInterceptor();
		txAdvice.setTransactionAttributeSource(txAttributeSource);
		txAdvice.setTransactionManager(txManager);

		return txAdvice;
	}

	@Bean
	public Advisor txAdvisor(@Qualifier("txManager") JpaTransactionManager txManager) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		//패키지: com.basic.service.impl 내의 클래스
//		* Impl 클래스 이름: Impl로 끝나는 모든 클래스
//		* 메서드 이름: 모든 메서드 이름
//		(..) 매개변수: 0개 이상의 매개변수를 가질 수 있음
//		즉, 이 Pointcut 표현식은 com.basic.service.impl 패키지의 모든 Impl로 끝나는 클래스의 모든 메서드 실행을 가로채도록 설정된 것입니다.
		pointcut.setExpression("execution(* com.basic.service.impl.*Impl.*(..))");
		return new DefaultPointcutAdvisor(pointcut, txAdvice(txManager));
	}
}