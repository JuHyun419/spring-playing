## Spring FrontController 만들어보자
https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/dashboard

### V1
- Front-Controller 도입
![image](https://user-images.githubusercontent.com/50076031/227992949-2a732bb0-b20b-4b67-bdbe-1bfd8d9019fe.png)
```java
// Front-Controller
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}

// Controller (interface, implements)
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
```

### V2
- View 분리
  ![image](https://user-images.githubusercontent.com/50076031/228285303-016d799c-e2df-4af0-a2c6-ea1158452219.png)
```java
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

// V1과 달리 Controller가 View 처리가 아닌 View 객체가 처리
public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}

// View 분리
public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

}

```

### V3
- Model 추가
- HttpServlet request,response 제거 (서블릿 의존성 제거)
- View 중복 제거(Prefix 경로)
  ![image](https://user-images.githubusercontent.com/50076031/228285601-58df4d7a-5a4f-418f-bd71-69489a0ba0dd.png)
```java
// Front-Controller
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

// "/WEB-INF/views/~.jsp"
private MyView viewResolver(String viewName) {
        return new MyView(VIEW_PREFIX + viewName + VIEW_SUFFIX);
}


public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}

public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
```

### V4


### V5