package com.heyd.servlet.web.frontcontroller.v3;

import com.heyd.servlet.web.frontcontroller.MyView;
import com.heyd.servlet.web.frontcontroller.v3.controller.ControllerV3;
import com.heyd.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.heyd.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.heyd.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    public static final String VIEW_PREFIX = "/WEB-INF/views/";
    public static final String VIEW_SUFFIX = ".jsp";

    private Map<String, ControllerV3> controllers = new HashMap<>();

    public FrontControllerServletV3() {
        controllers.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllers.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllers.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestUri = request.getRequestURI();
        final var controller = controllers.get(requestUri);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final var paramMap = createParamMap(request);
        final var mv = controller.process(paramMap);
        final var viewName = mv.getViewName();
        final var view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView(VIEW_PREFIX + viewName + VIEW_SUFFIX);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
