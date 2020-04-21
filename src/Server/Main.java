package Server;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.*;
import java.io.*;

public class Main extends HttpServlet{
    private String message;
    private String CompileUI2="<!doctype html>\n"+
            "<html>\n"+
            "<head>\n"+
            "<meta charset=\"utf-8\">\n"+
            "<title>无标题文档</title>\n"+
            "\t<style>\n"+
            "\t\t#Maintitle{\n"+
            "\t\t\ttext-align: center;\n"+
            "\t\t}\n"+
            "\t\t#inputArea{\n"+
            "\t\t    padding-left: 40px;\n"+
            "\t\t\tbackground-color: rgb(200,200,200) ;\n"+
            "\t\t\twidth: 750px;\n"+
            "\t\t\theight: 500px;\n"+
            "\t\n"+
            "\t\t\t\t\n"+
            "\t\t}\n"+
            "\t\t.Codeinput{\n"+
            "\t\t\twidth:650px;\n"+
            "\t\t\theight: 400px;\n"+
            "\t\t\tmargin: 20px;\n"+
            "\t\t}\n"+
            "\t</style>\n"+
            "\t  <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\n"+
            "</head>\n"+
            "\n"+
            "<body>\n"+
            "\t<div class=\"main\">\n"+
            "\t\t<h1 id=\"Maintitle\">C简单编译器实现</h1>\n"+
            "\t<div id=\"inputArea\">\n"+
            "\t\t<form action=\"Compile\" method=\"GET\">\n"+
            "\t<textarea  class=\"Codeinput\"></textarea><br>\n"+
            "\t\t\t<input type=\"submit\" class=\"btn\" value=\"编译\">\n"+
            "\t\t\t<!--<input type=\"submit\" class=\"btn \" value=\"清除\">-->\n"+
            "\t\t\t</form>\n"+
            "\t\t\n"+
            "\t\t</div>\n"+
            "\t\t<div>\n"+
            "\t\t\n"+
            "\t\t</div>\n"+
            "\t\t</div>\n"+
            "</body>\n"+
            "</html>";
    public void init() throws ServletException {
        message = "I WANT TO TEST";
    }
    /*public void service(ServletRegistration request,ServletResponse response) throws  ServletException, IOException {

    }*/
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+message+"</h1>");
        out.println(CompileUI2);
        // Servlet 代码
    }
    public void destory(){

    }
}
