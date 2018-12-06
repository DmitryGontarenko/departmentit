package com.accenture.entity.orders;

import com.accenture.entity.user.User;
import com.accenture.enums.Status;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long")
    private String text;
    @NotBlank(message = "Please fill the message")
    @Length(max = 255, message = "Message too long")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "smallint")
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", updatable = false)
    private Date updatedAt;

    public Orders() {
    }

    public Orders(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    // проверка, присутствует ли автор в сообщении(заявке)
    // если нет, ставится <none>
    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

}
