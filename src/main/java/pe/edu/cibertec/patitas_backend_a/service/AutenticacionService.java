package pe.edu.cibertec.patitas_backend_a.service;



import pe.edu.cibertec.patitas_backend_a.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backend_a.dto.LogoutRequestEFDTO;

import java.io.IOException;

public interface AutenticacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;
    String[] logout(LogoutRequestDTO logoutRequestDTO) throws IOException;
    String[] logoutEF(LogoutRequestEFDTO logoutRequestDTO) throws IOException;
}
