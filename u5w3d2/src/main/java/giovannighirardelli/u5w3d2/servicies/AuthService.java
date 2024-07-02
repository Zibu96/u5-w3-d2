package giovannighirardelli.u5w3d2.servicies;

import giovannighirardelli.u5w3d2.entities.Dipendenti;
import giovannighirardelli.u5w3d2.exceptions.UnauthorizedException;
import giovannighirardelli.u5w3d2.payloads.DipendenteLoginDTO;
import giovannighirardelli.u5w3d2.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendentiService dipendentiService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bCrypt;

    public String authenticateDipendenteAndGenerateToken(DipendenteLoginDTO payload){
        Dipendenti dipendenti = this.dipendentiService.findByEmail(payload.email());
        if (bCrypt.matches(payload.password(), dipendenti.getPassword())){
            return jwtTools.createToken(dipendenti);
        } else {
            throw  new UnauthorizedException("Credenziali non corrette");
        }
    }
}
