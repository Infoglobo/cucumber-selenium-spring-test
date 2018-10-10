package br.com.globo.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class EvidenceAspect {

    @Autowired
    private ApplicationContext applicationContext;
    @Value("${evidence-location}")
    private String path;

    @Around("br.com.globo.aop.PointCuts.stepDefinitionsPointcut()")
    public void takePrintScreen(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        proceedingJoinPoint.proceed();

        File file = ((TakesScreenshot) applicationContext.getBean(WebDriver.class)).getScreenshotAs(OutputType.FILE);

        String filePath = getFilePath(proceedingJoinPoint);

        FileUtils.copyFile(file, new File(filePath));
    }

    private String getFilePath(ProceedingJoinPoint proceedingJoinPoint) {

        return String.format("%s/%s-%s.png", path, setupFileName(proceedingJoinPoint), Instant.now().toEpochMilli());
    }

    private String setupFileName(JoinPoint joinPoint) {

        String stepDefinitionsClassName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String stepMethodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        String stepName = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations()[0].annotationType().getSimpleName();

        return stepDefinitionsClassName + "-" + stepName + "-" + stepMethodName;
    }
}
