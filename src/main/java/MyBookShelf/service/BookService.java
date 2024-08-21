package MyBookShelf.service;

import MyBookShelf.models.Book;
import MyBookShelf.models.BookAuthor;
import MyBookShelf.models.Image;
import MyBookShelf.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public BookAuthor bookAuthor;

    public boolean addBook(Book book, MultipartFile file) throws IOException {
        //Book cover
        Image bookCover;
        if (file.getSize() != 0) {
            bookCover = toImageEntity(file);
            book.addImageToBook(bookCover);
        }
        //Other book details
        String bookName = book.getBookName();
        String bookShortDescription = book.getBookShortDescription();
        String bookAuthorName = bookAuthor.getBookAuthorName();
        bookRepository.save(book);
        return true;
    }

    public Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImageName(file.getName());
        image.setImageOriginalFileName(file.getOriginalFilename());
        image.setImageContentType(file.getContentType());
        image.setImageSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
