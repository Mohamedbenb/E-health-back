package com.bezkoder.spring.security.jwt.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class DateCal {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="start")
    private LocalDateTime start;
    @Column(name="end")
    private LocalDateTime end;
    @Column(name="draggable")
    private Boolean draggable = true;


    @Column(name="active")
    private boolean active;

    @Column(name="color")
    @Embedded
    private Color  color ;
    @Embeddable

    public static class Color {

        @Column(name="primary")
        private String primary;
        @Column(name="secondary")
        private String secondary;

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public String getSecondary() {
            return secondary;
        }

        public void setSecondary(String secondary) {
            this.secondary = secondary;
        }
        // getters and setters
    }

}
