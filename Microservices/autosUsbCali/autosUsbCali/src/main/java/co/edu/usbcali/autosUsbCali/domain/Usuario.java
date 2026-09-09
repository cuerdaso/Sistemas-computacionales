package co.edu.usbcali.autosUsbCali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "celular", length = 20)
    private String celular;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 30)
    private String numeroDocumento;

    @ColumnDefault("false")
    @Column(name = "email_verificado", nullable = false)
    private Boolean emailVerificado;

    @ColumnDefault("false")
    @Column(name = "celular_verificado", nullable = false)
    private Boolean celularVerificado;

    @ColumnDefault("true")
    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ColumnDefault("now()")
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;


}
