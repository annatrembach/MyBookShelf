package MyBookShelf.models;

import jakarta.persistence.*;
import lombok.Builder;


@Entity
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long imageId;

    public String imageName;
    public String imageType;
    public String imageFilePath;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userPicture")
    public User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookCover")
    public Book book;

    public Long getImageId() {
        return imageId;
    }
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public Image(Long imageId, String imageName, String imageType, String imageFilePath, User user, Book book) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageFilePath = imageFilePath;
        this.user = user;
        this.book = book;
    }


    public Image() {
    }
}
