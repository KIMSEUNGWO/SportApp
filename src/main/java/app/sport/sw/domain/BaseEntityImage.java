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

    private String thumbnailName;

    public void setImage(String originalName, String storeName, String thumbnailName) {
        this.originalName = originalName;
        this.storeName = storeName;
        this.thumbnailName = thumbnailName;
    }

}
