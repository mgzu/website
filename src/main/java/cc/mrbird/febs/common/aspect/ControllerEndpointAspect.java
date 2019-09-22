package cc.mrbird.febs.common.aspect;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.AspectUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.utils.HttpContextUtil;
import cc.mrbird.febs.monitor.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * AOP 记录用户操作日志
 *
 * @author MrBird
 */
@Slf4j
@Aspect
@Component
public class ControllerEndpointAspect {

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(cc.mrbird.febs.common.annotation.ControllerEndpoint)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        Method targetMethod = AspectUtil.resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        try {
            // 执行方法
            result = point.proceed();
            String operation = annotation.value();
            long start = System.currentTimeMillis();
            if (StringUtils.isNotBlank(operation)) {
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
                logService.saveLog(point, targetMethod, request, operation, start);
            }
            return result;
        } catch (Throwable throwable) {
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            String error = FebsUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new FebsException(error);
        }
    }
}
