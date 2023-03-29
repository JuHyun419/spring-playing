package com.heyd.servlet.web.frontcontroller.v2;

import com.heyd.servlet.web.frontcontroller.v2.controller.ControllerV2;
import com.heyd.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.heyd.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.heyd.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllers = new HashMap<>();

    public FrontControllerServletV2() {
        controllers.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllers.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllers.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestUri = request.getRequestURI();
        final var controller = controllers.get(requestUri);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final var view = controller.process(request, response);
        view.render(request, response);
    }
}
