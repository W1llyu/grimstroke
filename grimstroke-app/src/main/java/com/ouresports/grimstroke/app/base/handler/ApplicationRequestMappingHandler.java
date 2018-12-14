package com.ouresports.grimstroke.app.base.handler;

import com.ouresports.grimstroke.app.base.annotation.*;
import com.ouresports.grimstroke.app.base.controller.BaseCrudController;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author will
 * @date 2018/12/7
 */
public class ApplicationRequestMappingHandler extends RequestMappingHandlerMapping {
    private final static String INDEX_METHOD = "index";
    private final static String SHOW_METHOD = "show";
    private final static String CREATE_METHOD = "create";
    private final static String UPDATE_METHOD = "update";
    private final static String DELETE_METHOD = "delete";

    @Override
    protected void detectHandlerMethods(Object handler) {
        Class handlerType = handler instanceof String?this.obtainApplicationContext().getType((String)handler):handler.getClass();
        if (AnnotatedElementUtils.hasAnnotation(handlerType, RestCrudController.class)) {
            detectRestHandlerMethods(handler);
        } else {
            super.detectHandlerMethods(handler);
        }
    }

    private void detectRestHandlerMethods(Object handler) {
        Class handlerType = handler instanceof String?this.obtainApplicationContext().getType((String)handler):handler.getClass();
        if (handlerType != null) {
            Class userType = ClassUtils.getUserClass(handlerType);
            Map<Method, RequestMappingInfo> methods = MethodIntrospector.selectMethods(userType, (MethodIntrospector.MetadataLookup<RequestMappingInfo>) method -> {
                try {
                    return this.getMappingForMethod(method, userType);
                } catch (Throwable var4) {
                    throw new IllegalStateException("Invalid mapping on handler class [" + userType.getName() + "]: " + method, var4);
                }
            });
            if(this.logger.isDebugEnabled()) {
                this.logger.debug(methods.size() + " request handler methods found on " + userType + ": " + methods);
            }
            methods.forEach((method, mapping) -> {
                boolean goahead = true;
                if (method.getDeclaringClass() == BaseCrudController.class) {
                    switch (method.getName()) {
                        case INDEX_METHOD:
                            goahead = AnnotatedElementUtils.hasAnnotation(handlerType, RestIndex.class);
                            break;
                        case SHOW_METHOD:
                            goahead = AnnotatedElementUtils.hasAnnotation(handlerType, RestShow.class);
                            break;
                        case CREATE_METHOD:
                            goahead = AnnotatedElementUtils.hasAnnotation(handlerType, RestCreate.class);
                            break;
                        case UPDATE_METHOD:
                            goahead = AnnotatedElementUtils.hasAnnotation(handlerType, RestUpdate.class);
                            break;
                        case DELETE_METHOD:
                            goahead = AnnotatedElementUtils.hasAnnotation(handlerType, RestDelete.class);
                            break;
                        default:
                            break;
                    }
                }
                if (goahead) {
                    Method invocableMethod = AopUtils.selectInvocableMethod(method, userType);
                    this.registerHandlerMethod(handler, invocableMethod, mapping);
                }
            });
        }
    }
}
