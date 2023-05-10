package ma.sir.event.bean.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import java.time.LocalDateTime;

@RedisHash("Evenement")
public class EvenementRedis {
    @Id
    private Long id;

    @Column(length = 500)
    private String reference;
    private LocalDateTime evenementStart ;
    private LocalDateTime evenementEnd ;
    @Column(length = 500)
    private String description;

    private Salle salle ;

    private EvenementState evenementState ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getEvenementStart() {
        return evenementStart;
    }

    public void setEvenementStart(LocalDateTime evenementStart) {
        this.evenementStart = evenementStart;
    }

    public LocalDateTime getEvenementEnd() {
        return evenementEnd;
    }

    public void setEvenementEnd(LocalDateTime evenementEnd) {
        this.evenementEnd = evenementEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public EvenementState getEvenementState() {
        return evenementState;
    }

    public void setEvenementState(EvenementState evenementState) {
        this.evenementState = evenementState;
    }
}
