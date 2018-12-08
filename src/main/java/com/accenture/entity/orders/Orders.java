package com.accenture.entity.orders;

import com.accenture.entity.tag.Tag;
import com.accenture.entity.user.User;
import com.accenture.enums.Status;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "Integer")
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Orders() {
    }

    public Orders(String text, Tag tag, User user) {
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
