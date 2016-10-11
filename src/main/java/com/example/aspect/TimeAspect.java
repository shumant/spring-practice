package com.example.aspect;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class TimeAspect {
    private Logger log = LoggerFactory.getLogger(getClass());

    private static Map<String, Long> count = new ConcurrentHashMap<>();
    private static Map<String, Long> time = new ConcurrentHashMap<>();

    private void addToMap(Map<String, Long> map, String key, Long value) {
        if (map.containsKey(key)) {
            Long prevValue = map.get(key);
            map.put(key, prevValue + value);
        } else {
            map.put(key, value);
        }
    }

    //    @Scheduled(fixedDelay = 1000 * 10)
    private void printOverAllTime() {
        log.info("Odata elapsed time:");
        count.forEach((key, value) -> {
            log.info("Method {} invoked {} times, took {} seconds", key, value, time.get(key) / 1000);
        });
    }

    @Pointcut("execution(* com.example.listener..*(..))")
    public void oDataInterfaces() {
    }

    @Around("oDataInterfaces()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object output = pjp.proceed();
        stopwatch.stop();
        String name = pjp.getTarget().getClass().getName() + '.' + pjp.getSignature().getName();
        addToMap(count, name, 1L);
        addToMap(time, name, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > 2) {
            log.info("Method {} execution time: {}", name, stopwatch);
        }
        return output;
    }
}

