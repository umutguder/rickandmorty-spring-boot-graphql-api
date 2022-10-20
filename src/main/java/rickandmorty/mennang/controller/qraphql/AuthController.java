package rickandmorty.mennang.controller.qraphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import rickandmorty.mennang.validator.ValidPassword;

import javax.validation.constraints.Email;

@Controller
public class AuthController {

    @MutationMapping
    public String signup(@Argument @Email(message = "E-mail address is not valid") String email,
                         @Argument String name,
                         @Argument @ValidPassword String password,
                         @Argument String bio
    ) {
        return "Success";
    }
}