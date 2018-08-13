package com.wyc.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wyc.entity.LogEntity;
import com.wyc.service.LogService;

@Aspect
public class LogAopAction {
    //ע��service,��������־��Ϣ���������ݿ�
    @Autowired
    private LogService logservice;
    
     //���ý����,�����֪����ô����,���԰ٶ�һ�¹���
     @Pointcut("execution(* com.wyc.controller..*.*(..))")  
     private void controllerAspect(){}//����һ�������
 
     @Around("controllerAspect()")
     public Object around(ProceedingJoinPoint pjp) throws Throwable {
         //������־ʵ�����
         LogEntity log = new LogEntity(); 
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

         //��ȡϵͳʱ��
         String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
         log.setData(time);
         
         //��ȡϵͳip,�����õ������Լ��Ĺ�����,���������ϲ�ѯ��ȡip����
         String ip = getIpAddr(request);
         log.setIp(ip);
         
        //����֪ͨǰ��ȡʱ��,ΪʲôҪ��¼���ʱ���أ���Ȼ����������ģ��ִ��ʱ���
         long start = System.currentTimeMillis();
        // ���ص�ʵ���࣬���ǵ�ǰ����ִ�е�controller
        Object target = pjp.getTarget();
        // ���صķ������ơ���ǰ����ִ�еķ���
        String methodName = pjp.getSignature().getName();
        // ���صķ�������
        Object[] args = pjp.getArgs();
        // ���صķŲ�������
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("��ע��ֻ�����ڷ���");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        
        Object object = null;
        // ��ñ����صķ���
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (null != method) {
            // �ж��Ƿ�����Զ����ע�⣬˵��һ�������SystemLog�������Լ��Զ����ע��
            if (method.isAnnotationPresent(SystemLog.class)) {
//            	System.out.println(SecurityUtils.getSubject().getPrincipal());
                
                
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setMethod(systemlog.methods());
                try {
                    object = pjp.proceed();
                    log.setUsername((SecurityUtils.getSubject().getPrincipal()).toString());
                    long end = System.currentTimeMillis();
                    //������õ�ʱ�䱣����ʵ����
                    log.setResponse_data(""+(end-start));
                    log.setComment("ִ�гɹ���");
                    //��������ݿ�
                    logservice.saveLog(log);
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    long end = System.currentTimeMillis();
                    log.setResponse_data(""+(end-start));
                    log.setComment("ִ��ʧ��");
                    logservice.saveLog(log);
                }
            } else {//û�а���ע��
                object = pjp.proceed();
            }
        } else { //����Ҫ����ֱ��ִ��
            object = pjp.proceed();
        }
        return object;
     }
     
 	// ������IP��ַ
 	public String getIpAddr(HttpServletRequest request) {
 		String ip = request.getHeader(" x-forwarded-for ");
 		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
 			ip = request.getHeader(" Proxy-Client-IP ");
 		}
 		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
 			ip = request.getHeader(" WL-Proxy-Client-IP ");
 		}
 		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
 			ip = request.getRemoteAddr();
 		}
 		return ip;
 	}
}