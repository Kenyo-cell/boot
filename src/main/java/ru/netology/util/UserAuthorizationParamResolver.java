package ru.netology.util;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.entity.UserEntity;
import ru.netology.exception.InvalidCredentialsException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public class UserAuthorizationParamResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserEntity user = new UserEntity(username, password);

        if (parameter.hasParameterAnnotation(Valid.class)) {
            var binder = binderFactory.createBinder(webRequest, user, user.getClass().getSimpleName());
            binder.validate();
            if (binder.getBindingResult().hasErrors()) {
                throw new InvalidCredentialsException("Username and password must be non empty min 5 length chars");
            }
        }

        return user;
    }
}
