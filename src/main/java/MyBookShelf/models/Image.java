package MyBookShelf.models;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_image")
    public Long imageId;
    public String imageName;
    public String imageOriginalFileName;
    public Long imageSize;
    public String imageContentType;
    @Lob
    public byte[] bytes;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userPicture")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookCover")
    private Book book;

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

    public String getImageOriginalFileName() {
        return imageOriginalFileName;
    }
    public void setImageOriginalFileName(String imageOriginalFileName) {
        this.imageOriginalFileName = imageOriginalFileName;
    }

    public Long getImageSize() {
        return imageSize;
    }
    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageContentType() {
        return imageContentType;
    }
    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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

    public Image() {
    }

    public Image orElse(Image image) {
        return image;
    }
}