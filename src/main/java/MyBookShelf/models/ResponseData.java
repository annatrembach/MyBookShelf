package MyBookShelf.models;


public class ResponseData {

    public String fileName;
    public String downloadURL;
    public String fileType;
    public Long fileSize;

    public ResponseData(String fileName, String downloadURL, String fileType, Long fileSize) {
        this.fileName = fileName;
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}