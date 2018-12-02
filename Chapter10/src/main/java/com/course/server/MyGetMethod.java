package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController//在使用springmvc框架的时候，在处理json的时候需要用到spring框架特有的注解@ResponseBody或者
//@RestController注解，这两个注解都会处理返回的数据格式，使用了该类型注解后返回的不再是视图，
//不会进行转跳，而是返回json或xml数据格式，输出在页面上,@ResponseBody，一般是使用在单独的方法上的，
//需要哪个方法返回json数据格式，就在哪个方法上使用，具有针对性。
//@RestController，一般是使用在类上的，它表示的意思其实就是结合了@Controller和@ResponseBody两个注解，
//如果哪个类下的所有方法需要返回json数据格式的，就在哪个类上使用该注解，具有统一性；需要注意的是，
//使用了@RestController注解之后，其本质相当于在该类的所有方法上都统一使用了@ResponseBody注解，
//所以该类下的所有方法都会返回json数据格式，输出在页面上，而不会再返回视图
@Api(value = "/",description = "这是我全部的get方法")//只有带注释的类@Api才会被Swagger扫描

public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    //@RequestMapping 是 Spring Web 应用程序中最常被用到的注解之一。这个注解会将 HTTP 请求映射到 MVC 和 REST 控制器的处理方法上。
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")
    //所述@ApiOperation用于将API资源内声明一个单个操作。操作被认为是路径和HTTP方法的唯一组合。只@ApiOperation扫描带有注释的方法并
    //添加API声明。注释将影响Swagger输出的两个部分，即每个路径创建一个的API对象，以及每个@ApiOperation创建一个操作对象的操作对象。
    //请记住，在使用Servlet时，@Api会在设置路径时影响API对象
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须携带cookies信息来";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求!";
            }
        }

        return "你必须携带cookies信息来";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url: key=value&key=value
     * 我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需求携带参数才能访问的get请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();

        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);

        return  myList;

    }

    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "需求携带参数才能访问的get请求的第二种实现",httpMethod = "GET")
    public  Map myGetList(@PathVariable Integer start,
                          @PathVariable Integer end){

        Map<String,Integer> myList = new HashMap<>();

        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);

        return  myList;

    }

}
