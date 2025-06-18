package com.api.utils;

import com.api.model.enums.TipoAgendaEnum;
import org.springframework.stereotype.Component;

@Component
public class Util {

    public String generarHtmlCita(String nombreCompleto, String fecha, String hora, TipoAgendaEnum tipoAgenda, String nombreReunion) {
        boolean esVirtual = tipoAgenda == TipoAgendaEnum.VIRTUAL;

        String linkReunion = "https://meet.jit.si/Reunion-" + nombreCompleto.replaceAll("\\s+", "") + "-" + System.currentTimeMillis();

        String botonJitsi = esVirtual ? """
                    <p>Haz clic en el siguiente botón para unirte a tu reunión:</p>
                    <a href="%s" target="_blank" style="display: inline-block; padding: 10px 20px; background-color: #003366; color: #ffffff; text-decoration: none; border-radius: 5px;">
                        Unirme a la Reunión
                    </a>
                    <p style="margin-top: 20px; font-size: 12px; color: #666;">Este es un enlace único generado automáticamente.</p>
                """.formatted(linkReunion) : "";

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
                        %s
                    </body>
                </html>
                """.formatted(nombreCompleto, nombreCompleto, fecha, hora, botonJitsi);
    }

}
