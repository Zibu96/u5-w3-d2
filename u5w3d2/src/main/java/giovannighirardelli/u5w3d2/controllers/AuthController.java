package giovannighirardelli.u5w3d2.controllers;


import giovannighirardelli.u5w3d2.exceptions.BadRequestException;
import giovannighirardelli.u5w3d2.payloads.DipendenteLoginDTO;
import giovannighirardelli.u5w3d2.payloads.DipendenteLoginResponseDTO;
import giovannighirardelli.u5w3d2.payloads.NewDipendentiDTO;
import giovannighirardelli.u5w3d2.payloads.NewDipendentiResponseDTO;
import giovannighirardelli.u5w3d2.servicies.AuthService;
import giovannighirardelli.u5w3d2.servicies.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private DipendentiService dipendentiService;

    @PostMapping("/login")
    public DipendenteLoginResponseDTO login(@RequestBody DipendenteLoginDTO payload){
        return new DipendenteLoginResponseDTO(authService.authenticateDipendenteAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendentiResponseDTO saveDipendenti(@RequestBody @Validated NewDipendentiDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }

        return new NewDipendentiResponseDTO(this.dipendentiService.saveDipendenti(body).getId());
    }
}
