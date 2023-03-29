package com.heyd.servlet.web.frontcontroller.v1;

import com.heyd.servlet.web.frontcontroller.v1.controller.ControllerV1;
import com.heyd.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.heyd.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.heyd.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllers = new ConcurrentHashMap<>();

    public FrontControllerServletV1(Map<String, ControllerV1> controllers) {
        controllers.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllers.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllers.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestUri = request.getRequestURI();
        final var controller = controllers.get(requestUri);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
