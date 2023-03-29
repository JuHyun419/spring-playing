package com.heyd.servlet.web.frontcontroller.v1.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var viewPath = "/WEB-INF/views/new-form.jsp";
        final var dispatcher = request.getRequestDispatcher(viewPath);

        dispatcher.forward(request, response);
    }
}
