package com.project.timetracking.contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * The type Error handler controller.
 */
@Controller
@ControllerAdvice
public class ErrorHandlerController implements ErrorController {
    private static final String ERROR_PAGE = "error";

    /**
     * Handle error string.
     *
     * @param request the request
     * @param model   the model
     * @return the string
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value() || statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("code", statusCode);
                return ERROR_PAGE;
            }
        }
        model.addAttribute("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ERROR_PAGE;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
