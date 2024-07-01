package app.sport.sw.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityImage {

    private String originalName;
    private String storeName;

    public void setImage(String originalName, String storeName) {
        this.originalName = originalName;
        this.storeName = storeName;
    }
}
