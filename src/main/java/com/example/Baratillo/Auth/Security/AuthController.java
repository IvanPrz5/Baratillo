package com.example.Baratillo.Auth.Security;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Baratillo.Auth.Config.UserDetailImp;
import com.example.Baratillo.Auth.Utils.ResultObjectResponse;
import com.example.Baratillo.Auth.Utils.TokenUtils;
import com.example.Baratillo.Repository.Usuarios.UsuariosRepository;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public ResultObjectResponse authenticateUser(@RequestBody AuthCredentials authCredentials) {
        System.out.println(authCredentials);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetailImp userDetails = (UserDetailImp) authentication.getPrincipal();
                String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername() + userDetails.getAuthorities());
                
                /* Long idUsuario = userDetails.getId();
                List<UsuariosDto> usuario = usuariosService.getByIdAndStatus(idUsuario, true); */

                HashMap<String, Object> response = new HashMap<>();
                response.put("token", token);
                // response.put("usuario", usuario);

                System.out.println(authCredentials);

                return new ResultObjectResponse(1, false, "Success", response);
            } else {
                return new ResultObjectResponse(0, true, "Verifique los datos de acceso e intentelo nuevamente.", null);
            }
        } catch (Exception ex) {
            return new ResultObjectResponse(0, true, "Verifique los datos de acceso e intentelo nuevamente aqui.", null);
        }
    }
    
    /* private Optional<UsuariosEntity> emailExist(String email){
        return usuariosRepository.findOneByEmail(email);
    } */
}
