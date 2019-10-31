package com.github.cundream.springbootbuilding.config.security;

import cn.hutool.core.util.StrUtil;
import com.github.cundream.springbootbuilding.common.constant.Consts;
import com.github.cundream.springbootbuilding.common.enums.ErrorCodeEnum;
import com.github.cundream.springbootbuilding.common.exception.SecurityException;
import com.github.cundream.springbootbuilding.entity.Permission;
import com.github.cundream.springbootbuilding.entity.Role;
import com.github.cundream.springbootbuilding.repository.PermissionRepository;
import com.github.cundream.springbootbuilding.repository.RoleRepository;
import com.github.cundream.springbootbuilding.vo.UserPrincipal;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author : Lison
 * @Date: 2019/10/28 17:05
 * @Description: 动态路由认证
 */
@Component
public class RbacAuthorityService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RequestMappingHandlerMapping mapping;

    private  UrlPathHelper urlPathHelper;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        //取消校验是否为系统内部映射
       // checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {
            UserPrincipal principal = (UserPrincipal) userInfo;
            Long userId = principal.getId();

            List<Role> roles = roleRepository.selectByUserId(userId);
            List<Long> roleIds = roles.stream()
                    .map(Role::getId)
                    .collect(Collectors.toList());
            List<Permission> permissions = permissionRepository.selectByRoleIdList(roleIds);

            //获取资源，前后端分离，所以过滤页面权限，只保留按钮权限
            List<Permission> btnPerms = permissions.stream()
                    // 过滤页面权限
                    .filter(permission -> Objects.equals(permission.getType(), Consts.BUTTON))
                    // 过滤 URL 为空
                    .filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
                    // 过滤 METHOD 为空
                    .filter(permission -> StrUtil.isNotBlank(permission.getMethod()))
                    .collect(Collectors.toList());

            for (Permission btnPerm : btnPerms) {
                AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(btnPerm.getUrl(), btnPerm.getMethod());
                if (antPathMatcher.matches(request)) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }

    /**
     * 校验请求是否存在
     *
     * @param request 请求
     */
    private void checkRequest(HttpServletRequest request) {
        // 获取当前 request 的方法
        String currentMethod = request.getMethod();
        Multimap<String, String> urlMapping = allUrlMapping();
        for (String uri : urlMapping.keySet()) {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request)) {
                if (!urlMapping.get(uri)
                        .contains(currentMethod)) {
                    throw new SecurityException(ErrorCodeEnum.HTTP_BAD_METHOD);
                } else {
                    return;
                }
            }
        }

        throw new SecurityException(ErrorCodeEnum.REQUEST_NOT_FOUND);
    }

    /**
     * 获取 所有URL Mapping，返回格式为{"/test":["GET","POST"],"/sys":["GET","DELETE"]}
     *
     * @return {@link ArrayListMultimap} 格式的 URL Mapping
     */
    private Multimap<String, String> allUrlMapping() {
        Multimap<String, String> urlMapping = ArrayListMultimap.create();

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((k, v) -> {
            // 获取当前 key 下的获取所有URL
            Set<String> url = k.getPatternsCondition()
                    .getPatterns();
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            // 为每个URL添加所有的请求方法
            url.forEach(s -> urlMapping.putAll(s, method.getMethods()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.toList())));
        });

        return urlMapping;
    }

    private String getRequestPath(HttpServletRequest request) {
        if (this.urlPathHelper != null) {
            return this.urlPathHelper.getPathWithinApplication(request);
        } else {
            String url = request.getServletPath();
            String pathInfo = request.getPathInfo();
            if (pathInfo != null) {
                url = StringUtils.hasLength(url) ? url + pathInfo : pathInfo;
            }

            return url;
        }
    }
}