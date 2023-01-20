package eventapp.model;

/**
 * @author Christopher Forget
 */

import java.util.Date;

public class Event {
    
    private String title;
    private String pubDate;
    private String link;
    private String guid;
    private String description;
    
    public Event(String title, String link, String guid, String pubDate, String description){
        this.title = title;
        this.link = link;
        this.guid = guid;
        this.pubDate = pubDate;
        this.description = description;
    }
    
    
    public String getTitle() {return title;}
    public String getPubDate() {return pubDate;}
    public String getLink() {return link;}
    public String getGuid() {return guid;}
    public String getDescription() {return description;}
    
    public void setTitle(String title) {this.title = title;}
    public void setPubDate(String pubDate) {this.pubDate = pubDate;}
    public void setLink(String link) {this.link = link;}
    public void setGuid(String guid) {this.guid = guid;}
    public void setDescription(String description) {this.description = description;}
    
    
}
