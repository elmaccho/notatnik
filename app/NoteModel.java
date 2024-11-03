public class NoteModel {
    private String title;
    private String content;
    private String imagePath;

    public NoteModel(String title, String content, String imagePath){
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(){
        this.title = title;
    }

    public String getContent(){
        return content;
    }
    public void setContent(){
        this.content = content;
    }

    public String getImagePath(){
        return imagePath;
    }
    public void setImagePath(){
        this.imagePath = imagePath;
    }

    @Override
    public String toString(){
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
