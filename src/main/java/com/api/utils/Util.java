package com.api.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class Util {

    public String generarHtmlCita(String nombreCompleto, String fecha, String hora) {
        return """
                <html>
                    <body style="font-family: Arial, sans-serif;">
                        <p>Hola, <strong style="color: #003366;">%s</strong></p>
                        <p>
                            Queremos contarte que tu cita fue programada de forma exitosa.
                            A continuación, te entregamos toda la información que necesitas para asistir:
                        </p>
                        <p><strong style="color: #003366;">Nombre:</strong> %s</p>
                        <p><strong style="color: #003366;">Fecha de la cita:</strong> %s</p>
                        <p><strong style="color: #003366;">Hora de la cita:</strong> %s</p>
                    </body>
                </html>
                """.formatted(nombreCompleto, nombreCompleto, fecha, hora);
    }

}
