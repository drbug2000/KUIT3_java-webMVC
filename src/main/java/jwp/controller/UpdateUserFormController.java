package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import jwp.Util.LoginUtil;
import jwp.model.User;

import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HTTPController {
    @Override
    protected ModelAndView doGet(HttpServletRequest req) throws ServletException, IOException {
        String id = req.getParameter("userId");
        HttpSession userSession = req.getSession();
        if(!LoginUtil.isLogin(userSession)){
            return new ModelAndView( new JspView(REDIRECT+"/user/login.jsp"));
        }
        User user = (User)userSession.getAttribute("user");
        if(!id.equals(user.getUserId())){
            //잘못된 접근
            return new ModelAndView( new JspView(REDIRECT+"/"));
        }
        req.setAttribute("user", MemoryUserRepository.getInstance().findUserById(id));
        return new ModelAndView( new JspView("/user/updateForm.jsp"));

    }
}

