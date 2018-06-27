package codeu.model.data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Activity {
    private final UUID id;
    private final Instant creation;
    private final String content;
    private String conversationTitle;

    /**
     * Constructs a new Activity.
     *
     * @param id the ID of this Activity
     * @param content the text content of this Activity
     * @param creation the creation time of this Activity
     */
    public Activity(UUID id, String content, Instant creation, String conversationTitle) {
        this.id = id;
        this.creation = creation;
        this.content = content;
        this.conversationTitle = conversationTitle;

    }

    /** Returns the ID of this Activity. */
    public UUID getId() {
        return id;
    }

    /** Returns the title of this Activity. */
    public String getContent() {
        return content;
    }

    /** Returns the creation time of this Activity. */
    public Instant getCreationTime() {
        return creation;
    }

    /** Returns the conversation associated with this Activity. */
    public String getConversationTitle() {
        return conversationTitle;
    }

    /** Returns the creation time of this Activity as a formatted string. */
    public String getCreationTimeAsString() {
        Date date = Date.from(this.getCreationTime());
        return date.toString();
    }
}
