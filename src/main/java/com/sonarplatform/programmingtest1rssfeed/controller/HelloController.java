package com.sonarplatform.programmingtest1rssfeed.controller;

import com.sonarplatform.programmingtest1rssfeed.service.impl.HelloServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/yeahbutstill")
public class HelloController {
    private final HelloServiceImpl helloServiceImpl;

    public HelloController(HelloServiceImpl helloServiceImpl) {
        this.helloServiceImpl = helloServiceImpl;
    }

    /***
     * http://localhost:8001/sonar/hello?name=DNL
     * @param httpServletRequest untuk set name dari req
     * @param httpServletResponse untuk get name dari res
     * @throws IOException
     */
    @GetMapping("/hello") // Request method GET
    public void hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String name = httpServletRequest.getParameter("name");
        // kalau objectnya null si name maka name set jadi "Sonar"
        if (Objects.isNull(name)) name = "Sonar";
        httpServletResponse.getWriter().println("Hello " + name + "!");
    }

    @GetMapping("/hello_mock_bean")
    public void helloGetHelloService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String name = httpServletRequest.getParameter("name");
        String responseBody = helloServiceImpl.hello(name);
        httpServletResponse.getWriter().println(responseBody);
    }

    /***
     * @RequestParam dengan annotation ini, kita memberitahu bahwa kita membutuhkan request parameter
     * selain itu kita bisa menambahkan apakah query parameter itu wajib atau tidak, dan juga bisa
     * menambahkan default valuenya jika tidak dikirim oleh user.
     * Secara atomatis data request parameter akan dikirim datanya ke parameter yang kita tentukan.
     * @param name, required = false (artinya ini tidak wajib) String name, artinya request parameter ini datanya
     *              akan dimasukan kedalam String name.
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping("/hello_request_param")
    public void helloRequestParam(@RequestParam(name = "name", required = false) String name,
                                  HttpServletResponse httpServletResponse) throws IOException {
        String responseBody = helloServiceImpl.hello(name);
        httpServletResponse.getWriter().println(responseBody);
    }
}
