package pe.edu.cibertec.patitas_backend_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.patitas_backend_a.dto.*;
import pe.edu.cibertec.patitas_backend_a.service.AutenticacionService;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/autenticacion")
public class AutentificacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        try {
            Thread.sleep(Duration.ofSeconds(5));
            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            System.out.println("Resultado: " + Arrays.toString(datosUsuario));

            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }
            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]);

        } catch (Exception e) {
            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");
        }


    }
    @PostMapping("/logout")
    public LogoutResponseDTO cerrarSesion(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        try {
            String[] datosUsuario = autenticacionService.logout(logoutRequestDTO);
            System.out.println("Resultado2: " + Arrays.toString(datosUsuario));
            if (datosUsuario == null) {
                return new LogoutResponseDTO("01", "Error: Usuario no encontrado");
            }
            return new LogoutResponseDTO("00", "Cierre de sesión exitoso");
        } catch (IOException e) {
            return new LogoutResponseDTO("99", "Error al registrar cierre de sesión");
        }
    }

    @PostMapping("/logout_ef")
    public LogoutResponseEFDTO closeEf(@RequestBody LogoutRequestEFDTO request){
        //bloque if
        if(request.numeroDocumento() == null || request.numeroDocumento().equals("")
                || request.tipoDocumento() == null || request.tipoDocumento().equals("")){
            return new LogoutResponseEFDTO("01","Falta información para el cerrar la sesion");
        }
        // bloque try catch
        try{
            Thread.sleep(Duration.ofSeconds(10));
            autenticacionService.logoutEF(request);
            return new LogoutResponseEFDTO("00","");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return new LogoutResponseEFDTO("99","Ocurrió un problema en el servidor");
        }
    }







}