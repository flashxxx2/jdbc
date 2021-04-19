package tech.itpark.proggerhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {
  @RequestMapping("/jsp")
  // HandlerMethod <- handle, parameters
  // ViewResolver ->
  public String jsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setAttribute("data", List.of("1", "2"));
    return "WEB-INF/hello.jsp";
  }

  @RequestMapping("/json")
  public String json(Model model) {
    model.addAttribute("items", List.of("first", "second", "third"));
    return "don't know";
  }

  @RequestMapping("/body")
  @ResponseBody // HttpMessageConverter
  public String json() {
    return List.of("first", "second", "third").toString();
  }

  @RequestMapping("like")
  public void like() {

  }
}
