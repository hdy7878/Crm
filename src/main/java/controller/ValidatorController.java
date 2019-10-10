package controller;

import entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ValidatorController {
    @RequestMapping("/testVali")
    public void testVail(@Valid Employee emp, BindingResult result){
        if (result.hasErrors()) {
            List<FieldError> list=result.getFieldErrors();
            for(FieldError f:list){
                System.out.println("出错的属性:"+f.getField()+"出错的提示:"+f.getDefaultMessage());

            }

        }else{
            System.out.println("---------服务器验证通过----------");
            System.out.println(emp);
        }

    }
}
